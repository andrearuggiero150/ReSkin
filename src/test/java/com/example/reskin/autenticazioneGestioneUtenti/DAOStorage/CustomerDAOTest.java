package com.example.reskin.autenticazioneGestioneUtenti.DAOStorage;

import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Admin;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Cliente;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Customer;
import com.example.reskin.connPool.connectionPoolMock;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.connectionPool;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    @Test
    void loginUtente() {
        assertEquals(-1, CustomerDAO.loginUtente("ciao", "ciao", new connectionPoolMock()));
        assertEquals(0, CustomerDAO.loginUtente("ciao@ciao.com", "Ciao000$", new connectionPoolReal()));
        assertEquals(1, CustomerDAO.loginUtente("mariorossi@gmail.com", "Mariorossi1$", new connectionPoolReal()));
        assertEquals(2, CustomerDAO.loginUtente("admin@admin.com", "Admin111@", new connectionPoolReal()));
    }

    @Test
    void returnCustomerData() {
        assertNull(CustomerDAO.returnCustomerData("ciao", new connectionPoolMock()));
        assertInstanceOf(Cliente.class, CustomerDAO.returnCustomerData("mariorossi@gmail.com", new connectionPoolReal()));
        assertInstanceOf(Admin.class, CustomerDAO.returnCustomerData("admin@admin.com", new connectionPoolReal()));
        assertNull(CustomerDAO.returnCustomerData("ciao@ciao.com", new connectionPoolReal()));
    }

    @Test
    void registerCliente() {
        Cliente c1 = new Cliente(1, "Antonio", "Rossi", "mariorossi@gmail.com", "ciao", "12312312311");
        assertEquals(2, CustomerDAO.registerCliente(c1, new connectionPoolReal()));
        Cliente c2 = new Cliente(1, "Antonio", "Rossi", "marionuovo@gmail.com", "ciao", "85968563851");
        assertEquals(2, CustomerDAO.registerCliente(c2, new connectionPoolReal()));
        Cliente c3 = new Cliente(1, "Antonio", "Rossi", "admin@admin.com", "ciao", "85968563851");
        assertEquals(2, CustomerDAO.registerCliente(c3, new connectionPoolReal()));
        assertEquals(0, CustomerDAO.registerCliente(c3, new connectionPoolMock()));
        Cliente c4 = new Cliente(1, "Antonio", "Rossi", "clientenuovo@admin.com", "ciao", "12311122233");
        assertEquals(1, CustomerDAO.registerCliente(c4, new connectionPoolReal()));
        try(Connection connection= new connectionPoolReal().setConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM Customer WHERE email=?");
            preparedStatement.setString(1,"clientenuovo@admin.com");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void registerAdmin() {
        Admin a1 = new Admin(1, "Antonio", "Rossi", "mariorossi@gmail.com", "ciao");
        assertEquals(2, CustomerDAO.registerAdmin(a1, new connectionPoolReal()));
        Admin a2 = new Admin(1, "Antonio", "Rossi", "admin@admin.com", "ciao");
        assertEquals(2, CustomerDAO.registerAdmin(a2, new connectionPoolReal()));
        assertEquals(0, CustomerDAO.registerAdmin(a2, new connectionPoolMock()));
        Admin a3 = new Admin(1, "Antonio", "Rossi", "adminnuovo@admin.com", "ciao");
        assertEquals(1, CustomerDAO.registerAdmin(a3, new connectionPoolReal()));
        try(Connection connection= new connectionPoolReal().setConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement("DELETE FROM Customer WHERE email=?");
            preparedStatement.setString(1,"adminnuovo@admin.com");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}