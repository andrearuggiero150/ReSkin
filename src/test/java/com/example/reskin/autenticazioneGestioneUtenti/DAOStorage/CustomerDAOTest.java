package com.example.reskin.autenticazioneGestioneUtenti.DAOStorage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    @Test
    void loginUtente() {
        assertEquals(-1, CustomerDAO.loginUtente("/*! MYSQL Special SQL */", "ciao"));
    }

    @Test
    void returnCustomerData() {

    }

    @Test
    void registerCliente() {
    }

    @Test
    void registerAdmin() {
    }
}