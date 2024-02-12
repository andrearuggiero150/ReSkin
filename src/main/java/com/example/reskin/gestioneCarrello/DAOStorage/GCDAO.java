package com.example.reskin.gestioneCarrello.DAOStorage;

import com.example.reskin.Entity.Cart;
import com.example.reskin.Entity.CartObject;
import com.example.reskin.Entity.Product;
import com.example.reskin.connPool.connectionPoolAbstraction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GCDAO {
    public static Cart viewCart(int customerID, connectionPoolAbstraction cpa) {
        Cart cart = new Cart();
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Product.productID, Product.nome, Product.descrizione, Product.binaryImage, Product.larghezza, Product.lunghezza, Product.quantita, Product.prezzo, Product.categoryID, Cart.cartID, Cart.quantita FROM Cart, Product WHERE customerID=? AND Cart.productID = Product.productID");
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product temp = new Product();
                temp.setProductID(resultSet.getInt(1));
                temp.setNome(resultSet.getString(2));
                temp.setDescrizione(resultSet.getString(3));
                temp.setBinaryImage(resultSet.getBytes(4));
                temp.setLarghezza(resultSet.getDouble(5));
                temp.setLunghezza(resultSet.getDouble(6));
                temp.setQuantita(resultSet.getInt(7));
                temp.setPrezzo(resultSet.getDouble(8));
                temp.setCategoryId(resultSet.getInt(9));
                CartObject o = new CartObject(resultSet.getInt(10) ,temp, resultSet.getInt(11));
                cart.addCarrello(o);
            }
        } catch (SQLException e) {
            System.out.println("ciao");
        }
        if (cart.isCarrelloEmpty())
            return null;
        else
            return cart;
    }

    public static int removeCartObject(int customerID, int cartObjectID, connectionPoolAbstraction cpa) {
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Cart WHERE cartID=?");
            ps.setInt(1, cartObjectID);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next())
                if(resultSet.getInt("customerID") != customerID)
                    return 3;
            PreparedStatement preparedStatement = connection.prepareStatement(" DELETE FROM Cart WHERE cartID = ?");
            preparedStatement.setInt(1, cartObjectID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    public static int modifyCartObject(int customerID, int cartObjectID, int quantity, connectionPoolAbstraction cpa) {
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cart WHERE cartID=?");
            preparedStatement.setInt(1, cartObjectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int i = resultSet.getInt("quantita");
                if(resultSet.getInt("customerID") != customerID)
                    return 3;
                if(quantity > i)
                    return 2;
                    if (i == quantity) {
                    PreparedStatement ps = connection.prepareStatement("DELETE FROM Cart WHERE cartID=?");
                    ps.setInt(1, cartObjectID);
                    ps.executeUpdate();
                } else {
                    int tot = i - quantity;
                    PreparedStatement ps2 = connection.prepareStatement("UPDATE Cart SET quantita = ? WHERE cartID=?");
                    ps2.setInt(1, tot);
                    ps2.setInt(2, cartObjectID);
                    ps2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }

    public static int addCartObject(int customerID, int productID, int quantita, connectionPoolAbstraction cpa) {
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Cart WHERE customerID=? AND productID=?");
            preparedStatement.setInt(1, customerID);
            preparedStatement.setInt(2, productID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                PreparedStatement testQuantita = connection.prepareStatement("SELECT quantita FROM Product WHERE productID=?");
                testQuantita.setInt(1, productID);
                ResultSet resultSet1 = testQuantita.executeQuery();
                if(resultSet1.next())
                    if(resultSet1.getInt("quantita") < quantita)
                        return 2;
                PreparedStatement ps = connection.prepareStatement("INSERT INTO Cart(customerID, productID, quantita) VALUES (?,?,?)");
                ps.setInt(1, customerID);
                ps.setInt(2, productID);
                ps.setInt(3, quantita);
                ps.executeUpdate();
            } else {
                int q = resultSet.getInt("quantita") + quantita;
                PreparedStatement testQuantita = connection.prepareStatement("SELECT quantita FROM Product WHERE productID=?");
                testQuantita.setInt(1, productID);
                ResultSet resultSet1 = testQuantita.executeQuery();
                if(resultSet1.next())
                    if(resultSet1.getInt("quantita") < q)
                        return 2;
                PreparedStatement ps2 = connection.prepareStatement("UPDATE Cart SET quantita = ? WHERE customerID = ? and productID=?");
                ps2.setInt(1, q);
                ps2.setInt(2, customerID);
                ps2.setInt(3, productID);
                ps2.executeUpdate();
            }
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }


}
