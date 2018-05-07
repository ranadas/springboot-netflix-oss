package com.rdas.restclient;

import com.rdas.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "application.yml",
        url = "${user.service.url}"
)
public interface PersonRestClient {
    //TODO : check for empty/null
    @GetMapping("/persons")
    List<Person> getPersons();
}
