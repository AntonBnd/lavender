package by.lavender.service;

import by.lavender.beans.TourType;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public interface TourTypeService {
    ArrayList<TourType> getTourTypes() throws ServiceException;
}
