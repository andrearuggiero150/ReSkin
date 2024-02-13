package com.example.reskin.gestioneNotifiche.controller;

import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.gestioneNotifiche.DAOStorage.GNDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/sendPOPServlet")
public class sendPOPServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        if(req.getParameter("opzione").equals("abilita")) {
            if(req.getParameter("email") == null || !req.getParameter("email").matches("^[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]@[a-zA-Z0-9.-]{1,29}[a-zA-Z0-9]\\.[a-zA-Z]{1,5}$") || req.getParameter("testo") == null || !req.getParameter("testo").matches("^.{1,150}$")) {
                req.setAttribute("POPinvalido", 4);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/POPAdminServlet");
                dispatcher.forward(req, resp);
            }
            else {
                int i = GNDAO.sendIndividualPOP(req.getParameter("email"), req.getParameter("testo"), new connectionPoolReal());
                req.setAttribute("POPinvalido", i);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/POPAdminServlet");
                dispatcher.forward(req, resp);
            }
        }
        if(req.getParameter("opzione").equals("disabilita")) {
            if(req.getParameter("testo") == null || !req.getParameter("testo").matches("^.{1,150}$")) {
                req.setAttribute("POPinvalido", 4);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/POPAdminServlet");
                dispatcher.forward(req, resp);
            }
            else {
                int i = GNDAO.sendBroadcastPOP(req.getParameter("testo"), new connectionPoolReal());
                req.setAttribute("POPinvalido", i);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/POPAdminServlet");
                dispatcher.forward(req, resp);
            }
        }
        req.setAttribute("POPinvalido", 4);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/POPAdminServlet");
        dispatcher.forward(req, resp);
    }
}
