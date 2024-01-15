package com.example.reskin.autenticazioneGestioneUtenti.EntityStorage;

public class Cliente extends Customer{
    private String piva;

    public Cliente() {
    }

    public Cliente(int id, String nome, String cognome, String email, String password, String piva) {
        super(id, nome, cognome, email, password);
        this.piva = piva;
    }

    public String getPiva() {
        return piva;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }
}
