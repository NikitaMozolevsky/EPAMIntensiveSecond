package com.example.epamintensive2.controller;


import com.example.epamintensive2.entity.Person;
import com.example.epamintensive2.request_response.PersonRequest;
import com.example.epamintensive2.service.PersonService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            (@RequestBody PersonRequest personRequest) {
        try {
            ResponseEntity.ok(personService.register(personRequest));
            logger.log(Level.INFO, "user registered successful");
            return true;
        } catch (Exception e) {
            logger.log(Level.ERROR, "user wasn't registered");
            return false;
        }
    }

    @GetMapping("/show")
    public ResponseEntity<List<Person>> showAll() {
        try {
            ResponseEntity<List<Person>> responseEntity = ResponseEntity.ok(personService.showAllPersons());
            logger.log(Level.INFO, "All users");
            return responseEntity;
        } catch (Exception e) {
            logger.log(Level.INFO, "Something went wrong");
            throw new NullPointerException();
        }

    }

}
