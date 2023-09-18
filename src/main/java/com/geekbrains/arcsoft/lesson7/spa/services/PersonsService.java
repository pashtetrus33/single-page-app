package com.geekbrains.arcsoft.lesson7.spa.services;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import com.geekbrains.arcsoft.lesson7.spa.persistences.PersistenceAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonsService implements BusinessService<Person> {
    @Autowired
    PersistenceAccess personsDAO;


    @Override
    public List<Person> getAll() {

        return personsDAO.getAll();
    }

    @Override
    public Optional<Person> getById(long id) {
        return personsDAO.getById(id);
    }

    @Override
    public List<Person> search(String searchTerm) {
        return personsDAO.search(searchTerm);
    }

    @Override
    public long add(Person newT) {
        return personsDAO.add(newT);
    }

    @Override
    public boolean delete(long id) {
        return personsDAO.delete(id);
    }

    @Override
    public Person update(long idToUpdate, Person updatedPerson) {
        return personsDAO.update(idToUpdate, updatedPerson);
    }
}
