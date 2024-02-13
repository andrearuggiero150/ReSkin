<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.example.reskin.Entity.Category" %>
<%@ page import="com.example.reskin.Entity.Product" %>

<html>
<head>
    <%ArrayList<Product> listaProdotti = (ArrayList<Product>) request.getAttribute("listaProdotti");%>
    <%ArrayList<Category> listaCategorie = (ArrayList<Category>) request.getAttribute("listaCategorie");%>
    <title>ReSkin - Catalogo prodotti</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"/>
<div>
    <div class="bg-dark d-flex justify-content-center" style="padding: 20px">
        <form method="post" action="productListServlet" class="mx-5" id="cambiaDisponibilitàForm">
            <select class="form-select" id="Disponibilità" name="Disponibilità" onchange="submitFormDisponibilita()">
                <option value="" disabled selected style="display:none;">Disponibilità</option>
                <option value="Disponibile" >Disponibile</option>
                <option value="Non disponibile">Non disponibile</option>
            </select>
        </form>

        <form method="post" action="productListServlet" class="mx-5" id="cambiaCategoriaForm">
            <select class="form-select" id="Categoria" name="Categoria" onchange="submitFormCategoria()">
                <option value="" disabled selected style="display:none;">Categoria</option>
                <% for (Category categoria : listaCategorie) { %>
                <option value="<%= categoria.getCategoryID() %>" data-nome="<%= categoria.getNome() %>">
                    <%=categoria.getNome()%>
                </option>
                <% } %>
            </select>
        </form>

        <div>
            <button type="button" class="btn btn-primary bg-white mx-5" style="border-color: #212121;" >
                <a style="text-decoration: none; color: #212121; border-color: #212121;" href="productListServlet">Reset filtri</a>
            </button>
        </div>
    </div>
</div>

<div class='max-w-2xl px-4 lg:max-w-4xl lg:px-0 mt-3'>
    <h1 class='text-2xl font-bold text-gray-900 sm:text-3xl'>
        Scopri i nostri prodotti
    </h1>
</div>


<div class=" row row-cols-1 row-cols-md-4 g-0">
    <%
        for (Product prodotto : listaProdotti) {
    %>
    <%int id = prodotto.getProductID();%>
    <div class="col g-0" onclick="">
        <div class="card h-80 w-75 border-white bg-dark mx-auto my-5 g-0">
            <div class="card-header border-white">
                <h5 class="card-title text-white"><%=prodotto.getNome()%>
                </h5>
            </div>
            <div class="card-body mx-auto">
                <div class="flex flex-column flex-fill">
                    <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(prodotto.getBinaryImage()) %>" class="card-img-top img-fluid" alt="IMMAGINE PRODOTTO">
                </div>
            </div>
            <div class="mx-auto mb-1">
                <p class="card-text text-white"><%=prodotto.getDescrizione()%>
                </p>
                <p class="card-text text-white">€<%=prodotto.getPrezzo()%>
                </p>
            </div>
            <div class="card-footer border-white d-flex justify-content-between align-items-center">
                <%if (prodotto.getQuantita() > 0) {%>
                <small class="text-white ">Disponibile</small>
                <%} else {%>
                <small class="text-white">Non disponibile</small>
                <% } %>
                <button class="btn btn-primary bg-white" style="border-color: #212121;" >
                    <a style="text-decoration: none; color: #212121; border-color: #212121" href="productServlet?id=<%=id%>">Vai al prodotto</a>
                </button>
            </div>
        </div>
    </div>
    <% } %>
</div>


<script>
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            loadLog(<%= request.getSession().getAttribute("loginStatus") %>);
        }, 100);
    });


    function submitFormCategoria() {
        var select = document.getElementById("Categoria");
        select.options[0].style.display = "none";
        document.getElementById("cambiaCategoriaForm").submit();
    }

    function submitFormDisponibilita() {
        var select = document.getElementById("Disponibilità");
        select.options[0].style.display = "none";
        document.getElementById("cambiaDisponibilitàForm").submit();
    }
</script>
</body>

</html>

