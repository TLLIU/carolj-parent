![SpringBoot 3](https://img.shields.io/badge/springboot-3-green)
![Java 17](https://img.shields.io/badge/java-17-orange)
![OpenAPI 3](https://img.shields.io/badge/openapi-3-green)

## Quick Start

### Running with Docker

```bash
#!/usr/bin/env bash

docker build -t api . && docker run -p 8080:8080 api

# Or alternatively with compose:

docker compose up

```

Access the OpenAPI documentation at [localhost:8080](http://localhost:8080/swagger-ui.html)

## Reference Documentation

For further reference, please consider the following sections:

### Maven

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### SpringBoot

* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.0/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#web)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.1.0/reference/htmlsingle/#actuator)

### OpenAPI

* [OpenAPI 3 Specification](https://swagger.io/specification/)
* [SpringDoc OpenAPI Documentation](https://springdoc.org/)

### Docker

* [Docker Documentation](https://docs.docker.com/)
* [Docker Compose Documentation](https://docs.docker.com/compose/)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

## License

[Apache License Version 2.0](./LICENSE)