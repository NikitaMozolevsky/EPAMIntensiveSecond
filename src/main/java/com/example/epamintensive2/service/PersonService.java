package com.example.epamintensive2.service;

import com.example.epamintensive2.entity.Person;
import com.example.epamintensive2.repository.PersonRepository;
import com.example.epamintensive2.request_response.PersonResponse;
import com.example.epamintensive2.request_response.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> showAllPersons() {

        return personRepository.findAll().stream()
                .sorted((o1, o2) -> o1.getEmail().compareTo(o2.getEmail()))
                .collect(Collectors.toList());
    }

    public boolean register(RegisterRequest request) {
        //настройка пользователя
        var user = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .patronymic(request.getPatronymic())
                //changed!!! from "Role.USER"
                //enum('ADMINISTRATOR', 'SALE_USER', 'CUSTOMER_USER', 'SECURE_API_USER')
                .role(request.getRole())
                .build();

        //сохранение
        personRepository.save(user);
        return true;
    }
}
