package by.lavender.dao.impl;

import by.lavender.dao.CountryDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.Country;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountryDAOImpl implements CountryDAO {
    private DBConnection pool = DBConnection.getInstance();
    private static final CountryDAOImpl instance = new CountryDAOImpl();
    private static final String GET_ALL = "SELECT country_id, country_name FROM country";
    private static final String GET_COUNTRY = "SELECT country_id, country_name " +
            "FROM country WHERE country_id=?";

    private CountryDAOImpl() {
    }

    public static CountryDAOImpl getInstance() {
        return instance;
    }

    @Override
    public Country getById(long id) throws DAOException {
        Country country = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNTRY)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long countryId = resultSet.getLong("country_id");
                    String countryName = resultSet.getString("country_name");
                    country = new Country(countryId, countryName);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get current country", e);
        }
        return country;
    }

    @Override
    public ArrayList<Country> getAll() throws DAOException {
        ArrayList<Country> countries = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                long countryId = resultSet.getLong("country_id");
                String countryName = resultSet.getString("country_name");
                countries.add(new Country(countryId, countryName));
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get all countries", e);
        }
        return countries;
    }

    @Override
    public void update(Country type) {
    }

    @Override
    public void create(Country type) throws DAOException {
    }

    @Override
    public void delete(Country type) throws DAOException {
    }

    @Override
    public void delete(long id) {
    }
}
