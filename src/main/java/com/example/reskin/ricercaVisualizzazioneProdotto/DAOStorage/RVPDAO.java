package com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage;

import com.example.reskin.Entity.Product;
import com.example.reskin.connPool.connectionPoolAbstraction;
import com.example.reskin.Entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RVPDAO {
    public static List<String> searchDropdownProduct(String nomeProdotto, connectionPoolAbstraction cpa) {
        List<String> listaNomi = new ArrayList<>();
        try {
            Connection connection = cpa.setConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT nome FROM Product WHERE nome LIKE ?");
            preparedStatement.setString(1, "%" + nomeProdotto + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                listaNomi.add(nome);
            }
        } catch (SQLException e) {
            return null;
        }
        return listaNomi;
    }

    public static List<Integer> getSearch(String nomeProdotto, connectionPoolAbstraction cpa) {
        List<Integer> listaID = new ArrayList<>();
        try (Connection connection = cpa.setConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT productID FROM Product WHERE nome LIKE ?");
            preparedStatement.setString(1, "%" + nomeProdotto + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaID.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            return null;
        }
        return listaID;

    }

    public static Product productFromID(int id, connectionPoolAbstraction cpa) {
        Product prodotto = new Product();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE productID=? and Product.categoryID = Category.categoryID");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prodotto.setProductID(id);
                prodotto.setNome(resultSet.getString(1));
                prodotto.setDescrizione(resultSet.getString(2));
                prodotto.setBinaryImage(resultSet.getBytes(3));
                prodotto.setLarghezza(resultSet.getDouble(4));
                prodotto.setLunghezza(resultSet.getDouble(5));
                prodotto.setQuantita(resultSet.getInt(6));
                prodotto.setPrezzo(resultSet.getDouble(7));
                prodotto.setCategoryId(resultSet.getInt(8));
            }
        } catch (SQLException e) {
            return null;
        }
        return prodotto;
    }

    public static List<Product> allProduct(connectionPoolAbstraction cpa) {
        List<Product> prodotti = new ArrayList<>();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Product.productID, Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE Category.categoryID=Product.categoryID ORDER BY Product.productID");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product prodotto = new Product();
                prodotto.setProductID(resultSet.getInt(1));
                prodotto.setNome(resultSet.getString(2));
                prodotto.setDescrizione(resultSet.getString(3));
                prodotto.setBinaryImage(resultSet.getBytes(4));
                prodotto.setLarghezza(resultSet.getDouble(5));
                prodotto.setLunghezza(resultSet.getDouble(6));
                prodotto.setQuantita(resultSet.getInt(7));
                prodotto.setPrezzo(resultSet.getDouble(8));
                prodotto.setCategoryId(resultSet.getInt(9));
                prodotti.add(prodotto);
            }
        } catch (SQLException e) {
            return null;
        }
        return prodotti;
    }

    public static List<Category> allCategory(connectionPoolAbstraction cpa) {
        List<Category> categorie = new ArrayList<>();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Category.categoryID, Category.nome, Category.descrizione FROM Category ORDER BY Category.categoryID");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category categoria = new Category();
                categoria.setCategoryID(resultSet.getInt(1));
                categoria.setNome(resultSet.getString(2));
                categoria.setDescrizione(resultSet.getString(3));
                categorie.add(categoria);
            }
        } catch (SQLException e) {
            return null;
        }
        return categorie;
    }


    public static String getCategoryName(int id, connectionPoolAbstraction cpa) {
        String nomeCategoria = null;
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT nome FROM Category WHERE Category.categoryID=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nomeCategoria = resultSet.getString(1);
            }
        } catch (SQLException e) {

            return null;
        }
        return nomeCategoria;
    }

    public static List<Product> productFilteredByCategory(int idCategoria, connectionPoolAbstraction cpa) {
        List<Product> prodottiFiltrati = new ArrayList<>();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Product.productID, Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE Product.categoryID=? AND Category.categoryID=Product.categoryID ORDER BY Product.productID");
            preparedStatement.setInt(1, idCategoria);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product prodotto = new Product();
                prodotto.setProductID(resultSet.getInt(1));
                prodotto.setNome(resultSet.getString(2));
                prodotto.setDescrizione(resultSet.getString(3));
                prodotto.setBinaryImage(resultSet.getBytes(4));
                prodotto.setLarghezza(resultSet.getDouble(5));
                prodotto.setLunghezza(resultSet.getDouble(6));
                prodotto.setQuantita(resultSet.getInt(7));
                prodotto.setPrezzo(resultSet.getDouble(8));
                prodotto.setCategoryId(resultSet.getInt(9));
                prodottiFiltrati.add(prodotto);
            }
        } catch (SQLException e) {
            return null;
        }
        return prodottiFiltrati;
    }

    public static List<Product> productFilteredByDisponibility(int quantita, connectionPoolAbstraction cpa) {
        List<Product> prodottiFiltrati = new ArrayList<>();
        String query = null;
        try (Connection connection = cpa.setConnection()) {
            if (quantita > 0) {
                query = "SELECT Product.productID, Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE Product.quantita>=? AND Category.categoryID=Product.categoryID ORDER BY Product.productID";

            } else if (quantita == 0) {
                query = "SELECT Product.productID, Product.nome, Product.descrizione, Product.binaryImage,Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID  FROM Product, Category WHERE Product.quantita=? AND Category.categoryID=Product.categoryID ORDER BY Product.productID";
            }

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, quantita);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product prodotto = new Product();
                prodotto.setProductID(resultSet.getInt(1));
                prodotto.setNome(resultSet.getString(2));
                prodotto.setDescrizione(resultSet.getString(3));
                prodotto.setBinaryImage(resultSet.getBytes(4));
                prodotto.setLarghezza(resultSet.getDouble(5));
                prodotto.setLunghezza(resultSet.getDouble(6));
                prodotto.setQuantita(resultSet.getInt(7));
                prodotto.setPrezzo(resultSet.getDouble(8));
                prodotto.setCategoryId(resultSet.getInt(9));
                prodottiFiltrati.add(prodotto);
            }
        } catch (SQLException e) {
            return null;
        }
        return prodottiFiltrati;
    }
}
