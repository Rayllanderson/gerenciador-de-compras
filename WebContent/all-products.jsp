<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@page import="com.ray.model.entities.Product"%>
            <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
                <!DOCTYPE html>
                <html lang="pt-br">

                <head>
                    <meta charset="ISO-8859-1">

                    <meta name="viewport" content="width=device-width, initial-scale=0.8">
                    <title>Seus Produtos</title>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
                    <script src="resource/javascript/jquery.mask.min.js"></script>
					<script src="resource/javascript/util/fa.js"></script>
					
					<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
					
					<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
					<link href="resource/css/icons-themes.css" type="text/css" rel="stylesheet" />
					<link href="resource/css/alert.css" type="text/css" rel="stylesheet" />
					<link href="resource/css/custom-produto-theme.css" type="text/css" rel="stylesheet" />	
                    <link href="resource/css/footer.css" type="text/css" rel="stylesheet" />	 
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
						
						#gerais p{
						line-height: 19px;
						}
										
                    </style>

                </head>

                <body>
 <!-- Início navbar -->
 <div class="content">
 			<div class="wall" id="wall">
                    <header>

					<!-- <nav class="navbar navbar-expand navbar-dark bg-primary"> -->
                        <nav class="navbar navbar-expand navbar-dark" id="navbar" >
                            <a class="navbar-brand" href="categorias.jsp"> <i class="fas fa-arrow-left fa-md"> </i></a>

                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
					        <span class="navbar-toggler-icon"></span>
					      </button>

                            <div class="collapse navbar-collapse" id="navbarsExample02">
                                <ul class="navbar-nav mr-auto">
                                    <li class="nav-item">
                                        <a class="nav-link" href="home.jsp"> <i class="fas fa-home fa-sm"></i> Home</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="categorias"><i class="fas fa-clipboard fa-sm"></i> Listas</a>
                                    </li>
                                    <li class="nav-item active">
                                        <a class="nav-link" href="#"><i class="fas fa-shopping-cart fa-md"></i> Produtos</a>
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
                                <a class="dropdown-item" href="my-account?action=view"> <i class="fas fa-user-circle"></i> Minha Conta</a>
                       			<a class="dropdown-item" href="estatisticas"> <i class="fas fa-chart-pie"></i>  Estatísticas</a>
                                <h6 class="dropdown-header"><i class="fas fa-palette"></i> Temas</h6>
                                <div class= "items">
                               <!-- <a class="dropdown-item disabled" data-toggle="modal" data-target="#temaModal">Temas</a> -->
	                                <div class="theme blue" onclick="swapColor('blue', true)"></div>
									<div class="theme pink" onclick="swapColor('pink', true)"></div>
									<div class="theme red" onclick="swapColor('red', true)"></div>
									<div class="theme purple" onclick="swapColor('purple', true)"></div>
									<div class="theme cian" onclick="swapColor('cian', true)"></div>
									<br>
									<div class="theme green" onclick="swapColor('green', true)"></div>
									<div class="theme orange" onclick="swapColor('orange', true)"></div>
									<div class="theme black" onclick="swapColor('black', true)"></div>
									<div class="theme galaxy" onclick="swapColor('galaxy', true)"></div>
									<div class="theme icon-default" onclick="swapColor('default', true)"></div>
								</div>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i>Logout</a>
                            </div>
                        </nav>

                    	<!-- ALERT -->
				 	 <div class="fixed-top">
				 		<div class="alert alert-success" id="success-alert">
				   			 <button type="button" class="close" onclick="$('.alert').hide();">x</button>
				   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
				  		</div>
					</div>

                    </header>
 <!-- FIM navbar -->
 
 <!-- INICIO 2NAV -->

