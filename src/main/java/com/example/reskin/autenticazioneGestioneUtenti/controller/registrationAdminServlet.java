package com.example.reskin.autenticazioneGestioneUtenti.controller;

import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registrationAdminServlet")
public class registrationAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("email")==null || !req.getParameter("email").matches("^[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]@[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]\\.[a-zA-Z]{1,5}$")) {
            req.setAttribute("registerSuccess", -1);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("password")==null || !req.getParameter("password").matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$")) {
            req.setAttribute("registerSuccess", -2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("nome")==null || !req.getParameter("nome").matches("^.{2,30}$")) {
            req.setAttribute("registerSuccess", -3);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("cognome")==null || !req.getParameter("cognome").matches("^.{2,30}$")) {
            req.setAttribute("registerSuccess", -4);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            req.setAttribute("nome", req.getParameter("nome"));
            req.setAttribute("cognome", req.getParameter("cognome"));
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("password", req.getParameter("password"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageOTP.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
