package by.lavender.service.impl;

import by.lavender.dao.OrderListDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.OrderListDAOImpl;
import by.lavender.beans.OrderList;
import by.lavender.service.OrderListService;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;
import java.sql.Date;

public class OrderListServiceImpl implements OrderListService {
    private static final OrderListServiceImpl instance = new OrderListServiceImpl();

    private OrderListServiceImpl(){}

    public static OrderListServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void processOrder(long statusId, long orderId) throws ServiceException {
        OrderListDAO orderListDAO = OrderListDAOImpl.getInstance();

        try {
            orderListDAO.updateStatus(orderId, statusId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteOrder(long id) throws ServiceException {
        OrderListDAO orderListDAO = OrderListDAOImpl.getInstance();
        try {
            orderListDAO.delete(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public ArrayList<OrderList> getAllOrders() throws ServiceException {
        OrderListDAO orderListDAO = OrderListDAOImpl.getInstance();
        ArrayList<OrderList> orders;

        try {
            orders = orderListDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public ArrayList<OrderList> getUserOrders(long id) throws ServiceException {
        OrderListDAO orderListDAO = OrderListDAOImpl.getInstance();
        ArrayList<OrderList> orders;

        try {
            orders = orderListDAO.getByUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public void orderTour(long tourId, long userId, Date date, int itemNumber) throws ServiceException {
        OrderListDAO orderListDAO = OrderListDAOImpl.getInstance();
        try {
            orderListDAO.create(tourId, userId, date, itemNumber);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
