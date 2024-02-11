package com.example.reskin.Entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Classe astratta che modella l'entità Customer, generalizzazione di Cliente e Admin
 * @author andrearuggiero
 */
public abstract class Customer {
    /**
     * Campo di tipo <code>int</code> per immagazzinare l'id del Customer
     */
    private int id;
    /**
     * Campo di tipo <code>String</code> per immagazzinare il nome del Customer
     */
    private String nome;
    /**
     * Campo di tipo <code>String</code> per immagazzinare il cognome del Customer
     */
    private String cognome;
    /**
     * Campo di tipo <code>String</code> per immagazzinare l'email del Customer
     */
    private String email;
    /**
     * Campo di tipo <code>String</code> per immagazzinare la password del Customer
     */
    private String password;
    /**
     * Costruttore vuoto
     */
    public Customer() {
    }
    /**
     * Costruttore completo
     * @param id <code>int</code> - id del Customer
     * @param nome <code>String</code> - nome del Customer
     * @param cognome <code>String</code> - cognome del Customer
     * @param email <code>String</code> - email del Customer
     * @param password <code>String</code> - password del Customer
     */
    public Customer(int id, String nome, String cognome, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
    }

    /**
     * Getter dell'id
     * restituisce l'id immagazzinato nell'oggetto Customer chiamante
     * @return <code>int</code>
     */
    public int getId() {
        return id;
    }
    /**
     * Setter dell'id
     * inserisce l'id nella variabile id dell'oggetto Customer chiamante
     * @param id <code>int</code>
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter del nome
     * restituisce il nome immagazzinato nell'oggetto Customer chiamante
     * @return <code>String</code>
     */
    public String getNome() {
        return nome;
    }
    /**
     * Setter del nome
     * inserisce il nome nella variabile nome dell'oggetto Customer chiamante
     * @param nome <code>String</code>
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    /**
     * Getter del cognome
     * restituisce il cognome immagazzinato nell'oggetto Customer chiamante
     * @return <code>String</code>
     */
    public String getCognome() {
        return cognome;
    }
    /**
     * Setter del cognome
     * inserisce il cognome nella variabile cognome dell'oggetto Customer chiamante
     * @param cognome <code>String</code>
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    /**
     * Getter dell'email
     * restituisce l'email immagazzinato nell'oggetto Customer chiamante
     * @return <code>String</code>
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter dell'email
     * inserisce l'email nella variabile email dell'oggetto Customer chiamante
     * @param email <code>String</code>
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getter della password
     * restituisce la password immagazzinato nell'oggetto Customer chiamante
     * @return <code>String</code>
     */
    public String getPassword() {
        return password;
    }
    /**
     * Setter della password
     * inserisce la password nella variabile password dell'oggetto Customer chiamante
     * @param password <code>String</code>
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Metodo statico per criptare una stringa
     * @param password <code>String</code>
     * @return <code>String</code> stringa criptata
     */
    public static String cryptPassword(String password) {
        String passwordHash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            passwordHash = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return passwordHash;
    }
}
