package idv.tlliu.springboot.concurrent.r2dbc;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import idv.tlliu.springboot.concurrent.domain.Users;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.Result;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsersDaoService{
  
  @Autowired
  private ConnectionFactory connectionFactory;
  
  public Flux<Users> findAccountWithId(long id) {
    return Mono
        .from(connectionFactory.create())
        .flatMap((conn) -> Mono.from(conn.createStatement("select id, firstname, lastname from Users where id = $1")
            .bind("$1", id).execute())
            .doFinally(stat -> conn.close()))
        .flatMapMany(result -> Flux.from(result.map((row, meta) -> {
          Users acc = new Users();
          acc.setId(row.get("id", Long.class));
          acc.setFirstname(row.get("firstname", String.class));
          acc.setLastname(row.get("lastname", String.class));
          return acc;
        })));
  }
  
  public Mono<Users> createAccount(Users account) {

    return Mono.from(connectionFactory.create())
            .flatMap(c -> Mono.from(c.beginTransaction())
                    .then(Mono.from(c.createStatement("insert into Users(firstname,lastname) values($1,$2)")
                            .bind("$1", account.getFirstname())
                            .bind("$2", account.getLastname())
                            .returnGeneratedValues("id")
                            .execute()))
                    .map(result -> result.map((row, meta) ->
                            new Users(row.get("id", Long.class),
                                    account.getFirstname(),
                                    account.getLastname())))
                    .flatMap(Mono::from)
                    .delayUntil(r -> c.commitTransaction())
                    .doFinally((st) -> c.close()));

}
}
