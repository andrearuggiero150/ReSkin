package com.example.reskin.gestioneInserzioni.controller;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.Entity.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/redirectNuovaInserzioneServlet")
public class redirectNuovaInserzioneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }
        else {
            List<Category> listaCategorie=searchBarDAO.allCategory();
            req.setAttribute("listaCategorie", listaCategorie);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
