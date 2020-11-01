<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@page pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.95">

<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
<link href="resource/css/footer.css" type="text/css" rel="stylesheet" />	
<link href="resource/css/estatisticas.css" type="text/css" rel="stylesheet" />	
 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>                    
<script src="resource/javascript/util/fa.js"></script>


<!-- canvas JS -->
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<!-- chart.js 2.9.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<style type="text/css">

</style>

<title>My Account</title>
</head>
<body>

<div class="content">

                   <header>
                         <nav class="navbar navbar-expand navbar-dark">
                            <a class="navbar-brand" href="home.jsp">
                           <i class="fas fa-arrow-left fa-md"></i>
                          </a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
      							 <span class="navbar-toggler-icon"></span>
     						 </button>
                            <div class="collapse navbar-collapse" id="navbarsExample02">
                                <ul class="navbar-nav mr-auto">
                                    <li class="nav-item">
                                        <a class="nav-link" href="home.jsp"> <i class="fas fa-home fa-sm"></i> Home</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="categorias"> <i class="fas fa-clipboard fa-sm"></i> Listas</a>
                                    </li>
                                    <li class="nav-item">
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
							
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="my-account">Minha Conta</a>
                                <a class="dropdown-item" href="#">Estatísticas</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>

</nav>
</header> 	<!--  END NAVBAR  -->
				
				
	<!--  

<hr>
<div class="container">
	
	
		<div class="card card-signin my-5" style="border-radius: 1em">
			<article class="card-body mx-auto">
			<h4 class="card-title mt-3 text-center">Informações Gerais</h4>
			<p id="pName">Olá, <strong> ${user.name}</strong>! </p>
			<p>Você possui ${tListas} listas no total </p>
			<p>Você comprou produtos de um total de ${tProdutos} </p>
			<p>Você já gastou ${tGasto} </p>
			<p>Você pretende gastar ${tEstipulado} </p>
			</article>
			</div>
			</div>
			
			
			 -->			
				


	<div class="container" style="margin-top: 5%">

		<div class="card mt-3 p-3" style="border-radius: 1em">
				<h4 class="card-title mt-3 text-center">Estatísticas Gerais</h4>
				<div class="text-center">
					<p>Oi, ${user.name}, aqui estão algumas estatísticas gerais:</p>
					
				</div>
			<ul>
				<li> <p>Número total de listas: <span> ${totalListas} </span> </p> </li>
			</ul>	
			
			<ul>
			 <li><p>Número total de produtos: <span> ${totalProdutos} </span></p></li>
			</ul>		
						
			<div class="text-center">
					<p class="text-uppercase font-weight-bold">Quantidade de Produtos</p>
			</div>	
			<div id="chartContainer" style="height: 370px; width: 100%;"></div>
			
			<div class="mb-5"></div>
			
			<div class="text-center">
					<p class="text-uppercase font-weight-bold">Valores Produtos Comprados</p>
			</div>	
			
			<p>PS: o gráfico abaixo está levando em consideração apenas os produtos <a href='#' class="marcado-comprado" data-toggle="modal" data-target="#exampleModal"> marcados como comprados </a> </p>
			<canvas id="myChart"></canvas>
			
			<div class="mb-5"></div>
			
			<div class="text-center">
					<p class="text-uppercase font-weight-bold">Valores Todos os Produtos</p>
			</div>	
			
			<p>PS: O gráfico abaixo está levando em consideração todos os produtos de todas as listas. </p>
			
			<canvas id="myChart2"></canvas>
			<p>Legenda: </p>
			<ul>
				<li><strong> Economizado:</strong> Subtração do preços estipulado com o preço do produto que foi comprado</li>
				<li><strong> Falta Gastar:</strong> Soma dos produtos que ainda não foram comprados </li>
				<li><strong> Valor Gasto:</strong> Soma dos <a href="#" class="valor-real" data-toggle="modal" data-target="#exampleModal">  preços reais </a> dos produtos, mesmo que não estejam <a  class="marcado-comprado" data-toggle="modal" data-target="#exampleModal" href="#"> marcados como comprados </a></li>
				<li><strong> Valor Estipulado:</strong> Soma dos preços estipulados dos produtos, mesmo que já tenham sido comprados</li>
				<li><strong> Valor Total:</strong> Soma dos produtos comprados e não comprados. Caso o produto já tenha sido comprado, leva em consideração seu <a  class="valor-real" data-toggle="modal" data-target="#exampleModal" href="#"> valor real </a> </li>
			</ul>
				
		</div>
		<!-- card.// -->
	</div>




<div class="modal fade modal-center" id="exampleModal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
      </div>
      <div class="modal-body">
      <p id="titulo-modal"></p>
      <img class="pb-2" alt="" id="img" style=" border:1px solid rgb(225, 225, 225); border-radius: 1em; margin-left: auto; margin-right: auto; display: block;">
      
    </div>
  </div>
</div>
</div>

</div>

    <!-- Footer -->
    <footer class="footer">

        <!-- Footer Elements -->
        <div class="container">
            <!-- Grid row-->
            <div class="row text-center d-flex justify-content-center mb-1" style="padding-top: 4%;">

                <!-- Grid column -->
                <div class="col-md-2 mb-3">
                    <h6 class="text-uppercase font-weight-bold">
                        <a href="about.jsp">Sobre</a>
                    </h6>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-2 mb-3">
                    <h6 class="text-uppercase font-weight-bold">
                        <a href="help.jsp">Ajuda</a>
                    </h6>
                </div>
                <!-- Grid column -->

                <!-- Grid column -->
                <div class="col-md-2 mb-3">
                    <h6 class="text-uppercase font-weight-bold">
                        <a href="contact.jsp">Contato</a>
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

<script type="text/javascript">

var numProdutosComprados = parseInt('${numProdutosComprados}');
var totalP = parseInt("${totalProdutos}");
var numProdutosNaoComprados = totalP - numProdutosComprados;

var valorGastoComprados = Number("${valorGastoComprados}").toFixed(2);
var valorEstipuladoComprados = Number("${valorEstipComprados}").toFixed(2);
var valorEconomizadoComprados = (valorEstipuladoComprados - valorGastoComprados).toFixed(2);

var valorGasto = Number("${valorGasto}").toFixed(2);
var valorEstipulado = Number("${valorEstip}").toFixed(2);
var economizado = Number("${economizado}").toFixed(2);
var restante = Number("${restante}").toFixed(2);
var total = Number("${total}").toFixed(2);

</script>

<script src="resource/javascript/graficos.js"></script>
</body>
</html>