package by.lavender.service;

import by.lavender.beans.Resort;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public interface ResortService {
    ArrayList<Resort> getAllResorts() throws ServiceException;
}
