package by.lavender.service;

import by.lavender.beans.Country;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public interface CountryService {
    ArrayList<Country> getAllCountries() throws ServiceException;

}
