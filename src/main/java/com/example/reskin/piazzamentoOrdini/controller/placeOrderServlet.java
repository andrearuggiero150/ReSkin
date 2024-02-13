package com.example.reskin.piazzamentoOrdini.controller;

import com.example.reskin.Entity.Customer;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.piazzamentoOrdini.DAOStorage.PODAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/placeOrderServlet")
public class placeOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("via") == null || req.getParameter("citta") == null || req.getParameter("CAP") == null || req.getParameter("stato") == null || req.getParameter("provincia") == null || !req.getParameter("via").matches("^[a-zA-Z\\s]{1,30}$") || !req.getParameter("stato").matches("^[a-zA-Z\\s]{1,30}$") || !req.getParameter("provincia").matches("^[a-zA-Z\\s]{1,30}$$") || !req.getParameter("citta").matches("^[a-zA-Z\\s]{1,30}$") || !req.getParameter("CAP").matches("^\\d{5}$")) {
            req.setAttribute("addCartSuccess", 4);
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewCartServlet");
            dispatcher.forward(req, resp);
        }
        if(req.getSession().getAttribute("loginStatus") == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
            Customer c = (Customer) req.getSession().getAttribute("customer");
            double totale = (double) req.getSession().getAttribute("totale");
            int i = PODAO.placeOrder(c.getId(), totale, req.getParameter("via"), req.getParameter("CAP"), req.getParameter("citta"), req.getParameter("provincia"), req.getParameter("stato"), new connectionPoolReal());
            req.setAttribute("checkOutSuccess", i);
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewCartServlet");
            dispatcher.forward(req, resp);
        }
    }
}
