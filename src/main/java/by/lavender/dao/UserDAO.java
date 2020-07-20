package by.lavender.dao;

import by.lavender.dao.exception.DAOException;
import by.lavender.beans.User;

public interface UserDAO extends GenericDAO<User> {
    String getLogin(String userLogin) throws DAOException;
    User getLoginPassword(String userName, String userPassword) throws DAOException;
}
