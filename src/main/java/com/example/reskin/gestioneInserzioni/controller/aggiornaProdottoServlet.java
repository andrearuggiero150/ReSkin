package com.example.reskin.gestioneInserzioni.controller;

import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Category;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;
import com.example.reskin.gestioneInserzioni.DAOStorage.gestisciInserzioniDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/aggiornaProdottoServlet")
public class aggiornaProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }


        List<Category> listaCategorie= searchBarDAO.allCategory();


        int codiceErrore;
        int idProdotto=Integer.parseInt(req.getParameter("idProdotto"));
        String titoloProdotto=req.getParameter("Titolo");
        String nomeCategoriaProdotto=req.getParameter("CategoriaNome");
        int idCategoriaProdotto=Integer.parseInt(req.getParameter("Categoria"));
        String descrizioneProdotto=req.getParameter("Descrizione");


        Prodotto prodottoNonAggiornato=searchBarDAO.prodottoFromID(idProdotto);


        if(titoloProdotto.length() < 5 || titoloProdotto.length() >30){
            codiceErrore=1;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        if(descrizioneProdotto.length() < 20 || descrizioneProdotto.length()> 150){
            codiceErrore=2;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        String verificaLunghezza=req.getParameter("Lunghezza");

        if(verificaLunghezza.isEmpty()){
            codiceErrore=3;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        double lunghezzaProdotto=Double.parseDouble(verificaLunghezza);

        if(lunghezzaProdotto < 1 ){
            codiceErrore=3;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        String verificaLarghezza=req.getParameter("Larghezza");

        if(verificaLarghezza.isEmpty()){
            codiceErrore=4;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        double larghezzaProdotto=Double.parseDouble(verificaLarghezza);


        if(larghezzaProdotto < 1){
            codiceErrore=4;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }


        String verificaIdCategoria=req.getParameter("Categoria");

        if(verificaIdCategoria.isEmpty()){
            codiceErrore=5;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }


        String verificaQuantita=req.getParameter("Quantità");

        if(verificaQuantita.isEmpty()){
            codiceErrore=5;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        int quantitaProdotto=Integer.parseInt(verificaQuantita);


        if(quantitaProdotto < 1){
            codiceErrore=5;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        String verificaPrezzo=req.getParameter("Prezzo");

        if(verificaPrezzo.isEmpty()){
            codiceErrore=6;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        double prezzoProdotto=Double.parseDouble(verificaPrezzo);

        if(prezzoProdotto < 1){
            codiceErrore=6;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }


        boolean nomePresente=false;

        for(Category category: listaCategorie){
            if(category.getNome().equals(nomeCategoriaProdotto)){
                nomePresente=true;
                break;
            }
        }

        if(!nomePresente){
            codiceErrore=7;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }


        Prodotto prodottoAggiornato=new Prodotto();
        prodottoAggiornato.setProductID(idProdotto);
        prodottoAggiornato.setNome(titoloProdotto);
        prodottoAggiornato.setLunghezza(lunghezzaProdotto);
        prodottoAggiornato.setLarghezza(larghezzaProdotto);
        prodottoAggiornato.setCategoryId(idCategoriaProdotto);
        prodottoAggiornato.setPrezzo(prezzoProdotto);
        prodottoAggiornato.setQuantita(quantitaProdotto);
        prodottoAggiornato.setDescrizione(descrizioneProdotto);
        int eseguiAggiornamento= gestisciInserzioniDAO.aggiornaInformazioniProdotto(prodottoAggiornato);
        String nomeCategoria=searchBarDAO.getCategoryName(prodottoAggiornato.getCategoryId());




        if(eseguiAggiornamento==1){
            List<Prodotto> listaInserzioni= searchBarDAO.allProdotti();
            req.setAttribute("listaProdotti", listaInserzioni);
            req.setAttribute("esitoOperazione", eseguiAggiornamento);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/listaInserzioni.jsp");
            dispatcher.forward(req,resp);
        } else if (eseguiAggiornamento == 0) {
            req.setAttribute("esitoOperazione", eseguiAggiornamento);
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);


            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
