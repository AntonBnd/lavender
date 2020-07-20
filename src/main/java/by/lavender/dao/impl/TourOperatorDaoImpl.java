package by.lavender.dao.impl;

import by.lavender.dao.TourOperatorDAO;
import by.lavender.dao.exception.DAOException;
import by.lavender.beans.TourOperator;
import by.lavender.pool.ConnectionImpl;
import by.lavender.pool.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourOperatorDaoImpl implements TourOperatorDAO {
    private DBConnection pool = DBConnection.getInstance();
    private static final TourOperatorDaoImpl instance = new TourOperatorDaoImpl();
    private static final String GET_OPERATOR_BY_ID = "SELECT operator_id, operator_name," +
            " phone_number, e_mail FROM tour_operator" +
            " WHERE operator_id=?";
    private static final String GET_OPERATORS = "SELECT operator_id, operator_name," +
            " phone_number, e_mail FROM tour_operator";

    private TourOperatorDaoImpl() {
    }

    public static TourOperatorDaoImpl getInstance() {
        return instance;
    }

    @Override
    public ArrayList<TourOperator> getAll() throws DAOException {
        ArrayList<TourOperator> operators = new ArrayList<>();

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_OPERATORS)) {
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long operatorId = resultSet.getLong("operator_id");
                    String operatorName = resultSet.getString("operator_name");
                    String telNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("e_mail");
                    operators.add(new TourOperator(operatorId,operatorName, telNumber, email));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot get all tour operators", e);
        }
        return operators;
    }

    @Override
    public TourOperator getById(long id) throws DAOException {
        TourOperator tourOperator = null;

        try(ConnectionImpl connection = pool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_OPERATOR_BY_ID)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    long operatorId = resultSet.getLong("operator_id");
                    String operatorName = resultSet.getString("operator_name");
                    String telNumber = resultSet.getString("phone_number");
                    String email = resultSet.getString("e_mail");
                    tourOperator = new TourOperator(operatorId, operatorName, telNumber, email);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get tour operator by ID", e);
        }
        return tourOperator;
    }

    @Override
    public void create(TourOperator type) throws DAOException {
    }

    @Override
    public void delete(TourOperator type) throws DAOException {
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(TourOperator type) {
    }
}
