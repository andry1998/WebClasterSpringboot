package com.example.test404.controllers;

import com.example.test404.config.SecurityConfig;
import com.example.test404.exception.ResourceNotFoundException;
import com.example.test404.models.Person;
import com.example.test404.repository.PersonRepository;
import com.example.test404.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/persons")
public class PersonController {

//    @Autowired
//    private PersonRepository personRepository;
//
//    @Autowired
//    private SecurityConfig securityConfig;

    @Autowired
    private PersonService personService;

    @GetMapping()
    @PreAuthorize("hasAuthority('persons:read')")
    public List<Person> getPersons() {
        //return personRepository.findAll();
        return personService.getPersons();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('persons:write')")
    public Person createPerson(@RequestBody Person person) {
        //return personRepository.save(person);
        return personService.createPerson(person);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:read')")
    public ResponseEntity<Person> getPersonById(@PathVariable long id) {
//        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not exist with id:" + id));
//        return ResponseEntity.ok(person);
        return personService.getPersonById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public ResponseEntity<Person> updatePerson(@PathVariable long id, @RequestBody Person personDetails) {
//        Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person not exist with id:" + id));
//        person.setFirst_name(personDetails.getFirst_name());
//        person.setLast_name(personDetails.getLast_name());
//        person.setEmail(personDetails.getEmail());
//        person.setPassword(securityConfig.passwordEncoder().encode(personDetails.getPassword()));
//        person.setStudy_group(personDetails.getStudy_group());
//        person.setRole(personDetails.getRole());
//        person.setStatus(personDetails.getStatus());
//
//
//        Person updatePerson = personRepository.save(person);
//        return ResponseEntity.ok(updatePerson);
        return personService.updatePersons(id, personDetails);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('persons:write')")
    public ResponseEntity<Map<String, Boolean>> deletePerson(@PathVariable long id) {
//        Person person = personRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Person not exist with id :" + id));
//        personRepository.delete(person);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
        return personService.deletePerson(id);
    }

    @GetMapping("/roles")
    public List<String> getRoles(){
        return personService.getRoles();
    }
}
