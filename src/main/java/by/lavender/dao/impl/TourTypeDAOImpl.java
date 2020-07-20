package by.lavender.dao.impl;

import by.lavender.dao.TourTypeDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.TourType;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourTypeDAOImpl implements TourTypeDAO {
    private DBConnection pool = DBConnection.getInstance();
    private static final TourTypeDAOImpl instance = new TourTypeDAOImpl();
    private static final String GET_TOUR_TYPE_BY_ID = "SELECT type_id, type_name" +
            " FROM tour_type WHERE type_id=?";
    private static final String GET_TOUR_TYPES = "SELECT type_id, type_name" +
            " FROM tour_type";

    private TourTypeDAOImpl() {
    }

    public static TourTypeDAOImpl getInstance() {
        return instance;
    }

    @Override
    public TourType getById(long id) throws DAOException {
        TourType tourType = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_TYPE_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long typeId = resultSet.getLong("type_id");
                    String typeName = resultSet.getString("type_name");
                    tourType = new TourType(typeId, typeName);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get tour type by ID", e);
        }
        return tourType;
    }

    @Override
    public ArrayList<TourType> getAll() throws DAOException {
        ArrayList<TourType> tourTypes = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TOUR_TYPES)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long typeId = resultSet.getLong("type_id");
                    String typeName = resultSet.getString("type_name");
                    tourTypes.add(new TourType(typeId, typeName));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get all tour types ", e);
        }
        return tourTypes;
    }

    @Override
    public void create(TourType type) throws DAOException {
    }

    @Override
    public void delete(TourType type) throws DAOException {
    }

    @Override
    public void delete(long id) {
    }


    @Override
    public void update(TourType type) {
    }
}
