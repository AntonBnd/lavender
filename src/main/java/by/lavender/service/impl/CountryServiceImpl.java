package by.lavender.service.impl;

import by.lavender.dao.CountryDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.CountryDAOImpl;
import by.lavender.beans.Country;
import by.lavender.service.CountryService;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public class CountryServiceImpl implements CountryService {
    private static final CountryServiceImpl instance = new CountryServiceImpl();

    private CountryServiceImpl(){}

    public static CountryServiceImpl getInstance() {
        return instance;
    }

    public ArrayList<Country> getAllCountries() throws ServiceException {
        ArrayList<Country> countries;
        CountryDAO countryDAOImpl = CountryDAOImpl.getInstance();

        try {
            countries = countryDAOImpl.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return countries;
    }
}
