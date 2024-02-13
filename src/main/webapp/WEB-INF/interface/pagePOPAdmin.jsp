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
<br>
<div class="container">
    <center><button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Invia POP</button></center>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Invia POP</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form method="get" action="sendPOPServlet">
                        <div class="mb-3">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opzione" id="opzioneAbilita" value="abilita" checked>
                                <label class="form-check-label" for="opzioneAbilita">Invio Singolo</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="opzione" id="opzioneDisabilita" value="disabilita">
                                <label class="form-check-label" for="opzioneDisabilita">Invio Broadcast</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" placeholder="example@gmail.com" required>
                        </div>
                        <div class="mb-3">
                            <label for="testo" class="form-label">Testo</label>
                            <textarea class="form-control" id="testo" name="testo"></textarea>
                        </div>
                        <center><button type="submit" class="btn btn-success">Invia</button></center>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
    <%List<POP> listaPOP = (List<POP>) request.getAttribute("listaPOP");%>
    <%if(listaPOP == null || listaPOP.isEmpty()) { %>
<h3 class='text-2xl font-bold text-gray-900 sm:text-3xl'>
    Nessun POP inviato
</h3>

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
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast0" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Fatal error: Riprova.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast1" class="toast bg-success" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Invio riuscito
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast2" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Errore: il destinatario è un Admin!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>

<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast3" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Errore: il destinatario non esiste!
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
    <div id="liveToast4" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
        <div class="d-flex">
            <div class="toast-body">
                Inserimento non valido.
            </div>
            <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    </div>
</div>
<script>
    const emailInput = document.getElementById('email');
    const opzioneAbilita = document.getElementById('opzioneAbilita');
    const opzioneDisabilita = document.getElementById('opzioneDisabilita');

    opzioneAbilita.addEventListener('change', function() {
        if (opzioneAbilita.checked) {
            emailInput.disabled = false;
        }
    });

    opzioneDisabilita.addEventListener('change', function() {
        if (opzioneDisabilita.checked) {
            emailInput.disabled = true;
        }
    });

    const toastnot = document.getElementById('liveToast0')
    const toastnotlog = document.getElementById('liveToast1')
    const toastnotlogAd = document.getElementById('liveToast2')
    const toastnotlogAdlow = document.getElementById('liveToast3')
    const toastnotlogAdlowlow = document.getElementById('liveToast4')
    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            let notAllowed = <%= request.getAttribute("POPinvalido") %>;
            if (notAllowed == 1) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlog)
                toastBootstrap.show()
            }
            if (notAllowed == 2) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlogAd)
                toastBootstrap.show()
            }
            if (notAllowed == 0) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnot)
                toastBootstrap.show()
            }
            if (notAllowed == 3) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlogAdlow)
                toastBootstrap.show()
            }
            if (notAllowed == 4) {
                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastnotlogAdlowlow)
                toastBootstrap.show()
            }
        })
    })
</script>
</body>
</html>
