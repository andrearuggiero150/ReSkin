package com.example.reskin.gestioneInserzioni.DAOStorage;


import com.example.reskin.Entity.Product;
import com.example.reskin.connPool.connectionPoolAbstraction;

import java.sql.*;

public class GIDAO {

    public static int updateProductInformation(Product prodotto, connectionPoolAbstraction cpa){
       try(Connection connection= cpa.setConnection()){
           PreparedStatement preparedStatement=connection.prepareStatement("UPDATE Product SET Product.nome=?, Product.lunghezza=?, Product.larghezza=?, Product.categoryID=?, Product.prezzo=?, Product.quantita=?, Product.descrizione=? WHERE Product.productID=?");
           preparedStatement.setInt(8,prodotto.getProductID());
           preparedStatement.setString(1,prodotto.getNome());
           preparedStatement.setDouble(2,prodotto.getLunghezza());
           preparedStatement.setDouble(3,prodotto.getLarghezza());
           preparedStatement.setInt(4,prodotto.getCategoryId());
           preparedStatement.setDouble(5,prodotto.getPrezzo());
           preparedStatement.setInt(6,prodotto.getQuantita());
           preparedStatement.setString(7,prodotto.getDescrizione());
           preparedStatement.executeUpdate();
           return 1;
       } catch (SQLException e){
            return 0;
       }
    }

    public static int deleteProduct(int idProdotto, connectionPoolAbstraction cpa){
        try(Connection connection=cpa.setConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM Product WHERE ProductID=?");
            preparedStatement.setInt(1,idProdotto);
            preparedStatement.executeUpdate();
          return 1;
        } catch (SQLException e ){
            return 0;
        }
    }

    public static int addProduct(Product nuovoProdotto, connectionPoolAbstraction cpa){
        try(Connection connection=cpa.setConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO Product (nome,descrizione,binaryImage,larghezza,lunghezza,quantita,prezzo,categoryID,color) VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nuovoProdotto.getNome());
            preparedStatement.setString(2,nuovoProdotto.getDescrizione());
            preparedStatement.setBytes(3, nuovoProdotto.getBinaryImage());
            preparedStatement.setDouble(4,nuovoProdotto.getLarghezza());
            preparedStatement.setDouble(5,nuovoProdotto.getLunghezza());
            preparedStatement.setInt(6,nuovoProdotto.getQuantita());
            preparedStatement.setDouble(7,nuovoProdotto.getPrezzo());
            preparedStatement.setInt(8,nuovoProdotto.getCategoryId());
            preparedStatement.setString(9,nuovoProdotto.getColore());

            if (preparedStatement.executeUpdate() != 1) {
                return 0;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return 2;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
