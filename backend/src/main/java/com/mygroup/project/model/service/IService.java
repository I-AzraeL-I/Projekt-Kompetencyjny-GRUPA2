package com.mygroup.project.model.service;

import java.util.Collection;

public interface IService<T> {

    T get(Long id);
    Collection<T> getAll();
    void create(T t);
    void update(T t);
    void delete(T t);
}
