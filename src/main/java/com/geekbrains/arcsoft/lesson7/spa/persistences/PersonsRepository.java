package com.geekbrains.arcsoft.lesson7.spa.persistences;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PersonsRepository extends MongoRepository<Person, String> {

    //Use the MongoRepository class in Spring to do the data operations on mangodb
    //already implies that we will use save, findall, findbyid, deletebyid etc.
    @Query("{'name': {'$regex': '?0','$options': 'i'}}")
    List<Person> findByName(String searchTerm);
}
