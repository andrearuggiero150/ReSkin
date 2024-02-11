<%@ page import="com.example.reskin.Entity.POP" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ReSkin - POP</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="headBar.jsp"></jsp:include>
<center><button type="button" class="btn btn-dark">Scrivi POP</button></center>
    <%List<POP> listaPOP = (List<POP>) request.getAttribute("listaPOP");%>
    <%if(listaPOP == null || listaPOP.isEmpty()) { %>
    <p> Nessun POP per te </p>
    <%} else { %>
    <div class="list-group m-5">
        <%for(int i=0; i<listaPOP.size(); i++) {
            String temp[] = listaPOP.get(i).getData().toString().split("T");%>
        <div class="list-group-item">
            <div class="d-flex w-100 justify-content-between">
                <p class="mb-1"><%=listaPOP.get(i).getTesto()%></p>
            </div>
            <small>Inviato a: <%=listaPOP.get(i).getEmail()%></small>
            <p class="text-end"><small><%=temp[0]%> <%=temp[1]%></small></p>
        </div>
        <%}}%>
    </div>
</body>
</html>
