package by.lavender.service.impl;

import by.lavender.dao.ResortDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.ResortDAOImpl;
import by.lavender.beans.Resort;
import by.lavender.service.ResortService;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public class ResortServiceImpl implements ResortService {
    private static ResortServiceImpl instance = new ResortServiceImpl();

    private ResortServiceImpl(){}

    public static ResortServiceImpl getInstance() {
        return instance;
    }

    public ArrayList<Resort> getAllResorts() throws ServiceException {
        ArrayList<Resort> resorts;
        ResortDAO resortDAO = ResortDAOImpl.getInstance();

        try {
            resorts = resortDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return resorts;
    }
}
