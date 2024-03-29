<%@ page import="com.example.reskin.Entity.Cart" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ReSkin - Cart</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"/>
    <div class="card">
        <div class="card-body">
            <% Cart c = (Cart) request.getAttribute("carrello"); %>
            <% double t = 0; %>
            <% if (c == null) { %>
            <p>Carrello vuoto</p>
            <% } else {%>
            <%for (int i = 0; i < c.sizeCarrello(); i++) { %>
            <div class="card mb-3">
                <div class="card-body">
                    <div class="d-flex justify-content-between align-items-center">
                        <div class="d-flex flex-row align-items-center">
                            <div>
                                <img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(c.getProdottoCarrello(i).getProdotto().getBinaryImage()) %>"
                                     class="card-img-top img-fluid" style="width: 100px; height: 100px;"
                                     alt="IMMAGINE PRODOTTO">
                            </div>
                            <div class="ms-3">
                                <h5 class="card-title"><%=c.getProdottoCarrello(i).getProdotto().getNome()%></h5>
                                <br>
                                <p class="small mb-0">Quantita&apos;: <%= c.getProdottoCarrello(i).getQuantita() %></p>
                                <p class="small mb-0">Prezzo: $<%= c.getProdottoCarrello(i).getProdotto().getPrezzo() %></p>
                            </div>
                        </div>
                        <div>
                            <form action="${pageContext.request.contextPath}/removeCartServlet" method="get">
                                <input type="hidden" name="cartObjectID" value="<%= c.getProdottoCarrello(i).getId() %>">
                                <button type="submit" class="btn btn-danger">Elimina Prodotto</button>
                            </form>
                            <br>
                            <form action="${pageContext.request.contextPath}/modifyCartServlet" method="get" class="form-inline">
                                <input type="hidden" name="cartObjectID" value="<%= c.getProdottoCarrello(i).getId() %>">
                                <select name="quantitaScelta" id="quantitaScelta" class="form-select mr-2">
                                    <% for (int j = 1; j <= c.getProdottoCarrello(i).getQuantita(); j++) { %>
                                    <option value="<%= j %>"><%= j %></option>
                                    <% } %>
                                </select>
                                <button type="submit" class="btn btn-primary">Modifica Quantit&agrave;</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <% double temp = c.getProdottoCarrello(i).getProdotto().getPrezzo() * c.getProdottoCarrello(i).getQuantita();
                t += temp;
            } %>
            <%}%>
            <div class="row">
                <div class="col">
                    <h1 class="fw-lighter">Totale: $<%=t%></h1>
                </div>
                <div class="col text-end">
                    <%if(c != null) {%>
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Procedi al Checkout
                    </button>
                    <%}%>
                </div>
            </div>

        </div>
    </div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Indirizzo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="login-form mx-auto text-center addressForm">
                        <form class="row g-3" action="${pageContext.request.contextPath}/placeOrderServlet" method="get">
                            <div class="col-md-64">
                                <label for="via" class="form-label">Via</label>
                                <input type="text" class="form-control" id="via" name="via"
                                       placeholder="Via Rossi"
                                       required>
                            </div>
                            <div class="col-md-64">
                                <label for="citta" class="form-label">Citta</label>
                                <input type="text" class="form-control" id="citta" name="citta"
                                       placeholder="Forino"
                                       required>
                            </div>
                            <div class="col-md-64">
                                <label for="provincia" class="form-label">Provincia</label>
                                <input type="text" class="form-control" id="provincia" name="provincia" required
                                       placeholder="Avellino">
                            </div>
                            <div class="col-md-64">
                                <label for="stato" class="form-label">Stato</label>
                                <input type="text" class="form-control" id="stato" name="stato"
                                       placeholder="Italia"
                                       required>
                            </div>
                            <div class="col-md-64">
                                <label for="CAP" class="form-label">CAP</label>
                                <input type="text" class="form-control" id="CAP" name="CAP" placeholder="83200"
                                       required>
                            </div>
                            <div class="col-64">
                                <p>Totale: <%=t%>
                                </p>
                                <button class="btn btn-success" type="submit">Piazza ordine</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%session.setAttribute("totale", t);%>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-primo" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Modifica avvenuta con successo!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-secondo" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                La quantita di questo prodotto che cerchi di aggiornare non è valida.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-terzo" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Stai cercando di modificare un prodotto non appartenente al tuo carrello.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-zero" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Fatal error: Riprovare!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-quattro" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Inserimenti non validi
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-checkbuono" class="toast bg-success" role="alert" aria-live="assertive"
         aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Ordine creato, puoi visualizzarlo nella sezione ordini.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-checkmale" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Fatal error: Riprova!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-checkpiu" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Sono stati rimossi prodotti nel tuo carrello non piu disponibili nella quantita selezionata.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast"
                    aria-label="Close"></button>
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

    const toastLiveExample5 = document.getElementById('liveToast-checkbuono')
    const toastLiveExample6 = document.getElementById('liveToast-checkmale')
    const toastLiveExample7 = document.getElementById('liveToast-checkpiu')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let check = <%= request.getAttribute("checkOutSuccess") %>;
            if (check == 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample6)
                toastBootstrap.show()
            }
            if (check == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample5)
                toastBootstrap.show()
            }
            if (check == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample7)
                toastBootstrap.show()
            }
        })
    })
</script>
</body>
</html>