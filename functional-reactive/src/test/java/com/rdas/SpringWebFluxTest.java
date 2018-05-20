package com.rdas;

import com.rdas.model.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
//http://www.baeldung.com/spring-5-webclient
//https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringWebFluxTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testHelloWorldResource() {
        webTestClient
                .get().uri("/helloWorld") // GET method and URI
                .accept(MediaType.TEXT_PLAIN) //setting ACCEPT-Content
                .exchange() //gives access to response
                .expectStatus().isOk() //checking if response is OK
                .expectBody(String.class).isEqualTo("Hello World!"); // checking for response type and message
    }
}
