<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	
<link href="resource/css/account.css" type="text/css" rel="stylesheet" />

                    

<title>My Account</title>
</head>
<body class="account">

<header>


<nav class="navbar navbar-expand navbar-dark bg-primary">
      <a class="navbar-brand" href="home.jsp"><img src="resource/img/back.png" width="25px" height="20px"/></a>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Categorias</a>
          </li>
          <li class="nav-item ">
      		<a class="nav-link" href="produtos" >Produtos</a>
      	  </li>
        </ul>
        </div>
       
     
  <button type="button" class="btn btn-outline-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
  <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
  <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
</svg>
  </button>
  <div class="dropdown-menu dropdown-menu-right">
    <a class="dropdown-item" href="#">Action</a>
    <a class="dropdown-item" href="#">Another action</a>
    <a class="dropdown-item" href="#">Something else here</a>
    <div class="dropdown-divider"></div>
    <a class="dropdown-item"  href="logout">Logout</a>
  </div>
</nav>

</header>



	<div class="container">
	
		<div class="card card-signin my-5">
			<article class="card-body mx-auto">
				<h4 class="card-title mt-3 text-center">Sua Conta</h4>
				<div class="text-center">
				  <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-person-square" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M14 1H2a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1zM2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2z"/>
  <path fill-rule="evenodd" d="M2 15v-1c0-1 1-4 6-4s6 3 6 4v1H2zm6-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
</svg>
				</div>
				<span class="input-text"> Nome </span>
					<div class="form-group input-group">
						
						<input name="nome" class="form-control" placeholder="Nome" readonly="readonly"
							type="text" value="${user.name}">
					</div>
					<!-- form-group// -->
					<span class="input-text"> Username</span>
					<div class="form-group input-group">
						<input name="username" class="form-control" placeholder="Username"
							type="text" value="${user.username}" required="required" readonly="readonly">
					</div>
					
					<div style="display: flex; justify-content: space-between;">
						<div class="text-left">
							<a href="index.jsp">Editar</a>
						</div>
					
						<div class="text-right">
							<a href="index.jsp">Mudar senha</a>
						</div>
					
					</div>
			</article>
		</div>
		<!-- card.// -->
	</div>

<hr>
<div class="container">
	
		<div class="card card-signin my-5">
			<article class="card-body mx-auto" style="max-width: 400px;">
			A
			</article>
			</div>
			</div>
	<script type="text/javascript">
		const msg = "${msg}"
		if (msg != null && msg != '') {
			alert(msg)
		}
	</script>
</body>
</html>