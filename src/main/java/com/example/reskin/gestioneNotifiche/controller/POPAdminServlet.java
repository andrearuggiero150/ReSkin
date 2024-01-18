package com.example.reskin.gestioneNotifiche.controller;

import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Customer;
import com.example.reskin.gestioneNotifiche.DAOStorage.POPDAO;
import com.example.reskin.gestioneNotifiche.EntityStorage.POP;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/POPAdminServlet")
public class POPAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
            List<POP> listaPOP = POPDAO.adminPOP();
            req.setAttribute("listaPOP", listaPOP);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/POPAdmin.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
