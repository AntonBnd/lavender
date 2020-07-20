package by.lavender.service;

import by.lavender.beans.Tour;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public interface TourService {
    ArrayList<Tour> getToursByCountry(long countryId) throws ServiceException;
    ArrayList<Tour> getToursByResort(long resortId) throws ServiceException;
    ArrayList<Tour> getBurningTours() throws ServiceException;
    Tour getTourById(long tourId) throws ServiceException;
    boolean delete(long tourId) throws ServiceException;
    void updateSeatsNumber(long tourId, int allItems, int currentItems) throws ServiceException;
    void createTour(Tour tour) throws ServiceException;
    void update(Tour tour) throws ServiceException;
}
