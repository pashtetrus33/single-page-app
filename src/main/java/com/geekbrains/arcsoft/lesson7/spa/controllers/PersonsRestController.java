package com.geekbrains.arcsoft.lesson7.spa.controllers;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import com.geekbrains.arcsoft.lesson7.spa.services.BusinessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonsRestController {

    BusinessService<Person> personsBusinessService;

    PersonsRestController(BusinessService<Person> personsBusinessService) {
        this.personsBusinessService = personsBusinessService;
    }

    @GetMapping(value = "/")
    public List<Person> showAllPersons() {

        return personsBusinessService.getAll();
    }

    @GetMapping(value = "/search/{searchTerm}")
    public List<Person> searchPersons(@PathVariable(name = "searchTerm") String searchString) {
        return personsBusinessService.search(searchString);
    }

    @GetMapping(value = "/{id}")
    public Optional<Person> getById(@PathVariable(name = "id") long id) {
        return personsBusinessService.getById(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean deleteById(@PathVariable(name = "id") long id) {
        return personsBusinessService.delete(id);
    }

    @PostMapping(value = "/")
    public long addPerson(@RequestBody Person newPerson) {
        return personsBusinessService.add(newPerson);
    }

    @PutMapping(value = "/update/{id}")
    public Person updateById(@RequestBody Person newPerson, @PathVariable(name = "id") long id) {
        return personsBusinessService.update(id, newPerson);
    }
}
