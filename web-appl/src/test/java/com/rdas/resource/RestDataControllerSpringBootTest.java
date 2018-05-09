package com.rdas.resource;

import com.rdas.model.Person;
import com.rdas.model.PersonType;
import com.rdas.restclient.PersonFeignClient;
import com.rdas.restclient.PersonHttpClient;
import com.rdas.restclient.PersonRestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//https://bitbucket.org/yassine_Ramzi/netflix-oss-spring-boot-example
//https://thepracticaldeveloper.com/2017/07/31/guide-spring-boot-controller-tests/
//https://github.com/mechero/spring-boot-testing-strategies/blob/master/src/test/java/es/macero/dev/controller/SuperHeroControllerMockMvcStandaloneTest.java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestDataControllerSpringBootTest {
    @MockBean
    private PersonRestClient personRestClient;

    @MockBean
    private PersonFeignClient personFeignClient;

    @MockBean
    private PersonHttpClient personHttpClient;

    @Autowired
    private TestRestTemplate restTemplate;

    private List<Person> peoples;

    @Before
    public void setup() {
        peoples = new ArrayList<>();
    }

    @Test
    public void canRetrieveAllPersons() throws Exception {
        // given
        peoples.add(Person.builder().id(1).name("Rana").age(50).type(PersonType.MALE).build());
        peoples.add(Person.builder().id(1).name("Jennifer").age(49).type(PersonType.FEMALE).build());
        peoples.add(Person.builder().id(1).name("Connal").age(100).type(PersonType.MALE).build());

        given(personRestClient.getPersons())
                .willReturn(peoples);

        // when
        ResponseEntity<Person[]> superHeroResponse = restTemplate.getForEntity("/persons", Person[].class);

        // then
        assertThat(superHeroResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        //assertThat(superHeroResponse.getBody().equals(new SuperHero("Rob", "Mannon", "RobotMan")));
    }
}
