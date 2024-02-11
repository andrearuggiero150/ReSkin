<%@ page import="com.example.reskin.Entity.Admin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <title>ReSkin - Registrazione Admin</title>
</head>
<body class="bg-dark text-light register">
<div class="container">
    <div class="login-form mx-auto text-center registerForm">
        <img src="resources/logo.png" alt="Logo" style="width:360px; height: 80px;">
        <form class="row g-3" method="post" action="registrationOTPServlet">
            <b> Registrazione Admin </b>
            <div class="col-md-12">
                <label for="otp" class="form-label">OTP</label>
                <input type="text" class="form-control" id="otp" name="otp" placeholder="12312312" required>
                <input type="hidden" name="nome" value="<%=request.getAttribute("nome")%>">
                <input type="hidden" name="cognome" value="<%=request.getAttribute("cognome")%>">
                <input type="hidden" name="email" value="<%=request.getAttribute("email")%>">
                <input type="hidden" name="password" value="<%=request.getAttribute("password")%>">
            </div>
            <div class="col-12">
                <a href="${pageContext.request.contextPath}/index.html"><button class="btn btn-outline-danger" type="button">Torna alla HomePage</button></a>
                <button class="btn btn-success" type="submit">Invia</button>
            </div>
        </form>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-1" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                OTP inserito non valido!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                OTP inserito non corretto! Ritenta l'inserimento con quello corretto che visioni sul Software Esterno
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>



<script>
    const toastLiveExample_1 = document.getElementById('liveToast-1')
    const toastLiveExample_2 = document.getElementById('liveToast-2')
    document.addEventListener("DOMContentLoaded", function() {
        setTimeout(function() {
            let registerSuccess = <%= request.getAttribute("registerSuccess") %>;
            if (registerSuccess == -1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_1)
                toastBootstrap.show()
            }
            if (registerSuccess == -2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_2)
                toastBootstrap.show()
            }
        })})

</script>
</body>
</html>

