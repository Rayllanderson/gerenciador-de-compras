<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="pt-br">

<head>
    <title>Sidebar 04</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.9, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
   
    <link rel="stylesheet" href="resource/css/help.css">
    <link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
    <link href="resource/css/footer.css" type="text/css" rel="stylesheet" />	 

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="resource/javascript/util/fa.js"></script>
    
    
    <style type="text/css">

		 img {
            display: block;
			margin: 10px auto;
            margin-top: 10px; margin-bottom: 10px;
        }
        
        .content{
			min-height: 100%;
			position: relative;
		}		
    </style>
    
</head>

<body>



<div class="content">
                   <header>

                         <nav class="navbar navbar-expand navbar-dark bg-dark">
                            <a class="navbar-brand" href="home.jsp">
                            
                           <i class="fas fa-arrow-left fa-md"></i>
                            
                          <!-- <img src="resource/img/back.png" width="25px" height="20px" /></a>  -->  
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
							
							
                            <div class="dropdown-menu dropdown-menu-right" style="border-radius: 1em;">
                                <a class="dropdown-item" href="#">Minha Conta</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>
</nav>
</header>




    <div class="wrapper d-flex align-items-stretch">
        <nav id="sidebar">
            <div class="custom-menu">
                <button type="button" id="sidebarCollapse" class="btn btn-primary">
	          <i class="fa fa-bars"></i>
	          <span class="sr-only">Toggle Menu</span>
	        </button>
            </div>
            <h1><a class="logo">Listas</a></h1>
            <ul class="list-unstyled components mb-5">
                <li>
                    <a href="#listas-vazias" class="menu-sub-item">Lista vazia?</a>
                </li>
                <li>
                    <a href="#Como-adicionar-uma-nova-lista" class="menu-sub-item">Como adicionar uma nova lista</a>
                </li>
                <li>
                     <a href="#Acessando-uma-lista" class="menu-sub-item">Acessando uma lista</a>
                </li>
                <li>
                   <a href="#Editando-uma-lista" class="menu-sub-item">Editando uma lista</a>
                </li>
                <li>
                   <a href="#Excluindo-uma-lista" class="menu-sub-item">Excluindo uma lista</a>
                </li>
            </ul>
			
			
			<h1><a class="logo">Produtos</a></h1>
			 <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="#Lista-produtos-vazia" class="menu-sub-item">Lista de produtos vazia?</a>
                        </li>
                        <li>
                            <a href="#Como-adiciono-um-produto" class="menu-sub-item">Como adicionar um novo produto</a>
                        </li>
                        <li>
                            <a href="#Adicionando-um-novo-produto" class="menu-sub-item">Adicionando um novo produto</a>
                        </li>
                        <li>
                            <a href="#valor-estipulado" class="menu-sub-item">O que siginifica Valor Estipulado?</a>
                        </li>
                        <li>
                            <a href="#valor-real" class="menu-sub-item">O que significa Valor Real?</a>
                        </li>
                        <li>
                            <a href="#comprado" class="menu-sub-item">Para que marcar um produto como comprado?</a>
                        </li>
                        <li>
                            <a href="#editando-produto" class="menu-sub-item">Editando um produto</a>
                        </li>
                        <li>
                            <a href="#excluindo-produto" class="menu-sub-item">Excluindo um produto</a>
                        </li>
                        <li>
                            <a href="#Editando-lista-atual" class="menu-sub-item">Editando a lista atual selecionada</a>
                        </li>
                        <li>
                            <a href="#listando-produtos" class="menu-sub-item">Listando produtos comprados e não comprados</a>
                        </li>
                    </ul>
			
			
			<h1><a class="logo">Card Produtos</a></h1>
			 <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="#valor-total" class="menu-sub-item">O que significa Valor total no card de produtos?</a>
                        </li>
                        <li>
                            <a href="#card-valor-estipulado" class="menu-sub-item">O que significa Valor Total Estipulado no card de produtos?</a>
                        </li>
            </ul>
			 
			 
			 
			 
			<h1><a class="logo">Minha Conta</a></h1>
			  <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="#foto-perfil" class="menu-sub-item">Como eu uso uma foto de perfil?</a>
                        </li>
                        <li>
                            <a href="#mudar-username" class="menu-sub-item">Mudando Username ou nome</a>
                        </li>
                        <li>
                            <a href="#mudar-senha" class="menu-sub-item">Mudando a senha</a>
                        </li>
              </ul>
			  
			  
			  
			<h1><a class="logo"> Temas</a></h1>
			 <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="#mudar-tema" class="menu-sub-item">Como mudar o tema?</a>
                        </li>
                        <li>
                            <a href="#tema-outras-paginas" class="menu-sub-item">Por que não posso mudar o tema em outras páginas?</a>
                        </li>
              </ul>
			
			
			<h1><a href="#duvidas"class="logo menu-sub-item">Dúvidas?</a></h1>

        </nav>

		<div class="container">
        <!-- Page Content  -->
        <div id="content" class="p-4 p-md-5 pt-5">
         <h1 class="mt-4 mb-3 text-primary">Listas</h1>
            <h2 id="listas-vazias" class="mb-4">Acessei minhas listas, mas não tem nada. O que isso significa?</h2>
            <p>Significa que sua lista de listas (lol) está vazia. Você não possui nenhuma lista. Mas, tudo bem, basta adicionar uma!</p>
            
            <h2 id="Como-adicionar-uma-nova-lista">Como adicionar uma nova lista?</h2>
            <p>Para adicionar uma nova lista, apenas o nome é obrigatório. Orçamento pode ser qualquer valor, incluindo sem valor. No entanto, não terá como calcular o valor disponível para gastar de uma lista sem orçamento. imagem>
            </p>
            

            <h2 id="Acessando-uma-lista" class="mb-4">Acessando uma lista</h2>
            <p>Para acessar uma lista, basta clicar no nome da lista desejada
                <img src="resource/img/help/list-selecionada.png" class="img-fluid">
            </p>


            <h2 id="Editando-uma-lista" class="mb-4">Editando uma lista</h2>
            <p> Para editar, basta clicar no lápis imae maybe>. Depois de clicar, abrirá uma janela contendo os dados da lista selecionada. Você pode editar o nome e orçamento. Apenas o nome é obrigatório.</p>


            <h2 id="Excluindo-uma-lista" class="mb-4">Excluindo uma lista</h2>
            <p>Para excluir uma lista, clique no X
                <img src="resource/img/help/excluir.png" class="img-fluid"> depois confirme, caso realmente queira remover.</p>
            <img src="resource/img/help/exluindo-lista.png" class="img-fluid">
            
          
            
            <h1 class="mt-4 mb-3 text-primary">Produtos</h1> 
             <h2 id="Lista-produtos-vazia" class="mb-4">Acessei minha lista de produtos, mas não tem nada. O que isso significa?</h2>
            <p>Significa que sua lista de produtos está vazia. Você não possui nenhum produto na lista atual selecionada. Mas, tudo bem, basta adicionar um produto! </p>

            <h2 id="Como-adiciono-um-produto" class="mb-4">Como eu adiciono um produto?</h2>
            <p>Para adicionar um produto, clique no canto superior direito, no ícone de ‘Add’>
                <img src="resource/img/help/adicionando-novo-prod.png"  class="img-fluid"/>
            </p>


            <h2 id="Adicionando-um-novo-produto" class="mb-4">Adicionando um novo produto</h2>
            <p>Para adicionar um produto, apenas o nome é necessário. O preço estipulado e o preço real podem ser nulos.</p>


            <h2 id="valor-estipulado" class="mb-4">O que significa Valor Estipulado?</h2>
            <p>Significa um valor que você acha que vai pagar em tal produto, um valor estipulado, um valor que você tem em mente para comprar o produto. Como assim? Bom, digamos que você esteja querendo comprar uma geladeira. Você faz uma rápida pesquisa
                na internet e olha os preços médios, após isso você deve ter um preço em mente, certo? É esse preço que você colocará aqui, um preço estipulado. </p>


            <h2 id="valor-real" class="mb-4">O que significa valor Real?</h2>
            <p>O valor que você realmente pagou no produto! Lembra do valor estipulado? Que era o preço que você achava que iria pagar? Então, aqui no valor real, você coloca o valor que, de fato, você pagou. Pode ser ele maior ou menor que o preço estipulado,
                afinal de contas, nem sempre os preços saem mais baratos que a gente espera.</p>


            <h2 id="comprado" class="mb-4">Para que marcar um produto como comprado?</h2>
            <p>Marcar um produto como comprado será importante para realização dos cálculos. Tais como: Valor economizado e o valor restante.</p>


            <h2 id="editando-produto" class="mb-4">Editando um produto</h2>
            <p>Para editar, basta clicar no lápis
                <img src="resource/img/help/editar.png" class="img-fluid"> Depois de clicar, abrirá uma janela contendo os dados do produto selecionado. Você pode editar o nome, valores, se foi comprado ou não e agora pode movê-lo para outra lista, se preferir. Nome e
                listas são obrigatórios, o resto pode ser nulo.</p>


            <h2 id="excluindo-produto" class="mb-4">Excluindo um produto</h2>
            <p>Para excluir um produto, clique no X
                <img src="resource/img/help/excluir.png" class="img-fluid"> depois confirme, caso realmente queira remover.
                <img src="resource/img/help/excluindo-prod.png" class="img-fluid">
            </p>


            <h2 id="Editando-lista-atual" class="mb-4">Editando a lista atual selecionada.</h2>
            <p>Para editar a lista atual, basta clicar no pequeno lápis azul que fica ao lado do nome da lista
                <img src="resource/img/help/edit-cat.png" class="img-fluid"> Após clicar, abrirá um modal com os dados da lista atual. Apenas o nome é obrigatório</p>


            <h2 id="listando-produtos" class="mb-4">Como eu listo os produtos comprados ou não comprados?</h2>
            <p>Para listar apenas esses produtos, basta selecionar o dropdown
                <img src="resource/img/help/list-products.png" class="img-fluid"> e clicar na opção desejada.</p>
                
                
                
            <h1 class="mt-4 mb-3 text-primary">Card Produtos</h1>     
             <img src="resource/img/help/card.png"  class="img-fluid">

            <!-- <h2>Lista de produtos vazia?</h2> -->
            <h2 id="valor-total" class="mb-4">O que significa Valor total no card de produtos?</h2>
            <p>Significa a soma dos produtos que você já comprou com a soma do preço estipulado que você ainda não comprou. Caso você já tenha comprado, considera o valor do preço real, caso não, considera o valor Estipulado. Como assim? Bom, digamos que
                você quer comprar um computado, para isso, vamos dizer que um computador é composto por 2 peças principais: Monitor e gabinete. O monitor você achava que iria pagar R$ 800 reais e o gabinete R$800 reais também. Você pegou uma boa promoção
                e pagou R$ 700 no monitor, e a partir de agora, o valor total passará ser de R$ 1500 em vez de R$ 1600.</p>


            <h2 id="card-valor-estipulado" class="mb-4">Valor Total Estipulado Card de Produtos</h2>
            <p>Significa a soma do preço estipulado de todos os produtos. Não considera o preço real aqui.</p>
			
                
            
             <h1 class="mt-4 mb-3 text-primary">Minha Conta</h1>
             <h2 id="foto-perfil" class="mb-4">Como eu uso uma foto de perfil? </h2>
           	 <p>Para usar uma foto de perfil basta clicar no ícone que fica no canto superior direito
                <img src="resource/img/help/acessando-my-account.png" class="img-fluid"> clique em Minha Conta. Depois clique na foto
                <img src="resource/img/help/upload.png" class="img-fluid"> clique em upload, depois basta selecionar e não se esqueça de clicar em Salvar</p>

            <h2 id="mudar-username" class="mb-4">Mudar Username ou nome</h2>
            <p>Para mudar o username clique no ícone que fica no canto superior direito
                <img src="resource/img/help/acessando-my-account2.png" class="img-fluid"> clique em Minha Conta. Depois selecione seu novo username ou nome.
                <br> Nota: Username deve ser único. Nome é opcional, pode colocar qualquer um.
            </p>

            <h2 id="mudar-senha" class="mb-4">Mudando a senha</h2>
            <p>Para mudar a clique no ícone que fica no canto superior direito
                <img src="resource/img/help/acessando-my-account3.png" class="img-fluid"> clique em Minha Conta. Depois clique em mudar senha, abrirá uma tela modal.
                 <img src="resource/img/help/senha.png" class="img-fluid"> Para confirmar que é mesmo você, confirme sua senha e em seguida digite sua nova senha 2x. Depois clique
                em Salvar e pronto!</p>
             
            
              <h1 class="mt-4 mb-3 text-primary">Temas</h1>
              <h2 id="mudar-tema" class="mb-4">Como mudar o tema?</h2>
            <p>Para mudar o tema, certifique-se que esteja nas páginas de listas ou produtos. Depois clique no ícone que fica no canto superior direito e selecione uma das opções
                <img src="resource/img/help/temas.png" class="img-fluid">
            </p>

            <h2 id="tema-outras-paginas" class="mb-4">Por que não posso mudar o tema em outras páginas?</h2>
            <p>Infelizmente o intuito dos temas não era algo tão grande. Os temas servem apenas para dar uma pequena mudada no visual nessas 2 páginas, que as quais eu estava sem ideia de qual tema definitivo implementar. Então eu decidi deixar que o próprio
                usuário escolha qual ele preferir. ~o meu preferido é o galaxy~
            </p>


            <h1 class="mt-4 mb-3 text-primary" id="duvidas">Duvidas?</h1>
            <p>Ficou com alguma dúvida? Encontrou algum bug? Entre em contato comigo <a href="contact.jsp">aqui</a>
            </p>
                  
            
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
                            <a>Ajuda</a>
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
                            <a href="https://www.facebook.com/rayllanderson.goncalves/">
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
            <div class="copyright footer-copyright text-center py-3">©<span id="year"></span>
                <span style="display: inline-block;"> Made with <i class="fab fa-java fa-md" title="Java"></i> and <i class="fab fa-bootstrap fa-md" title="Bootstrap"></i></span>
            </div>
            <!-- Copyright -->
        </footer>
        <!-- Footer -->




    <script type="text/javascript">
   
    (function($) {

	"use strict";

	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	$('#sidebarCollapse').on('click', function () {
      $('#sidebar').toggleClass('active');
 	 });

	})(jQuery);

	
    
   
    </script>
    
    <script type="text/javascript">
   
   
		$(".menu-sub-item").on('click', function(){
			 $('#sidebarCollapse').click();
			 });

		if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)){
        		$('p').css('width', '75vw')
        	}
    </script>
</body>

</html>