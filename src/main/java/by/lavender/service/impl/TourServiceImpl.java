package by.lavender.service.impl;

import by.lavender.dao.TourDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.dao.impl.TourDAOImpl;
import by.lavender.beans.OrderList;
import by.lavender.beans.Tour;
import by.lavender.service.OrderListService;
import by.lavender.service.TourService;
import by.lavender.service.exception.ServiceException;

import java.util.ArrayList;

public class TourServiceImpl implements TourService {
    private final static TourServiceImpl instance = new TourServiceImpl();
    private static final long PROCESSED_ID = 1;
    private static final long APPROVED_ID = 2;
    private final static int BURNING_TOUR_STATUS = 1;

    private TourServiceImpl(){}

    public static TourServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(long tourId) throws ServiceException {
        OrderListService orderListService = OrderListServiceImpl.getInstance();
        TourDAO tourDAO = TourDAOImpl.getInstance();
        ArrayList<OrderList> orders = orderListService.getAllOrders();
        int counter = 0;

        for(OrderList order : orders) {
            if(order.getTour().getTourId() == tourId && (order.getOrderStatus().getStatusId() == PROCESSED_ID
                    || order.getOrderStatus().getStatusId() == APPROVED_ID )) {
                counter++;
            }
        }
        if(counter == 0) {
            try {
                tourDAO.updateTourState(tourId);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Tour getTourById(long tourId) throws ServiceException {
        TourDAO tourDAO = TourDAOImpl.getInstance();
        Tour tour = null;
        try {
            tour = tourDAO.getById(tourId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tour;
    }

    @Override
    public ArrayList<Tour> getBurningTours() throws ServiceException {
        TourDAO tourDAO = TourDAOImpl.getInstance();
        ArrayList<Tour> tours = new ArrayList<>();

        try {
            for(Tour tour : tourDAO.getAll()) {
                if (tour.getTourStatus() == BURNING_TOUR_STATUS && tour.getNumberOfSeats() > 0 && tour.getState() == 0) {
                    tours.add(tour);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tours;
    }

    @Override
    public void update(Tour tour) throws ServiceException {
        TourDAO tourDAO = TourDAOImpl.getInstance();
        try {
            tourDAO.update(tour);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createTour(Tour tour) throws ServiceException {
        TourDAO tourDAO = TourDAOImpl.getInstance();
        try {
            tourDAO.create(tour);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateSeatsNumber(long tourId, int allItems, int currentItems) throws ServiceException {
        TourDAO tourDAO = TourDAOImpl.getInstance();
        int difference =  allItems - currentItems;
        try {
            tourDAO.updateNumberOfSeats(tourId, difference);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public ArrayList<Tour> getToursByCountry(long countryId) throws ServiceException {
        ArrayList<Tour> tours;
        TourDAO tourDAOImpl = TourDAOImpl.getInstance();

        try {
            tours = tourDAOImpl.getToursByCountry(countryId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tours;
    }

    public ArrayList<Tour> getToursByResort(long resortId) throws ServiceException {
        ArrayList<Tour> tours;
        TourDAO tourDAOImpl = TourDAOImpl.getInstance();

        try {
            tours = tourDAOImpl.getToursByResort(resortId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tours;
    }
}
