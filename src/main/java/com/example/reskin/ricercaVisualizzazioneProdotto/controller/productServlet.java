package com.example.reskin.ricercaVisualizzazioneProdotto.controller;

import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO;
import com.example.reskin.Entity.Product;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/prodottoServlet")
public class productServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") == null || !req.getParameter("id").matches("^\\d+$") || req.getSession().getAttribute("loginStatus") == null) {
            RequestDispatcher dispatcher=req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        Product prodotto= RVPDAO.productFromID(Integer.parseInt(req.getParameter("id")), new connectionPoolReal());
        req.setAttribute("Prodotto", prodotto);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageProdotto.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
