package com.example.reskin.autenticazioneGestioneUtenti.controller;

import com.example.reskin.autenticazioneGestioneUtenti.DAOStorage.CustomerDAO;
import com.example.reskin.Entity.Admin;
import com.example.reskin.connPool.connectionPoolReal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

@WebServlet("/registrationOTPServlet")
public class registrationOTPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("otp")==null || !req.getParameter("otp").matches("^\\d{8}$")) {
            req.setAttribute("registerSuccess", -1);
            req.setAttribute("nome", req.getParameter("nome"));
            req.setAttribute("cognome", req.getParameter("cognome"));
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("password", req.getParameter("password"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageOTP.jsp");
            dispatcher.forward(req, resp);
        }
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
            try {
                Socket socket = new Socket("localhost", 7878);

                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();

                ObjectOutputStream oos = new ObjectOutputStream(os);
                ObjectInputStream ois = new ObjectInputStream(is);

                String stringa = req.getParameter("otp");

                oos.writeObject(stringa);
                boolean bool = (boolean) ois.readObject();

                if(bool) {
                    Admin a = new Admin();
                    a.setNome(req.getParameter("nome"));
                    a.setCognome(req.getParameter("cognome"));
                    a.setEmail(req.getParameter("email"));
                    a.setPassword(req.getParameter("password"));
                    int i = CustomerDAO.registerAdmin(a, new connectionPoolReal());
                    if (i == 1) {
                        req.setAttribute("registerSuccess", 1);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
                        dispatcher.forward(req, resp);
                    }
                    if (i == 2) {
                        req.setAttribute("registerSuccess", 2);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
                        dispatcher.forward(req, resp);
                    }
                    if (i == 0) {
                        req.setAttribute("registerSuccess", 0);
                        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
                        dispatcher.forward(req, resp);
                    }
                }
                else {
                    req.setAttribute("registerSuccess", -2);
                    req.setAttribute("nome", req.getParameter("nome"));
                    req.setAttribute("cognome", req.getParameter("cognome"));
                    req.setAttribute("email", req.getParameter("email"));
                    req.setAttribute("password", req.getParameter("password"));
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageOTP.jsp");
                    dispatcher.forward(req, resp);
                }
                socket.close();
            } catch (ConnectException ce) {
                req.setAttribute("registerSuccess", -5);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
                dispatcher.forward(req, resp);
            } catch (ClassNotFoundException e) {
                req.setAttribute("registerSuccess", 0);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/registrationAdmin.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
