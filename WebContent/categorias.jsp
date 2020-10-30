<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=0.88">
<title>Suas Listas</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link href="resource/css/icons-themes.css" type="text/css" rel="stylesheet" />
<link href="resource/css/custom-categoria-theme.css" type="text/css" rel="stylesheet" />
<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
<link href="resource/css/alert.css" type="text/css" rel="stylesheet" />
<link href="resource/css/footer.css" type="text/css" rel="stylesheet" />	 


<script src="resource/javascript/util/fa.js"></script>

 <style type="text/css">
                      
                        body {
                       	background-color: #f8f9fa;
                       
                        }
                        
                        html, body{
                        	height: 100%;
                        }
                        
                        .content{
						min-height: 100%;
						position: relative;
						}		
                       
                    </style>
                    
</head>
<body>

<div class="content">


<div class="wall" id="wall">

<header>
	 <nav class="navbar navbar-expand navbar-dark" id="navbar">
      <a class="navbar-brand" href="home.jsp"><i class="fas fa-arrow-left fa-md"></i></a>
      
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample02">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="home.jsp"> <i class="fas fa-home fa-sm"></i> Home</a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#"><i class="fas fa-clipboard fa-md"></i> Listas</a>
          </li>
          <li class="nav-item ">
      		<a class="nav-link" href="produtos" ><i class="fas fa-shopping-cart fa-sm"></i> Produtos</a>
      	  </li>
        </ul>
        </div>

                         
                       <c:if test="${!user.miniatura.isEmpty() && user.miniatura != null}">
								<div class="icon-perfil" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            			<input  class="img-perfil" type="image" src="${user.miniatura }" />
                            	</div>
                       </c:if>
                            
                            
                             <c:if test="${user.miniatura.isEmpty() || user.miniatura == null}">
								                            <button type="button" class="btn btn-outline-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <!-- botao user -->
								    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								  <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
								  <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
								  <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
								</svg>
								  </button>
							</c:if>
	
                            <div class="dropdown-menu dropdown-menu-right" style="border-radius: 1em;">
                            
                           
                            
                                <a class="dropdown-item" href="my-account?action=view">Minha Conta</a>
                                <a class="dropdown-item" href="estatisticas">Estatísticas</a>
                                <div class="dropdown-divider"></div>
                                <h6 class="dropdown-header">Temas</h6>
                                <div class= "items">
                               <!-- <a class="dropdown-item disabled" data-toggle="modal" data-target="#temaModal">Temas</a> -->
	                                <div class="theme blue" onclick="swapColor('blue', true, 'categoria')"></div>
									<div class="theme pink" onclick="swapColor('pink', true, 'categoria')"></div>
									<div class="theme red" onclick="swapColor('red', true, 'categoria')"></div>
									<div class="theme purple" onclick="swapColor('purple', true, 'categoria')"></div>
									<div class="theme cian" onclick="swapColor('cian', true, 'categoria')"></div>
									<br>
									<div class="theme green" onclick="swapColor('green', true, 'categoria')"></div>
									<div class="theme orange" onclick="swapColor('orange', true, 'categoria')"></div>
									<div class="theme black" onclick="swapColor('black', true, 'categoria')"></div>
									<div class="theme galaxy" onclick="swapColor('galaxy', true, 'categoria')" style=" background-size: cover;
						background-repeat: no-repeat;"></div>
									<div class="theme icon-default" onclick="swapColor('default', true, 'categoria')"></div>
								</div>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>
                        </nav>


</header>

                    	<!-- ALERT -->
				 	  <div class="fixed-top">
				 		<div class="alert alert-success" id="success-alert">
				   			 <button type="button" class="close"onclick="$('.alert').hide();">x</button>
				   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
				  		</div>
					</div>


<!-- 2navbar -->

 <div class="navbar navbar-expand navbar-dark justify-content-end" id="seccond-navbar">
                        <ul class="nav justify-content-end">


                            <li class="nav-item">
                                <div class="container" style="height: 50px;">

                                   <button type="submit" id="btn-new-list" data-toggle="modal" data-title="Nova Lista" class="btn btn-outline-success" data-target="#categoriaModal" > 
		
<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-cart-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1H.5zM4 14a1 1 0 1 1 2 0 1 1 0 0 1-2 0zm7 0a1 1 0 1 1 2 0 1 1 0 0 1-2 0zM9 5.5a.5.5 0 0 0-1 0V7H6.5a.5.5 0 0 0 0 1H8v1.5a.5.5 0 0 0 1 0V8h1.5a.5.5 0 0 0 0-1H9V5.5z"/>
</svg> Nova Lista
		 </button>
    </div>
                            </li>
                        </ul>
                    </div>
                    


	<h4 id="list-titulo">Listas</h4>
	<div>&nbsp;</div>
	<div>&nbsp;</div>
