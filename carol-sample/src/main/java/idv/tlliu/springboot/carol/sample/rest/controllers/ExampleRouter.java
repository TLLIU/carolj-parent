package idv.tlliu.springboot.carol.sample.rest.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import idv.tlliu.springboot.carol.sample.rest.service.ExampleHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ExampleRouter {
  private static final RequestPredicate ACCEPT_JSON = accept(MediaType.APPLICATION_JSON);
  
//  @Bean
//  public RouterFunction<ServerResponse> route(ExampleHandler exampleHandler) {
//    return RouterFunctions
//        .route(RequestPredicates.GET("/example"), request -> exampleHandler.hello(request))
//        .route()
//        .GET("/example", ACCEPT_JSON, request -> exampleHandler.hello(request))
//        .build();
//    
//  }

}
