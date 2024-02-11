package com.example.reskin.ricercaVisualizzazioneProdotto.controller;

import com.example.reskin.Entity.Product;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.searchBarDAO;
import com.example.reskin.Entity.Category;
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

        List<Product> listaProdotti = new ArrayList<>();
        List<Category> listaCategorie = searchBarDAO.allCategory();
        String categoriaSelezionata = req.getParameter("Categoria");
        String disponibilitaSelezionata = req.getParameter("Disponibilità");
        System.out.println("Disponibilità selezionata: "+disponibilitaSelezionata);


        if (req.getParameter("nomeProdotto") != null) {
            List<Integer> lista = searchBarDAO.eseguiRicerca(req.getParameter("nomeProdotto"));

            for (Integer integer : lista) {
                listaProdotti.add(searchBarDAO.prodottoFromID(integer));
            }

            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaProdotti.jsp");
            dispatcher.forward(req, resp);

        } else if (categoriaSelezionata != null && !categoriaSelezionata.isEmpty()) {
            int idCategoria = Integer.parseInt(categoriaSelezionata);
            listaProdotti = searchBarDAO.productFilteredByCategory(idCategoria);
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaProdotti.jsp");
            dispatcher.forward(req, resp);

        }else if (disponibilitaSelezionata != null && disponibilitaSelezionata.equals("Non disponibile")) {
            listaProdotti = searchBarDAO.productFilteredByDisponibility(0);
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaProdotti.jsp");
            dispatcher.forward(req, resp);

        } else if (disponibilitaSelezionata != null && disponibilitaSelezionata.equals("Disponibile")) {
            listaProdotti = searchBarDAO.productFilteredByDisponibility(1);
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaProdotti.jsp");
            dispatcher.forward(req, resp);
        }

        else {
            listaProdotti = searchBarDAO.allProdotti();
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaProdotti.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
