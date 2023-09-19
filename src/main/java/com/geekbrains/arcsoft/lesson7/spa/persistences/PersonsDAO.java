package com.geekbrains.arcsoft.lesson7.spa.persistences;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;


@Repository
public class PersonsDAO implements PersistenceAccess<Person, String> {

    //region Fields
    PersonsRepository personsRepository;
    private static Logger log = Logger.getLogger(PersonsDAO.class.getName());
    private final Random random = new Random();
    //ModelMapper modelMapper = new ModelMapper();

    //endregion

    //region Constructors
    public PersonsDAO(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }
    //endregion

    //region Public methods
    @Override
    public Optional<Person> getById(String id) {
        log.info("GetById request. id = " + id);

        return personsRepository.findById(id);
    }


    @Override
    public List<Person> getAll() {
        log.info("GetAll request");
        return personsRepository.findAll();
    }

    @Override
    public List<Person> search(String searchTerm) {
        log.info("Search request. SearchTerm = " + searchTerm);
        return personsRepository.findByName(searchTerm);
    }

    @Override
    public String add(Person newPerson) {
        log.info("Add person request");
        newPerson.setId(null);
        personsRepository.save(newPerson);
        return newPerson.getId();
    }

    @Override
    public boolean delete(String id) {
        log.info("Delete request. id = " + id);
        personsRepository.deleteById(id);
        return true;
    }

    @Override
    public Person update(String idToUpdate, Person updatedPerson) {

        log.info("Update request. id = " + idToUpdate);


        Optional<Person> person = getById(idToUpdate);
        if (person.isEmpty()) return null;
        else {
            updatedPerson.setId(person.get().getId());
            return personsRepository.save(updatedPerson);
        }
    }
    //endregion
}
