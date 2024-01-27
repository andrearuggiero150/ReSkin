package com.example.reskin.gestioneInserzioni.controller;

import com.example.reskin.gestioneInserzioni.DAOStorage.gestisciInserzioniDAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/eliminaProdottoServlet")
public class eliminaProdottoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        int idProdotto=Integer.parseInt(req.getParameter("idProdotto"));
        int risultatoOperazione= gestisciInserzioniDAO.eliminaProdotto(idProdotto);

        if(risultatoOperazione==1){
            List<Prodotto> listaInserzioni= searchBarDAO.allProdotti();
            req.setAttribute("listaProdotti", listaInserzioni);
            req.setAttribute("risultatoOperazione", risultatoOperazione);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/listaInserzioni.jsp");
            dispatcher.forward(req,resp);
        } else if (risultatoOperazione == 0) {
            List<Prodotto> listaInserzioni= searchBarDAO.allProdotti();
            req.setAttribute("risultatoOperazione", risultatoOperazione);
            req.setAttribute("listaProdotti", listaInserzioni);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/listaInserzioni.jsp");
            dispatcher.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
