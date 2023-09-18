package com.geekbrains.arcsoft.lesson7.spa.services;

import com.geekbrains.arcsoft.lesson7.spa.models.Person;

import java.util.List;
import java.util.Optional;

public interface BusinessService<T> {
    public List<T> getAll();

    public Optional<T> getById(long id);

    public List<T> search(String searchTerm);

    public long add(T newT);

    public boolean delete(long id);

    public T update(long idToUpdate, T updatedT);
}
