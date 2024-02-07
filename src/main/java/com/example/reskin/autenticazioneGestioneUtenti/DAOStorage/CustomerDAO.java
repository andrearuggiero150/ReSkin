package com.example.reskin.autenticazioneGestioneUtenti.DAOStorage;

import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Admin;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Cliente;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Customer;
import com.example.reskin.connPool.connectionPoolAbstraction;
import com.example.reskin.connPool.connectionPoolMock;
import com.example.reskin.connPool.connectionImplementor;
import com.example.reskin.connectionPool;

import java.sql.*;

public class CustomerDAO {
    public static int loginUtente(String email, String password, connectionPoolAbstraction cpa) {
        try {
            String passwordHash = Customer.cryptPassword(password);
            Connection c = cpa.setConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT email, passwordHash, isAdmin FROM Customer WHERE email=? AND passwordhash=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, passwordHash);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return 0;
            } else if (resultSet.getBoolean(3)) {
                return 2;
            }
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    public static Customer returnCustomerData(String email, connectionPoolAbstraction cpa) {
        try {
            Customer customer = null;
            Connection c = cpa.setConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Customer WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean(7)) {
                    customer = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(6), resultSet.getString(5));
                } else if(!resultSet.getBoolean(7)){
                    customer = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(6), resultSet.getString(5), resultSet.getString(4));
                }
            }
            return customer;
        } catch (SQLException e) {
            return null;
        }
    }

    public static int registerCliente(Cliente cliente, connectionPoolAbstraction cpa) {
        try (Connection c = cpa.setConnection()) {
            PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO Customer (nome, cognome,PIVA, passwordhash, email, isAdmin) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getCognome());
            preparedStatement.setString(3, cliente.getPiva());
            preparedStatement.setString(4, Customer.cryptPassword(cliente.getPassword()));
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setBoolean(6, false);
            return preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            return 2;
        } catch (SQLException e) {
            return 0;
        }
    }
    public static int registerAdmin(Admin admin, connectionPoolAbstraction cpa) {
        try (Connection c = cpa.setConnection()) {
            PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO Customer (nome, cognome, passwordhash, email, isAdmin) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, admin.getNome());
            preparedStatement.setString(2, admin.getCognome());
            preparedStatement.setString(3, Customer.cryptPassword(admin.getPassword()));
            preparedStatement.setString(4, admin.getEmail());
            preparedStatement.setBoolean(5, true);
            return preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            return 2;
        } catch (SQLException e) {
            return 0;
        }
    }
}
