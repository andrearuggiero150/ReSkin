package com.example.reskin.autenticazioneGestioneUtenti.DAOStorage;

import com.example.reskin.connPool.connectionPoolMock;
import com.example.reskin.connPool.connectionPoolReal;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {
    @Test
    public void testSQLException() throws SQLException {
        assertEquals(-1, CustomerDAO.loginUtente("ciao", "ciao", new connectionPoolMock()));
    }
}