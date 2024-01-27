package com.example.reskin.gestioneInserzioni.controller;

import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Category;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/modificaInserzioneServlet")
public class modificaInserzioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
            Prodotto prodottoDaModificare;
            int id=Integer.parseInt(req.getParameter("idProdotto"));
            prodottoDaModificare= searchBarDAO.prodottoFromID(id);
            String nomeCategoria=searchBarDAO.getCategoryName(prodottoDaModificare.getCategoryId());
            List<Category> listaCategorie=searchBarDAO.allCategory();
            req.setAttribute("prodottoDaModificare", prodottoDaModificare);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("listaCategorie", listaCategorie);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
