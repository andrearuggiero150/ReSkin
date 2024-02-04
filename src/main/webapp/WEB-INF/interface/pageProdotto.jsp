<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>
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
    <div class="card mt-5" style="width: auto;">
        <div class="card-body">
            <div class="card mb-3" style="width: auto; margin: 0 auto;">
                <div class="row g-0">
                    <div class="col-md-4 mx-auto">
                        <div>
                            <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(prodotto.getBinaryImage()) %>"
                                 class="card-img-top img-fluid" alt="IMMAGINE PRODOTTO">
                        </div>
                    </div>
                    <div class="col-md-8 mt-3">
                        <h1 class="card-title fw-bold fw-italic"><%=prodotto.getNome()%>
                        </h1>
                        <div class="card-body" style="margin-top: 150px">
                            <p class="card-text">LUNGHEZZA: <%=prodotto.getLunghezza()%> cm
                            </p>
                            <p class="card-text">LARGHEZZA: <%=prodotto.getLarghezza()%> cm
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
