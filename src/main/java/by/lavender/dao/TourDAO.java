package by.lavender.dao;

import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Tour;

import java.util.ArrayList;

public interface TourDAO extends GenericDAO<Tour> {
    ArrayList<Tour> getToursByCountry(long countryId) throws DAOException;
    ArrayList<Tour> getToursByResort(long id) throws DAOException;
    void updateNumberOfSeats(long tourId, int numberOfSeats) throws DAOException;
    void updateTourState(long tourId) throws DAOException;
}
