<%@ page import="com.example.reskin.Entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.reskin.Entity.OrderDetails" %>
<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="com.example.reskin.ricercaVisualizzazioneProdotto.DAOStorage.RVPDAO" %>
<%@ page import="com.example.reskin.connPool.connectionPoolReal" %><%--
  Created by IntelliJ IDEA.
  User: giovi
  Date: 09/02/2024
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%List<Order> orderList = (List<Order>) request.getAttribute("listaOrdini");%>

    <title>ReSkin -Lista ordini</title>
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
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
