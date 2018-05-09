package com.rdas.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rdas.model.Person;
import com.rdas.model.PersonType;
import com.rdas.utils.JsonUtility;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonHttpClient {
    private JsonUtility jsonUtility;
    private String baseUrl;

    @Autowired
    public PersonHttpClient(JsonUtility jsonUtility,
                            @Value("${user.service.url}") String url) {
        this.jsonUtility = jsonUtility;
        baseUrl = url;
    }

    public List<Person> getPersons() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("%s/persons", baseUrl))
                .build();

        Response response = client.newCall(request).execute();
        List<Person> persons = jsonUtility.getPersons(response.body().string());
        System.out.println(response.body().string());
        return persons;
    }

    public List<Person> getPersonsByType(PersonType type) {
        return null;
    }

    public Optional<Person> getPersonsById(Integer id) {
        return null;
    }

    public boolean addPerson(Person person) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        RequestBody body = RequestBody.create(JSON, jsonUtility.getString(person));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(String.format("%s/create", baseUrl))
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }
        return false;
    }
    /*
@GetMapping("/persons")

        return personService.getPersons();
    }

    @GetMapping("/personsByType")
    public List<Person> getPersonsByType(@RequestParam("type") PersonType type) {
        return personService.getPersons(type);
    }

    @GetMapping("/personbyid/{id}")
    public Optional<Person> getPersonsById(@PathVariable("id") Integer id) {
        return personService.getPersonById(id);
    }
     */
}
