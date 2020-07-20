package by.lavender.dao;

import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Entity;

import java.util.ArrayList;

public interface GenericDAO<T extends Entity> {
    void create(T type) throws DAOException;
    T getById(long id) throws DAOException;
    ArrayList<T> getAll() throws DAOException;
    void update(T type) throws DAOException;
    void delete(T type) throws DAOException;
    void delete(long id) throws DAOException;
}
