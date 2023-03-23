package com.example.epamintensive2.service;

import com.example.epamintensive2.entity.Person;
import com.example.epamintensive2.repository.PersonRepository;
import com.example.epamintensive2.request_response.PersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> showAllPersons() {
        return personRepository.findAll();
    }

    public boolean register(PersonRequest request) {
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
