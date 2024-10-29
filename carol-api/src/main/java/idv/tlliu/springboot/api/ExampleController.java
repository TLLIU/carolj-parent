package idv.tlliu.springboot.api;

import org.springframework.stereotype.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import idv.tlliu.springboot.api.controller.ExampleApi;

@Controller
public class ExampleController implements ExampleApi {

    @Override
    public ResponseEntity<String> sayHelloToWorld() {
        return ResponseEntity.ok("Hello World");
    }

}
