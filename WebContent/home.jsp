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
<script src="https://kit.fontawesome.com/7b8bfaf036.js" crossorigin="anonymous"></script>


<style type="text/css">

html {
  box-sizing: border-box;
}
*,*::before,*::after {
  box-sizing: inherit;
}
body {
  margin: 0;
}
.wave-container {
  background: #EEE;
}
.wave-container > svg {
  display: block;
  background: #fff;
}

body {
  margin: 0;
  font-family: system-ui, sans-serif;
}
.wave-container {
  position: relative;
  background: #09F;
  text-align: center;
  overflow: hidden;
}
h3 {
  font-size: 5rem;
  margin: 7rem 1.25rem 2.5rem 1.25rem;
}
p {
  font-size: 1.5rem;
  margin: 0 1.25rem 5rem 1.25rem;
}

@keyframes animateWave {
  0% {
    transform: scale(1,0);
  }
  100% {
    transform: scale(1,1);
  }
}
.wave-container > svg {
  display: block;
  transform-origin: bottom;
  animation: animateWave 1000ms cubic-bezier(0.23, 1, 0.32, 1) forwards;
}
	

.form-label-group input {
  height: auto;
  border-radius: 2rem;
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
            <a class="nav-link active" href="#"><i class="fas fa-home fa-md"></i> Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="categorias"><i class="fas fa-clipboard fa-sm"></i> Listas</a>
          </li>
          <li class="nav-item">
      		<a class="nav-link" href="produtos" ><i class="fas fa-shopping-cart fa-sm"></i> Produtos</a>
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
<div class="wave-container">
<div class="container" style="border-radius: 2em;">
<div class="card card-signin my-5" style="border-radius: 1em; box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.3);">
			<article class="card-body mx-auto">
			<h1 class="card-title mt-3 text-center">Olá, ${user.name}, Seja bem vindo!</h1>
						<div class="form-group">
						<a class="btn btn-primary" type="submit" href="categorias?acao=listar"><i class="fas fa-clipboard fa-sm"></i> Acessar suas listas</a>
						</div>
						
						<div class="form-group">
						<a class="btn btn-primary" type="submit" href="my-account"><i class="fas fa-clipboard fa-sm"></i> Acessar sua conta</a>
						 </div>
						 
						 <div class="form-group">
						<a class="btn btn-primary" type="submit" href="#"><i class="fas fa-clipboard fa-sm"></i> Acessar estatítiscas</a>
						</div>
						
						<div class="form-group">
						<a class="btn btn-primary" type="submit" href="#"><i class="fas fa-clipboard fa-sm"></i> Todos os seus produtos</a>
						</div>
						
						<div class="form-group">
						<a class="btn btn-primary" type="submit" href="#"><i class="fas fa-clipboard fa-sm"></i> Sobre</a>
						</div>
						
						<div class="form-group">
						<a class="btn btn-primary" type="submit" href="#"><i class="fas fa-clipboard fa-sm"></i> Ajuda</a>
						</div>
				</article>
		</div>
		 
</div>
<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#0099ff" fill-opacity="1" d="M0,192L48,170.7C96,149,192,107,288,85.3C384,64,480,64,576,106.7C672,149,768,235,864,266.7C960,299,1056,277,1152,234.7C1248,192,1344,128,1392,96L1440,64L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"></path></svg>
</div>
	

</body>

<script type="text/javascript">
	
	var oi = "${b80bb7740288fda1f201890375a60c8f}"
	console.log(oi)

</script>

</html>