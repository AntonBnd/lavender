package by.lavender.dao.impl;

import by.lavender.dao.RoleDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Role;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDAOImpl implements RoleDAO {
    private DBConnection pool = DBConnection.getInstance();
    private static final RoleDAOImpl instance = new RoleDAOImpl();
    private static final String GET_ROLE_BY_ID = "SELECT role_id, role_name FROM role" +
            " WHERE role_id=?";

    private RoleDAOImpl() {
    }

    public static RoleDAOImpl getInstance() {
        return instance;
    }

    @Override
    public Role getById(long id) throws DAOException {
        Role role = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROLE_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long roleId = resultSet.getLong("role_id");
                    String roleName = resultSet.getString("role_name");
                    role = new Role(roleId,roleName);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get role by id", e);
        }
        return role;
    }

    @Override
    public ArrayList<Role> getAll() throws DAOException {
        return null;
    }

    @Override
    public void update(Role type) {
    }

    @Override
    public void create(Role type) throws DAOException {
    }

    @Override
    public void delete(Role type) throws DAOException {
    }

    @Override
    public void delete(long id) {
    }
}
