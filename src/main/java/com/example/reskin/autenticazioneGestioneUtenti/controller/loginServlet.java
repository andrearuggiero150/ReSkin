package com.example.reskin.autenticazioneGestioneUtenti.controller;

import com.example.reskin.autenticazioneGestioneUtenti.DAOStorage.CustomerDAO;
import com.example.reskin.autenticazioneGestioneUtenti.EntityStorage.Customer;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("email")==null || !req.getParameter("email").matches("^[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]@[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]\\.[a-zA-Z]{1,5}$")) {
            req.setAttribute("loginSuccess", -2);
            req.getSession().setAttribute("loginStatus", 0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("password")==null || !req.getParameter("password").matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$")) {
            req.setAttribute("loginSuccess", -3);
            req.getSession().setAttribute("loginStatus", 0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
                int i = CustomerDAO.loginUtente(req.getParameter("email"), req.getParameter("password"));
                if(i == 1) {
                    req.setAttribute("loginSuccess", 1);
                    req.getSession().setAttribute("loginStatus", 1);
                    Customer c = CustomerDAO.returnCustomerData(req.getParameter("email"));
                    if(c == null) {
                        req.setAttribute("loginSuccess", -1);
                        req.getSession().setAttribute("loginStatus", 0);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                        dispatcher.forward(req, resp);
                    }
                    req.getSession().setAttribute("customer", c);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                    dispatcher.forward(req, resp);
                }
                else if(i == 2) {
                    req.setAttribute("loginSuccess", 2);
                    req.getSession().setAttribute("loginStatus", 2);
                    Customer c = CustomerDAO.returnCustomerData(req.getParameter("email"));
                    if(c == null) {
                        req.setAttribute("loginSuccess", -1);
                        req.getSession().setAttribute("loginStatus", 0);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                        dispatcher.forward(req, resp);
                    }
                    req.getSession().setAttribute("customer", c);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                    dispatcher.forward(req, resp);
                }
                else if(i == -1) {
                    req.setAttribute("loginSuccess", -1);
                    req.getSession().setAttribute("loginStatus", 0);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                    dispatcher.forward(req, resp);
                }
                else {
                    req.setAttribute("loginSuccess", 0);
                    req.getSession().setAttribute("loginStatus", 0);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                    dispatcher.forward(req, resp);
                }
        }
    }
}
