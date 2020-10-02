<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ray.model.entities.Product"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=0.7">
<title>Seus Produtos</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="resource/javascript/jquery.mask.min.js"></script>


</head>

<body>


<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
  <a class="navbar-brand" href="categorias.jsp"><img src="resource/img/back.png" width="25px" height="20px"/></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="categorias">Categorias</a>
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="#">Produtos</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="produtos?acao=search" method="post">
      <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search">
      <button type="submit" class="btn btn-outline-light my-2 my-sm-0">Search</button>
    </form>
  </div>
</nav>

	<img data-toggle="tooltip" data-placement="right"
		title="texto texto texto fjshfjbvbdjvdiav  ifhifhuhvdshvshvshvjshv"
		src="resource/img/question.png" width="20px" height="20px">


	<h1>Produtos</h1>

	<div>
		<!--  -->

		<button type="submit" data-toggle="modal" data-title="Novo Produto"
			data-target="#exampleModal" class="btn btn-success"
			onclick="disableCheckBox()">Novo Produto</button>

		<a class="btn btn-primary" data-toggle="collapse"
			href="#collapseExample" role="button" aria-expanded="false"
			aria-controls="collapseExample"> Informações </a>


		<div class="collapse" id="collapseExample">
			<div class="card card-body">
				<div class="accordion" id="accordionExample">
					<div class="card">
						<div class="card-header" id="headingOne">
							<h2 class="mb-0">
								<button class="btn btn-link btn-block text-center" type="button"
									data-toggle="collapse" data-target="#collapseOne"
									aria-expanded="true" aria-controls="collapseOne">Gerais</button>
							</h2>
						</div>
						<div id="collapseOne" class="collapse show"
							aria-labelledby="headingOne" data-parent="#accordionExample"
							style="text-align: center;">
							<div class="card-body"
								style="display: inline-block; text-align: left;">${gerais}</div>
						</div>
					</div>


					<div class="accordion" id="accordionExample">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h2 class="mb-0">
									<button class="btn btn-link btn-block text-center"
										type="button" data-toggle="collapse" data-target="#collapse3"
										aria-expanded="true" aria-controls="collapseOne">
										Quanto você Já economizou</button>
								</h2>
							</div>
							<div id="collapse3" class="collapse hide"
								aria-labelledby="headingOne" data-parent="#accordionExample"
								style="text-align: center;">
								<div class="card-body"
									style="display: inline-block; text-align: left;">${economizado}</div>
							</div>
						</div>
					</div>


					<div class="accordion" id="accordionExample">
						<div class="card">
							<div class="card-header" id="headingOne">
								<h2 class="mb-0">
									<button class="btn btn-link btn-block text-center"
										type="button" data-toggle="collapse"
										data-target="#collapseTwo" aria-expanded="true"
										aria-controls="collapseOne">Quanto disponível você
										tem pra gastar</button>
								</h2>
							</div>
							<div id="collapseTwo" class="collapse hide"
								aria-labelledby="headingOne" data-parent="#accordionExample"
								style="text-align: center;">
								<div class="card-body"
									style="display: inline-block; text-align: left;">${disponivel}</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- fim card -->
		</div>
		<!-- tela de infos -->
	</div>



	<h3 style="text-align: center">${categoria.name}</h3>

	<div class="table-responsive-xl">
		<table class="table" id="table">

			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Preço Estipulado</th>
					<th scope="col">Preço Real</th>
					<th scope="col">Comprado</th>
					<th scope="col">Editar</th>
					<th scope="col">Excluir</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${produtos}" var="prod">
					<tr>
						<td data-label="Nome">${prod.nome}</td>
						<td data-label="Preço Estipulado">R$ <fmt:formatNumber
								type="number" maxFractionDigits="3"
								value="${prod.precoEstipulado}" /></td>
						<td data-label="Preço Real">R$ <fmt:formatNumber
								type="number" maxFractionDigits="2" value="${prod.precoReal}" /></td>
						<td data-label="Comprado">${prod.comprado()}</td>


						<!-- onclick="sendPost('produtos?acao=editar', {id: '${prod.id}'});" -->

						<td data-label="Editar"><input type="image"
							src="resource/img/edit.png" data-toggle="modal"
							data-target="#exampleModal" data-title="Editar"
							data-id="${prod.id}" data-nome="${prod.nome}"
							data-estipulado="${prod.getValorEstipuladoEmReal()}"
							data-real="${prod.getValorRealEmReal()}"
							data-comprado="${prod.comprado}"
							<c:set var="nomeCategoria" scope="session" value="${prod.categoria.name}"/>
							width="30px" height="30px"
							onclick="setCheckedIfTrue('${prod.isComprado()}')" /></td>


						<td data-label="Excluir"><input type="image"
							onclick="
					if(confirm('Você tem certeza que deseja excluir o produto ${prod.nome}?')){
						sendPost('produtos?acao=excluir', {id: '${prod.id}'});
					}"
							src="resource/img/excluir.png" width="30px" height="30px" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<form id="form2" method="POST">
		<div class="text-center" style="margin-top: 1%">
					<div class="btn-group" role="group" aria-label="Basic example" >
							<button type="submit" class="btn btn-success" title="Lista todos os produtos comprados dessa lista"
							onclick="document.getElementById('form2').action = 'produtos?acao=comprados'"
							>Produtos
								Comprados</button>

							<button type="submit" id="todos" class="btn btn-info"
								title="Lista todos os produtos dessa lista"
								onclick="document.getElementById('form2').action = 'produtos?acao=listar'"
								>Todos os
								Produtos</button> 
								
							<button type="submit" id="n-comprados" class="btn btn-danger"
								title="Lista todos os produtos não comprados dessa lista"
								onclick="document.getElementById('form2').action = 'produtos?acao=nao_comprados'"
								>Produtos
								não Comprados</button>

					</div>
				</div>
				</form>
	</div>
	<!-- fim tabela -->


	<!-- Tela Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title text-center" id="myModalLabel">
						${prod.id}</h4>
				</div>
				<div class="modal-body"></div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel"></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>

				</div>
				<div class="modal-body">
					<form method="POST" action="produtos?acao=salvar">

						<div class="form-group">
							<label for="recipient-name" class="control-label"
								style="text-align: center">Nome:</label> <input name="nome"
								type="text" class="form-control" id="recipient-name"
								required="required" value="${prod.name}">
						</div>


						<div class="form-group">
							<label for="message-text" class="control-label">Preço
								Estipulado:</label> <input name="estipulado" type="text"
								class="form-control" id="estipulado" required="required"
								style="width: 50%;" inputmode="numeric">
						</div>


						<div class="form-group">
							<label for="message-text" class="control-label">Preço
								Real:</label> <input name="real" type="text" class="form-control"
								id="real" style="width: 50%;" data-thousands="."
								data-decimal="," data-prefix="R$ " inputmode="numeric">
						</div>


						<div id="lista" class="form-group"
							style="display: none; width: 50%">
							<label class="message-text" for="categoria">Lista</label> <select
								id="categoria" name="cat_id" class="custom-select mr-sm-2">
								<c:forEach items="${categorias}" var="cat">
									<option value="${cat.id}" id="${cat.id}"
										<c:if test="${cat.name ==nomeCategoria}">
 			 					<c:out value="selected=selected"/> </c:if>>${cat.name}
									</option>
								</c:forEach>
							</select>
						</div>

						<div class="form-group">
							<label for="comprado">Produto já foi comprado?</label> <input
								type="checkbox" id="comprado" name="comprado"
								onclick="myFunction()"
								style="margin-left: 3px; margin-top: 5px; transform: scale(1.5);">

							<span id="text"></span>

						</div>

						<input name="id" type="hidden" class="form-control" id="id"
							value="${prod.id}">
						<button type="submit" class="btn btn-success">Salvar</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Fim Tela Modal  -->

	<!-- ---------------------------------------------- -->
	
	
	
	
