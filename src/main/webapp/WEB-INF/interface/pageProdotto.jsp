<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.example.reskin.Entity.Product" %>
<html>
<head>
    <% Product prodotto = (Product) request.getAttribute("Prodotto");%>
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
                            <p class="card-text">Lunghezza: <%=prodotto.getLunghezza()%> cm
                            </p>
                            <p class="card-text">Larghezza: <%=prodotto.getLarghezza()%> cm
                            </p>
                            <p class="card-text">Prezzo: <%=prodotto.getPrezzo()%> $
                            </p>
                            <%if (prodotto.getQuantita() > 0) {%>
                            <p class="card-text" style="color: green">Prodotto disponibile</p>
                            <%}%>
                            <%if (prodotto.getQuantita() == 0) {%>
                            <p class="card-text" style="color: red">Prodotto non disponibile</p>
                            <%}%>
                            <form method="post" action="${pageContext.request.contextPath}/addCartServlet">
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <select name="quantitaScelta" id="quantitaScelta">
                                    <% for (int j = 1; j <= prodotto.getQuantita(); j++) { %>
                                    <option value="<%= j %>"><%= j %></option>
                                    <% } %>
                                </select>
                            </div>
                                <input type="hidden" id="prodID" name="prodID" value="<%=prodotto.getProductID()%>">
                               <input type="submit" class="btn btn-dark btn-lg" value="Aggiungi al carrello">
                            </form>
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
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-primo" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Aggiunto al carrello!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-secondo" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                La quantita di questo prodotto che hai nel carrello aggiunta a quella che stai cercando di inserire eccede la quantita di prodotto disponibile.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-terzo" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Funzione riservata ai Clienti! Registrati ora o effettua il Login.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-zero" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Fatal error: Riprovare!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-quattro" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Inserimenti non validi
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<script>
    const toastLiveExample0 = document.getElementById('liveToast-primo')
    const toastLiveExample1 = document.getElementById('liveToast-secondo')
    const toastLiveExample2 = document.getElementById('liveToast-terzo')
    const toastLiveExample_1 = document.getElementById('liveToast-zero')
    const toastLiveExample3 = document.getElementById('liveToast-quattro')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let addCartSuccess = <%= request.getAttribute("addCartSuccess") %>;
            if (addCartSuccess == 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_1)
                toastBootstrap.show()
            }
            if (addCartSuccess == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }
            if (addCartSuccess == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample1)
                toastBootstrap.show()
            }
            if (addCartSuccess == 3) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample2)
                toastBootstrap.show()
            }
            if (addCartSuccess == 4) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample3)
                toastBootstrap.show()
            }
        })
    })
</script>
</body>
</html>
