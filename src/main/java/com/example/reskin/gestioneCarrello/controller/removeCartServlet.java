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
@WebServlet("/removeCartServlet")
public class removeCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("cartObjectID") == null || !req.getParameter("cartObjectID").matches("[1-9]\\d*$")) {
            req.setAttribute("addCartSuccess", 4);
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewCartServlet");
            dispatcher.forward(req, resp);
            return;
        }
        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 1) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewCartServlet");
            dispatcher.forward(req, resp);
        }
        else {
            Customer c = (Customer) req.getSession().getAttribute("customer");
            int i = GCDAO.removeCartObject(c.getId(), Integer.parseInt(req.getParameter("cartObjectID")), new connectionPoolReal());
            req.setAttribute("addCartSuccess", i);
            RequestDispatcher dispatcher = req.getRequestDispatcher("viewCartServlet");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
