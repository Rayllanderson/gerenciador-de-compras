<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=0.9">

<title>Home</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />

<link href="resource/css/wave.css" type="text/css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



<style type="text/css">

		body{
		  background-color: #f8f9fa;
		
		}
	

		body{
		 background-image: url('resource/img/ola/a.jpg');
		 background-repeat: no-repeat;
		 
		}

		
</style>

</head>
<body>



<header>

<nav class="navbar navbar-expand navbar-dark bg-primary">
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link active" href="#">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="categorias">Categorias</a>
          </li>
          <li class="nav-item">
      		<a class="nav-link" href="produtos" >Produtos</a>
      	  </li>
        </ul>
        </div>
       
  
                            <c:if test="${!user.miniatura.isEmpty() && user.miniatura != null}">
                            		<div class="icon-perfil" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            			<input  class="img-perfil" type="image" src="${user.miniatura }" />
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
  
  
  <div class="dropdown-menu dropdown-menu-right">
    <a class="dropdown-item" href="my-account?action=view">Minha Conta</a>
    <a class="dropdown-item" href="#">Another action</a>
    <a class="dropdown-item" href="#">Something else here</a>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item"  href="logout">Logout</a>
  </div>
</nav>

</header>


<!-- pôr um botao de procurar um produto ou categoria -->
<h3>Login Success</h3>
<br><br>

<div class="container" >
<div class="card card-signin my-5" style="border-radius: 1em; box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.6);">
			<article class="card-body mx-auto">
			<h3 class="card-title mt-3 text-center">Olá, ${user.name}, O que você quer fazer?</h3>
						<a class="btn btn-primary" type="submit" href="categorias?acao=listar">Acessar suas listas</a>
				</article>
		</div>
</div>

	
		
</body>
</html>