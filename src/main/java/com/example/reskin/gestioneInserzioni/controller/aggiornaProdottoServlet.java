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
        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        List<Category> listaCategorie = searchBarDAO.allCategory();


        int codiceErrore;
        int idProdotto = Integer.parseInt(req.getParameter("idProdotto"));

        Prodotto prodottoNonAggiornato = searchBarDAO.prodottoFromID(idProdotto);


        if (req.getParameter("Titolo").length() < 5 || req.getParameter("Titolo").length() > 30) {
            codiceErrore = 1;
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;
        }


        if (req.getParameter("Descrizione").length() < 20 || req.getParameter("Descrizione").length() > 150) {
            codiceErrore = 2;
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Lunghezza").isEmpty() || Double.parseDouble(req.getParameter("Lunghezza")) < 1) {
            codiceErrore = 3;
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Larghezza").isEmpty() || Double.parseDouble(req.getParameter("Larghezza")) < 1) {
            codiceErrore = 4;
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Quantità").isEmpty() || Integer.parseInt(req.getParameter("Quantità")) < 1) {
            codiceErrore = 5;
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Prezzo").isEmpty() || Double.parseDouble(req.getParameter("Prezzo")) < 1) {
            codiceErrore = 6;
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        boolean nomePresente = false;

        for (Category category : listaCategorie) {
            if (category.getNome().equals(req.getParameter("CategoriaNome"))) {
                nomePresente = true;
                break;
            }
        }

        if(req.getParameter("Categoria").isEmpty() || !nomePresente ){
            codiceErrore=7;
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoNonAggiornato.getCategoryId());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("prodottoDaModificare", prodottoNonAggiornato);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        else {
            System.out.println("Sono nell'else");
            Prodotto prodottoAggiornato = new Prodotto();
            prodottoAggiornato.setProductID(idProdotto);
            prodottoAggiornato.setNome(req.getParameter("Titolo"));
            prodottoAggiornato.setLunghezza(Double.parseDouble(req.getParameter("Lunghezza")));
            prodottoAggiornato.setLarghezza(Double.parseDouble(req.getParameter("Larghezza")));
            prodottoAggiornato.setCategoryId(Integer.parseInt(req.getParameter("Categoria")));
            prodottoAggiornato.setPrezzo(Double.parseDouble(req.getParameter("Prezzo")));
            prodottoAggiornato.setQuantita(Integer.parseInt(req.getParameter("Quantità")));
            prodottoAggiornato.setDescrizione(req.getParameter("Descrizione"));
            int eseguiAggiornamento = gestisciInserzioniDAO.aggiornaInformazioniProdotto(prodottoAggiornato);
            System.out.println("Esecuzione query aggiornamento: " + eseguiAggiornamento);
            String nomeCategoria = searchBarDAO.getCategoryName(prodottoAggiornato.getCategoryId());


            if (eseguiAggiornamento == 1) {
                List<Prodotto> listaInserzioni = searchBarDAO.allProdotti();
                req.setAttribute("listaProdotti", listaInserzioni);
                req.setAttribute("esitoOperazione", eseguiAggiornamento);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaInserzioni.jsp");
                dispatcher.forward(req, resp);
            } else if (eseguiAggiornamento == 0) {
                req.setAttribute("esitoOperazione", eseguiAggiornamento);
                req.setAttribute("listaCategorie", listaCategorie);
                req.setAttribute("prodottoDaModificare", prodottoAggiornato);
                req.setAttribute("nomeCategoria", nomeCategoria);


                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}