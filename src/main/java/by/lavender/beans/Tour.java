package by.lavender.beans;

import java.sql.Date;

public class Tour extends Entity {
    private long tourId;
    private Date startDate;
    private Date endDate;
    private TourType tourType;
    private Resort resort;
    private double price;
    private String discription;
    private TourOperator tourOperator;
    private int numberOfSeats;
    private int tourStatus;
    private int state;

    public Tour() {
    }

    public Tour(Date startDate, Date endDate, TourType tourType, Resort resort, double price,
                String discription, TourOperator tourOperator, int numberOfSeats, int tourStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourType = tourType;
        this.price = price;
        this.discription = discription;
        this.resort = resort;
        this.tourOperator = tourOperator;
        this.numberOfSeats = numberOfSeats;
        this.tourStatus = tourStatus;
    }

    public Tour(long tourId, Date startDate, Date endDate, TourType tourType, Resort resort, double price,
                String discription, TourOperator tourOperator, int numberOfSeats, int tourStatus) {
        this.tourId = tourId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourType = tourType;
        this.price = price;
        this.discription = discription;
        this.resort = resort;
        this.tourOperator = tourOperator;
        this.numberOfSeats = numberOfSeats;
        this.tourStatus = tourStatus;
    }

    public Tour(long tourId, Date startDate, Date endDate, TourType tourType, Resort resort, double price,
                String discription, TourOperator tourOperator, int numberOfSeats, int tourStatus, int state) {
        this.tourId = tourId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tourType = tourType;
        this.price = price;
        this.discription = discription;
        this.resort = resort;
        this.tourOperator = tourOperator;
        this.numberOfSeats = numberOfSeats;
        this.tourStatus = tourStatus;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTourId() {
        return tourId;
    }

    public void setTourId(long tourId) {
        this.tourId = tourId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TourType getTourType() {
        return tourType;
    }

    public void setTourType(TourType tourType) {
        this.tourType = tourType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public Resort getResort() {
        return resort;
    }

    public void setResort(Resort resort) {
        this.resort = resort;
    }

    public TourOperator getTourOperator() {
        return tourOperator;
    }

    public void setTourOperator(TourOperator tourOperator) {
        this.tourOperator = tourOperator;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getTourStatus() {
        return tourStatus;
    }

    public void setTourStatus(int tourStatus) {
        this.tourStatus = tourStatus;
    }
}
