package com.turing.alan.cpifp.incidentify.core;

public interface CustomRestService<T,I> {

    public Iterable<T> getAll();
    public T create(T entity);
    public T getOne(I id);
    public void delete(I id);
    
}
