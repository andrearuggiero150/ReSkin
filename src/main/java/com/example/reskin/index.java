package com.example.reskin;

import com.example.reskin.Entity.Category;
import com.example.reskin.Entity.Product;
import com.example.reskin.connPool.connectionPoolReal;
import com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;


@WebServlet("/index.html")
public class index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("loginStatus") == null)
            req.getSession().setAttribute("loginStatus", 0);
        List<Product> product= RVPDAO.allProduct(new connectionPoolReal());
        req.setAttribute("listaProdotti", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/interface/pageHome.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
