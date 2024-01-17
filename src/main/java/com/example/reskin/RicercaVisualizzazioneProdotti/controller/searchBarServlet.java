package com.example.reskin.RicercaVisualizzazioneProdotti.controller;
import com.example.reskin.RicercaVisualizzazioneProdotti.DAOStorage.searchBarDAO;
import com.google.gson.JsonArray;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/searchBarServlet")
public class searchBarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeBarraDiRicerca=req.getParameter("nomeProdotto");
        List<String> listaNomi=searchBarDAO.ricercaProdottoDropdown(nomeBarraDiRicerca);
        JsonArray nomiJson=new JsonArray();
        for (String name : listaNomi) {
            System.out.println("Nome: " +name);
            nomiJson.add(name);
        }
        resp.setContentType("application/json");
        PrintWriter out=resp.getWriter();
        out.print(nomiJson);
        out.flush();
    }
}
