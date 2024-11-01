package idv.tlliu.springboot.concurrent.r2dbc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import idv.tlliu.springboot.concurrent.r2dbc.SampleEntityRepository.SampleEntityReadingConverter;
import idv.tlliu.springboot.concurrent.r2dbc.SampleEntityRepository.SampleEntityWritingConverter;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import reactor.core.publisher.Flux;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfig extends AbstractR2dbcConfiguration {

	@Value("spring.r2dbc.url")
	private String r2dbcUrl;
	
  @Autowired
  private R2dbcConnectionProperties properties;
  
	@Override
	public ConnectionFactory connectionFactory() {
//	  ConnectionFactoryOptions baseOptions = ConnectionFactoryOptions.parse(properties.getUrl());
//	  ConnectionFactoryOptions.Builder builder = ConnectionFactoryOptions.builder().from(baseOptions);
//    if (!StringUtils.isNoneEmpty(properties.getUser())) {
//      builder = builder.option(ConnectionFactoryOptions.USER, properties.getUser());
//    }
//    if (!StringUtils.isNoneEmpty(properties.getPassword())) {
//      builder = builder.option(ConnectionFactoryOptions.PASSWORD, properties.getPassword());
//    }
//    return ConnectionFactories.get(builder.build());
	  
		return ConnectionFactories.get(r2dbcUrl);
	}

	@Override
	protected List<Object> getCustomConverters() {
		return List.of(new SampleEntityReadingConverter(), new SampleEntityWritingConverter());
	}

	@Bean
	ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
	  //Use sql file to initialize database
		final var initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);
		initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
		return initializer;
	}
	
//	@Bean
//	CommandLineRunner initDatabase(ConnectionFactory cf) {
//	  //initialize database using sql command
//	  return (args) -> 
//	    Flux.from(cf.create()).flatMap(c -> Flux.from(c.createBatch()
//	        .add("drop table if exists Users")
//	        .add("create table Users(" +
//              "id IDENTITY(1,1)," +
//              "firstname varchar(80) not null," +
//              "lastname varchar(80) not null))")
//	        .add("insert into Users(firstname,lastname)" +
//              "values('flydean','ma')")
//          .add("insert into Users(firstname,lastname)" +
//              "values('jacken','yu')")
//	        .execute())
//	        .doFinally((st) -> c.close())
//	        ).log().blockLast();
//	}
}