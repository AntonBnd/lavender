package by.lavender.beans;

import java.util.Date;

public class OrderList extends Entity {
    private long orderId;
    private User user;
    private Tour tour;
    private Date orderDate;
    private OrderStatus orderStatus;
    private int itemNumber;

    public OrderList() {
    }

    public OrderList(long orderId, User user, Tour tour, Date orderDate,
                     OrderStatus orderStatus, int itemNumber) {
        this.orderId = orderId;
        this.user = user;
        this.tour = tour;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.itemNumber = itemNumber;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
