package com.example.reskin.ricercaVisualizzazioneProdotto.controller;

import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.Entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/prodottoServlet")
public class prodottoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product prodotto= searchBarDAO.prodottoFromID(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("Prodotto", prodotto);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageProdotto.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
