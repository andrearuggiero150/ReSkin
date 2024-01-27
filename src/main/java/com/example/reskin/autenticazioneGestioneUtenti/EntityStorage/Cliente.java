package com.example.reskin.autenticazioneGestioneUtenti.EntityStorage;
/**
 * Classe che modella l'entità Cliente, specializzazione di Customer
 * @author andrearuggiero
 */
public class Cliente extends Customer{
    /**
     * Campo di tipo <code>String</code> per immagazzinare la partita IVA del Cliente
     */
    private String piva;
    /**
     * Costruttore vuoto
     */
    public Cliente() {
    }
    /**
     * Costruttore completo
     * @param id <code>int</code> - id del Cliente
     * @param nome <code>String</code> - nome del Cliente
     * @param cognome <code>String</code> - cognome del Cliente
     * @param email <code>String</code> - email del Cliente
     * @param password <code>String</code> - password del Cliente
     * @param piva <code>String</code> - partita IVA del Cliente
     */
    public Cliente(int id, String nome, String cognome, String email, String password, String piva) {
        super(id, nome, cognome, email, password);
        this.piva = piva;
    }
    /**
     * Getter della partita IVA
     * restituisce la partita IVA immagazzinata nell'oggetto Cliente chiamante
     * @return <code>String</code>
     */
    public String getPiva() {
        return piva;
    }
    /**
     * Setter della partita IVA
     * inserisce la partita IVA nella variabile partita IVA dell'oggetto Cliente chiamante
     * @param piva <code>String</code>
     */
    public void setPiva(String piva) {
        this.piva = piva;
    }
}
