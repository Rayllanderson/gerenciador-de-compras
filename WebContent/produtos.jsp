<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ray.model.entities.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=0.7">
<title>Suas Listas</title>

<link href="resource/css/popup.css" type="text/css" rel="stylesheet" />


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
	<a href="categorias?acao=voltar">Voltar</a>
	<h1>produtos</h1>
	
	
	
	<form action="produtos?acao=salvar"></form>
	<button type="submit" data-toggle="modal" data-title="Novo Produto"data-target="#exampleModal"  class="btn btn-success" onclick="disableCheckBox()">Novo Produto</button>
	


	<a href="javascript: abrir()">Funções Úteis</a>
	<a href="#" onmouseover="abrir()" onmouseout="fechar()">Efeitos com
		mouse</a>

	<div id="popup" class="popup">
		<p>ola</p>
		<a id="close" class="close" href="javascript: fechar()">×</a>
	</div>

<h3 style="text-align: center">${categoria.name}</h3>

<div class="table-responsive-xl">
  	<table class="table">
		
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
					data-catId="${prod.precoReal}"
					<c:set var="nomeCategoria" scope="session" value="${prod.categoria.name}"/>
										
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
</div>

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
	
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog" role="document">
			<div class="modal-content">
			  <div class="modal-header">
				 <h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
 
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
				   

			<div id="lista" class="form-group" style="display: none; width: 50%">
			<label class="message-text" for="categoria">Lista</label>
				<select id="categoria" name="cat_id" class="custom-select mr-sm-2">
					<c:forEach items="${categorias}" var="cat"  >
 						 <option value="${cat.id}" id="${cat.id}" 
 							 <c:if test="${cat.name ==nomeCategoria}">
 			 					<c:out value="selected=selected"/> </c:if>
 								 	>${cat.name}
 						</option>
 				</c:forEach>
				</select>
			 </div> 
				   
				    <div class="form-group">
				  <label for="comprado">Produto
					já foi comprado?</label>
				  <input type="checkbox" id="comprado" name="comprado" onclick="myFunction()" style="margin-left:3px;margin-top:5px; transform: scale(1.5);">
		 
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



<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
    

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
		  
		  if(title == 'Editar'){
			  const showList = document.getElementById("lista").style.display='block';
		  }else{
			  const showList = document.getElementById("lista").style.display='none';
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
</html>