<script type="text/javascript">

function buscaDeGrupoPorID(nome, categoria){	
	jQuery.ajax({
	    type:  'POST',
	    url:   'produtos?acao=nao_comprados',
	    data: {
	    	nome: nome,
	    	categoria : catId
	    },
	    success: function(data){	
	    	alert(deu certo?)
	    	$("#table").html(data);		    	
	     }
	});		
}

</script>	
	
	
	


	<script src="resource/javascript/esconderUrl.js"></script>

	<script type="text/javascript">
		const msg = "${error}"
		console.log(msg)
		if (msg != null && msg != '') {
			alert(msg)
		}

		function setCheckedIfTrue(funcao) {
			if (funcao == 'true') {
				document.getElementById("comprado").checked = true;
				myFunction();
			} else {
				document.getElementById("comprado").checked = false;
				myFunction();
			}
		}

		myFunction();

		function myFunction() {
			var checkBox = document.getElementById("comprado");
			var text = document.getElementById("text");
			if (checkBox.checked == true) {
				text.innerHTML = 'Sim'
			} else {
				text.innerHTML = 'Não'
			}
		}

		function disableCheckBox() {
			document.getElementById("comprado").checked = false;
			myFunction();
		}
	</script>

	<script type="text/javascript">
		$('#exampleModal')
				.on(
						'show.bs.modal',
						function(event) {
							var button = $(event.relatedTarget)
							var title = button.data('title')
							var id = button.data('id')
							var recipientnome = button.data('nome')
							var estipulado = button.data('estipulado')
							var real = button.data('real')
							var comprado = button.data('comprado')

							if (title == 'Editar') {
								const showList = document
										.getElementById("lista").style.display = 'block';
							} else {
								const showList = document
										.getElementById("lista").style.display = 'none';
							}

							var modal = $(this)
							modal.find('.modal-title').text(title)
							modal.find('#id').val(id)
							modal.find('#recipient-name').val(recipientnome)
							modal.find('#estipulado').val(estipulado)
							modal.find('#real').val(real)
							// modal.find('#comprado').val(comprado)
							// modal.find('#categoria').val(catId)
						})
	</script>


</body>


<script>
	$(document).ready(function() {
		$('#real').mask('000.000.000.000.000,00', {
			reverse : true
		});
	});
	$(document).ready(function() {
		$('#estipulado').mask('#.##0,00', {
			reverse : true
		});
	});
</script>
</html>