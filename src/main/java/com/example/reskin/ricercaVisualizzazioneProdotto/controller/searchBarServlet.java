package com.example.reskin.ricercaVisualizzazioneProdotto.controller;

import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO;
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
        doPost(req,resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nomeBarraDiRicerca=req.getParameter("nomeGioco");
        List<String> listaNomi= RVPDAO.searchDropdownProduct(nomeBarraDiRicerca, new connectionPoolReal());
        JsonArray nomiJson=new JsonArray();
        for (String name : listaNomi) {
            nomiJson.add(name);
        }
        resp.setContentType("application/json");
        PrintWriter out=resp.getWriter();
        out.print(nomiJson);
        out.flush();
    }
}
