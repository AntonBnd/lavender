package by.lavender.service.impl;

import by.lavender.dao.TourTypeDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.TourTypeDAOImpl;
import by.lavender.beans.TourType;
import by.lavender.service.TourTypeService;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public class TourTypeServiceImpl implements TourTypeService {
    private static final TourTypeServiceImpl instance = new TourTypeServiceImpl();

    private TourTypeServiceImpl(){}

    public static TourTypeServiceImpl getInstance() {
        return instance;
    }

    @Override
    public ArrayList<TourType> getTourTypes() throws ServiceException {
        TourTypeDAO tourTypeDAO = TourTypeDAOImpl.getInstance();
        ArrayList<TourType> tourTypes;
        try {
            tourTypes = tourTypeDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tourTypes;
    }
}
