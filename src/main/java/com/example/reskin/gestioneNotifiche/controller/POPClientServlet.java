package com.example.reskin.gestioneNotifiche.controller;

import com.example.reskin.Entity.Customer;
import com.example.reskin.gestioneNotifiche.DAOStorage.POPDAO;
import com.example.reskin.Entity.POP;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/POPClientServlet")

public class POPClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 1) {
           req.setAttribute("notAllowed", 1);
           RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
           dispatcher.forward(req, resp);
        }
        else {
            Customer c = (Customer)req.getSession().getAttribute("customer");
            List<POP> listaPOP = POPDAO.clientPOP(c.getId());
            req.setAttribute("listaPOP", listaPOP);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/POPClient.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
