<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@page import="com.ray.model.entities.Product"%>
            <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
                <!DOCTYPE html>
                <html lang="pt-br">

                <head>
                    <meta charset="ISO-8859-1">

                    <meta name="viewport" content="width=device-width, initial-scale=0.7">
                    <title>Seus Produtos</title>

                    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
                    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
                    <script src="resource/javascript/jquery.mask.min.js"></script>

                    <style type="text/css">
                        body {
                            background-color: #f8f9fa;
						}
						
                        #success-alert{
                       	 margin-top: 1%;
                         top: 0px; 
						 margin-left: 0;
						 margin-right: 0;
						 text-align: center;
                        }
                    </style>

                </head>

                <body>
 
 <!-- Início navbar -->
                    <header>

                        <nav class="navbar navbar-expand navbar-dark bg-primary">
                            <a class="navbar-brand" href="categorias.jsp"><img src="resource/img/back.png" width="25px" height="20px" /></a>

                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

                            <div class="collapse navbar-collapse" id="navbarsExample02">
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
                            </div>
                            <button type="button" class="btn btn-outline-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <!-- botao user -->
    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
  <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
  <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
</svg>
  </button>

                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="my-account?action=view">Minha Conta</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>
                        </nav>

                    </header>
 <!-- FIM navbar -->
 
 <!-- INICIO 2NAV -->

                    <div class="card card-signin" style="height: 5%">
                        <ul class="nav justify-content-end">



                            <li class="nav-item">
                                <div class="container" style="height: 50px;">

                                    <button type="submit" data-toggle="modal" data-title="Novo Produto" style="margin-top: 3%; box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);" data-target="#exampleModal" class="btn btn-success" onclick="disableCheckBox()" title="Adicionar Novo Produto" >
			<svg width="1.4em" height="1.1em" viewBox="0 0 16 16" class="bi bi-bag-plus-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M5.5 3.5a2.5 2.5 0 0 1 5 0V4h-5v-.5zm6 0V4H15v10a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V4h3.5v-.5a3.5 3.5 0 1 1 7 0zM8.5 8a.5.5 0 0 0-1 0v1.5H6a.5.5 0 0 0 0 1h1.5V12a.5.5 0 0 0 1 0v-1.5H10a.5.5 0 0 0 0-1H8.5V8z"/>
</svg> Add
</button><!-- botao add novo produto -->

									
                                    <a class="btn btn-primary" data-toggle="collapse" style="margin-top: 3%; box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
                                        <svg width="1em" height="1.1em" viewBox="0 0 16 16" class="bi bi-info-circle-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zm.93-9.412l-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM8 5.5a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
</svg> Infos <!-- botao infos -->
                                    </a>
                                </div>
                            </li>
                        </ul>
                    </div>

 <!-- FIM 2NAV -->



<div class="container">

                    	<!-- ALERT -->
 	 <div class="fixed-top">
 		<div class="alert alert-success" id="success-alert">
   			 <button type="button" class="close" data-dismiss="alert">x</button>
   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
  		</div>
	</div>
	  	  
                    
                        <div class="card card-signin my-5" style=" border: 0;
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
                            <article class="card-body mx-auto" style="max-width: 400px;">
                                <h4 class="card-title mt-3 text-center">${categoria.name}</h4>

                                <p class="text-center" title="valor somando todos os seus produtos comprados + preço estipulado dos que não foram comprados"> Valor Total: ${tTotal} </p>
                                <p class="text-center" title="soma total dos produtos com preço estipulado"> Valor Total Estipulado: ${tEstipulado} </p>

                            </article>
                        </div>
                    </div>



                    <!-- INÍCIO CARD INFOS -->
                    <div class="container" style="margin-bottom: 6%">
                        <div class="collapse" id="collapseExample">
                            <div class="card card-body" style=" border: 0;
  border-radius: 1rem;">
                                <div class="accordion" id="accordionExample">
                                    <div class="card">
                                        <div class="card-header" id="headingOne">
                                            <h2 class="mb-0">
                                                <button class="btn btn-link btn-block text-center" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">Gerais</button>
                                            </h2>
                                        </div>
                                        <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample" style="text-align: center;">
                                            <div class="card-body" style="display: inline-block; text-align: left; font-size: 19px">${gerais}</div>
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
                                                <div class="card-body" style="display: inline-block; text-align: left; font-size: 17px">${economizado}</div>
                                            </div>
                                        </div>
                                    </div>


                                    <div class="accordion" id="accordionExample">
                                        <div class="card">
                                            <div class="card-header" id="headingOne">
                                                <h2 class="mb-0">
                                                    <button class="btn btn-link btn-block text-center" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseOne">Quanto disponível você
										tem pra gastar</button>
                                                </h2>
                                            </div>
                                            <div id="collapseTwo" class="collapse hide" aria-labelledby="headingOne" data-parent="#accordionExample" style="text-align: center;">
                                                <div class="card-body" style="display: inline-block; text-align: left; font-size: 17px">${disponivel}</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- fim card -->
                        </div>
                    </div>

                    <!-- FIM INFORMAÇÕES -->

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
                                    <form class="form-inline" action="produtos?acao=search" method="post">
                                        <input class="form-control mr-sm-2" type="search" name="search" placeholder="Search" aria-label="Search" title="procure algum produto que queira encontrar" style="
  border-radius: 0.9rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
                                        <button type="submit" class="btn btn-outline-primary my-2 my-sm-0" style=" border: 0.5px solid;
  border-radius: 0.9rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
  ">
      
      <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-search" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"/>
  <path fill-rule="evenodd" d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"/>
