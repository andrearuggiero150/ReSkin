package com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage;

import com.example.reskin.connectionPool;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static List<Integer> eseguiRicerca(String nomeProdotto) {
        List<Integer> listaID = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT productID FROM Product WHERE nome LIKE ?");
            preparedStatement.setString(1, "%" + nomeProdotto + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaID.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaID;

    }

    public static Prodotto prodottoFromID(int id) {
        Prodotto prodotto = new Prodotto();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE productID=? and Product.categoryID = Category.categoryID");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prodotto.setProductID(id);
                prodotto.setNome(resultSet.getString(1));
                prodotto.setDescrizione(resultSet.getString(2));
                prodotto.setBinaryImage(resultSet.getBlob(3));
                prodotto.setLarghezza(resultSet.getDouble(4));
                prodotto.setLunghezza(resultSet.getDouble(5));
                prodotto.setQuantita(resultSet.getInt(6));
                prodotto.setPrezzo(resultSet.getDouble(7));
                prodotto.setCategoryId(resultSet.getInt(8));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotto;
    }

    public static List<Prodotto> allProdotti() {
        List<Prodotto> prodotti = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Product.productID, Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE Category.categoryID=Product.categoryID ORDER BY Product.productID");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Prodotto prodotto = new Prodotto();
                prodotto.setProductID(resultSet.getInt(1));
                prodotto.setNome(resultSet.getString(2));
                prodotto.setDescrizione(resultSet.getString(3));
                prodotto.setBinaryImage(resultSet.getBlob(4));
                prodotto.setLarghezza(resultSet.getDouble(5));
                prodotto.setLunghezza(resultSet.getDouble(6));
                prodotto.setQuantita(resultSet.getInt(7));
                prodotto.setPrezzo(resultSet.getDouble(8));
                prodotto.setCategoryId(resultSet.getInt(9));
                prodotti.add(prodotto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }
}
