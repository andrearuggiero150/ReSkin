<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.reskin.Entity.Category" %>
<%@ page import="com.example.reskin.Entity.Order" %>
<%@ page import="com.example.reskin.Entity.OrderDetails" %>
<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO" %>
<%@ page import="com.example.reskin.connPool.connectionPoolReal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ReSkin -Lista ordini</title>
    <%List<Order> orderList = (List<Order>) request.getAttribute("listaOrdini");%>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body class="text-light">
<jsp:include page="headBar.jsp"/>
<div class="container-fluid mt-5">
    <div class="table-responsive d-flex">
        <table class=" table table-dark table-hover">
            <thead>
            <tr>
                <th scope="col">Nr.Ordine</th>
                <th scope="col">Contenuto ordine</th>
                <th scope="col">Prezzo totale ordine</th>
                <th scope="col">Stato attuale</th>
                <th scope="col">Modifica stato</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <%
                for (Order order : orderList) {
                    int ordine = order.getId();
            %>
            <tr>
                <td>
                    <%=ordine%>
                </td>

                <td>
                    <%
                        List<OrderDetails> orderDetailsList = order.getListaProdotti();
                        for (OrderDetails details : orderDetailsList) {
                            Product product = RVPDAO.productFromID(details.getProdottoID(), new connectionPoolReal());
                    %>
                    <%=product.getNome()%> <br>
                    <%}%>
                </td>

                <td>
                    <%=order.getTotale()%>
                </td>

                <td>
                    <%=order.getStatus()%>
                </td>

                <td>
                    <form method="post" action="modifyStatusServlet" class="mx-5" id="cambiaStatusForm<%=ordine%>">
                        <input type="hidden" name="idOrdine" id="idOrdine<%=ordine%>" value="<%=ordine%>">
                        <select class="form-select" id="Status<%=ordine%>" name="Status" onchange="submitFormStatus(<%=ordine%>)">
                        <option value="" disabled selected style="display:none;">Modifica status</option>
                            <option value="Completo">Completo</option>
                            <option value="In transito">In transito</option>
                            <option value="Pagato">Pagato</option>
                            <option value="Da pagare">Da pagare</option>
                            </option>
                        </select>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Errore nella modifica dello status
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast1" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Status modificato correttamente
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<script>
    function submitFormStatus(ordine) {
        var select = document.getElementById("Status" + ordine);
        select.options[0].style.display = "none";
        document.getElementById("cambiaStatusForm" + ordine).submit();
    }

    const toastLiveExample0 = document.getElementById('liveToast0')
    const toastLiveExample1 = document.getElementById('liveToast1')

    document.addEventListener("DOMContentLoaded", function () {

        setTimeout(function () {
            let esitoOperazione =
                <%=request.getAttribute("esitoModifica")%>;

            if (esitoOperazione === -1) {
            }
            if (esitoOperazione === 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }

            if (esitoOperazione === 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample1)
                toastBootstrap.show()
            }

        })
    });

</script>

</body>
</html>
