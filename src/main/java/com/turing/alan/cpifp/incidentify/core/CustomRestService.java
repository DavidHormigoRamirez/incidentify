package com.turing.alan.cpifp.incidentify.core;

public interface CustomRestService<T,I> {

    public Iterable<T> getAll();
    public T create(T entity);
    public T getOne(I id);
    public void delete(I id);
<<<<<<< HEAD
    public T update(T entity,I id);
=======
>>>>>>> b7a3dc2ba0900249f28b94b01f0ce5cf355b6277
    
}
