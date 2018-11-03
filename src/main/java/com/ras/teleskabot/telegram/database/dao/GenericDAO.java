package com.ras.teleskabot.telegram.database.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, Id extends Serializable> {

    Id create(T entity);
    T read(Id id);
    List<T> readAll();
    void update(T entity);
    void delete(T entity);

}