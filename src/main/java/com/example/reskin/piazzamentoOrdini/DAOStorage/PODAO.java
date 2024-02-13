package com.example.reskin.piazzamentoOrdini.DAOStorage;

import com.example.reskin.connPool.connectionPoolAbstraction;

import java.sql.*;
import java.time.LocalDate;

public class PODAO {
    public static int placeOrder(int customerID, double totale, String via, String CAP, String citta, String provincia, String stato, connectionPoolAbstraction cpa) {
        try (Connection connection = cpa.setConnection()) {
            PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM Cart WHERE customerID=?");
            ps1.setInt(1, customerID);
            ResultSet resultSet = ps1.executeQuery();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            PreparedStatement ps2 = connection.prepareStatement("INSERT INTO OrderPlace (dataPiazzamento,statusOrder,via,citta,provincia,cap,stato,customerID,totale) VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            ps2.setTimestamp(1, timestamp);
            ps2.setString(2, "In elaborazione");
            ps2.setString(3, via);
            ps2.setString(4, citta);
            ps2.setString(5, provincia);
            ps2.setString(6, CAP);
            ps2.setString(7, stato);
            ps2.setInt(8, customerID);
            ps2.setDouble(9, totale);
            int codiceOrdine = ps2.executeUpdate();
            while (resultSet.next()) {
                PreparedStatement ps3 = connection.prepareStatement("INSERT INTO OrderDetails (quantita,productID,orderID) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
                ps3.setInt(1, resultSet.getInt("quantita"));
                ps3.setInt(2, resultSet.getInt("productID"));
                ps3.setInt(3, codiceOrdine);
                ps3.executeUpdate();
                PreparedStatement ps4 = connection.prepareStatement("DELETE FROM Cart WHERE cartID=?");
                ps4.setInt(1, resultSet.getInt("cartID"));
                ps4.executeUpdate();
                PreparedStatement ps5 = connection.prepareStatement("SELECT quantita FROM Product WHERE productID=?");
                ps5.setInt(1, resultSet.getInt("productID"));
                ResultSet resultSet2 = ps5.executeQuery();
                resultSet2.next();
                int quantitaPrima = resultSet2.getInt("quantita");
                PreparedStatement ps6 = connection.prepareStatement("UPDATE Product SET quantita=? WHERE productID=?");
                ps6.setInt(1, quantitaPrima-resultSet.getInt("quantita"));
                ps6.setInt(2, resultSet.getInt("productID"));
                ps6.executeUpdate();
            }
        } catch (SQLException e) {
            return 0;
        }
        return 1;
    }
}
