package com.example.reskin.ricercaVisualizzazioneProdotti.EntityStorage;

public class Category {

    private int categoryID;
    private String nome;
    private String descrizione;

    public Category(){

    }

    public Category(int categoryID, String nome, String descrizione) {
        this.categoryID = categoryID;
        this.nome = nome;
        this.descrizione = descrizione;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
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
}
