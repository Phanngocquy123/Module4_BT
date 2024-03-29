package com.ra.springmvc2.repository;


import java.util.List;

public interface Repository<T> {
    List<T> findAll(Class<T> entityClass);
    T findId(Class<T> entityClass, Object... keys);
    T add(T entity);
    T edit(T entity);
    boolean remove(Class<T> entityClass, Object... keys);
}
