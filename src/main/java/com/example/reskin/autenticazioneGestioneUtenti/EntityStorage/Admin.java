package com.example.reskin.autenticazioneGestioneUtenti.EntityStorage;
/**
 * Classe che modella l'entità Admin, specializzazione di Customer
 * @author andrearuggiero
 */
public class Admin extends Customer{
    /**
     * Costruttore vuoto
     */
    public Admin() {
    }
    /**
     * Costruttore completo
     * @param id <code>int</code> - id del Customer
     * @param nome <code>String</code> - nome del Customer
     * @param cognome <code>String</code> - cognome del Customer
     * @param email <code>String</code> - email del Customer
     * @param password <code>String</code> - password del Customer
     */
    public Admin(int id, String nome, String cognome, String email, String password) {
        super(id, nome, cognome, email, password);
    }

}
