<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.9">

<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link href="resource/css/icon-perfil.css" type="text/css"
	rel="stylesheet" />
<link href="resource/css/alert.css" type="text/css" rel="stylesheet" />
<link href="resource/css/wave.css" type="text/css" rel="stylesheet" />
<link href="resource/css/account.css" type="text/css" rel="stylesheet" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="resource/javascript/util/fa.js"></script>

<title>My Account</title>
</head>
<body class="account">

	<div class="content">
		<header>

			<nav class="navbar navbar-expand navbar-dark">
				<a class="navbar-brand" href="home.jsp"> <i
					class="fas fa-arrow-left fa-md"></i> <!-- <img src="resource/img/back.png" width="25px" height="20px" /></a>  -->
				</a>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarsExample02" aria-controls="navbarsExample02"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarsExample02">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link" href="home.jsp">
								<i class="fas fa-home fa-sm"></i> Home
						</a></li>
						<li class="nav-item"><a class="nav-link" href="categorias">
								<i class="fas fa-clipboard fa-sm"></i> Listas
						</a></li>
						<li class="nav-item"><a class="nav-link" href="produtos"><i
								class="fas fa-shopping-cart fa-sm"></i> Produtos</a></li>
					</ul>
				</div>




				<c:if test="${!user.miniatura.isEmpty() && user.miniatura != null}">


					<div class="icon-perfil" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
						<input class="img-perfil" type="image" src="${user.miniatura }" />
					</div>


				</c:if>

				<c:if test="${user.miniatura.isEmpty() || user.miniatura == null}">
					<button type="button" class="btn btn-outline-light"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<!-- botao user -->
						<svg width="1em" height="1em" viewBox="0 0 16 16"
							class="bi bi-person-circle" fill="currentColor"
							xmlns="http://www.w3.org/2000/svg">
									  <path
								d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z" />
									  <path fill-rule="evenodd"
								d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
									  <path fill-rule="evenodd"
								d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z" />
								</svg>
					</button>

				</c:if>


				<div class="dropdown-menu dropdown-menu-right"
					style="border-radius: 1em;">
					<a class="dropdown-item" href="#"> <i
						class="fas fa-user-circle"></i> Minha Conta
					</a> <a class="dropdown-item" href="estatisticas"> <i
						class="fas fa-chart-pie"></i> Estatísticas
					</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="logout"><i
						class="fas fa-sign-out-alt"></i>Logout</a>
				</div>

			</nav>
		</header>


		<div class="fixed-top">
			<div class="alert alert-success" id="success-alert">
				<button type="button" class="close" onclick="$('.alert').hide();">x</button>
				<h4 id="titulo"></h4>
				<p id="alertMsg"></p>
			</div>
		</div>

		<div class="wrapper" id="wrapper">

			<div class="container">

				<div class="card card-signin my-5" style="border-radius: 1em">
					<article class="card-body mx-auto">
						<h4 class="card-title mt-3 text-center">Sua Conta</h4>
						<div class="text-center"></div>


						<form class="" action="my-account?action=editar" method="POST"
							id="formUser" enctype="multipart/form-data">
							<article class="roll">
								<div class="box">
									<label class="avatar" for="btn"> <!--  NAO POSSUI FOTO  -->
										<c:if
											test="${user.getFoto().isEmpty() || user.getFoto() == null}">
											<div class="without-photo" style="display: none"></div>
											<img id="target" src="resource/img/user.png" width="260"
												height="260"
												style="border-radius: 50%; border: 1px solid gray;"
												title="Nova foto de perfil" alt="Imagem de perfil">
										</c:if> <!--  USER POSSUI FOTO  --> <c:if
											test="${!user.getFoto().isEmpty() && user.getFoto() != null}">
											<img id="target" src="<c:out value="${user.getFoto()}"/>"
												width="260" height="260" style="border-radius: 50%;"
												alt="Imagem de perfil" title="Nova foto de perfil">

										</c:if>
									</label>



									<button id="btn" type="button" style="display: none;">A</button>
									<div class="card" id="menu">

										<label class="yo dropdown-item text-info" id="label-file"
											for="file" style="cursor: pointer; text-decoration: none">

											<i class="fas fa-camera fa-fw"></i>Upload <input id="file"
											type="file" name="file" style="display: none"
											onchange="upload()" accept="image/*" />

										</label>

										<div class="yo dropdown-item" id="remove" data-toggle="modal"
											data-target="#exampleModalCenter">
											<a class="text-danger"><i class="fas fa-times"> <span
													class="items">Remove</span></i></a>
										</div>
									</div>

								</div>
							</article>

							<br>
							<div>

								<input value="${user.id}" name="id" hidden="true" id="id">
								<span class="input-text"> Nome </span>
								<div class="form-group input-group">
									<input name="nome" class="form-control" placeholder="Nome"
										type="text" value="${user.name}" name="nome" id="nome"
										maxlength="130">
								</div>
								<!-- form-group// -->
								<span class="input-text"> Username</span>
								<div class="form-group input-group">
									<input name="username" class="form-control"
										placeholder="Username" type="text" value="${user.username}"
										required="required" name="username" id="username"
										maxlength="180">
								</div>

								<div style="display: flex; justify-content: space-between;">
									<div class="text-left">

										<input id="" class="btn btn-success" type="submit"
											value="&nbsp; Salvar &nbsp;" />
									</div>

									<div class="text-right">
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#exampleModal">Mudar
											senha</button>
									</div>
								</div>
							</div>
						</form>
					</article>


				</div>
				<!-- card.// -->
			</div>



			<!-- Modal confirmar remover foto -->
			<div class="modal fade" id="exampleModalCenter" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalCenterTitle"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title text-danger" id="exampleModalLongTitle">
								Atenção <i class="fas fa-exclamation-triangle"></i>
							</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">Você tem certeza que deseja remover
							sua foto?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Cancelar</button>
							<button type="button" class="btn btn-danger" id="excluir">Excluir</button>
						</div>
					</div>
				</div>
			</div>



			<!-- MODAL DE MUDAR SENHA -->

			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body"></div>
					</div>
				</div>
			</div>


			<div class="modal fade" id="exampleModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Alterar Senha</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>



						</div>

						<div class="alert alert-success" id="alertE">
							<button type="button" class="close"
								onclick="$('#alertE').hide();">x</button>
							<h4 id="titulo"></h4>
							<p id="alertMsgE"></p>
						</div>

						<div class="modal-body">

							<div class="form-group">
								<label for="old" class="control-label"
									style="text-align: center">Senha Atual:</label>
								<div class="input-group" id="show_hide_password">
									<input type="password" name="old" type="text"
										class="form-control" id="old" required="required"
										maxlength="130">
									<div class="input-group-append">
										<a class="input-group-text"
											style="cursor: pointer; text-decoration: none"><i
											class="fa fa-eye-slash" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label for="new1" class="control-label"
									style="text-align: center">Nova Senha:</label>
								<div class="input-group" id="show_hide_password2">
									<input type="password" name="new1" type="text"
										class="form-control" id="new1" required="required"
										maxlength="130">
									<div class="input-group-append">
										<a class="input-group-text"
											style="cursor: pointer; text-decoration: none"><i
											class="fa fa-eye-slash" aria-hidden="true"></i></a>
									</div>
								</div>
							</div>


							<div class="form-group">
								<label for="new2" class="control-label"
									style="text-align: center">Confirme a Nova Senha:</label>
								<div class="input-group" id="show_hide_password3">
									<input type="password" name="new2" type="text"
										class="form-control" id="new2" required="required"
										maxlength="130">
									<div class="input-group-append">
										<a class="input-group-text"
											style="cursor: pointer; text-decoration: none"><i
											class="fa fa-eye-slash" aria-hidden="true"></i></a>
									</div>
								</div>

							</div>

						</div>

						<div class="modal-footer">
							<button id="change-pass" type="submit" class="btn btn-success">&nbsp;
								Salvar &nbsp;</button>
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Cancelar</button>
						</div>
					</div>
				</div>
			</div>

		</div>


	</div>

	<footer class="footer">
		<svg class="wave-svg" viewBox="0 0 120 28">
 <defs> 
   <mask id="xxx">
     <circle cx="7" cy="12" r="40" fill="#fff" />
   </mask>
   
   <filter id="goo">
      <feGaussianBlur in="SourceGraphic" stdDeviation="2" result="blur" />
      <feColorMatrix in="blur" mode="matrix"
				values="
           1 0 0 0 0  
           0 1 0 0 0  
           0 0 1 0 0  
           0 0 0 13 -9"
				result="goo" />
      <feBlend in="SourceGraphic" in2="goo" />
  	</filter>
     <path id="wave"
				d="M 0,10 C 30,10 30,15 60,15 90,15 90,10 120,10 150,10 150,15 180,15 210,15 210,10 240,10 v 28 h -240 z" />
  </defs> 

   <use id="wave3" class="wave" xlink:href="#wave" x="0" y="-2"></use> 
   <use id="wave2" class="wave" xlink:href="#wave" x="0" y="0"></use>
 

  </g>
  <g class="gooeff">
  <circle class="drop drop1" cx="20" cy="2" r="1.8" />
  <circle class="drop drop2" cx="25" cy="2.5" r="1.5" />
  <circle class="drop drop3" cx="16" cy="2.8" r="1.2" />
    <use id="wave1" class="wave" xlink:href="#wave" x="0" y="1" />

</svg>

	</footer>




	<script type="text/javascript">
	$(".alert").hide();

	var error = "${error}"
	var success = "${success}"
    </script>



	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script src="resource/javascript/ajax/accountAjax.js"></script>
	<script src="resource/javascript/remover-foto.js"></script>
	<script src="resource/javascript/account.js"></script>


</body>
</html>