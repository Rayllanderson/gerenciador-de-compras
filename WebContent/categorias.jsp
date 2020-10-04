<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=0.9">
<title>Suas Listas</title>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">


</head>
<body>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
  <a class="navbar-brand" href="home.jsp"><img src="resource/img/back.png" width="25px" height="20px"/></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Categorias</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#">Produtos</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="categorias?acao=search" method="post">
      <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search" title="procure alguma categoria que queira encontrar">
      <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>


	<h1>Listas</h1>
	
	
	
  	<div class="alert alert-success" id="success-alert">
   		 <button type="button" class="close" data-dismiss="alert">x</button>
   	 <strong>Sucesso! </strong> Sua lista foi salva!</div>
	
	
	<button type="submit" data-toggle="modal" data-title="Nova Lista" class="btn btn-success" data-target="#exampleModal">Nova
		Lista </button>
		
	<h3 style="text-align: center">Listas</h3>
	
	
	<div class="table-responsive-lg">	
	<table class="table" id="tabela">
		<thead>
			<tr>
				<th scope="col" style="text-align: center">Nome</th>
				<th scope="col">Orçamento</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody id="tabela">
			<c:forEach items="${categorias}" var="cat">
				<tr>
					<td data-label="Nome">
					<button class="btn btn-light"
					onclick="sendPost('categorias?acao=selecionar', {id: '${cat.id}'});"
					style="width: 100%;">${cat.name}</button>
					</td>
					
					<td data-label="Orçamento">R$ <fmt:formatNumber type="number" maxFractionDigits="2" value="${cat.orcamento}"/></td>
					
					
					<!-- onclick="sendPost('categorias?acao=editar', {id: '${cat.id}'});" -->
					
					<td data-label="Editar">
					
					<button
					class="btn btn-info"
					data-toggle="modal" data-target="#exampleModal" 
					data-title="Editar"
					data-id="${cat.id}" 
					data-nome="${cat.name}"
					data-orcamento="${cat.getOrcamentoEmReal()}" >Editar</button> </td>


					<td data-label="Excluir"><button type="submit"class="btn btn-danger"
					onclick="
					if(confirm('Você tem certeza que deseja excluir a lista ${cat.name}?')){
						sendPost('categorias?acao=excluir', {id: '${cat.id}'});
					}">Excluir</button>
					
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
				<h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  </div>
			  <div class="modal-body">
			  <form method="POST" action="categorias?acao=salvar">
				  <div class="form-group">
					<label for="recipient-name" class="control-label">Nome:</label>
					<input name="nomeLista" type="text" class="form-control" id="recipient-name" required="required" value="${cat.name}">
				  </div>
				  <div class="form-group">
					<label for="message-text" class="control-label">Orcamento:</label>
					<input name="orcamento" type="text" class="form-control" id="orcamento" style="width: 50%" inputmode="numeric">
				  </div>
				<input name="id" type="hidden" class="form-control" id="id" value="${cat.id}">
				<button type="submit" id="save1" class="btn btn-success" >Salvar</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
			  </form>
			  </div>
			</div>
		  </div>
		  </div>
	
	

<script src="resource/javascript/esconderUrl.js"></script>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<script src="resource/javascript/jquery.mask.min.js"></script>
    
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

<script type="text/javascript">

const msg = "${error}"
	console.log(msg)
	if (msg != null && msg != '') {
		alert(msg)
	}

$(".alert").hide();
$('#save1').click( function() { 
  $(".alert").show();
  $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
});


</script>



<script>
$(document).ready(function(){
   $('#orcamento').mask('000.000.000.000.000,00', {reverse: true});
});
	
</script>

</html>