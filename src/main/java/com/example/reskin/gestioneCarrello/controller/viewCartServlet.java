package com.example.reskin.gestioneCarrello.controller;

import com.example.reskin.Entity.Customer;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.gestioneCarrello.DAOStorage.GCDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/viewCartServlet")
public class viewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 1) {
            req.setAttribute("notAllowed", 1);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
            Customer c = (Customer) req.getSession().getAttribute("customer");
            req.setAttribute("carrello", GCDAO.viewCart(c.getId(), new connectionPoolReal()));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageCart.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
