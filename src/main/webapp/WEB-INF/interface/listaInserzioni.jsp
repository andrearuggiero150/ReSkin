<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.example.reskin.Entity.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: giovi
  Date: 20/01/2024
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%ArrayList<Product> listaProdoto = (ArrayList<Product>) request.getAttribute("listaProdotti");%>
    <title>ReSkin -Catalogo prodotti</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"/>

<div class='max-w-2xl px-4 lg:max-w-4xl lg:px-0 mt-3'>
    <div class="d-flex justify-content-between align-items-center">
        <h1 class='text-2xl font-bold text-gray-900 sm:text-3xl'>
            Scopri i nostri prodotti
        </h1>
        <form class="mx-4" action="redirectNuovaInserzioneServlet" method="post">
            <button class="btn btn-secondary" type="submit">Aggiungi nuova inserzione</button>
        </form>
    </div>
</div>



<div class="row row-cols-1 row-cols-md-4 g-0">
    <%for (Product prodotto : listaProdoto) {%>
    <%int id = prodotto.getProductID();%>
    <div class="col g-0" onclick="">
        <div class="card h-80 w-75 border-white bg-dark mx-auto my-5 g-0">
            <div class="card-header border-white">
                <h5 class="card-title text-white"><%=prodotto.getNome()%>
                </h5>
            </div>
            <div class="card-body mx-auto">
                <div class="flex flex-column flex-fill">
                    <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(prodotto.getBinaryImage()) %>" class="card-img-top img-fluid"  alt="IMMAGINE PRODOTTO">
                </div>
            </div>
            <div class="card-footer border-white">
                <div class="d-flex justify-content-center align-items-center mt-2">
                    <form class="mx-4" id="eliminaForm<%=id%>" action="eliminaProdottoServlet" method="post">
                        <input type="hidden" value="<%=id%>" name="idProdotto">
                        <button class="btn btn-danger" type="button" onclick="mostraConferma(<%=id%>)">Elimina</button>
                    </form>

                    <form class="mx-4" action="modificaInserzioneServlet" method="post">
                        <input hidden="hidden" value="<%=id%>" name="idProdotto">
                        <button class="btn btn-success" type="submit">Modifica</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Modifica effettuata correttamente
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast1" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Prodotto eliminato correttamente
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Errore nell'eliminazione del prodotto. Prego riprovare
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0">
    <div id="toastConferma" role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-bs-autohide="false">
        <div class="toast-header">
            <strong class="me-auto">Conferma Eliminazione</strong>
            <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
        <div class="toast-body">
            Sei sicuro di voler eliminare questo prodotto?
            <button type="button" class="btn btn-success mt-2 btn-sm" onclick="confermaEliminazione()">Conferma</button>
            <button type="button" class="btn btn-secondary mt-2 btn-sm" data-bs-dismiss="toast">Annulla</button>
        </div>
    </div>
</div>






<script>

    function mostraConferma(idProdotto) {
        var toastEliminaProdotto = new bootstrap.Toast(document.getElementById('toastConferma'));
        // Passa l'id del prodotto alla funzione di confermaEliminazione
        document.getElementById('toastConferma').setAttribute('data-idProdotto', idProdotto);
        toastEliminaProdotto.show();
    }

    function confermaEliminazione() {
        // Ottieni l'id del prodotto dal data attributo
        var idProdotto = document.getElementById('toastConferma').getAttribute('data-idProdotto');
        // Modifica l'ID del form dinamicamente prima di inviarlo
        document.getElementById('eliminaForm' + idProdotto).submit();
    }

    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            loadLog(<%= request.getSession().getAttribute("loginStatus") %>);
        }, 100);
    });

    const toastLiveExample0 = document.getElementById('liveToast0')
    const toastLiveExample1 = document.getElementById('liveToast1')
    const toastLiveExample2 = document.getElementById('liveToast2')


    document.addEventListener("DOMContentLoaded", function () {

        setTimeout(function () {
            let esitoOperazione =
                <%=request.getAttribute("esitoOperazione")%>;

            let risultatoOperazione =
                <%=request.getAttribute("risultatoOperazione")%>;

            if (esitoOperazione === -1) {
            }
            if (esitoOperazione === 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }


            if (risultatoOperazione === -1) {
            }

            if (risultatoOperazione === 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample1)
                toastBootstrap.show()
            }

            if (risultatoOperazione === 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample2)
                toastBootstrap.show()
            }
        })
    });


</script>
</body>

</html>


