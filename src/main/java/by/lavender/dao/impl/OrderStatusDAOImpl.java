package by.lavender.dao.impl;

import by.lavender.dao.OrderStatusDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.OrderStatus;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderStatusDAOImpl implements OrderStatusDAO {
    private DBConnection pool = DBConnection.getInstance();;
    private static final OrderStatusDAOImpl instance = new OrderStatusDAOImpl();
    private static final String GET_STATUS_BY_ID = "SELECT status_id, status_name FROM order_status" +
            " WHERE status_id=?";
    private OrderStatusDAOImpl() {
    }

    public static OrderStatusDAOImpl getInstance() {
        return instance;
    }

    @Override
    public OrderStatus getById(long id) throws DAOException {
        OrderStatus orderStatus = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_STATUS_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long statusId = resultSet.getLong("status_id");
                    String statusName = resultSet.getString("status_name");
                    orderStatus = new OrderStatus(statusId, statusName);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get order status by id", e);
        }
        return orderStatus;
    }

    @Override
    public void create(OrderStatus type) throws DAOException {
    }

    @Override
    public void delete(OrderStatus type) throws DAOException {
    }

    @Override
    public void delete(long id) {
    }

    @Override
    public ArrayList<OrderStatus> getAll() throws DAOException {
        return null;
    }

    @Override
    public void update(OrderStatus type) {
    }
}
