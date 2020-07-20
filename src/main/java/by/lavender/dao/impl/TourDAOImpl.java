package by.lavender.dao.impl;

import by.lavender.dao.ResortDAO;
import by.lavender.dao.TourDAO;
import by.lavender.dao.TourOperatorDAO;
import by.lavender.dao.TourTypeDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Resort;
import by.lavender.beans.Tour;
import by.lavender.beans.TourOperator;
import by.lavender.beans.TourType;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourDAOImpl implements TourDAO {
    private DBConnection pool = DBConnection.getInstance();
    private static final TourDAOImpl instance = new TourDAOImpl();
    private static final String GET_TOURS_BY_COUNTRY =
            "SELECT tour_id, beg_date, end_date, type_id, resort_id," +
                    "price,discription, operator_id, number_of_seats, tour_status FROM tour" +
                    " INNER JOIN resort ON resort_id=resort_id" +
                    " WHERE country_id=? AND number_of_seats!=0 AND state!=1";
    private static final String GET_TOURS_BY_RESORT =
            "SELECT tour_id, beg_date, end_date, type_id, resort_id, " +
                    "price, discription, operator_id, number_of_seats, tour_status" +
                    " FROM tour WHERE resort_id=? AND number_of_seats!=0 AND state!=1";
    private static final String UPDATE_NUMBER_OF_SEATS = "UPDATE tour SET number_of_seats=?" +
            " WHERE tour_id=?";
    private static final String GET_TOUR_BY_ID =
            "SELECT tour_id, beg_date, end_date, type_id, resort_id, " +
                    "price, discription, operator_id, number_of_seats, tour_status" +
                    " FROM tour WHERE tour_id=?";
    private static final String CREATE_TOUR =
            "INSERT INTO tour(beg_date, end_date, type_id, resort_id, price," +
                    " discription, operator_id, number_of_seats, tour_status)" +
                    " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_TOUR =
            "UPDATE tour SET beg_date=?, end_date=?, type_id=?, resort_id=?, " +
                    "price=?, discription=?, operator_id=?, number_of_seats=?, tour_status=?" +
                    " WHERE tour_id=?";
    private static final String GET_ALL =
            "SELECT tour_id, beg_date, end_date, type_id, resort_id, " +
                    "price, discription, operator_id, number_of_seats, tour_status, state" +
                    " FROM tour";
    private static final String DELETE_BY_ID = "DELETE FROM tour WHERE tour_id=?";
    private static final String UPDATE_TOUR_STATE = "UPDATE tour SET state=1 WHERE tour_id=?";

    private TourDAOImpl() {
    }

    public static TourDAOImpl getInstance() {
        return instance;
    }

    @Override
    public void delete(long id) throws DAOException {
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot delete tour by id", e);
        }
    }

    @Override
    public ArrayList<Tour> getAll() throws DAOException {
        TourTypeDAO tourTypeDAO = TourTypeDAOImpl.getInstance();
        ResortDAO resortDAO = ResortDAOImpl.getInstance();
        TourOperatorDAO tourOperatorDao = TourOperatorDaoImpl.getInstance();
        ArrayList<Tour> tours = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long tourId = resultSet.getLong("tour_id");
                    Date begDate = resultSet.getDate("beg_date");
                    Date endDate = resultSet.getDate("end_date");
                    TourType tourType = tourTypeDAO.getById(resultSet.getLong("type_id"));
                    Resort resort = resortDAO.getById(resultSet.getLong("resort_id"));
                    double price = resultSet.getDouble("price");
                    String discription = resultSet.getString("discription");
                    TourOperator operator = tourOperatorDao.getById(resultSet.getLong("operator_id"));
                    int numberOfSeats = resultSet.getInt("number_of_seats");
                    int tourStatus = resultSet.getInt("tour_status");
                    int state = resultSet.getInt("state");
                    tours.add(new Tour(tourId, begDate, endDate, tourType, resort, price,
                            discription, operator, numberOfSeats, tourStatus, state));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot find tour by country", e);
        }
        return tours;
    }

    @Override
    public void update(Tour type) throws DAOException {
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR)) {
            preparedStatement.setDate(1, type.getStartDate());
            preparedStatement.setDate(2, type.getEndDate());
            preparedStatement.setLong(3, type.getTourType().getTypeId());
            preparedStatement.setLong(4, type.getResort().getResortId());
            preparedStatement.setDouble(5, type.getPrice());
            preparedStatement.setString(6, type.getDiscription());
            preparedStatement.setLong(7, type.getTourOperator().getOperatorId());
            preparedStatement.setInt(8, type.getNumberOfSeats());
            preparedStatement.setInt(9, type.getTourStatus());
            preparedStatement.setLong(10, type.getTourId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update number of seats tour ", e);
        }
    }

    @Override
    public void create(Tour type) throws DAOException {
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TOUR)) {
            preparedStatement.setDate(1, type.getStartDate());
            preparedStatement.setDate(2, type.getEndDate());
            preparedStatement.setLong(3, type.getTourType().getTypeId());
            preparedStatement.setLong(4, type.getResort().getResortId());
            preparedStatement.setDouble(5, type.getPrice());
            preparedStatement.setString(6, type.getDiscription());
            preparedStatement.setLong(7, type.getTourOperator().getOperatorId());
            preparedStatement.setInt(8, type.getNumberOfSeats());
            preparedStatement.setInt(9, type.getTourStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot create tour", e);
        }
    }

    @Override
    public Tour getById(long id) throws DAOException {
        TourTypeDAO tourTypeDAO = TourTypeDAOImpl.getInstance();
        ResortDAO resortDAO = ResortDAOImpl.getInstance();
        TourOperatorDAO tourOperatorDao = TourOperatorDaoImpl.getInstance();
        Tour tour = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long tourId = resultSet.getLong("tour_id");
                    Date begDate = resultSet.getDate("beg_date");
                    Date endDate = resultSet.getDate("end_date");
                    TourType tourType = tourTypeDAO.getById(resultSet.getLong("type_id"));
                    Resort resort = resortDAO.getById(resultSet.getLong("resort_id"));
                    double price = resultSet.getDouble("price");
                    String discription = resultSet.getString("discription");
                    TourOperator operator = tourOperatorDao.getById(resultSet.getLong("operator_id"));
                    int numberOfSeats = resultSet.getInt("number_of_seats");
                    int tourStatus = resultSet.getInt("tour_status");
                    tour = new Tour(tourId, begDate, endDate, tourType, resort, price,
                            discription, operator, numberOfSeats, tourStatus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tour;
    }

    @Override
    public void updateTourState(long tourId) throws DAOException {
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TOUR_STATE)) {
            preparedStatement.setLong(1, tourId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update tour state", e);
        }
    }

    @Override
    public void updateNumberOfSeats(long tourId, int numberOfSeats) throws DAOException {
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NUMBER_OF_SEATS)){
            preparedStatement.setInt(1, numberOfSeats);
            preparedStatement.setLong(2, tourId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Cannot update number of seats number of seats in current tour");
        }
    }

    @Override
    public ArrayList<Tour> getToursByCountry(long countryId) throws DAOException {
        TourTypeDAO tourTypeDAO = TourTypeDAOImpl.getInstance();
        ResortDAO resortDAO = ResortDAOImpl.getInstance();
        TourOperatorDAO tourOperatorDao = TourOperatorDaoImpl.getInstance();
        ArrayList<Tour> tours = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TOURS_BY_COUNTRY)) {
            preparedStatement.setLong(1, countryId);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long tourId = resultSet.getLong("tour_id");
                    Date begDate = resultSet.getDate("beg_date");
                    Date endDate = resultSet.getDate("end_date");
                    TourType tourType = tourTypeDAO.getById(resultSet.getLong("type_id"));
                    Resort resort = resortDAO.getById(resultSet.getLong("resort_id"));
                    double price = resultSet.getDouble("price");
                    String discription = resultSet.getString("discription");
                    TourOperator operator = tourOperatorDao.getById(resultSet.getLong("operator_id"));
                    int numberOfSeats = resultSet.getInt("number_of_seats");
                    int tourStatus = resultSet.getInt("tour_status");
                    tours.add(new Tour(tourId, begDate, endDate, tourType, resort, price,
                            discription, operator, numberOfSeats, tourStatus));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not find tour by country", e);
        }
        return tours;
    }

    @Override
    public ArrayList<Tour> getToursByResort(long id) throws DAOException {
        TourTypeDAO tourTypeDAO = TourTypeDAOImpl.getInstance();
        ResortDAO resortDAO = ResortDAOImpl.getInstance();
        TourOperatorDAO tourOperatorDao = TourOperatorDaoImpl.getInstance();
        ArrayList<Tour> tours = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TOURS_BY_RESORT)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long tourId = resultSet.getLong("tour_id");
                    Date begDate = resultSet.getDate("beg_date");
                    Date endDate = resultSet.getDate("end_date");
                    TourType tourType = tourTypeDAO.getById(resultSet.getLong("type_id"));
                    Resort resort = resortDAO.getById(resultSet.getLong("resort_id"));
                    double price = resultSet.getDouble("price");
                    String discription = resultSet.getString("discription");
                    TourOperator operator = tourOperatorDao.getById(resultSet.getLong("operator_id"));
                    int numberOfSeats = resultSet.getInt("number_of_seats");
                    int tourStatus = resultSet.getInt("tour_status");
                    tours.add(new Tour(tourId, begDate, endDate, tourType, resort, price,
                            discription, operator, numberOfSeats, tourStatus));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get tour by resort", e);
        }
        return tours;
    }

    @Override
    public void delete(Tour type) throws DAOException {
    }

}
