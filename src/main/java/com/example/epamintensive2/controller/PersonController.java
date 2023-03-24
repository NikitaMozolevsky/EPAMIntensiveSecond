package com.example.epamintensive2.controller;


import com.example.epamintensive2.entity.Person;
import com.example.epamintensive2.request_response.PersonResponse;
import com.example.epamintensive2.request_response.RegisterRequest;
import com.example.epamintensive2.service.PersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping
public class PersonController {

    private final PersonService personService;
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public boolean register
            (@RequestBody RegisterRequest registerRequest) {
        try {
            ResponseEntity.ok(personService.register(registerRequest));
            logger.log(Level.INFO, "user registered successful");
            return true;
        } catch (Exception e) {
            logger.log(Level.ERROR, "user wasn't registered");
            return false;
        }
    }

    @GetMapping("/show")
    public ResponseEntity<List<PersonResponse>> showAll() {
        try {
            /*ResponseEntity<List<Person>> responseEntity = ResponseEntity.ok(personService.showAllPersons());*/
            List<Person> personList = personService.showAllPersons();

            List<PersonResponse> personResponseList =
                    personList.stream()
                            .map(p -> new PersonResponse
                                    (p.getFck(), p.getEmail(), p.getRole()))
                            .collect(Collectors.toList());

            logger.log(Level.INFO, "All users");

            return ResponseEntity.ok(personResponseList);
        } catch (Exception e) {
            logger.log(Level.INFO, "Something went wrong");
            throw new NullPointerException();
        }

    }

}