</div>	

<div class="container">

	  	

<main role="main" class="">

<div class="box" style=" display: flex;
            justify-content: space-between;
             margin-top: 10%;
           	 margin-bottom: 1%;
            ">
            <div></div>
<div>

	<!-- SEARCH -->
    <div class="form-inline" id="searchForm">
      <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search" id="search" title="procure alguma categoria que queira encontrar" style="width: 80%;
  border-radius: 0.9rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
      <button type="button" onclick="search()" class="btn btn-outline-primary my-2 my-sm-0" style="border: 0.5px solid; border-radius: 0.9rem; 
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
      
      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
  <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
</svg>
      
      </button>
    </div>
</div>
<div>&nbsp;</div>
</div>

<data>
     <div class="table-responsive-lg" id="divtable">	
	<table class="table" id="tabela" style="border: 0; border-radius: 1rem; box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
		<thead>
			<tr class="text-primary">
				<th scope="col" style="text-align: center">Nome</th>
				<th scope="col">Orçamento</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody id="tabela">
			<tr>
					<td data-label="Nome">
					<a class="btn btn-light" href="all-products"
					style="width: 100%; color: dodgerblue; border-radius: 1rem;">Todos os produtos</a>
					</td>
					
					<td data-label="Orçamento" style="color: deeppink">#####</td>
					
					<td data-label="Editar">
					<button
					class="btn btn-outline-info disabled" style="
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
					
<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pen-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
</svg>
					</button> </td>


					<td data-label="Excluir"><button type="submit"class="btn btn-outline-danger disabled" style="
 					 box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);"
					 >
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
</svg>
					</button>
				</tr>
		
		
			<c:forEach items="${categorias}" var="cat">
				<tr>
					<td data-label="Nome">
					<button class="btn btn-light" 
					onclick="sendPost('categorias?acao=selecionar', {id: '${cat.id}'});"
					style="width: 100%; color: dodgerblue; border-radius: 1rem;">${cat.name}</button>
					</td>
					
					<td data-label="Orçamento" style="color: deeppink">R$ <fmt:formatNumber type="number" maxFractionDigits="2" value="${cat.orcamento}"/></td>
					
					
					
					<td data-label="Editar">
					<button
					class="btn btn-outline-info"
					data-toggle="modal" data-target="#categoriaModal" 
					data-title="Editar"
					data-id="${cat.id}" 
					data-nome="${cat.name}"
					data-orcamento="${cat.getOrcamentoEmReal()}" style="
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
					
<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pen-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
</svg>
					</button> </td>


					<td data-label="Excluir"><button type="submit"class="btn btn-outline-danger" style="
 					 box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);"
					 data-toggle="modal" data-target="#exampleModalCenter" data-id="${cat.id}" 
					 data-nome="${cat.name}">
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
</svg>
					
					</button>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</data>
	
 </main>
</div>
	
 

 
 <!-- Modal confirmar excluir -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Atenção</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       	Você tem certeza que deseja excluir a lista <strong><span id="nomeCat"></span></strong>?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-danger" id="excluir">Excluir</button>
      </div>
    </div>
  </div>
</div>
 
	
	<!-- Tela Modal EDITAR/SALVAR-->

		<div class="modal fade" id="categoriaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
			<div class="modal-content">
			  <div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel"></h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  </div>
			  <div class="modal-body">
				  <div class="form-group">
					<label for="recipient-name" class="control-label">Nome:</label>
					<input name="nomeLista" type="text" class="form-control" id="categoriaName" value="${cat.name}" required/>
				  </div>
				  <div class="form-group">
					<label for="message-text" class="control-label">Orcamento:</label>
					<input name="orcamento" type="text" class="form-control" id="orcamento" style="width: 50%" inputmode="numeric">
				  </div>
				<input name="id" type="hidden" class="form-control" id="idCat" value="${cat.id}">
				<button type="button" id="save1" class="btn btn-success" onclick="saveAjax();" >&nbsp; Salvar &nbsp;</button> 
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
			  </div>
			</div>
		  </div>
		  </div>
	
	
	<!-- Fim Tela Modal -->
	
	
	
	
	
	
	<!-- Tela Modal EDITAR CATEGORIA -->
	
					
		<div class="modal fade" id="temaModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		  <div class="modal-dialog" role="document">
			<div class="modal-content">
			  <div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Temas</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  </div>
			  <div class="modal-body">
				  <div class="form-group">
					<div class="pink" onclick="teste('pink')"></div>
					<div class="blue" onclick="teste('blue')"></div>
					<div class="pink" onclick="teste('pink')"></div>
					<div class="blue" onclick="teste('blue')"></div>
					<div class="pink" onclick="teste('pink')"></div>
					<div class="blue" onclick="teste('blue')"></div>
				  </div>
				 
				<button type="button" id="save1" class="btn btn-success" >&nbsp; Salvar &nbsp;</button> 
				<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
			  </div>
			</div>
		  </div>
		  </div>
	
 </div>

