package by.lavender.service;

import by.lavender.beans.OrderList;
import by.lavender.service.exception.ServiceException;

import java.sql.Date;
import java.util.ArrayList;


public interface OrderListService {
    void orderTour(long tourId, long userId, Date date, int itemNumber) throws ServiceException, ServiceException;
    ArrayList<OrderList> getUserOrders(long id) throws ServiceException;
    void deleteOrder(long id) throws ServiceException;
    ArrayList<OrderList> getAllOrders() throws ServiceException;
    void processOrder(long statusId, long orderId) throws ServiceException;
}
