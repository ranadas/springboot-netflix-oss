package com.rdas.restclient;

import com.rdas.model.Person;
import com.rdas.model.PersonType;
import com.rdas.utils.JsonUtility;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonRestTemplateClient {
    private JsonUtility jsonUtility;
    private String baseUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public PersonRestTemplateClient(JsonUtility jsonUtility,
                                    @Value("${user.service.url}") String url,
                                    RestTemplateBuilder templateBuilder) {
        this.jsonUtility = jsonUtility;
        baseUrl = url;
        restTemplate = templateBuilder.build();
    }

    public List<Person> getPersons() throws IOException {
        return restTemplate.getForObject("/persons", List.class);
    }

    public List<Person> getPersonsByType(PersonType type) {
        return null;
    }

    public Optional<Person> getPersonsById(Integer id) {
        return null;
    }

    public boolean addPerson(Person person) throws IOException {

        return false;
    }
}
