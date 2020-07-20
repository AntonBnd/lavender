package by.lavender.dao;

import by.lavender.dao.exception.DAOException;
import by.lavender.beans.OrderList;

import java.sql.Date;
import java.util.ArrayList;

public interface OrderListDAO extends GenericDAO<OrderList> {
    void create(long tourId, long userId, Date date, int itemNumber) throws DAOException;
    ArrayList<OrderList> getByUserId(long userId) throws DAOException;
    void updateStatus(long orderId, long statusId) throws DAOException;
}
