package idv.tlliu.springboot.security.web;

import idv.tlliu.springboot.security.dto.HelloDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping(path = "/hello")
    public HelloDTO hello() {
        return new HelloDTO("Hello World!");
    }
}
