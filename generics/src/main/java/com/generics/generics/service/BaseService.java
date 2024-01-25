package com.generics.generics.service;

import java.util.List;

public interface BaseService<T, ID> {

    T save(T t);

    T findById(ID id);

    List<T> findAll();

    String deleteById(ID id);

    String delete(T t);

}
