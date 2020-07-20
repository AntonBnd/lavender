package by.lavender.dao.impl;

import by.lavender.dao.RoleDAO;
import by.lavender.dao.UserDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Role;
import by.lavender.beans.User;
import by.lavender.pool.DBConnection;
import by.lavender.pool.ConnectionImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    private DBConnection pool = DBConnection.getInstance();
    private static final UserDAOImpl instance = new UserDAOImpl();
    private static final String CHECK_USERNAME = "SELECT login FROM user WHERE login=?";
    private static final String CREATE_USER =
            "INSERT INTO user(f_name, l_name, login, password, phone_number)" +
                    " VALUES(?,?,?,?,?)";
    private static final String CHECK_USER_INFO =
            "SELECT user_id, f_name, l_name, login, password, role_id, phone_number" +
                    " FROM user WHERE login=? AND password=?";
    private static final String GET_USER_BY_ID = "SELECT user_id, f_name, l_name, login, password, role_id, phone_number" +
            " FROM user WHERE user_id=?";

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        return instance;
    }

    @Override
    public User getById(long id) throws DAOException {
        RoleDAO roleDAO = RoleDAOImpl.getInstance();
        User user = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long userId = resultSet.getLong("user_id");
                    String firstName = resultSet.getString("f_name");
                    String lastName = resultSet.getString("l_name");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    Role role = roleDAO.getById(resultSet.getLong("role_id"));
                    String telNumber = resultSet.getString("phone_number");
                    user = new User(userId, firstName, lastName, password, login, role, telNumber);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get user by Id", e);
        }
        return user;
    }

    @Override
    public String getLogin(String userLogin) throws DAOException {
        String login = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME)) {
            preparedStatement.setString(1, userLogin);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    login = resultSet.getString("login");
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not user by login", e);
        }
        return login;
    }

    @Override
    public User getLoginPassword(String userName, String userPassword) throws DAOException {
        RoleDAO roleDAO = RoleDAOImpl.getInstance();
        User user = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_INFO)) {
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long userId = resultSet.getLong("user_id");
                    String firstName = resultSet.getString("f_name");
                    String lastName = resultSet.getString("l_name");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    Role role = roleDAO.getById(resultSet.getLong("role_id"));
                    String telNumber = resultSet.getString("phone_number");
                    user = new User(userId, firstName, lastName, password, login, role, telNumber);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get user information by login and password", e);
        }
        return user;
    }

    @Override
    public void create(User type) throws DAOException {
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, type.getFirstName());
            preparedStatement.setString(2, type.getLastName());
            preparedStatement.setString(3, type.getLogin());
            preparedStatement.setString(4, type.getPassword());
            preparedStatement.setString(5, type.getPhoneNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DAOException("Can not create user", e);
        }
    }

    @Override
    public void delete(User type) throws DAOException {
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public ArrayList<User> getAll() throws DAOException {
        return null;
    }

    @Override
    public void update(User type) {
    }
}