</svg>
      
      </button>
                                    </form>
                                </div>

                                <div class="btn-group dropleft">
                                    <div class="dropdown">
                                        <button class="btn btn-light" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="
  border-radius: 0.9rem;">
		<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-three-dots-vertical" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/>
</svg>
</button>
                                        <div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-left" style="
  border-radius: 1rem;
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
                                            <a class="dropdown-item" href="produtos?acao=listar">Todos os Produtos</a>
                                            <a class="dropdown-item" href="produtos?acao=comprados">Produtos Comprados</a>
                                            <a class="dropdown-item" href="produtos?acao=nao_comprados">Produtos Não Comprados</a>
                                        </div>
                                    </div>
                                </div>
                            </div>


<data>
                            <!--  INICIO TABELA  -->
                            <div class="table-responsive-xl" id="tabela-produtos">
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


                                                <!-- onclick="sendPost('produtos?acao=editar', {id: '${prod.id}'});" -->

                                                <td data-label="Editar"><button class="btn btn-outline-info" 
                                                style="
  box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);"
                                                data-toggle="modal" data-target="#exampleModal" data-title="Editar" data-id="${prod.id}" data-nome="${prod.nome}" data-estipulado="${prod.getValorEstipuladoEmReal()}"
                                                        data-real="${prod.getValorRealEmReal()}" data-comprado="${prod.comprado}" <c:set var="nomeCategoria" scope="session" value="${prod.categoria.name}" /> onclick="setCheckedIfTrue('${prod.isComprado()}')">

                                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pen-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  <path fill-rule="evenodd" d="M13.498.795l.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"/>
</svg>

                                                    </button>
                                                </td>


                                                <td data-label="Excluir"><button class="btn btn-outline-danger" onclick="
					if(confirm('Você tem certeza que deseja excluir o produto ${prod.nome}?')){
						sendPost('produtos?acao=excluir', {id: '${prod.id}'});
					}"
					style="box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);"
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

<hr>


                    <!-- Tela Modal -->
                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
                                    <h4 class="modal-title text-center" id="myModalLabel">
                                        ${prod.id}</h4>
                                </div>
                                <div class="modal-body"></div>
                            </div>
                        </div>
                    </div>


                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel"></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>

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
                                            <label class="message-text" for="categoria">Lista</label> <select id="categoria" name="cat_id" class="custom-select mr-sm-2">
								<c:forEach items="${categorias}" var="cat">
									<option id="cat_id" value="${cat.id}"  
										<c:if test="${cat.name == nomeCategoria}">
 			 					<c:out value="selected=selected"/> </c:if>>${cat.name}
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

                    <!-- ---------------------------------------------- -->

					<script src="resource/javascript/produtosAjax.js"></script>
                    <script src="resource/javascript/esconderUrl.js"></script>
                    <script src="resource/javascript/alert.js"></script>

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
                            reverse: true
                        });
                    });
                    $(document).ready(function() {
                        $('#estipulado').mask('#.##0,00', {
                            reverse: true
                        });
                    });
                </script>

                </html>