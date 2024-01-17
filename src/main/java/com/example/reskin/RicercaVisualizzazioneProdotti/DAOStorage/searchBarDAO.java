package com.example.reskin.ricercaVisualizzazioneProdotti.DAOStorage;

import com.example.reskin.connectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class searchBarDAO {

    public static List<String> ricercaProdottoDropdown(String nomeProdotto) {
        List<String> listaNomi = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT nome FROM Product WHERE nome LIKE ?");
            preparedStatement.setString(1, "%" + nomeProdotto + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                listaNomi.add(nome);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaNomi;
    }

}
