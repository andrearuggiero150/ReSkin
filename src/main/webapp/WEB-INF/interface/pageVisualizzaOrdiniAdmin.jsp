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
    <title>ReSkin - Lista ordini</title>
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
                <th scope="col" class="text-center">Nr.Ordine</th>
                <th scope="col" class="text-center">ID Acquirente</th>
                <th scope="col" class="text-center">Contenuto ordine</th>
                <th scope="col" class="text-center">Quantità</th>
                <th scope="col" class="text-center">Prezzo totale ordine</th>
                <th scope="col" class="text-center">Stato attuale</th>
                <th scope="col" class="text-center">Modifica stato</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <%
                for (Order order : orderList) {
            %>
            <tr>
                <td class="text-center ">
                    <%=order.getId()%>
                </td>

                <td class="text-center ">
                    <%=order.getCustomerId()%>
                </td>

                <td class="text-center">
                    <%
                        List<OrderDetails> orderDetailsList = order.getListaProdotti();
                        for (OrderDetails details : orderDetailsList) {
                            Product product = RVPDAO.productFromID(details.getProdottoID(), new connectionPoolReal());
                    %>
                    <%=product.getNome()%><br>
                    <%}%>
                </td>

                <td class="text-center">
                    <%
                        for (OrderDetails details : orderDetailsList) {
                    %>
                   <%=details.getQuantita()%> <br>
                    <%}%>
                </td>

                <td class="text-center">
                    €<%=order.getTotale()%>
                </td>

                <td class="text-center">
                    <%=order.getStatus()%>
                </td>

                <td>
                    <form method="post" action="modifyStatusServlet" id="cambiaStatusForm<%=order.getId()%>">
                        <div class="d-flex align-items-center">
                            <input type="hidden" name="idOrdine" id="idOrdine<%=order.getId()%>" value="<%=order.getId()%>">
                            <select class="form-select me-2" id="Status<%=order.getId()%>" name="Status">
                                <option value="" disabled selected style="display:none;">Modifica status</option>
                                <option value="Completo">Completo</option>
                                <option value="In transito">In transito</option>
                                <option value="Pagato">Pagato</option>
                                <option value="Da pagare">Da pagare</option>
                            </select>
                            <input type="hidden" id="StatusNome" name="StatusNome" value="">
                            <button class="btn btn-success" type="submit" onclick="submitFormStatus(<%=order.getId()%>)">Modifica</button>
                        </div>
                    </form>
                </td>

            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>


<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
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
        var statusSelect = document.getElementById("Status" +ordine);
        var statusNomeInput = document.getElementById("StatusNome");
        var selectedCategoriaNome = statusSelect.options[statusSelect.selectedIndex].text;
        statusNomeInput.value = selectedCategoriaNome;
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