<!--  <div class="card card-signin" style="height: 5%"> -->
                 <div class="navbar navbar-expand navbar-dark justify-content-end" id="seccond-navbar" >
                           <ul class="nav justify-content-end">
                            <li class="nav-item">
                                <div class="container" style="height: 50px;">

                                    <button type="submit" data-toggle="modal" data-title="Novo Produto" id="btn-new" data-target="#exampleModal" class="btn btn-outline-success" onclick="disableCheckBox()" title="Adicionar Novo Produto" >
									<svg width="1.4em" height="1.1em" viewBox="0 0 16 16" class="bi bi-bag-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
									  <path fill-rule="evenodd" d="M5.5 3.5a2.5 2.5 0 0 1 5 0V4h-5v-.5zm6 0V4H15v10a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V4h3.5v-.5a3.5 3.5 0 1 1 7 0zM8.5 8a.5.5 0 0 0-1 0v1.5H6a.5.5 0 0 0 0 1h1.5V12a.5.5 0 0 0 1 0v-1.5H10a.5.5 0 0 0 0-1H8.5V8z"/>
									</svg> Add
									</button><!-- botao add novo produto -->

									
                                    <a class="btn btn-outline-primary" data-toggle="collapse" id="btn-info" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                                        <svg width="1em" height="1.1em" viewBox="0 0 16 16" class="bi bi-info-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
</svg> Infos <!-- botao infos -->
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>

 <!-- FIM 2NAV -->


<infos>


<div  id="info">

<div class="container">

                        <div class="card card-signin my-5" style=" border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
                            <article class="card-body mx-auto" style="max-width: 400px;">
                                <h4 id="catTitulo" class="card-title mt-3 text-center"> Todos os produtos</h4>	
					
                                <p class="text-center" id="total"title="valor somando todos os seus produtos comprados + preço estipulado dos que não foram comprados"> Valor Total: <span class="text-primary"> ${total} </span> </p>
                                <p class="text-center" id="vtEstipulado" title="soma total dos produtos com preço estipulado"> Valor Total Estipulado:  <span style="color:deeppink"> ${valorEstip} </span> </p>

                            </article>
                        </div>
                    </div>


                    <!-- INÍCIO CARD INFOS -->
                    <div class="container" style="">
                        <div class="collapse" id="collapseExample">
                            <div id="cardCol" class="card card-body" style="
                             border: 0;
  border-radius: 1rem;
  margin-bottom: 4%;
 
  line-height: 1.6;">
                                <div class="accordion" id="accordionExample">
                                    <div class="card">
                                        <div class="card-header" id="headingOne">
                                            <h2 class="mb-0">
                                                <button class="btn btn-link btn-block text-center" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Gerais</button>
                                            </h2>
                                        </div>
                                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample" style="text-align: center;">
                                            <div class="card-body" id="gerais" style="display: inline-block; text-align: left;">
                                            	 <p> Você possui <span class="text-primary font-weight-bold"> ${totalProdutos} </span>  produtos em <span style="color:deeppink;" class="font-weight-bold"> ${totalListas} </span> listas</p>
                                            	 <p> Você comprou <span class="text-primary font-weight-bold"> ${numProdutosComprados} </span> produtos </p>
                                            	 <p> Faltam <span style="color:deeppink;" class="font-weight-bold" > ${numProdutosNComprados} </span> produtos para comprar </p>
                                            	 <p> Você já gastou <span class="text-primary font-weight-bold"> ${valorGastoComprados} </span> </p>
                                            	 <p> Você planeja gastar <span style="color:deeppink;" class="font-weight-bold"> ${valorEstip} </span> </p>
                                            	 <p> Falta gastar <span style="color:deeppink;" class="font-weight-bold"> ${restante } </span> </p>
                                            	 <p> Valor total <span class="text-primary font-weight-bold"> ${total} </span> </p>
                                            
                                            </div>
                                        </div>
                                    </div>

                                    <div class="accordion" id="accordionExample">
                                        <div class="card">
                                            <div class="card-header" id="headingOne">
                                                <h2 class="mb-0">
                                                    <button class="btn btn-link btn-block text-center" type="button" data-toggle="collapse" data-target="#collapse3" aria-expanded="true" aria-controls="collapseOne">
										Quanto você Já economizou</button>
                                                </h2>
                                            </div>
                                            <div id="collapse3" class="collapse hide" aria-labelledby="headingOne" data-parent="#accordionExample" style="text-align: center;">
                                                <div class="card-body" id="economizado" style="display: inline-block; text-align: left; font-size: 17px">${economizado}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- fim card -->
                        </div>
                    </div>
                    <!-- FIM INFORMAÇÕES -->
                    </div>
</infos>

 
 <div>&nbsp;</div>
 
