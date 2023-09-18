package com.geekbrains.arcsoft.lesson7.spa.persistences;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Repository
public class PersonsDAO implements PersistenceAccess<Person> {

    //region Fields
    private static Logger log = Logger.getLogger(PersonsDAO.class.getName());
    private final Random random = new Random();
    List<Person> persons = new ArrayList<>();
    //endregion

    //region Constructors
    public PersonsDAO() {

        int weightBound = 50;
        int heightBound = 75;
        int ageBound = 60;

        for (int i = 1; i < 2 + random.nextInt(5); i++) {

            persons.add(new Person("Ivan_" + i, 18 + random.nextInt(ageBound), 2 * heightBound + random.nextInt(heightBound), weightBound + (float) weightBound * Math.round(random.nextFloat() * 100) / 100));
            persons.add(new Person("Mariia_" + i, 18 + random.nextInt(ageBound), 2 * heightBound + random.nextInt(heightBound), weightBound + (float) weightBound * Math.round(random.nextFloat() * 100) / 100));
            persons.add(new Person("Pavel_" + i, 18 + random.nextInt(ageBound), 2 * heightBound + random.nextInt(heightBound), weightBound + (float) weightBound * Math.round(random.nextFloat() * 100) / 100));
        }
    }
    //endregion

    //region Public methods
    @Override
    public Optional<Person> getById(long id) {
        log.info("GetById request. id = " + id);

        return persons.stream().filter(person -> person.getId() == id).findFirst();
    }


    @Override
    public List<Person> getAll() {
        log.info("GetAll request");
        return persons;
    }

    @Override
    public List<Person> search(String searchTerm) {
        log.info("Search request. SearchTerm = " + searchTerm);
        return persons.stream().filter(person -> person.getName().toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public long add(Person newPerson) {

        log.info("Add person request");
        return persons.add(newPerson) ? 1 : 0;
    }

    @Override
    public boolean delete(long id) {
        log.info("Delete request. id = " + id);
        Optional<Person> person = persons.stream().filter(p -> p.getId() == id).findFirst();
        return person.map(value -> persons.remove(value)).orElse(false);
    }

    @Override
    public Person update(long idToUpdate, Person updatedPerson) {

        log.info("Update request. id = " + idToUpdate);
        Optional<Person> person = persons.stream().filter(p -> p.getId() == idToUpdate).findFirst();
        if (person.isEmpty()) return null;
        else {
            updatedPerson.setId(idToUpdate);
            long id = persons.stream().filter(p -> p.getId() == idToUpdate).findFirst().get().getId();
            int index = persons.indexOf(person.get());
            persons.set(index, updatedPerson);
            return persons.get(index);
        }
    }
    //endregion
}
