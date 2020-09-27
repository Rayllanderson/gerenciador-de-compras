<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>
<link href="resource/css/table.css" type="text/css" rel="stylesheet" />
<link href="resource/css/btn.css" type="text/css" rel="stylesheet" />
<link href="resource/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<a href="home.jsp">Voltar</a>
	<h1>Listas</h1>
	
	<form action="categorias?acao=newList"></form>
	<button type="submit" data-toggle="modal" data-title="Nova Lista"data-target="#exampleModal">Nova
		Lista </button>
		
		
	<table>
		<caption>Listas</caption>
		<thead>
			<tr>
				<th scope="col">Nome</th>
				<th scope="col">Orçamento</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categorias}" var="cat">
				<tr>
					<td data-label="Nome">
					<button class="btn btn-info"
					onclick="sendPost('categorias?acao=selecionar', {id: '${cat.id}'});"
					style="width: 50%; color: white;">${cat.name}</button>
					</td>
					
					<td data-label="Orçamento">${cat.orcamento}</td>
					
					
					<!-- onclick="sendPost('categorias?acao=editar', {id: '${cat.id}'});" -->
					
					<td data-label="Editar"><input type="image"
					src="resource/img/edit.png" width="30px" height="30px" class="btn btn-xs btn-warning" data-toggle="modal" data-target="#exampleModal" 
					data-title="Editar"
					data-id="${cat.id}" 
					data-nome="${cat.name}"
					data-orcamento="${cat.orcamento}" /></td>


					<td data-label="Excluir"><input type="image"
					onclick="
					if(confirm('Você tem certeza que deseja excluir a lista ${cat.name}?')){
						sendPost('categorias?acao=excluir', {id: '${cat.id}'});
					}" src="resource/img/excluir.png" width="30px" height="30px" />
					
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
												<h4 class="modal-title text-center" id="myModalLabel"> ${cat.id} </h4>
											</div>
											<div class="modal-body">
											</div>
										</div>
									</div>
								</div>
		<!-- Fim Tela Modal 
		
		 onclick="sendPost('categorias?acao=salvar', {id: '${cat.id}'});"
		-->
		
		
		
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
			<div class="modal-content">
			  <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title" id="exampleModalLabel">Editar</h4>
			  </div>
			  <div class="modal-body">
			  <form method="POST" action="categorias?acao=salvar">
				  <div class="form-group">
					<label for="recipient-name" class="control-label">Nome:</label>
					<input name="nomeLista" type="text" class="form-control" id="recipient-name" value="${cat.name}">
				  </div>
				  <div class="form-group">
					<label for="message-text" class="control-label">Orcamento:</label>
					<input name="orcamento" type="text" class="form-control" id="orcamento">
				  </div>
				<input name="id" type="hidden" class="form-control" id="id" value="${cat.id}">
				<button type="submit" class="btn btn-success" >Salvar</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
			  </form>
			  </div>
			</div>
		  </div>
		  </div>
	
	
	<script src="resource/javascript/esconderUrl.js"></script>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resource/javascript/bootstrap.min.js"></script>
	<script type="text/javascript">
		$('#exampleModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var title = button.data('title')
		  var id = button.data('id') // Extract info from data-* attributes
		  var recipientnome = button.data('nome')
		  var recipientOrcamento = button.data('orcamento')
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  modal.find('.modal-title').text(title)
		  modal.find('#id').val(id)
		  modal.find('#recipient-name').val(recipientnome)
		  modal.find('#orcamento').val(recipientOrcamento)
		})
	</script>  
	
</body>
</html>