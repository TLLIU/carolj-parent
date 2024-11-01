package idv.tlliu.springboot.carol.sample.rest.service;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Component
public class ExampleHandler {
  public ServerResponse hello(ServerRequest request) {
    String name = request.param("name").get();
    return ServerResponse.ok().body("hello " + name);
}
}
