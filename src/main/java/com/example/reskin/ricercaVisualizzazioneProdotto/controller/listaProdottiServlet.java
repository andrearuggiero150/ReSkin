package com.example.reskin.ricercaVisualizzazioneProdotto.controller;

import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listaProdottiServlet")
public class listaProdottiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Prodotto> listaProdotti= new ArrayList<>();
        if(req.getParameter("nomeProdotto")!=null){
            List<Integer> lista= searchBarDAO.eseguiRicerca(req.getParameter("nomeProdotto"));
            for (int i=0; i< lista.size();i++){
                listaProdotti.add(searchBarDAO.prodottoFromID(lista.get(i)));
            }
        }
        else {
            listaProdotti=searchBarDAO.allProdotti();
            System.out.println("Quantità prodotti: " +listaProdotti.size());
            for(int i=0; i<listaProdotti.size();i++){
                System.out.println("Nome prodotto: " +listaProdotti.get(i).getNome());
            }
        }
        req.setAttribute("listaProdotti", listaProdotti);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/listaProdotti.jsp");
        dispatcher.forward(req, resp);    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
