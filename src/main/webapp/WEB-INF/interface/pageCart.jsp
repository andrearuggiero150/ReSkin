<%@ page import="com.example.reskin.Entity.Cart" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReSkin - Home</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"/>
<% Cart c = (Cart) request.getAttribute("carrello"); %>
<% if (c==null) { %>
    <p>Carrello vuoto</p>
   <% } else {%>
<%for(int i=0; i<c.sizeCarrello(); i++) { %>
<div class="card mb-3" style="max-width: 540px;">
    <div class="row g-0">
        <div class="col-md-4">
            <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(c.getProdottoCarrello(i).getProdotto().getBinaryImage()) %>"
                 class="card-img-top img-fluid" alt="IMMAGINE PRODOTTO">
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title"><%= c.getProdottoCarrello(i).getProdotto().getNome() %></h5>
                <p class="card-text">Quantita:
                    <%= c.getProdottoCarrello(i).getQuantita() %>
                </p>
                <p class="card-text">Prezzo: <%= c.getProdottoCarrello(i).getProdotto().getPrezzo() %></p>
                <form action="${pageContext.request.contextPath}/removeCartServlet" method="get">
                    <input type="hidden" name="cartObjectID" value="<%= c.getProdottoCarrello(i).getId() %>">
                    <button type="submit" class="btn btn-danger">Elimina Prodotto</button>
                </form>
                <form action="${pageContext.request.contextPath}/modifyCartServlet" method="get">
                    <input type="hidden" name="cartObjectID" value="<%= c.getProdottoCarrello(i).getId() %>">
                    <select name="quantitaScelta" id="quantitaScelta">
                        <% for (int j = 1; j <= c.getProdottoCarrello(i).getQuantita(); j++) { %>
                        <option value="<%= j %>"><%= j %></option>
                        <% } %>
                    </select>
                    <button type="submit" class="btn btn-primary">Modifica Quantità</button>
                </form>
            </div>
        </div>
    </div>
</div>
<% } %>
<%}%>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-primo" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Modifica avvenuta con successo!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-secondo" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                La quantita di questo prodotto che cerchi di aggiornare non è valida.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-terzo" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Stai cercando di modificare un prodotto non appartenente al tuo carrello.
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