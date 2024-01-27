package com.example.reskin.gestioneInserzioni.DAOStorage;

import com.example.reskin.connectionPool;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gestisciInserzioniDAO {

    public static int aggiornaInformazioniProdotto(Prodotto prodotto){
       try(Connection connection= connectionPool.getConnection()){
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

    public static int eliminaProdotto(int idProdotto){
        try(Connection connection=connectionPool.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM Product WHERE ProductID=?");
            preparedStatement.setInt(1,idProdotto);
            preparedStatement.executeUpdate();
          return 1;
        } catch (SQLException e ){
            return 0;
        }
    }

}
