package by.lavender.service.impl;

import by.lavender.dao.TourOperatorDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.TourOperatorDaoImpl;
import by.lavender.beans.TourOperator;
import by.lavender.service.TourOperatorService;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public class TourOperatorServiceImpl implements TourOperatorService {
    private static final TourOperatorServiceImpl instance = new TourOperatorServiceImpl();

    private TourOperatorServiceImpl(){}

    public static TourOperatorServiceImpl getInstance() {
        return instance;
    }

    @Override
    public ArrayList<TourOperator> getAllTourOperators() throws ServiceException {
        TourOperatorDAO operatorDAO = TourOperatorDaoImpl.getInstance();
        ArrayList<TourOperator> tourOperators;
        try {
            tourOperators = operatorDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tourOperators;
    }
}
