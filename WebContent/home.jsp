<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.9">

    <title>Home</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
    <link href="resource/css/home.css" type="text/css" rel="stylesheet" />
  	<link href="resource/css/alert.css" type="text/css" rel="stylesheet" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 	<script src="resource/javascript/util/fa.js"></script>  

	  

    <style type="text/css">
		.dropdown-item.active, .dropdown-item:active{
		    color: #fff;
		    background-color: #7f00ff;
		}
		
		
    </style>

</head>

<body>
    <div id="landing-hero" class="hero is-relative is-theme-secondary hero-waves">
        <div style="height: 100vh;">


            <header>

                <nav class="navbar navbar-expand navbar-dark">

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

                    <div class="collapse navbar-collapse" id="navbarsExample02">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="nav-link active" href="#"><i class="fas fa-home fa-md"></i> Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="categorias"><i class="fas fa-clipboard fa-sm"></i> Listas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="produtos"><i class="fas fa-shopping-cart fa-sm"></i> Produtos</a>
                            </li>
                        </ul>
                    </div>


                    <c:if test="${!user.miniatura.isEmpty() && user.miniatura != null}">
                        <div class="icon-perfil" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <input class="img-perfil" type="image" src="${user.miniatura }" />
                        </div>
                    </c:if>


                    <c:if test="${user.miniatura.isEmpty() || user.miniatura == null}">
                        <button type="button" class="btn btn-outline-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <!-- botao user -->
								    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								  <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
								  <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								  <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
								</svg>
 							 </button>

                    </c:if>


                    <div class="dropdown-menu dropdown-menu-right" style="border-radius: 1em;">
                        <a class="dropdown-item" href="my-account?action=view"> <i class="fas fa-user-circle"></i> Minha Conta</a>
                        <a class="dropdown-item" href="estatisticas"> <i class="fas fa-chart-pie"></i>  Estatísticas</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i>Logout</a>
                    </div>
                </nav>
            </header>


            <!-- pôr um botao de procurar um produto ou categoria -->
            
              	<!-- ALERT -->
				 	 <div class="fixed-top">
				 		<div class="alert alert-success" id="success-alert">
				   			 <button type="button" class="close" onclick="$('.alert').hide();">x</button>
				   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
				  		</div>
					</div>

            <div class="container">
                <div class="cartao text-center">
                    <h1 class="titulo card-title mt-3 text-center">Olá, ${user.name}!</h1>
                  	<div class="form-group">
					<div class="main1">
						<div class="mt-3"></div>						
						<div class="SearchBox">
							<input type="text" id="search" class="SearchBox-input" placeholder="Busque por um produto">
						
								<button class="SearchBox-button">
								
									<i class="SearchBox-icon fas fa-search"></i>

								</button>
							
				</div>       
		                    </div>
		                    </div>
                    <div class="form-group" style="margin-top: 3%;">
                        <a href="categorias">
                        <button class="botao btn btn-outline"><i class="fas fa-clipboard fa-sm"></i> Acessar suas listas </button></a>
                    </div>

                    <div class="form-group">
                        <a href="all-products">
                        <button class="botao btn btn-outline"><i class="fas fa-shopping-bag"></i> Todos os produtos </button></a>
                    </div>
                    
                    <div class="form-group">
                        <a href="estatisticas">
                        <button class="botao btn btn"><i class="fas fa-chart-pie"></i> Estatítiscas </button></a>
                    </div>
                    
                     <div class="form-group">
                        <a href="my-account">
                        <button class="botao btn btn-outline"><i class="fas fa-user-circle"></i> Acessar sua conta </button> </a>
                    </div>

                    <div class="form-group">
                        <a href="about.jsp">
                        <button class="botao btn btn-outline"><i class="fas fa-info"></i> Sobre </button></a>
                    </div>

                    <div class="form-group">
                        <a href="help.jsp">
                        <button class="botao btn btn-outline"><i class="fas fa-question"></i> Ajuda </button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>




</body>

<script src="resource/javascript/ajax/searchProdutosHome.js"></script>
<script src="resource/javascript/util/alert.js"></script>


<script type="text/javascript">
if ( window.history.replaceState ) {
	  window.history.replaceState( null, null, window.location.href );
	}

    var oi = "${b80bb7740288fda1f201890375a60c8f}"

</script>

</html>