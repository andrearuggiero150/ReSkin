package com.example.reskin.gestioneInserzioni.controller;

import com.example.reskin.Entity.Product;
import com.example.reskin.connPool.connectionPoolMock;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO;
import com.example.reskin.Entity.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/modificaInserzioneServlet")
public class modificaInserzioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
            Product prodottoDaModificare;
            int id=Integer.parseInt(req.getParameter("idProdotto"));
            System.out.println("ID Prodotto: "+id);
            prodottoDaModificare= RVPDAO.productFromID(id, new connectionPoolReal());
            String nomeCategoria= RVPDAO.getCategoryName(prodottoDaModificare.getCategoryId(), new connectionPoolReal());
            List<Category> listaCategorie= RVPDAO.allCategory(new connectionPoolReal());
            req.setAttribute("prodottoDaModificare", prodottoDaModificare);
            req.setAttribute("nomeCategoria", nomeCategoria);
            req.setAttribute("listaCategorie", listaCategorie);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageModificaInserzione.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
