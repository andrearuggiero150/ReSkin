package com.example.reskin.RicercaVisualizzazioneProdotti.EntityStorage;

import java.sql.Blob;

public class Prodotto {

    private String productId;
    private String nome;
    private String desrcizione;
    private Blob binaryImage;
    private double larghezza;
    private double lunghezza;
    private int quantita;
    private double prezzo;
    private int categoryId;



    public Prodotto(){

    }

    public Prodotto(String productId, String nome, String desrcizione, Blob binaryImage, double larghezza, double lunghezza, int quantita, double prezzo, int categoryId) {
        this.productId = productId;
        this.nome = nome;
        this.desrcizione = desrcizione;
        this.binaryImage = binaryImage;
        this.larghezza = larghezza;
        this.lunghezza = lunghezza;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.categoryId = categoryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesrcizione() {
        return desrcizione;
    }

    public void setDesrcizione(String desrcizione) {
        this.desrcizione = desrcizione;
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
