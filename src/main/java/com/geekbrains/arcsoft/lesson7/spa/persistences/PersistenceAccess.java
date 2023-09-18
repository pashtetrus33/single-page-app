package com.geekbrains.arcsoft.lesson7.spa.persistences;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;

import java.util.List;
import java.util.Optional;

public interface PersistenceAccess<T> {
    public Optional<T> getById(long id);

    public List<T> getAll();

    public List<T> search(String searchTerm);

    public long add(T newT);

    public boolean delete(long id);

    public Person update(long idToUpdate, T updatedT);

}

