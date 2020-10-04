<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Home</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


</head>
<body>


<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="categorias">Categorias</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#">Produtos</a>
      </li>
    </ul>
  </div>
</nav>


<!-- pôr um botao de procurar um produto ou categoria -->
<h3>Login Success</h3>
<br><br>
	<h1>Olá, ${user.name}, O que você quer fazer?!</h1>
	<form action="categorias?acao=listar" method="post">
		<input type="submit" value="Acessar suas listas"> 
	</form>

	<form action="logout" method="post"> <input type="submit" value="Logout"/></form>
</body>
</html>