package by.lavender.dao.impl;

import by.lavender.dao.CountryDAO;
import by.lavender.dao.ResortDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Country;
import by.lavender.beans.Resort;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResortDAOImpl implements ResortDAO {
    private DBConnection pool = DBConnection.getInstance();;
    private static final ResortDAOImpl instance = new ResortDAOImpl();
    private static final String GET_RESORT_BY_ID = "SELECT resort_id, resort_name, country_id" +
            " FROM resort WHERE resort_id=?";
    private static final String GET_ALL_RESORTS = "SELECT resort_id, resort_name, country_id" +
            " FROM resort ORDER BY resort_name ";
    private ResortDAOImpl() {
    }

    public static ResortDAOImpl getInstance() {
        return instance;
    }

    @Override
    public Resort getById(long id) throws DAOException {
        CountryDAO countryDAO = CountryDAOImpl.getInstance();
        Resort resort = null;
        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_RESORT_BY_ID)) {
            preparedStatement.setLong(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long resortId = resultSet.getLong("resort_id");
                    String resortName = resultSet.getString("resort_name");
                    Country country = countryDAO.getById(resultSet.getLong("country_id"));
                    resort = new Resort(resortId, resortName, country);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get resort by ID", e);
        }
        return resort;
    }

    @Override
    public ArrayList<Resort> getAll() throws DAOException {
        CountryDAO countryDAO = CountryDAOImpl.getInstance();
        ArrayList<Resort> resorts = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_RESORTS);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                long resortId = resultSet.getLong("resort_id");
                String resortName = resultSet.getString("resort_name");
                Country country = countryDAO.getById(resultSet.getLong("country_id"));
                resorts.add(new Resort(resortId, resortName, country));
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get resort by ID", e);
        }
        return resorts;
    }

    @Override
    public void update(Resort type) {
    }

    @Override
    public void create(Resort type) throws DAOException {
    }

    @Override
    public void delete(Resort type) throws DAOException {
    }

    @Override
    public void delete(long id) {
    }
}
