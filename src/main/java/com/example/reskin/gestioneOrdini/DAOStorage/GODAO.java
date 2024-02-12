package com.example.reskin.gestioneOrdini.DAOStorage;

import com.example.reskin.Entity.Order;
import com.example.reskin.Entity.OrderDetails;
import com.example.reskin.connPool.connectionPoolAbstraction;
import com.example.reskin.connPool.connectionPoolReal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GODAO {

    public static List<Order> retriveOrder(connectionPoolAbstraction cpa){
        List<Order> listaOrdini=new ArrayList<>();
        try(Connection connection= cpa.setConnection();){
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT orderID, dataPiazzamento, statusOrder,totale,via,citta,provincia,cap,stato,customerID FROM OrderPlace ORDER BY OrderPlace.datapiazzamento");
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order=new Order();
                order.setId(resultSet.getInt(1));
                order.setData(resultSet.getDate(2));
                order.setStatus(resultSet.getString(3));
                order.setTotale(resultSet.getDouble(4));
                order.setVia(resultSet.getString(5));
                order.setCitta(resultSet.getString(6));
                order.setProvincia(resultSet.getString(7));
                order.setCAP(resultSet.getString(8));
                order.setStato(resultSet.getString(9));
                order.setCustomerId(resultSet.getInt(10));
                order.setListaProdotti(retriveOrderObject(order,new connectionPoolReal()));
                listaOrdini.add(order);
            }
        } catch (SQLException e){
            return null;
        }
        return listaOrdini;
    }

    private static List<OrderDetails> retriveOrderObject(Order ordine, connectionPoolAbstraction cpa){
        List<OrderDetails> orderDetailsList=new ArrayList<>();
        try(Connection connection= cpa.setConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT orderDetailsID, quantita, productID, orderID FROM orderDetails WHERE orderID=?");
            preparedStatement.setInt(1,ordine.getId());
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                OrderDetails orderDetails=new OrderDetails();
                orderDetails.setId(resultSet.getInt(1));
                orderDetails.setQuantita(resultSet.getInt(2));
                orderDetails.setProdottoID(resultSet.getInt(3));
                orderDetails.setOrderID(resultSet.getInt(4));
                orderDetailsList.add(orderDetails);
            }
        } catch (SQLException e){
            return null;
        }
        return orderDetailsList;
    }

    public static int modifyOrderStatus(int idOrdine, String stato, connectionPoolAbstraction cpa){
        try(Connection connection= cpa.setConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE OrderPlace SET OrderPlace.statusOrder=? WHERE OrderPlace.orderID=?");
            preparedStatement.setString(1,stato);
            preparedStatement.setInt(2,idOrdine);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e){
            return 0;
        }
    }

    public static List<Order> retriveCustomerOrder(int customerID, connectionPoolAbstraction cpa){
        List<Order> listaOrdini=new ArrayList<>();
        try(Connection connection= cpa.setConnection();){
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT orderID, dataPiazzamento, statusOrder,totale,via,citta,provincia,cap,stato,customerID FROM OrderPlace WHERE OrderPlace.customerID=? ORDER BY OrderPlace.datapiazzamento");
            preparedStatement.setInt(1,customerID);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order=new Order();
                order.setId(resultSet.getInt(1));
                order.setData(resultSet.getDate(2));
                order.setStatus(resultSet.getString(3));
                order.setTotale(resultSet.getDouble(4));
                order.setVia(resultSet.getString(5));
                order.setCitta(resultSet.getString(6));
                order.setProvincia(resultSet.getString(7));
                order.setCAP(resultSet.getString(8));
                order.setStato(resultSet.getString(9));
                order.setCustomerId(resultSet.getInt(10));
                order.setListaProdotti(retriveOrderObject(order,new connectionPoolReal()));
                listaOrdini.add(order);
            }
        } catch (SQLException e){
            return null;
        }
        return listaOrdini;
    }

}
