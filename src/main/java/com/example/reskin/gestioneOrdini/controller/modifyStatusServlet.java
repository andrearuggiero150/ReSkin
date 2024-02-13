package com.example.reskin.gestioneOrdini.controller;

import com.example.reskin.Entity.Order;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.gestioneOrdini.DAOStorage.GODAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/modifyStatusServlet")
public class modifyStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        String statusOrdine=req.getParameter("StatusNome");
        int idOrdine=Integer.parseInt(req.getParameter("idOrdine"));

        List<String> listaStatus=new ArrayList<>();
        listaStatus.add("Completo");
        listaStatus.add("In transito");
        listaStatus.add("Pagato");
        listaStatus.add("Da pagare");
        boolean nomePresente=false;

        for(String nomi: listaStatus){
            if(nomi.equals(statusOrdine)){
                nomePresente=true;
                break;
            }
        }



        if(statusOrdine.isEmpty() || !nomePresente){
            req.setAttribute("esitoModifica", 0);
            List<Order> orderList= GODAO.retriveOrder(new connectionPoolReal());
            req.setAttribute("listaOrdini", orderList);
            RequestDispatcher dispatcher= req.getRequestDispatcher("WEB-INF/interface/pageVisualizzaOrdiniAdmin.jsp");
            dispatcher.forward(req,resp);
            return;
        }

        int updateStatus= GODAO.modifyOrderStatus(idOrdine,statusOrdine, new connectionPoolReal());

        if(updateStatus==1 && nomePresente){
            req.setAttribute("esitoModifica", updateStatus);
            List<Order> orderList= GODAO.retriveOrder(new connectionPoolReal());
            req.setAttribute("listaOrdini", orderList);
            RequestDispatcher dispatcher= req.getRequestDispatcher("WEB-INF/interface/pageVisualizzaOrdiniAdmin.jsp");
            dispatcher.forward(req,resp);
        } else if (updateStatus == 0 || !nomePresente) {
            req.setAttribute("esitoModifica", updateStatus);
            List<Order> orderList= GODAO.retriveOrder(new connectionPoolReal());
            req.setAttribute("listaOrdini", orderList);
            RequestDispatcher dispatcher= req.getRequestDispatcher("WEB-INF/interface/pageVisualizzaOrdiniAdmin.jsp");
            dispatcher.forward(req,resp);
        }
    }
}
