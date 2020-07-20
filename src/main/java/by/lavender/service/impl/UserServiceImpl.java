package by.lavender.service.impl;

import by.lavender.dao.UserDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.UserDAOImpl;
import by.lavender.beans.User;
import by.lavender.service.UserService;
import by.lavender.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        return instance;
    }

    public boolean addUser(User user) throws ServiceException {
        UserDAO userDAOImpl = UserDAOImpl.getInstance();
        try {
            if (checkUsername(user.getLogin())) {
                userDAOImpl.create(user);
                return true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    private boolean checkUsername(String userLogin) throws DAOException {
        UserDAO userDAOImpl = UserDAOImpl.getInstance();
        String login = userDAOImpl.getLogin(userLogin);

        if (login == null) {
            return true;
        } else {
            return false;
        }
    }

    public User getByLoginPassword(String login, String password) throws ServiceException {
        UserDAO userDAOImpl = UserDAOImpl.getInstance();
        User user;

        try {
            user = userDAOImpl.getLoginPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}
