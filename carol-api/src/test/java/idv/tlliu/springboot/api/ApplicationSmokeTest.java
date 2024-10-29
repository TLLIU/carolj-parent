package idv.tlliu.springboot.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApplicationSmokeTest {


    @DisplayName("Should load application context When application starts up")
    @Test
    void shouldLoadApplicationContextWhenApplicationStartsUp(@Autowired Application application) {
        assertThat(application).isNotNull();
    }

}