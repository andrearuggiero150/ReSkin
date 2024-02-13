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
        <form class="row g-3" method="post" action="registrationAdminServlet">
            <b> Registrazione Admin </b>
            <div class="col-md-6">
                <label for="nome" class="form-label">Nome</label>
                <input type="text" class="form-control" id="nome" name="nome" placeholder="Mario" required>
            </div>
            <div class="col-md-6">
                <label for="cognome" class="form-label">Cognome</label>
                <input type="text" class="form-control" id="cognome" name="cognome" placeholder="Rossi" required>
            </div>
            <div class="col-md-12">
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control" id="email" name="email" required placeholder="email@example.com">
            </div>
            <div class="col-md-12">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="Password">
                <small id="passTips" class="form-text text-start text-light">- Contenere almeno 8 caratteri e massimo 30<br>- Contenere almeno una lettera minuscola ed una maiuscola<br>- Contenere almeno 1 numero<br>- Contenere almeno un carattere speciale tra @$!%*?&</small>
            </div>
            <div class="col-12">
                <a href="${pageContext.request.contextPath}/index.html"><button class="btn btn-outline-danger" type="button">Torna alla HomePage</button></a>
                <button class="btn btn-success" type="submit">Invia</button>
            </div>
        </form>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                L'email o la partita IVA risulta già registrata. Se sei il proprietario effettua il login o se hai dimenticato la password inviaci un email a inditexreskin@clientsupport.com
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Fatal Error: Riprova.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-1" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Email inserita non valida!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Password inserita non valida!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-3" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Nome inserito non valido!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-4" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Cognome inserito non valido!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-5" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Il software esterno non è raggiungibile. Inviaci un email a inditexreskin@clientsupport.com per notificarci il disservizio e permetterci di risolvere
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>



<script>
    const toastLiveExample2 = document.getElementById('liveToast2')
    const toastLiveExample0 = document.getElementById('liveToast0')
    const toastLiveExample_1 = document.getElementById('liveToast-1')
    const toastLiveExample_2 = document.getElementById('liveToast-2')
    const toastLiveExample_3 = document.getElementById('liveToast-3')
    const toastLiveExample_4 = document.getElementById('liveToast-4')
    const toastLiveExample_5 = document.getElementById('liveToast-5')
    document.addEventListener("DOMContentLoaded", function() {
        setTimeout(function() {
            let registerSuccess = <%= request.getAttribute("registerSuccess") %>;
            if (registerSuccess == 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }
            if (registerSuccess == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample2)
                toastBootstrap.show()
            }
            if (registerSuccess == -1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_1)
                toastBootstrap.show()
            }
            if (registerSuccess == -2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_2)
                toastBootstrap.show()
            }
            if (registerSuccess == -3) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_3)
                toastBootstrap.show()
            }
            if (registerSuccess == -4) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_4)
                toastBootstrap.show()
            }
            if (registerSuccess == -5) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_5)
                toastBootstrap.show()
            }
        })})

</script>
</body>
</html>

