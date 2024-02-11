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

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast1" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Benvenuto
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast2" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Benvenuto Admin
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Utente non trovato controlla email e password inseriti. Se non hai un account registrati ora!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-1" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Fatal Error: Riprova.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Email inserita non valida!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-3" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Password inserita non valida!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-notLogged" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Funzione riservata ai Clienti! Registrati ora o effettua il Login.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast-notLoggedAdmin" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Funzione riservata agli Admin! Registrati ora o effettua il Login.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToastreg" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Registrazione avvenuta con successo! Ora puoi effettuare il login.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
<div id="liveToastlogout" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="d-flex">
        <div class="toast-body">
            Logout avvenuto con successo!
        </div>
        <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
</div>
</div>


<script>
    const toastLiveExample0 = document.getElementById('liveToast0')
    const toastLiveExample1 = document.getElementById('liveToast1')
    const toastLiveExample2 = document.getElementById('liveToast2')
    const toastLiveExample_1 = document.getElementById('liveToast-1')
    const toastLiveExample_2 = document.getElementById('liveToast-2')
    const toastLiveExample_3 = document.getElementById('liveToast-3')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let loginSuccess = <%= request.getAttribute("loginSuccess") %>;
            if (loginSuccess == 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample0)
                toastBootstrap.show()
            }
            if (loginSuccess == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample1)
                toastBootstrap.show()
            }
            if (loginSuccess == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample2)
                toastBootstrap.show()
            }
            if (loginSuccess == -1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_1)
                toastBootstrap.show()
            }
            if (loginSuccess == -2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_2)
                toastBootstrap.show()
            }
            if (loginSuccess == -3) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_3)
                toastBootstrap.show()
            }
        })
    })

    const toastnotlog = document.getElementById('liveToast-notLogged')
    const toastnotlogAd = document.getElementById('liveToast-notLoggedAdmin')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let notAllowed = <%= request.getAttribute("notAllowed") %>;
            if (notAllowed == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlog)
                toastBootstrap.show()
            }
            if (notAllowed == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlogAd)
                toastBootstrap.show()
            }
        })
    })

    const toastreg = document.getElementById('liveToastreg')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let registerSuccess = <%= request.getAttribute("registerSuccess") %>;
            if (registerSuccess == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastreg)
                toastBootstrap.show()
            }
        })
    })

    const toastlogout = document.getElementById('liveToastlogout')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let logoutSuccess = <%= request.getAttribute("logoutSuccess") %>;
            if (logoutSuccess == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastlogout)
                toastBootstrap.show()
            }
        })
    })
</script>
</body>
</html>