package com.example.reskin.gestioneNotifiche.DAOStorage;

import com.example.reskin.Entity.POP;
import com.example.reskin.connPool.connectionPoolAbstraction;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GNDAO {
    public static List<POP> clientPOP(int clientID, connectionPoolAbstraction cpa) {
        List<POP> popList = new ArrayList<>();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM POP WHERE customerID = ?");
            preparedStatement.setInt(1, clientID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String data = resultSet.getString(3);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime retrievedDatetime = LocalDateTime.parse(data, formatter);

                popList.add(new POP(resultSet.getInt(1), resultSet.getString(2), retrievedDatetime, resultSet.getInt(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return popList;
    }

    public static List<POP> adminPOP(connectionPoolAbstraction cpa) {
        List<POP> popList = new ArrayList<>();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT POP.POPID, POP.testo, POP.dataInvio, POP.customerID, Customer.email FROM POP, Customer WHERE Customer.customerID = POP.customerID");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String data = resultSet.getString(3);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime retrievedDatetime = LocalDateTime.parse(data, formatter);

                popList.add(new POP(resultSet.getInt(1), resultSet.getString(2), retrievedDatetime, resultSet.getInt(4), resultSet.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return popList;
    }

    public static int sendIndividualPOP(String email, String testo, connectionPoolAbstraction cpa) {
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT customerID, isAdmin FROM Customer WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(!resultSet.next())
                return 3;
            if(resultSet.getBoolean("isAdmin"))
                return 2;
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            PreparedStatement preparedStatement1= connection.prepareStatement("INSERT INTO POP (testo, dataInvio, customerID) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement1.setString(1, testo);
            preparedStatement1.setTimestamp(2, timestamp);
            preparedStatement1.setInt(3, resultSet.getInt("customerID"));
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    public static int sendBroadcastPOP(String testo, connectionPoolAbstraction cpa) {
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT customerID, isAdmin FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                if (!resultSet.getBoolean("isAdmin")) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO POP (testo, dataInvio, customerID) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                    preparedStatement1.setString(1, testo);
                    preparedStatement1.setTimestamp(2, timestamp);
                    preparedStatement1.setInt(3, resultSet.getInt("customerID"));
                    preparedStatement1.executeUpdate();
                }
            }
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

}
