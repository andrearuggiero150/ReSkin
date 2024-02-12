<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.html">
            <img src="resources/logo.png" alt="Logo" style="width:180px; height: 40px;">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navigationBar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navigationBar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link " href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="#">Catalogo</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link " href="#">AboutUs</a>
                </li>
            </ul>
            <div>
                <div class="d-flex justify-lg-content-end justify-content-start">
                    <button type="button" class="btn btn-outline-light m-1 order-lg-1 order-2" id="cartBtn">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                             class="bi bi-cart" viewBox="0 0 16 16">
                            <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
                        </svg>
                        Cart
                    </button>
                    <div class="dropdown order-lg-2 order-1">
                        <button type="button" class="btn btn-outline-light dropdown-toggle m-1 "
                                data-bs-toggle="dropdown" aria-expanded="false">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd"
                                      d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </button>
                        <% if ((int) request.getSession().getAttribute("loginStatus") == 2) {%>
                        <div class="dropdown-menu dropdown-menu-lg-end dropdown-menu-start" id="loggedAdminDropdown"
                             style="width: 300px">
                            <a class="dropdown-item" href="POPAdminServlet">Scrivi POP</a>
                            <a class="dropdown-item" href="viewOrderServlet">Visualizza Ordini </a>
                            <a class="dropdown-item" href="manageProductServlet">Visualizza Inserzioni </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logoutServlet">LogOut</a>
                        </div>
                        <%} else if ((int) request.getSession().getAttribute("loginStatus") == 1) {%>
                        <div class="dropdown-menu dropdown-menu-lg-end dropdown-menu-start" id="loggedDropdown"
                             style="width: 300px">
                            <a class="dropdown-item" href="POPClientServlet">Visualizza POP</a>
                            <a class="dropdown-item" href="viewOrderServlet">Visualizza Ordini</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/logoutServlet">LogOut</a>
                        </div>
                        <%} else {%>
                        <form class="dropdown-menu dropdown-menu-lg-end dropdown-menu-start p-4" id="loginDropdown"
                              style="width: 300px" method="post" action="loginServlet">
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="text" class="form-control" id="email" name="email"
                                       placeholder="email@example.com" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password" required>
                            </div>
                            <div class="mb-3">
                            </div>
                            <button type="submit" class="btn btn-success">Sign in</button>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item p-1" href="registrationServlet">Sign up</a>
                        </form>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>


<form method="post" action="${pageContext.request.contextPath}/listaProdottiServlet" style="margin-bottom: 0;">
    <div class="dropdown">
        <div class="input-group bg-dark" style="padding: 10px 50px;">
            <input id="search-bar" type="text" class="form-control" placeholder="Cerca..." name="nomeProdotto">
            <ul class="dropdown-menu" id="results-container">
            </ul>
            <button class="btn btn-outline-light" type="submit">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
                     viewBox="0 0 16 16">
                    <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
                </svg>
            </button>
        </div>
    </div>
</form>
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

<script>
    $(document).ready(function () {
        var inputBarraDiRicerca = $('#search-bar');
        var listaRisultati = $('#results-container');
        inputBarraDiRicerca.on('input', function () {
            var termineCercato = inputBarraDiRicerca.val();
            if (termineCercato === '') {
                listaRisultati.empty().hide();
            } else {
                $.ajax({
                    url: 'searchBarServlet',
                    method: 'POST',
                    data: {nomeGioco: termineCercato}, dataType: 'json',
                    success: function (rispostaDatabase) {
                        listaRisultati.empty();
                        if (rispostaDatabase.length > 0) {
                            $.each(rispostaDatabase, function (indice, nome) {
                                var nomeElemento = $('<li><a class="dropdown-item" href="#">' + nome + '</a></li>');
                                nomeElemento.click(function () {
                                    inputBarraDiRicerca.val(nome);
                                    listaRisultati.hide();
                                });
                                listaRisultati.append(nomeElemento);
                            });

                            var offset = inputBarraDiRicerca.offset();
                            listaRisultati.css({
                                width: inputBarraDiRicerca.outerWidth(),
                                top: '45px',
                                left: offset.left,
                                position: 'absolute'
                            });
                            listaRisultati.show();
                        } else {
                            listaRisultati.append('<li><a class="dropdown-item" href="#">' + 'Nessun risultato trovato' + '</a></li>');
                            listaRisultati.show();
                        }
                    }
                });
            }
        });

        inputBarraDiRicerca.on('focus', function () {
            if (inputBarraDiRicerca.val() === '') {
                listaRisultati.hide();
            }
        });

        inputBarraDiRicerca.on('blur', function () {
            setTimeout(function () {
                listaRisultati.hide();
            }, 200);
        });
    });

    document.addEventListener("DOMContentLoaded", function () {
        setTimeout(function () {
            const myEl = document.getElementById('cartBtn');
            myEl.addEventListener('click', function () {
                var loginStatus = <%= session.getAttribute("loginStatus") %>;

                if (loginStatus != 1) {
                    const toastTrigger = document.getElementById('cartBtn')
                    const toastLiveExample = document.getElementById('liveToast-notLogged')

                    if (toastTrigger) {
                        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
                        toastTrigger.addEventListener('click', () => {
                            toastBootstrap.show()
                        })
                    }
                }
            }, 100);
        });
    });
</script>
