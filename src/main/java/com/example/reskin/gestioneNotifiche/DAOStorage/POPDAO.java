package com.example.reskin.gestioneNotifiche.DAOStorage;

import com.example.reskin.connectionPool;
import com.example.reskin.Entity.POP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class POPDAO {
    public static List<POP> clientPOP(int clientID) {
        List<POP> popList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
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

    public static List<POP> adminPOP() {
        List<POP> popList = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
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
}
