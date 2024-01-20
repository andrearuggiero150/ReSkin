<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: giovi
  Date: 19/01/2024
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% Prodotto prodotto = (Prodotto) request.getAttribute("Prodotto");%>
    <% int quantitaProdotto=prodotto.getQuantita();%>
    <% int quantitaIniziale=0;%>


    <title>ReSkin - <%=prodotto.getNome()%>
    </title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"/>
<div class="container-fluid">
    <div class="card" style="width: auto;">
        <div class="card-body">
            <div class="card mb-3" style="width: auto; margin: 0 auto;">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="${pageContext.request.contextPath}/resources/Prodotto%20di%20prova.jpg"
                             class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8 mt-2">
                        <h1 class="card-title fw-bold fw-italic"><%=prodotto.getNome()%>
                        </h1>
                        <div class="card-body" style="margin-top: 90px">
                            <p class="card-text">LUNGHEZZA: <%=prodotto.getLunghezza()%>
                            </p>
                            <p class="card-text">LARGHEZZA: <%=prodotto.getLarghezza()%>
                            </p>
                            <%if (prodotto.getQuantita() > 0) {%>
                            <p class="card-text" style="color: green">Prodotto disponibile</p>
                            <%}%>
                            <%if (prodotto.getQuantita() == 0) {%>
                            <p class="card-text" style="color: red">Prodotto non disponibile</p>
                            <%}%>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button type="button" class="btn btn-dark btn-lg" id="pulsanteDecrementa">-</button>
                                <p class="card-text mx-2 mb-0 my-2 fs-4" id="quantitaScelta"><%=quantitaIniziale%></p>
                                <button type="button" class="btn btn-dark btn-lg" id="pulsanteIncrementa">+</button>
                            </div>


                            <button type="button" class="btn btn-dark btn-lg">
                                <a href="#" style="text-decoration: none; color: white">
                                    Aggiungi al carrello
                                </a>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="card-footer">
            <small class="text-body-secondary"><%=prodotto.getDescrizione()%></small>
        </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        var quantitaIniziale = <%=quantitaIniziale%>;
        var quantitaProdotto = <%=quantitaProdotto%>;

        document.getElementById("pulsanteDecrementa").addEventListener("click", function () {
            quantitaIniziale = Math.max(quantitaIniziale - 1, 0);
            document.getElementById("quantitaScelta").innerText = quantitaIniziale;
        });

        document.getElementById("pulsanteIncrementa").addEventListener("click", function () {
            quantitaIniziale = Math.min(quantitaIniziale + 1, quantitaProdotto);
            document.getElementById("quantitaScelta").innerText = quantitaIniziale;
        });
    });
</script>
</body>
</html>