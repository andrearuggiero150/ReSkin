package com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage;

import java.sql.Blob;

public class Prodotto {
    private String nome;
    private String descrizione;
    private Blob binaryImage;
    private double larghezza;
    private double lunghezza;
    private int quantita;
    private double prezzo;
    private int categoryId;
    private int productID;

    public Prodotto(){
    }

    public Prodotto(int productID, String nome, String descrizione, Blob binaryImage, double larghezza, double lunghezza, int quantita, double prezzo, int categoryId) {
        this.productID = productID;
        this.nome = nome;
        this.descrizione = descrizione;
        this.binaryImage = binaryImage;
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.categoryId = categoryId;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Blob getBinaryImage() {
        return binaryImage;
    }

    public void setBinaryImage(Blob binaryImage) {
        this.binaryImage = binaryImage;
    }

    public double getLarghezza() {
        return larghezza;
    }

    public void setLarghezza(double larghezza) {
        this.larghezza = larghezza;
    }

    public double getLunghezza() {
        return lunghezza;
    }

    public void setLunghezza(double lunghezza) {
        this.lunghezza = lunghezza;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
