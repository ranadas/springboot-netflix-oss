package com.rdas.restclient;

import com.rdas.model.Person;
import com.rdas.model.PersonType;
import com.rdas.testConfig.TestConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
//@TestPropertySource(properties = {
//        "user.service.url=localhost:8081"
//})
@TestPropertySource(locations = "classpath:application-tests.properties")
public class PersonHttpClientTest {

    @Value("${user.service.url}")
    private String baseUrl;

    @Autowired
    public PersonHttpClient personHttpClient;

    @Before
    public void init() {
        log.info(baseUrl);
    }

    @Test
    public void smokeTests() {
        assertThat(baseUrl).isNotBlank();
        assertThat(personHttpClient).isNotNull();
    }
    @Test
    public void test1() throws IOException {
        Person person = Person.builder().type(PersonType.MALE).age(21).name("Raja").id(24).build();
        personHttpClient.addPerson(person);
    }
}