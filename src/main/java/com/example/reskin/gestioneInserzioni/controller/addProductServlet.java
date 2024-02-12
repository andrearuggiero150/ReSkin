package com.example.reskin.gestioneInserzioni.controller;
import com.example.reskin.Entity.Product;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.gestioneInserzioni.DAOStorage.GIDAO;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO;
import com.example.reskin.Entity.Category;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@MultipartConfig
@WebServlet("/addProductServlet")
public class addProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }

        List<Category> listaCategorie= RVPDAO.allCategory(new connectionPoolReal());
        req.setAttribute("listaCategorie", listaCategorie);
        RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("loginStatus") == null || (int) req.getSession().getAttribute("loginStatus") != 2) {
            req.setAttribute("notAllowed", 2);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.forward(req, resp);
        }


        List<Category> listaCategorie = RVPDAO.allCategory(new connectionPoolReal());


        int codiceErrore;


        if (req.getParameter("Titolo").length() < 5 || req.getParameter("Titolo").length() > 30) {
            codiceErrore = 1;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;
        }


        if (req.getParameter("Descrizione").length() < 20 || req.getParameter("Descrizione").length() > 150) {
            codiceErrore = 2;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;
        }


        if (req.getParameter("Lunghezza").isEmpty() || Double.parseDouble(req.getParameter("Lunghezza")) < 1) {
            codiceErrore = 3;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Larghezza").isEmpty() || Double.parseDouble(req.getParameter("Larghezza")) < 1) {
            codiceErrore = 4;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Quantità").isEmpty() || Integer.parseInt(req.getParameter("Quantità")) < 1) {
            codiceErrore = 5;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        if (req.getParameter("Prezzo").isEmpty() || Double.parseDouble(req.getParameter("Prezzo")) < 1) {
            codiceErrore = 6;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;

        }


        boolean nomePresente = false;

        for (Category category : listaCategorie) {
            if (category.getNome().equals(req.getParameter("CategoriaNome"))) {
                nomePresente = true;
                break;
            }
        }

        if(req.getParameter("Categoria").isEmpty() || !nomePresente ){
            codiceErrore=7;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher=req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req,resp);
        }

        System.out.println("Tipo file: " +req.getPart("CaricaFoto").getContentType());

        if(!req.getPart("CaricaFoto").getContentType().equals("image/jpeg")){
            codiceErrore = 8;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        if (req.getParameter("Colore").length() < 3 || req.getParameter("Colore").length() > 20) {
            codiceErrore = 9;
            req.setAttribute("listaCategorie", listaCategorie);
            req.setAttribute("codiceErrore", codiceErrore);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
            dispatcher.forward(req, resp);
            return;
        } else {

            Part file = req.getPart("CaricaFoto");
            InputStream inputStream = file.getInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);

            Product nuovoProdotto=new Product();
            nuovoProdotto.setNome(req.getParameter("Titolo"));
            nuovoProdotto.setDescrizione(req.getParameter("Descrizione"));
            nuovoProdotto.setBinaryImage(data);
            nuovoProdotto.setLarghezza(Double.parseDouble(req.getParameter("Larghezza")));
            nuovoProdotto.setLunghezza(Double.parseDouble(req.getParameter("Lunghezza")));
            nuovoProdotto.setQuantita(Integer.parseInt(req.getParameter("Quantità")));
            nuovoProdotto.setPrezzo(Double.parseDouble(req.getParameter("Prezzo")));
            nuovoProdotto.setCategoryId(Integer.parseInt(req.getParameter("Categoria")));
            nuovoProdotto.setColore(req.getParameter("Colore"));

            int aggiungiNuovoProdotto= GIDAO.addProduct(nuovoProdotto, new connectionPoolReal());


            if (aggiungiNuovoProdotto == 1) {
                List<Product> listaInserzioni = RVPDAO.allProduct(new connectionPoolReal());
                req.setAttribute("listaProdotti", listaInserzioni);
                req.setAttribute("esitoOperazione", aggiungiNuovoProdotto);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/listaInserzioni.jsp");
                dispatcher.forward(req, resp);

            } else if (aggiungiNuovoProdotto == 0 || aggiungiNuovoProdotto==2) {
                req.setAttribute("esitoOperazione", aggiungiNuovoProdotto);
                req.setAttribute("listaCategorie", listaCategorie);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageAggiungiNuovaInserzione.jsp");
                dispatcher.forward(req, resp);
            }
        }
    }
}
