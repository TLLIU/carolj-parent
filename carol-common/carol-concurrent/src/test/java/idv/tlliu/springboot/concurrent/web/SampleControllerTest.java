package idv.tlliu.springboot.concurrent.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

import idv.tlliu.springboot.concurrent.ControllerTest;
import idv.tlliu.springboot.concurrent.domain.SampleEntity;
import idv.tlliu.springboot.concurrent.r2dbc.SampleEntityRepository;
import idv.tlliu.springboot.concurrent.web.dtos.SampleEditDto;
import idv.tlliu.springboot.concurrent.web.dtos.SampleResponseDto;
//import com.c4_soft.springaddons.security.oauth2.test.annotations.OpenId;
//import com.c4_soft.springaddons.security.oauth2.test.webflux.WebTestClientSupport;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(SampleController.class)
@ControllerTest
@Import({ SampleMapperImpl.class })
class SampleControllerTest {
	SampleEntity sampleEntity1;
	SampleEntity sampleEntity42;

	final ObjectMapper json = new ObjectMapper();

	@Autowired
	private ApplicationContext context;
	
	WebTestClient rest;
//	WebTestClientSupport rest;

	@MockBean
	SampleEntityRepository sampleEntityRepository;

	@BeforeEach
	public void before() {
		sampleEntity1 = new SampleEntity(1L, "Sample label 1");
		sampleEntity42 = new SampleEntity(42L, "Sample label 42");
		when(sampleEntityRepository.findById(1L)).thenReturn(Mono.just(sampleEntity1));
		when(sampleEntityRepository.findById(42L)).thenReturn(Mono.just(sampleEntity42));
		when(sampleEntityRepository.findAll()).thenReturn(Flux.fromArray(new SampleEntity[] { sampleEntity1, sampleEntity42 }));
		rest = WebTestClient.bindToApplicationContext(context).build();
	}

	@Test
	void whenRetrieveAllWithoutAuthThenUnauthenticated() throws Exception {
	  rest.get()
	    .uri("/sample").exchange()
		  .expectStatus().isUnauthorized();
	}

	@Test
	//@OpenId()
	void whenRetrieveAllWithAuthThenOk() throws Exception {
	  rest.get()
    .uri("/sample").exchange().expectStatus().isOk().expectBodyList(SampleResponseDto.class).hasSize(2);
	}

	@Test
	//@OpenId()
	void whenPostValidSampleEditDtoThenAccepted() throws Exception {
		when(sampleEntityRepository.save(any())).thenReturn(Mono.just(sampleEntity42));
		rest.post()
      .uri("/sample").bodyValue(new SampleEditDto("Edited label")).exchange() 
		  .expectStatus().isCreated();
	}

	@Test
	//@OpenId()
	void whenPutValidSampleEditDtoAtValidIdThenAccepted() throws Exception {
		when(sampleEntityRepository.save(any())).thenReturn(Mono.just(sampleEntity42));
    rest.put()
      .uri("/sample/" + sampleEntity42.getId()).bodyValue(new SampleEditDto("Edited label")).exchange()
		  .expectStatus().isAccepted();
	}
}
