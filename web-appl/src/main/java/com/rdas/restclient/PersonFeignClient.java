package com.rdas.restclient;

import com.rdas.model.Person;
import com.rdas.model.PersonType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient("USERSERVICE")
public interface PersonFeignClient {
    @GetMapping("/personsByType")
    List<Person> getPersonsByType(@RequestParam("type") PersonType type);

    @GetMapping("/personbyid/{id}")
    Optional<Person> getPersonsById(@PathVariable("id") Integer id);
}
