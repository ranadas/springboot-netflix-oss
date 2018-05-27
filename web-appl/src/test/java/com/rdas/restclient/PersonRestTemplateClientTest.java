package com.rdas.restclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.model.Person;
import com.rdas.model.PersonType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersonRestTemplateClientTest.LocalConfig.class})
@RestClientTest(PersonRestTemplateClient.class)
public class PersonRestTemplateClientTest {

    @Autowired
    private PersonRestTemplateClient client;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper testOMapper;

    private List<Person> peoples;

    @Before
    public void setUp() throws Exception {
        peoples = new ArrayList<>();
        peoples.add(Person.builder().type(PersonType.MALE).age(21).name("Raja").id(24).build());
        peoples.add(Person.builder().id(1).name("Rana").age(50).type(PersonType.MALE).build());
        peoples.add(Person.builder().id(1).name("Jennifer").age(49).type(PersonType.FEMALE).build());
        String detailsString = testOMapper.writeValueAsString(peoples);

        this.server.expect(MockRestRequestMatchers.requestTo("/persons"))
                .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
    }

    @Test
    public void smokeTests() {
        assertThat(client).isNotNull();
        assertThat(server).isNotNull();
        assertThat(testOMapper).isNotNull();
    }

    @Test
    public void whenCallingGetUserDetails_thenClientMakesCorrectCall()  throws Exception {
        Person person = Person.builder().type(PersonType.MALE).age(21).name("Raja").id(24).build();

        List<Person> persons = this.client.getPersons();
        System.out.println(persons);
//        assertThat(details.getLogin()).isEqualTo("john");
//        assertThat(details.getName()).isEqualTo("John Smith");
    }


    @Test
    public void whenCallingGetUserDetails()  throws Exception {
        List<Person> persons = this.client.getPersonsByType(PersonType.MALE);
        System.out.println(persons);
    }


    @ComponentScan({"com.rdas.utils", "com.rdas.restclient"})
    @Configuration
    public static class LocalConfig {
    }
}