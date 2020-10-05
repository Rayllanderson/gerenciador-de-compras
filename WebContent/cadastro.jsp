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
<link href="resource/css/login.css" type="text/css" rel="stylesheet" />
<link href="resource/css/cadastro.css" type="text/css" rel="stylesheet" />

<title>Cadastrar</title>
</head>
<body>

	<div class="container">

		<hr>

		<div class="card card-signin my-5">
			<article class="card-body mx-auto" style="max-width: 400px;"
				style="background-color: black">
				<h4 class="card-title mt-3 text-center">Criar Conta</h4>
				<p class="text-center">Comece com sua conta gratuita</p>
				<form action="cadastro" method="POST">
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<input name="nome" class="form-control" placeholder="Nome"
							type="text" value="${nome}">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i
								class="fa fa-user-circle" aria-hidden="true"></i>
							</span>
						</div>
						<input name="username" class="form-control" placeholder="Username"
							type="text" value="${username}" required="required">
					</div>
					<!-- form-group// -->

					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="password" class="form-control" placeholder="Senha"
							type="password" required="required">
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i>
							</span>
						</div>
						<input name="password2" class="form-control"
							placeholder="Repita a Senha" type="password" required="required">
					</div>
					<!-- form-group// -->
					<div class="form-group">
						<button type="submit" class="btn btn-primary btn-block">
							Criar Conta</button>
					</div>
					<!-- form-group// -->
					<p class="text-center">
						Tem uma conta? <a href="index.jsp">Conecte-se</a>
					</p>
				</form>
			</article>
		</div>
		<!-- card.// -->
	</div>

	<script type="text/javascript">
		const msg = "${msg}"
		if (msg != null && msg != '') {
			alert(msg)
		}
	</script>
</body>
</html>