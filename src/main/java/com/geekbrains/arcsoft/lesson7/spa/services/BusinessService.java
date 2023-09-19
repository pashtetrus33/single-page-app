package com.geekbrains.arcsoft.lesson7.spa.services;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;

import java.util.List;
import java.util.Optional;

public interface BusinessService<T, K> {
    public List<T> getAll();

    public Optional<T> getById(K id);

    public List<T> search(String searchTerm);

    public K add(T newT);

    public boolean delete(K id);

    public T update(K idToUpdate, T updatedT);
}
