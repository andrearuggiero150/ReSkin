<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.EntityStorage.Category" %>
<%@ page import="org.json.JSONArray" %>
<%--
  Created by IntelliJ IDEA.
  User: giovi
  Date: 21/01/2024
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <% Prodotto prodottoDaModificare = (Prodotto) request.getAttribute("prodottoDaModificare");%>
    <%String nomeCategoria = (String) request.getAttribute("nomeCategoria");%>
    <%ArrayList<Category> listaCategorie = (ArrayList<Category>) request.getAttribute("listaCategorie");%>
    <title>ReSkin - Modifica inserzione</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body class="bg-dark text-light">
<jsp:include page="headBar.jsp"/>
<div class='max-w-2xl px-4 lg:max-w-4xl lg:px-0 mt-5'>
    <h1 class='text-2xl font-bold text-gray-900 sm:text-3xl'>
        Modifica inserzione
    </h1>
</div>
<div class="container">
    <div class="login-form mx-auto text-center registerForm">
        <img src="resources/logo.png" alt="Logo" style="width:360px; height: 80px;">
        <form class="row g-3" method="post" action="aggiornaProdottoServlet" id="idForm">
            <input type="hidden" value="<%=prodottoDaModificare.getProductID()%>" name="idProdotto">
            <div class="col-md-6">
                <label for="Titolo" class="form-label">Titolo</label>
                <input type="text" class="form-control"
                       value="<%=prodottoDaModificare.getNome()%>" id="Titolo" name="Titolo" placeholder="Titolo"
                >
            </div>
            <div class="col-md-6">
                <label for="Lunghezza" class="form-label">Lunghezza</label>
                <input type="number" step="any" class="form-control" value="<%=prodottoDaModificare.getLunghezza()%>"
                       id="Lunghezza" name="Lunghezza" placeholder="Lunghezza"
                >
            </div>
            <div class="col-md-6">
                <label for="Larghezza" class="form-label">Larghezza</label>
                <input type="number" step="any" class="form-control" value="<%=prodottoDaModificare.getLarghezza()%>"
                       id="Larghezza" name="Larghezza" placeholder="Larghezza"
                >
            </div>

            <div class="col-md-6">
                <label for="Categoria" class="form-label">Categoria</label>
                <select class="form-select" id="Categoria" name="Categoria">
                    <% for (Category categoria : listaCategorie) { %>
                    <option value="<%= categoria.getCategoryID() %>" data-nome="<%= categoria.getNome() %>" <% if (categoria.getNome().equals(nomeCategoria)) { %>
                            selected <% } %>><%=categoria.getNome()%>
                    </option>
                    <% } %>
                </select>
                <input type="hidden" id="CategoriaNome" name="CategoriaNome" value="">
            </div>


            <div class="col-md-6">
                <label for="Prezzo" class="form-label">Prezzo</label>
                <input type="number" step="any" class="form-control" value="<%=prodottoDaModificare.getPrezzo()%>"
                       id="Prezzo" name="Prezzo" placeholder="Prezzo">
            </div>
            <div class="col-md-6">
                <label for="Quantità" class="form-label">Quantità</label>
                <input type="number" class="form-control" value="<%=prodottoDaModificare.getQuantita()%>"
                       id="Quantità" name="Quantità"
                       placeholder="Quantità">
            </div>
            <div class="col-md-12">
                <label for="Descrizione" class="form-label">Descrizione</label>
                <div class="form-floating">
                    <textarea class="form-control" id="Descrizione"
                              name="Descrizione"><%=prodottoDaModificare.getDescrizione()%></textarea>
                </div>
            </div>

            <div class="col-12">
                <a href="gestisciInserzioniServlet">
                    <button class="btn btn-outline-danger" type="button">Torna alla lista delle inserzioni</button>
                </a>
                <button class="btn btn-success" type="submit" onclick="updateCategoriaNome()">Invia</button>
            </div>

        </form>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast1" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il Titolo deve contenere minimo 5 caratteri fino ad un massimo di 30
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                La Descrizione deve contenere minimo 20 caratteri fino ad un massimo di 150
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast3" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il valore della Lunghezza non può essere minore di 1
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast4" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il valore della Larghezza non può essere minore di 1
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast5" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il valore della quantità non può essere minore di 1
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast6" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il valore del prezzo non può essere minore di 1
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast7" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                La categoria inserita non è valida
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Errore nell'aggiornamento. Prego riprovare
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>


<script>

    document.getElementById("idForm").addEventListener("submit", function (event) {
        var categoriaSelect = document.getElementById("Categoria");
        var categoriaNomeInput = document.getElementById("CategoriaNome");
        var selectedCategoriaNome = categoriaSelect.options[categoriaSelect.selectedIndex].text;
        categoriaNomeInput.value = selectedCategoriaNome;
    });

    const toastLiveExample0 = document.getElementById('liveToast0')
    const toastLiveExample1 = document.getElementById('liveToast1')
    const toastLiveExample2 = document.getElementById('liveToast2')
    const toastLiveExample3 = document.getElementById('liveToast3')
    const toastLiveExample4 = document.getElementById('liveToast4')
    const toastLiveExample5 = document.getElementById('liveToast5')
    const toastLiveExample6 = document.getElementById('liveToast6')
    const toastLiveExample7 = document.getElementById('liveToast7')


    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let codiceErrore = <%=request.getAttribute("codiceErrore")%>

            console.log("Codice errore: " +codiceErrore)


            if (codiceErrore == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample1)
                toastBootstrap.show()
            }

            if (codiceErrore == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample2)
                toastBootstrap.show()
            }

            if (codiceErrore == 3) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample3)
                toastBootstrap.show()
            }

            if (codiceErrore == 4) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample4)
                toastBootstrap.show()
            }

            if (codiceErrore == 5) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample5)
                toastBootstrap.show()
            }

            if (codiceErrore == 6) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample6)
                toastBootstrap.show()
            }

            if (codiceErrore == 7) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample7)
                toastBootstrap.show()
            }
        })
    })


    document.addEventListener("DOMContentLoaded", function () {


        setTimeout(function () {
            let esitoOperazione =
            <%=request.getAttribute("esitoOperazione")%>
            if (esitoOperazione === -1) {
            }
            if (esitoOperazione === 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }
        })
    })




</script>


</body>
</html>
