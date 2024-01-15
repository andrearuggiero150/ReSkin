<%@ page import="model.customer" %>
<!DOCTYPE html>
<html>
<head>
  <title>ReSkin - Home</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">
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
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
              <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
            </svg> Cart
          </button>
          <div class="dropdown order-lg-2 order-1">
            <button type="button" class="btn btn-outline-light dropdown-toggle m-1 " data-bs-toggle="dropdown" aria-expanded="false">
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
              </svg>
            </button>
            <% if((int)request.getSession().getAttribute("loginStatus")==2) {%>
            <div class="dropdown-menu dropdown-menu-lg-end dropdown-menu-start" id="loggedAdminDropdown" style="width: 300px">
              <a class="dropdown-item" href="#">Scrivi POP</a>
              <a class="dropdown-item" href="#">Visualizza Ordini </a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">LogOut</a>
            </div>
            <%} else if((int)request.getSession().getAttribute("loginStatus")==1){%>
            <div class="dropdown-menu dropdown-menu-lg-end dropdown-menu-start" id="loggedDropdown" style="width: 300px">
              <a class="dropdown-item" href="#">Visualizza POP</a>
              <a class="dropdown-item" href="#">Visualizza Ordini</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">LogOut</a>
            </div>
            <%} else {%>
            <form class="dropdown-menu dropdown-menu-lg-end dropdown-menu-start p-4 was-validated" id="loginDropdown" style="width: 300px" method="post" action="loginServlet">
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control" id="email" name="email" placeholder="email@example.com" required pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$">
              </div>
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required pattern="^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?!.*\s).{8,}$">
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
<form method="get">
  <div class="input-group bg-dark" style="padding: 10px 50px;">
    <input type="text" class="form-control" placeholder="Cerca..." name="cerca">
    <button class="btn btn-outline-light" type="submit">
      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
        <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"/>
      </svg>
    </button>
  </div>
</form>


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
        Utente non trovato. Registrati ora!
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
        Credenziali non valide!
      </div>
      <button type="button" class="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
    </div>
  </div>
</div>
<div class="toast-container position-fixed bottom-0 end-0 p-3">
  <div id="liveToast-notLogged" class="toast bg-danger" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="d-flex">
      <div class="toast-body">
        Funzione riservata agli utenti registrati! Registrati ora o effettua il Login.
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
  document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
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
    })})

  document.addEventListener('DOMContentLoaded', function () {
    var loginDropdown = document.getElementById('loginDropdown');
    var buttonsInsideDropdown = loginDropdown.querySelectorAll('button');
    buttonsInsideDropdown.forEach(function(button) {
      button.addEventListener('click', function (e) {
        if (validateFields()) {
          loginDropdown.classList.remove('show');
        } else {
          e.preventDefault();
          e.stopPropagation();
        }
      });
    });

    function validateFields() {
      var emailInput = document.getElementById('email');
      var passwordInput = document.getElementById('password');

      if (emailInput.validity.valid && passwordInput.validity.valid) {
        return true;
      } else {
        const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample_2)
        toastBootstrap.show()
        return false;
      }
    }
  });

  document.addEventListener("DOMContentLoaded", function() {
    setTimeout(function() {
      const myEl = document.getElementById('cartBtn');
      myEl.addEventListener('click', function () {
        var loginStatus = <%= session.getAttribute("loginStatus") %>;

        if (loginStatus == 0) {
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
</body>
</html>