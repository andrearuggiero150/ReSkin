<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="com.example.reskin.Entity.Category" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: giovi
  Date: 26/01/2024
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ReSkin - Aggiungi inserzione</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <%ArrayList<Category> listaCategorie = (ArrayList<Category>) request.getAttribute("listaCategorie");%>
</head>
<body class="bg-dark text-light">
<div class='max-w-2xl px-4 lg:max-w-4xl lg:px-0 mt-5'>
    <h1 class='text-2xl font-bold text-gray-900 sm:text-3xl'>
        Aggiungi inserzione
    </h1>
</div>
<div class="container">
    <div class="mx-auto text-center mt-5">
        <form class="row g-3" method="post" action="aggiungiNuovaInserzioneServlet" enctype="multipart/form-data" id="idForm">
            <div class="col-md-4">
                <label for="Titolo" class="form-label">Titolo</label>
                <input type="text" class="form-control" id="Titolo" name="Titolo" placeholder="Titolo">
            </div>

            <div class="col-md-4">
                <label for="Lunghezza" class="form-label">Lunghezza</label>
                <input type="number" step="any" class="form-control" id="Lunghezza" name="Lunghezza" placeholder="Lunghezza">
            </div>

            <div class="col-md-4">
                <label for="Larghezza" class="form-label">Larghezza</label>
                <input type="number" step="any" class="form-control" id="Larghezza" name="Larghezza" placeholder="Larghezza">
            </div>

            <div class="col-md-4">
                <label for="Categoria" class="form-label">Categoria</label>
                <select class="form-select" id="Categoria" name="Categoria">
                    <% for (Category categoria : listaCategorie) { %>
                    <option value="<%= categoria.getCategoryID() %>" data-nome="<%= categoria.getNome() %>">
                        <%=categoria.getNome()%>
                    </option>
                    <% } %>
                </select>
                <input type="hidden" id="CategoriaNome" name="CategoriaNome" value="">
            </div>


            <div class="col-md-4">
                <label for="Prezzo" class="form-label">Prezzo</label>
                <input type="number" step="any" class="form-control" id="Prezzo" name="Prezzo" placeholder="Prezzo">
            </div>

            <div class="col-md-4">
                <label for="Quantità" class="form-label">Quantità</label>
                <input type="number" class="form-control" id="Quantità" name="Quantità" placeholder="Quantità">
            </div>

            <div class="col-md-4">
                <label for="Colore" class="form-label">Colore</label>
                <input type="text" class="form-control" id="Colore" name="Colore" placeholder="Colore">
            </div>

            <div class="col-md-4">
                <label for="Descrizione" class="form-label">Descrizione</label>
                <div class="form-floating">
                    <textarea class="form-control" id="Descrizione" name="Descrizione"></textarea>
                </div>
            </div>

            <div class="col-md-4">
                <label for="CaricaFoto" class="form-label">Carica foto</label>
                <input type="file" class="form-control" value="" id="CaricaFoto" name="CaricaFoto" placeholder="CaricaFoto">
            </div>

            <div class="col-12 mt-5">
                <a href="gestisciInserzioniServlet">
                    <button class="btn btn-outline-danger" type="button">Torna alla lista delle inserzioni</button>
                </a>
                <button class="btn btn-success mx-5" type="submit">Invia</button>
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
    <div id="liveToast8" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                La foto del prodotto deve essere in formato PNG
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast9" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il Colore deve contenere minimo 3 caratteri fino ad un massimo di 20
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
    const toastLiveExample8 = document.getElementById('liveToast8')
    const toastLiveExample9 = document.getElementById('liveToast9')

    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let codiceErrore = <%=request.getAttribute("codiceErrore")%>


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

            if (codiceErrore == 8) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample8)
                toastBootstrap.show()
            }

            if (codiceErrore == 9) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample9)
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
            if (esitoOperazione === 0 || esitoOperazione===2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }
        })
    })



</script>
</body>
</html>
