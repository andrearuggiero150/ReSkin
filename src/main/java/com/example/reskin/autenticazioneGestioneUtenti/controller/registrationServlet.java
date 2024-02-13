package com.example.reskin.autenticazioneGestioneUtenti.controller;

import com.example.reskin.autenticazioneGestioneUtenti.DAOStorage.AGUDAO;
import com.example.reskin.Entity.Cliente;
import com.example.reskin.connPool.connectionPoolReal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registrationServlet")
public class registrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("email")==null || !req.getParameter("email").matches("^[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]@[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]\\.[a-zA-Z]{1,5}$")) {
            req.setAttribute("registerSuccess", -1);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("password")==null || !req.getParameter("password").matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$")) {
            req.setAttribute("registerSuccess", -2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("nome")==null || !req.getParameter("nome").matches("^.{2,30}$")) {
            req.setAttribute("registerSuccess", -3);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("cognome")==null || !req.getParameter("cognome").matches("^.{2,30}$")) {
            req.setAttribute("registerSuccess", -4);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("PIVA")==null || !req.getParameter("PIVA").matches("^\\d{11}$")) {
            req.setAttribute("registerSuccess", -5);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
            dispatcher.forward(req, resp);
        }
        else {
            Cliente c = new Cliente();
            c.setNome(req.getParameter("nome"));
            c.setCognome(req.getParameter("cognome"));
            c.setPiva(req.getParameter("PIVA"));
            c.setEmail(req.getParameter("email"));
            c.setPassword(req.getParameter("password"));
            int i = AGUDAO.registerCliente(c, new connectionPoolReal());
            if(i == 1) {
                req.setAttribute("registerSuccess", 1);
                RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                dispatcher.forward(req, resp);
            }
            if(i == 2) {
                req.setAttribute("registerSuccess", 2);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
                dispatcher.forward(req, resp);
            }
            if(i == 0) {
                req.setAttribute("registerSuccess", 0);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageRegistration.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