</div>

                    <div class="container-xl">
                        <main role="main" class="">

                            <!-- TELA DE FILTRO DE PRODUTOS -->

                            <div class="box" style=" display: flex;
            justify-content: space-between;
             margin-top: 5%;
           	 margin-bottom: 1%;
            ">
                                <div></div>
                                <div>
                                    <div class="form-inline">
                                        <input class="form-control mr-sm-2" type="search" name="search" id="search" placeholder="Search" aria-label="Search" title="procure algum produto que queira encontrar" style="
  border-radius: 0.9rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1); width: 80%">
                                        <button type="submit" class="btn btn-outline-primary my-2 my-sm-0" onclick="search()" style="border: 0.5px solid;
  border-radius: 0.9rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
  ">
      
      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
  <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
</svg>
      
      </button>
                                    </div>
                                </div>

                                
                                        <button class="btn btn-light" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="
								  border-radius: 0.9rem;">
										<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-three-dots-vertical" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								  <path fill-rule="evenodd" d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
								</svg>
								</button>									

                                        <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-left" style="
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
                                            <a class="dropdown-item" type="button" onclick="listar('all-products?action=listar')">Todos os Produtos</a>
                                            <a class="dropdown-item" type="button" onclick="listar('all-products?action=comprados')">Produtos Comprados</a>
                                            <a class="dropdown-item" type="button" onclick="listar('all-products?action=nao_comprados')">Produtos Não Comprados</a>
                                        </div>
                                        </div>

  <data>

                            <!--  INICIO TABELA  -->
                            <div class="table-responsive" id="tabela-produtos">
                                <table class="table" id="tabela" style="
                                 border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);" >

                                    <thead>
                                        <tr class="text-primary">
                                            <th scope="col">Nome</th>
                                            <th scope="col">Preço Estipulado</th>
                                            <th scope="col">Preço Real</th>
                                            <th scope="col" style="text-align: center">Comprado</th>
                                            <th scope="col">Editar</th>
                                            <th scope="col">Excluir</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <c:forEach items="${produtos}" var="prod">
                                            <tr>
                                                <td data-label="Nome" >${prod.nome}</td>
                                                <td data-label="Preço Estipulado" style="color: deeppink">R$
                                                    <fmt:formatNumber type="number" maxFractionDigits="3" value="${prod.precoEstipulado}" />
                                                </td>
                                                <td data-label="Preço Real" class="text-primary">R$
                                                    <fmt:formatNumber type="number" maxFractionDigits="2" value="${prod.precoReal}" />
                                                </td>
                                                <td data-label="Comprado" style="text-align:center;">${prod.comprado()}</td>


                                                <td data-label="Editar"><button class="btn btn-outline-info" 

                                                data-toggle="modal" data-target="#exampleModal" data-title="Editar" data-id="${prod.id}" data-nome="${prod.nome}" data-estipulado="${prod.getValorEstipuladoEmReal()}"
                                                        data-real="${prod.getValorRealEmReal()}" data-comprado="${prod.comprado}" data-cat-id="${prod.categoria.id}" onclick="setCheckedIfTrue('${prod.isComprado()}')">

                                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pen-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
</svg>

                                                    </button>
                                                </td>


                                                <td data-label="Excluir"><button class="btn btn-outline-danger" 
                                                
                                                data-toggle="modal" data-target="#exampleModalCenter" data-id="${prod.id}" data-cat_id="${prod.categoria.id}"
												data-nome="${prod.nome}"
					 							>
													
						
											<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM5.354 4.646a.5.5 0 1 0-.708.708L7.293 8l-2.647 2.646a.5.5 0 0 0 .708.708L8 8.707l2.646 2.647a.5.5 0 0 0 .708-.708L8.707 8l2.647-2.646a.5.5 0 0 0-.708-.708L8 7.293 5.354 4.646z"/>
