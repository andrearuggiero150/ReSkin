package com.example.reskin.gestioneInserzioni.DAOStorage;

import com.example.reskin.connectionPool;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;

import java.sql.*;

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

    public static int aggiungiProdotto(Prodotto nuovoProdotto){
        try(Connection connection=connectionPool.getConnection()){
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

    public static int inserisciImmagine (String nomeFoto){
        try(Connection connection=connectionPool.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO Image (imageFileName) VALUES (?)");
            preparedStatement.setString(1,nomeFoto);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e ){
            return 0;
        }

    }

    public static String getImmagine (int id){
        String file=null;
        try(Connection connection=connectionPool.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("SELECT imageFileName FROM Image WHERE imageId=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                file= resultSet.getString(1);

            }
        } catch (SQLException e ){
            e.printStackTrace();
        }
        return file;


    }

}
