<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=0.8">
<title>Suas Listas</title>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

 <style type="text/css">
                        body {
                            background-color: #f8f9fa;
                        }
                    </style>
</head>
<body>


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
          <li class="nav-item active">
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



 <div class="card card-signin" style="height: 5%">
                        <ul class="nav justify-content-end">



                            <li class="nav-item">
                                <div class="container" style="height: 50px;">

                                   <button type="submit" data-toggle="modal" data-title="Nova Lista" class="btn btn-success" data-target="#exampleModal"  style="margin-top: 3%" > 
		
<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-cart-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zM4 14a1 1 0 1 1 2 0 1 1 0 0 1-2 0zm7 0a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"/>
</svg> Nova Lista
		 </button>
    </div>
                            </li>
                        </ul>
                    </div>
                    
                    

	<h4 style="text-align: center">Listas</h4>
	
	
	<!-- INICIO NOVA LISTA -->
	
	<!-- FIM NOVA LISTA -->
	
	
	
	
	

	
	
	
	
	
<div class="container-xl">
<main role="main" class="">

<div class="box" style=" display: flex;
            justify-content: space-between;
             margin-top: 10%;
           	 margin-bottom: 1%;
            ">
            <div></div>
<div>
    <form class="form-inline" action="categorias?acao=search" method="post" >
      <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search" title="procure algum produto que queira encontrar" style="width: 80%">
      <button type="submit" class="btn btn-outline-primary my-2 my-sm-0">
      
      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
  <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
</svg>
      
      </button>
    </form>
</div>
<div></div>
</div>


     <div class="table-responsive-lg">	
	<table class="table" id="tabela"  style="border: 1px solid #ddd !important;">
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
					class="btn btn-outline-info"
					data-toggle="modal" data-target="#exampleModal" 
					data-title="Editar"
					data-id="${cat.id}" 
					data-nome="${cat.name}"
					data-orcamento="${cat.getOrcamentoEmReal()}" >
					
<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pen-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
</svg>
					</button> </td>


					<td data-label="Excluir"><button type="submit"class="btn btn-outline-danger"
					onclick="
					if(confirm('Você tem certeza que deseja excluir a lista ${cat.name}?')){
						sendPost('categorias?acao=excluir', {id: '${cat.id}'});
					}">
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
</svg>
					
					</button>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
 </main>
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
				<button type="submit" id="save1" class="btn btn-success" >&nbsp; Salvar &nbsp;</button> 
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

if ( window.history.replaceState ) {
	  window.history.replaceState( null, null, window.location.href );
	}

const msg = "${error}"
	console.log(msg)
	if (msg != null && msg != '') {
		alert(msg)
	}
</script>



<script>
$(document).ready(function(){
   $('#orcamento').mask('000.000.000.000.000,00', {reverse: true});
});
	
</script>

</html>