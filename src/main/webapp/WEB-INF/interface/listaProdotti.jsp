<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: giovi
  Date: 19/01/2024
  Time: 13:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%ArrayList<Prodotto> listaProdoto = (ArrayList<Prodotto>) request.getAttribute("listaProdotti");%>
    <title>ReSkin -Catalogo prodotti</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"></jsp:include>
<div>
    <div class="dropdown bg-dark d-flex justify-content-center" style="padding: 20px">
        <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
            Disponibilità
        </button>
        <ul class="dropdown-menu">
            <li>
                <button class="dropdown-item" type="button">Disponibile</button>
            </li>
            <li>
                <hr class="dropdown-divider">
            </li>
            <li>
                <button class="dropdown-item" type="button">Non disponibile</button>
            </li>
        </ul>
        <button class="btn btn-secondary dropdown-toggle mx-5" type="button" data-bs-toggle="dropdown"
                aria-expanded="false">
            Categoria
        </button>
        <ul class="dropdown-menu">
            <li>
                <button class="dropdown-item" type="button">Esempio categoria</button>
            </li>
            <li>
                <hr class="dropdown-divider">
            </li>
            <li>
                <button class="dropdown-item" type="button">Esempio categoria</button>
            </li>
            <li>
                <hr class="dropdown-divider">
            </li>
            <li>
                <button class="dropdown-item" type="button">Esempio categoria</button>
            </li>
        </ul>
    </div>
</div>

<div class='max-w-2xl px-4 lg:max-w-4xl lg:px-0 mt-3'>
    <h1 class='text-2xl font-bold text-gray-900 sm:text-3xl'>
        Scopri i nostri prodotti
    </h1>
</div>


<div class=" row row-cols-1 row-cols-md-4 g-0">
    <%for (Prodotto prodotto : listaProdoto) {%>
    <div class="col g-0">
        <div class="card h-80 w-75 border-white bg-dark mx-auto my-5 g-0">
            <div class="card-header border-white">
                <h5 class="card-title text-white"><%=prodotto.getNome()%>
                </h5>
            </div>
            <div class="card-body align-content-center">
                <div class="flex flex-column flex-fill">
                    <img src="..."
                         class="card-img-top" style="height: 180px; width: 180px" alt="IMMAGINE PRODOTTO">
                </div>
                <p class="card-text text-white"><%=prodotto.getDescrizione()%>
                </p>
                <p class="card-text text-white">€<%=prodotto.getPrezzo()%>
                </p>
            </div>
            <div class="card-footer border-white d-flex justify-content-between align-items-center">
                <%if (prodotto.getQuantita() > 0) {%>
                <small class="text-white ">Disponibile</small>
                <%} else {%>
                <small class="text-white">Non disponibile</small>
                <% } %>
                <button href="#" class="btn btn-primary bg-white text-dark">Vai al prodotto</button>
            </div>
        </div>
    </div>
    <% } %>
</div>


<script>
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            loadLog(<%= request.getSession().getAttribute("loginStatus") %>);
        }, 100);
    });
</script>
</body>

</html>

