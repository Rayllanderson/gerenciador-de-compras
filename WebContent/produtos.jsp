<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ray.model.entities.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>

<link href="resource/css/popup.css" type="text/css" rel="stylesheet" />
<link href="resource/css/table.css" type="text/css" rel="stylesheet" />
<link href="resource/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<a href="categorias?acao=voltar">Voltar</a>
	<h1>produtos</h1>
	
	
	
	<form action="produtos?acao=salvar"></form>
	<button type="submit" data-toggle="modal" data-title="Novo Produto"data-target="#exampleModal"  class="btn btn-primary" onclick="disableCheckBox()">Novo Produto</button>
	


	<a href="javascript: abrir()">Funções Úteis</a>
	<a href="#" onmouseover="abrir()" onmouseout="fechar()">Efeitos com
		mouse</a>

	<div id="popup" class="popup">
		<p>ola</p>
		<a id="close" class="close" href="javascript: fechar()">×</a>
	</div>


	<table>
		<caption>${categoria.name}</caption>
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
					<td data-label="Preço Estipulado">${prod.precoEstipulado}</td>
					<td data-label="Preço Real">${prod.precoReal}</td>
					<td data-label="Comprado">
						${prod.comprado()}
					</td>
										
					
					<!-- onclick="sendPost('produtos?acao=editar', {id: '${prod.id}'});" -->
					
					<td data-label="Editar">
					<input type="image"
					src="resource/img/edit.png"
					
					data-toggle="modal" data-target="#exampleModal" 
					data-title="Editar"
					data-id="${prod.id}" 
					data-nome="${prod.nome}"
					data-estipulado="${prod.precoEstipulado}"
					data-real="${prod.precoReal}"
					data-comprado="${prod.comprado}"
					
					width="30px" height="30px" onclick="setCheckedIfTrue('${prod.isComprado()}')"/></td>
					

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


		<!-- Tela Modal -->
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
												<h4 class="modal-title text-center" id="myModalLabel"> ${prod.id} </h4>
											</div>
											<div class="modal-body">
											</div>
										</div>
									</div>
								</div>
		<!-- Fim Tela Modal  -->
	
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="exampleModalLabel"></h4>
			  </div>
			  <div class="modal-body">
			  <form method="POST" action="produtos?acao=salvar">
				  <div class="form-group">
					<label for="recipient-name" class="control-label" style="text-align: center">Nome:</label>
					<input name="nome" type="text" class="form-control" id="recipient-name" value="${prod.name}">
				  </div>
				  
				  
				  <div class="form-group">
					<label for="message-text" class="control-label">Preço Estipulado:</label>
					<input name="estipulado" type="text" class="form-control" id="estipulado" style="width: 50%;">
				  </div>
				  
				  
				  <div class="form-group">
					<label for="message-text" class="control-label">Preço Real:</label>
					<input name="real" type="text" class="form-control" id="real" style="width: 50%;">
				  </div>
				   
				   
				    <div class="form-group">
				  <label for="comprado">Produto
					já foi comprado?</label>
				  <input type="checkbox" id="comprado" name="comprado" onclick="myFunction()" style="margin-left:3px;margin-top:5px; transform: scale(1.5);"
				  
				  		<%Product p = (Product) request.getAttribute("prod");
							if (p != null) {
								if (p.isComprado()) {
									out.print(" ");
									out.print("checked=\"checked\"");
									out.print(" ");
							}
						}%>>
		 
				<span id="text"></span>

				  </div>
				
				
				<input name="id" type="hidden" class="form-control" id="id" value="${prod.id}">
				<button type="submit" class="btn btn-success" >Salvar</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
			  </form>
			  </div>
			</div>
		  </div>
		  </div>
	
	<!-- ---------------------------------------------- -->


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resource/javascript/bootstrap.min.js"></script>

	<script src="resource/javascript/popup.js"></script>
	<script src="resource/javascript/esconderUrl.js"></script>
	
	
	<script type="text/javascript">
		const msg = "${error}"
		console.log(msg)
		if (msg != null && msg != '') {
			alert(msg)
		}
		
		
		function setCheckedIfTrue(funcao){
			if(funcao == 'true'){
				 document.getElementById("comprado").checked = true;
				 myFunction();
			}else{
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
		
		function disableCheckBox(){
			 document.getElementById("comprado").checked = false;
			 myFunction();
		}
		
	</script>
	
	
	
	
		<script type="text/javascript">
		$('#exampleModal').on('show.bs.modal', function (event) {
		  
			
		  var button = $(event.relatedTarget) 
		  var title = button.data('title')
		  var id = button.data('id')
		  var recipientnome = button.data('nome')
		  var estipulado = button.data('estipulado')
		  var real = button.data('real')
		  var comprado = button.data('comprado')
		 
		  
		  var modal = $(this)
		  modal.find('.modal-title').text(title)
		  modal.find('#id').val(id)
		  modal.find('#recipient-name').val(recipientnome)
		  modal.find('#estipulado').val(estipulado)
		  modal.find('#real').val(real)
		  modal.find('#comprado').val(comprado)
		})
	</script>  
	
	
</body>
</html>