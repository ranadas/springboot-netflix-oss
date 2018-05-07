package com.rdas.resource;

import com.rdas.model.Person;
import com.rdas.model.PersonType;
import com.rdas.restclient.PersonFeignClient;
import com.rdas.restclient.PersonRestClient;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * Created by rdas on 22/04/2018.
 */
@Slf4j
@RestController
public class RestDataController {

    @Autowired
    private PersonRestClient personRestClient;

    @Autowired
    private PersonFeignClient personFeignClient;

//    @Autowired
//    public RestDataController (PersonRestClient restClient, PersonFeignClient feignClient) {
//        this.personRestClient=restClient;
//        this.personFeignClient=feignClient;
//    }

    @PostConstruct
    public void init() {
        System.out.println(personRestClient);
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personRestClient.getPersons();
    }

    @GetMapping("/personsByType")
    public List<Person> getPersonsByType(@RequestParam("type") PersonType type) {
        return personFeignClient.getPersonsByType(type);
    }

    @GetMapping("/personbyid/{id}")
    public Optional<Person> getPersonsById(@PathVariable("id") Integer id) {
        return personFeignClient.getPersonsById(id);
    }

}
