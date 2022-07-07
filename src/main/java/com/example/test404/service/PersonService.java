package com.example.test404.service;

import com.example.test404.config.SecurityConfig;
import com.example.test404.exception.ResourceNotFoundException;
import com.example.test404.models.Person;
import com.example.test404.models.Role;
import com.example.test404.models.Status;
import com.example.test404.models.Student;
import com.example.test404.repository.PersonRepository;
import com.example.test404.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final SecurityConfig securityConfig;

    public PersonService(PersonRepository personRepository, SecurityConfig securityConfig) {
        this.personRepository = personRepository;
        this.securityConfig = securityConfig;
    }
    public List<Person> getPersons() {
        return personRepository.findAll();
    }
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
    public ResponseEntity<Person> getPersonById(long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not exist with id:" + id));
        return ResponseEntity.ok(person);
    }
    public ResponseEntity<Person> updatePersons(long id, Person personDetails) {
        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not exist with id:" + id));
        person.setFirst_name(personDetails.getFirst_name());
        person.setLast_name(personDetails.getLast_name());
        person.setEmail(personDetails.getEmail());
        person.setPassword(securityConfig.passwordEncoder().encode(personDetails.getPassword()));
        person.setRole(personDetails.getRole());
        person.setStatus(personDetails.getStatus());

        Person updateStudent = personRepository.save(person);
        return ResponseEntity.ok(updateStudent);
    }

    public ResponseEntity<Map<String, Boolean>> deletePerson(long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not exist with id :" + id));
        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public Role[] getRoles(){
        return Role.values();
    }
    public Status[] getStatus(){
        return Status.values();
    }


}
