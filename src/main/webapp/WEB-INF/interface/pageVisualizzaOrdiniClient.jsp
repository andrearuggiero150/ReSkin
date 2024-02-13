<%@ page import="com.example.reskin.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.reskin.Entity.OrderDetails" %>
<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO" %>
<%@ page import="com.example.reskin.connPool.connectionPoolReal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%List<Order> orderList = (List<Order>) request.getAttribute("listaOrdini");%>

    <title>ReSkin - Lista ordini</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body class="text-light">
<jsp:include page="headBar.jsp"/>
<div class="container-fluid mt-5">
    <div class="table-responsive d-flex">
        <table class="table table-dark table-hover">
            <thead>
            <tr>
                <th scope="col" class="text-center">Nr.Ordine</th>
                <th scope="col" class="text-center">Contenuto ordine</th>
                <th scope="col" class="text-center">Quantità</th>
                <th scope="col" class="text-center">Prezzo totale ordine</th>
                <th scope="col" class="text-center">Stato attuale</th>
            </tr>
            </thead>
            <tbody class="table-group-divider">
            <%
                for (Order order : orderList) {
                    int ordine = order.getId();
            %>
            <tr>
                <td class="text-center">
                    <%=ordine%>
                </td>

                <td class="text-center">
                    <%
                        List<OrderDetails> orderDetailsList = order.getListaProdotti();
                        for (OrderDetails details : orderDetailsList) {
                            Product product = RVPDAO.productFromID(details.getProdottoID(), new connectionPoolReal());
                    %>
                    <%=product.getNome()%> <br>
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
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
