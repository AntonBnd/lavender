package by.lavender.service;

import by.lavender.beans.TourOperator;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public interface TourOperatorService {
    ArrayList<TourOperator> getAllTourOperators() throws ServiceException;
}
