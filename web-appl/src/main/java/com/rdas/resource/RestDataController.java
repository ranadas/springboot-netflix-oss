package com.rdas.resource;

import com.rdas.model.Person;
import com.rdas.restclient.PersonRestClient;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by rdas on 22/04/2018.
 */
@Slf4j
@RestController
public class RestDataController {

    @Autowired
    private PersonRestClient personRestClient;


    @PostConstruct
    public void init() {
        System.out.println(personRestClient);
    }

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personRestClient.getPersons();
    }

}
