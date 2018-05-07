package com.rdas.resource;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rdas.model.Person;
import com.rdas.model.PersonType;
import com.rdas.restclient.PersonFeignClient;
import com.rdas.restclient.PersonRestClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(RestDataController.class)
public class RestDataControllerMockMvcWithContextTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonRestClient personRestClient;

    @MockBean
    private PersonFeignClient personFeignClient;

    // This object will be magically initialized by the initFields method below.
//    private JacksonTester<SuperHero> jsonSuperHero;
    private List<Person> peoples;

    private ObjectMapper objectMapper () {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return objectMapper;
    }

    @Before
    public void setup() {
        // Initializes the JacksonTester
        JacksonTester.initFields(this, new ObjectMapper());
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
        MockHttpServletResponse response = mvc.perform(
                get("/persons")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        List<Person> personList = getPersons(response.getContentAsString());
        assertThat(personList).isEqualTo(peoples);
    }

    private List<Person> getPersons(String json) throws IOException {
        TypeReference<List<Person>> mapType = new TypeReference<List<Person>>() {};
        List<Person> persons = objectMapper().readValue(json, mapType);
        return persons;
    }

    private Person getPerson(String json) throws IOException {
        Person person = objectMapper().readValue(json, Person.class);
        return person;
    }

    @Test
    public void canRetrievePersonById() throws Exception {
        // given
        Person p1 = Person.builder().id(1).name("Rana").age(50).type(PersonType.MALE).build();

        given(personFeignClient.getPersonsById(1)).willReturn(Optional.of(p1));

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/personbyid/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        Person person = getPerson(response.getContentAsString());
        assertThat(person).isEqualTo(p1);
    }
}
