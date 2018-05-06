package com.rdas.restclient;

//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.hateoas.Resources;
import com.rdas.model.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//import com.orders.dto.Details;

@FeignClient(
        name = "application.yml",
        url = "${user.service.url}"
)
public interface PersonRestClient {

    @GetMapping("/persons")
    public List<Person> getPersons();
//    Resources<Details> readDetails(@PathVariable("idProduct") long id);

}
