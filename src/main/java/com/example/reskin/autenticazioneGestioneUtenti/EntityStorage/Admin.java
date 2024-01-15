package com.example.reskin.autenticazioneGestioneUtenti.EntityStorage;

public class Admin extends Customer{
    public Admin() {
    }

    public Admin(int id, String nome, String cognome, String email, String password) {
        super(id, nome, cognome, email, password);
    }
}
