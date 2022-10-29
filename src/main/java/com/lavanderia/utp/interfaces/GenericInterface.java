package com.lavanderia.utp.interfaces;

import java.util.List;


public interface GenericInterface<T> {
    public List<T> getAll();
    public List<T> search(String searchText);
    public void add(T data);
    public T getById(int id);
    public void update(T data);
    public void delete(int id);
}
