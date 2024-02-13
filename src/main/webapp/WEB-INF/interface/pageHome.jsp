<%@ page import="com.example.reskin.Entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.reskin.Entity.Category" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReSkin - Home</title>
    <link href="${pageContext.request.contextPath}/css/carousel.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/carousel/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <%List<Product> prodotti=(List<Product>)request.getAttribute("listaProdotti");%>
</head>
<body>
<jsp:include page="headBar.jsp"/>
<div id="myCarousel" class="carousel slide mb-6" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>
            <div class="container">
                <div class="carousel-caption text-start">
                    <h1>Foto illustrativa.</h1>
                    <p>Prima foto illustrativa.</p>
                    <p><a class="btn btn-lg btn-dark" href="#">Vai al prodotto</a></p>
                </div>
            </div>
        </div>
        <div class="carousel-item">
            <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>
            <div class="container">
                <div class="carousel-caption">
                    <h1>Foto illustrativa.</h1>
                    <p>Seconda foto illustrativa.</p>
                    <p><a class="btn btn-lg btn-dark" href="#">Vai al prodotto</a></p>
                </div>
            </div>
        </div>
        <div class="carousel-item">
            <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false"><rect width="100%" height="100%" fill="var(--bs-secondary-color)"/></svg>
            <div class="container">
                <div class="carousel-caption text-end">
                    <h1>Foto illustrativa</h1>
                    <p>Terza foto illustrativa.</p>
                    <p><a class="btn btn-lg btn-dark" href="#">Vai al prodotto</a></p>
                </div>
            </div>
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Indietro</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Avanti</span>
    </button>
</div>
<div class="container marketing">
    <div class="row">
        <div class="col-lg-4">
            <svg xmlns="http://www.w3.org/2000/svg" width="140" height="140" fill="currentColor" class="bi bi-instagram" viewBox="0 0 16 16">
                <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.9 3.9 0 0 0-1.417.923A3.9 3.9 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.9 3.9 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.9 3.9 0 0 0-.923-1.417A3.9 3.9 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599s.453.546.598.92c.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.5 2.5 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.5 2.5 0 0 1-.92-.598 2.5 2.5 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233s.008-2.388.046-3.231c.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92s.546-.453.92-.598c.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92m-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217m0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334"/>
            </svg>
            <h2 class="fw-normal">Instagram</h2>
            <p>Rimani aggiornato e scopri in anteprima i prossimi prodotti in vendita.</p>
            <p><a class="btn btn-secondary" href="#">Seguici &raquo;</a></p>
        </div>
        <div class="col-lg-4">
            <svg xmlns="http://www.w3.org/2000/svg" width="140" height="140" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951"/>
            </svg>
            <h2 class="fw-normal">Facebook</h2>
            <p>Rimani aggiornato e scopri in anteprima i prossimi prodotti in vendita.</p>
            <p><a class="btn btn-secondary" href="#">Seguici &raquo;</a></p>
        </div>
        <div class="col-lg-4">
            <svg xmlns="http://www.w3.org/2000/svg" width="140" height="140" fill="currentColor" class="bi bi-twitter-x" viewBox="0 0 16 16">
                <path d="M12.6.75h2.454l-5.36 6.142L16 15.25h-4.937l-3.867-5.07-4.425 5.07H.316l5.733-6.57L0 .75h5.063l3.495 4.633L12.601.75Zm-.86 13.028h1.36L4.323 2.145H2.865z"/>
            </svg>
            <h2 class="fw-normal">X</h2>
            <p>Rimani aggiornato e scopri in anteprima i prossimi prodotti in vendita.</p>
            <p><a class="btn btn-secondary" href="#">Seguici &raquo;</a></p>
        </div>
    </div>
    <hr class="featurette-divider">
    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading fw-normal lh-1">
                Secoli di inquinamento
                <span class="text-body-secondary">
                        <br>
                        Nel corso dell&apos;ultimo decennio, nonostante gli sforzi considerevoli compiuti da agenzie
                        e governi per promuovere una vera e propria transizione ecologica attraverso ampi programmi di sviluppo,
                        diversi settori continuano a lottare per adottare le pratiche comuni necessarie per lo smaltimento adeguato dei rifiuti.
                        Uno dei settori pi&ugrave; inquinanti che ha scalato posizioni, raggiungendo il secondo posto nella graduatoria tra quelli pi&ugrave; dannosi per l&apos;ambiente
                        &egrave; senz&apos;altro quello della moda, in particolare il segmento noto come fast fashion
                        che si concentra sulla produzione in massa di capi d&apos;abbigliamento. Diversi report hanno evidenziato la presenza di metano e petrolio
                        tra altre sostanze chimiche, in materiali ampiamente utilizzati dalle grandi aziende, come cotone, seta e fibre sintetiche.
                    </span>
            </h2>
        </div>
    </div>
    <hr class="featurette-divider">
    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading fw-normal lh-1">
                Una nuova proposta
                <span class="text-body-secondary">
                        <br>
                        L&apos;azienda INDITEX propone quindi un sistema innovativo chiamato INDITEX ReSkin.
                        L&apos;obiettivo principale di questo sistema &egrave; creare un canale di comunicazione che consenta a piccoli artigiani
                        e innovative start-up italiane di acquistare a prezzi vantaggiosi il materiale di produzione in eccesso.
                        L&apos;azienda potr&agrave; dunque mettere in vendita sottoprodotti (ovvero uno scarto di lavorazione derivante da processi industriali,
                        il quale viene riutilizzato in un altro processo produttivo come materia prima non vergine) derivanti dalla produzione di capi per
                        i suoi principali brand di Fast Fashion.
                    </span>
            </h2>
        </div>
    </div>
    <hr class="featurette-divider">
</div>
<footer class="container">
    <p class="float-end"><a href="#" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Torna su</a></p>
    <p>&copy; 2023 Company, Inc. &middot; <a href="#" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Privacy</a> &middot; <a href="#" class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">Termini</a></p>
</footer>

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
    <div id="liveToast-notallowed0" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Funzione riservata agli utenti registrati! Registrati ora o effettua il Login.
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
    const toastnotlogAdlow = document.getElementById('liveToast-notallowed0')
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
            if (notAllowed == 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlogAdlow)
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
</html>