</svg>
						
						</button> </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
          </data> 
                        </main>
                    </div>
                   <!-- FIM TABLE -->


                    <!-- Tela Modal editar e adicionar -->

                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
                                </div>
                                
                                <div class="alert alert-success" id="alertE">
				   				 <button type="button" class="close" onclick="$('#alertE').hide();">x</button>
				   					<h4 id="titulo"></h4> <p id="alertMsgE"></p>
				  				 </div>
                                
                                
                                <div class="modal-body">

                                        <div class="form-group">
                                            <label for="recipient-name" class="control-label" style="text-align: center">Nome:</label> <input name="nome" type="text" class="form-control" id="recipient-name" required="required" value="${prod.name}">
                                        </div>


                                        <div class="form-group">
                                            <label for="message-text" class="control-label">Preço
								Estipulado:</label> <input name="estipulado" type="text" class="form-control" id="estipulado" required="required" style="width: 50%;" inputmode="numeric">
                                        </div>


                                        <div class="form-group">
                                            <label for="message-text" class="control-label">Preço
								Real:</label> <input name="real" type="text" class="form-control" id="real" style="width: 50%;" data-thousands="." data-decimal="," data-prefix="R$ " inputmode="numeric">
                                        </div>


                                        <div id="lista" class="form-group" style="display: none; width: 50%">
                                            <label class="message-text" for="categoria">Lista</label>
                                <select id="categoria" name="cat_id" class="custom-select mr-sm-2">
								<c:forEach items="${categorias}" var="cat">
									<option id="cat_id" value="${cat.id}"> 
										${cat.name}
									</option>
								</c:forEach>
							</select>
                                        </div>

                                        <div class="form-group">
                                            <label for="comprado">Produto já foi comprado?</label> <input type="checkbox" id="comprado" name="comprado" onclick="myFunction()" style="margin-left: 3px; margin-top: 5px; transform: scale(1.5);">

                                            <span id="text"></span>

                                        </div>

                                        <input name="id" type="hidden" class="form-control" id="id" value="${prod.id}">
                                        <button type="submit" class="btn btn-success" onclick="saveProduct()">&nbsp; Salvar &nbsp;</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Fim Tela Modal  -->




 <!-- Modal confirmar excluir -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title text-danger" id="exampleModalLongTitle">Atenção <i class="fas fa-exclamation-triangle"></i></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       	Você tem certeza que deseja excluir o produto <strong><span id="nomeProd"></span></strong>?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-danger" id="excluir">Excluir</button>
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



                    <!-- ---------------------------------------------- -->

					<script src="resource/javascript/ajax/excluirProdutoAjax2.js"></script>
					<script src="resource/javascript/ajax/salvarProdutosAjax2.js"></script>
                    <script src="resource/javascript/esconderUrl.js"></script>
					<script src="resource/javascript/util/alert.js"></script>
					<script src="resource/javascript/ajax/listarProdutos2.js"></script>
					<script src="resource/javascript/ajax/searchProdutosAjax2.js"></script>
					<script src="resource/javascript/ajax/sendThemeAjax.js" ></script>
					<script src="resource/javascript/switchThemeProdutos.js" ></script>
					<script src="resource/javascript/util/getYear.js"></script>
					
<script type="text/javascript">

var theme = "${theme}"
	produtosThemeSwitch(theme);
</script>

                    <script type="text/javascript">
                    
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
                    //modal de add e editar prod
                        $('#exampleModal')
                            .on(
                                'show.bs.modal',
                                function(event) {
                                    $('#alertE').hide();
                                    var button = $(event.relatedTarget)
                                    var title = button.data('title')
                                    var id = button.data('id')
                                    var recipientnome = button.data('nome')
                                    var estipulado = button.data('estipulado')
                                    var real = button.data('real')
                                    var comprado = button.data('comprado')
									const showList = document.getElementById("lista").style.display = 'block';
                                    var cat = button.data('cat-id')    
 									$('#categoria').val(cat)
                                    
                                    var modal = $(this)
                                    modal.find('.modal-title').text(title)
                                    modal.find('#id').val(id)
                                    modal.find('#recipient-name').val(recipientnome)
                                    modal.find('#estipulado').val(estipulado)
                                    modal.find('#real').val(real)
                                })
                    </script>

                </body>

                <script>
                
                function f1() {
                    window.open(document.getElementById("teste").href, "#editCat");
                }
                
                $(".alert").hide();
                
                    $(document).ready(function() {
                        $('#real').mask('000.000.000.000.000,00', {
                            reverse: true
                        });
                    });
                    $(document).ready(function() {
                        $('#estipulado').mask('#.##0,00', {
                            reverse: true
                        });
                    });
                                        	
                    
                </script>
                
                
              <script type="text/javascript">
	                function tupi(){
	            	if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)){
	            		document.getElementById('tabela').className = "table table-sm";
	            		document.getElementById('search').style.marginLeft = "2%"
	            		document.getElementById('search').style.marginRight = "2%"
	            	}else{
	            		document.getElementById('tabela').className = "table";
	            	}
	                }

	                tupi();
                </script>
                

                
                
                </html>