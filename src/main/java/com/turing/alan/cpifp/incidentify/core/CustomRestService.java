package com.turing.alan.cpifp.incidentify.core;

import java.util.List;
public interface CustomRestService<T,I> {

    public List<T> getAll();
    public T create(T entity);
    public T getOne(I id);
    public void delete(I id);
    public T update(T entity);
    
}
