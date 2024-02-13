package com.example.reskin.gestioneOrdini.controller;

import com.example.reskin.Entity.Category;
import com.example.reskin.Entity.Customer;
import com.example.reskin.Entity.Order;
import com.example.reskin.Entity.OrderDetails;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.gestioneOrdini.DAOStorage.GODAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewOrderServlet")
public class viewOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") == 0) {
            req.setAttribute("notAllowed", 0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        int tipologiaCustomer=(int) req.getSession().getAttribute("loginStatus");
        RequestDispatcher dispatcher;

        if(tipologiaCustomer== 2) {
            List<Order> orderList= GODAO.retriveOrder(new connectionPoolReal());
            req.setAttribute("listaOrdini", orderList);
            dispatcher= req.getRequestDispatcher("WEB-INF/interface/pageVisualizzaOrdiniAdmin.jsp");
            dispatcher.forward(req,resp);
        } else if (tipologiaCustomer == 1) {
            Customer customer= (Customer) req.getSession().getAttribute("customer");
            List<Order> orderList=GODAO.retriveCustomerOrder(customer.getId(),new connectionPoolReal());
            req.setAttribute("listaOrdini", orderList);
            dispatcher= req.getRequestDispatcher("WEB-INF/interface/pageVisualizzaOrdiniClient.jsp");
            dispatcher.forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
