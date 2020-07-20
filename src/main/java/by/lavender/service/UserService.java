package by.lavender.service;

import by.lavender.beans.User;
import by.lavender.service.exception.ServiceException;

public interface UserService {
    boolean addUser(User user) throws ServiceException;
    User getByLoginPassword(String login, String password) throws ServiceException;
}
