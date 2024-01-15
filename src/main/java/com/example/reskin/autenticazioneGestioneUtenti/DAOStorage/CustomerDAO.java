package com.example.reskin.autenticazioneGestioneUtenti.DAOStorage;

import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Admin;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Cliente;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Customer;
import com.example.reskin.connectionPool;

import java.sql.*;

public class CustomerDAO {
    public static int loginUtente(String email, String password) throws SQLException {
        String passwordHash = Customer.cryptPassword(password);
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT email, passwordHash, isAdmin FROM Customer WHERE email=? AND passwordhash=?");
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, passwordHash);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            return 0;
        } else if (resultSet.getBoolean(3)) {
            return 2;
        }
        return 1;
    }

    public static Customer returnCustomerData(String email) throws SQLException {
        Customer customer = null;
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE email=?");
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if(resultSet.getBoolean(7)) {
                customer = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(6), resultSet.getString(5));
            }
            else {
                customer = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(6), resultSet.getString(5), resultSet.getString(4));
            }
        }
        return customer;
    }

    public static int registerCliente(Cliente cliente) {
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer (nome, cognome,PIVA, passwordhash, email, isAdmin) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCognome());
            preparedStatement.setString(3, cliente.getPiva());
            preparedStatement.setString(4, cliente.getPassword());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setBoolean(6, false);
            if (preparedStatement.executeUpdate() != 1) {
                return 0;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            return 2;
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }
}