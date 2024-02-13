package com.example.reskin.ricercaVisualizzazioneProdotto.controller;

import com.example.reskin.Entity.Product;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listaProdottiServlet")
public class productListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Product> listaProdotti = new ArrayList<>();
        List<Category> listaCategorie = RVPDAO.allCategory(new connectionPoolReal());
        String categoriaSelezionata = req.getParameter("Categoria");
        String disponibilitaSelezionata = req.getParameter("Disponibilità");
        System.out.println("Disponibilità selezionata: "+disponibilitaSelezionata);
        if(req.getSession().getAttribute("loginStatus") == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        if (req.getParameter("nomeProdotto") != null) {
            List<Integer> lista = RVPDAO.getSearch(req.getParameter("nomeProdotto"), new connectionPoolReal());

            for (Integer integer : lista) {
                listaProdotti.add(RVPDAO.productFromID(integer, new connectionPoolReal()));
            }

            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageListaProdotti.jsp");
            dispatcher.forward(req, resp);

        } else if (categoriaSelezionata != null && !categoriaSelezionata.isEmpty()) {
            int idCategoria = Integer.parseInt(categoriaSelezionata);
            listaProdotti = RVPDAO.productFilteredByCategory(idCategoria, new connectionPoolReal());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageListaProdotti.jsp");
            dispatcher.forward(req, resp);

        }else if (disponibilitaSelezionata != null && disponibilitaSelezionata.equals("Non disponibile")) {
            listaProdotti = RVPDAO.productFilteredByDisponibility(0, new connectionPoolReal());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageListaProdotti.jsp");
            dispatcher.forward(req, resp);

        } else if (disponibilitaSelezionata != null && disponibilitaSelezionata.equals("Disponibile")) {
            listaProdotti = RVPDAO.productFilteredByDisponibility(1, new connectionPoolReal());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageListaProdotti.jsp");
            dispatcher.forward(req, resp);
        }

        else {
            listaProdotti = RVPDAO.allProduct(new connectionPoolReal());
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("listaProdotti", listaProdotti);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageListaProdotti.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