<div style="padding-bottom: 100px;"></div>

    <!-- Footer -->
    <footer class="footer">

        <!-- Footer Elements -->
        <div class="container">
            <!-- Grid row-->
            <div class="row text-center d-flex justify-content-center mb-1" style="padding-top: 4%;">

                <!-- Grid column -->
                <div class="col-md-2 mb-3">
                    <h6 class="text-uppercase font-weight-bold">
                        <a href="#!">Sobre</a>
                    </h6>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-2 mb-3">
                    <h6 class="text-uppercase font-weight-bold">
                        <a href="#!">Ajuda</a>
                    </h6>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-2 mb-3">
                    <h6 class="text-uppercase font-weight-bold">
                        <a href="#!">Contato</a>
                    </h6>
                </div>
                <!-- Grid column -->

            </div>
            <!-- Grid row-->
            <hr class="mt-2" style="margin: 0 32%;">

            <!-- Grid row-->
            <div class="row d-flex text-center justify-content-center mb-md-0 mb-4">

                <!-- Grid column -->
                <div class="col-md-4 mt-2">

                </div>
                <!-- Grid column -->

            </div>
            <!-- Grid row-->

            <!-- Grid row-->
            <div class="p-2">

                <!-- Grid column -->
                <div class="icons">

                    <div class="text-center justify-content-center">

                        <!-- Facebook -->
                        <a  href="https://www.facebook.com/rayllanderson.goncalves/">
                            <i class="fab fa-facebook fa-lg mr-3"> </i>
                        </a>

                        <a href="https://github.com/Rayllanderson">
                            <i class="fab fa-github fa-lg white-text mr-3"></i>
                        </a>
                        <!-- Google +-->

                        <a href="www.instagram.com/_ray_goncalves_/">
                            <i class="fab fa-instagram fa-lg white-text mr-3"> </i>
                        </a>


                    </div>

                </div>
                <!-- Grid column -->

            </div>
            <!-- Grid row-->

        </div>
        <!-- Footer Links -->

        <!-- Copyright -->
        <div class="copyright footer-copyright text-center py-3">© <span id="year"></span>
            <p style="display: inline-block;"> Made with <i class="fab fa-java fa-md" title="Java"></i> and <i class="fab fa-bootstrap fa-md" title="Bootstrap"></i></p>
        </div>
        <!-- Copyright -->

    </footer>
    <!-- Footer -->




	

	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> 
<script src="resource/javascript/jquery.mask.min.js"></script>
			
			<script src="resource/javascript/esconderUrl.js"></script>
			<script src="resource/javascript/salvarCategoriaAjax.js" ></script>
			<script src="resource/javascript/excluirCategoriaAjax.js" ></script>
			<script src="resource/javascript/searchCategoriaAjax.js" ></script>
			<script src="resource/javascript/alert.js"></script>
			<script src="resource/javascript/sendThemeAjax.js"></script>
			<script src="resource/javascript/switchThemeCategoria.js"></script>
			<script src="resource/javascript/getYear.js"></script>   

    
<script type="text/javascript">
		
		$('#categoriaModal').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var title = button.data('title')
		  var id = button.data('id') // Extract info from data-* attributes
		  var recipientnome = button.data('nome')
		  var recipientOrcamento = button.data('orcamento')
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  modal.find('.modal-title').text(title)
		  modal.find('#idCat').val(id)
		  modal.find('#categoriaName').val(recipientnome)
		  modal.find('#orcamento').val(recipientOrcamento)
		})
</script>  
	
<div id="#script">

<script type="text/javascript">
	var theme = "${theme}"
	console.log('hey..body')
	categoriaThemeSwitch(theme);
</script>


</div>	

	
</body>

<script type="text/javascript">


if ( window.history.replaceState ) {
	  window.history.replaceState( null, null, window.location.href );
	}


const msg = "${catNula}"
    console.log(msg)
    if (msg != null && msg != '') {
    	$(document).ready(function() {  	
    	$.getScript("resource/javascript/alert.js", function() {
    		alertBoostrap(msg, 'alert alert-warning', "Ei!")
    	});
    });
    }
</script>


<script>
$(".alert").hide();

$(document).ready(function(){
   $('#orcamento').mask('000.000.000.000.000,00', {reverse: true});
});
	
</script>

 <script type="text/javascript">
 
                if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)){
                		document.getElementById('search').style.marginLeft = "2%"
                		document.getElementById('search').style.marginRight = "2%"
                	}
</script>



</html>