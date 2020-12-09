<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="pt-br">

<head>
    <title>Contato</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.85, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
   
    <link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
    <link href="resource/css/footer.css" type="text/css" rel="stylesheet" />	 

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="resource/javascript/util/fa.js"></script>
    
    
    <style type="text/css">
		
		html, body{
		height: 100%;
		}
		
		body{
			background-color: #17202A;
			
		}
		
		 img {
            display: block;
			margin: 10px auto;
            margin-top: 10px; margin-bottom: 10px;
        }
        
        .content{
			min-height: 100%;
			position: relative;
			margin-bottom: 2%;
		}	
		
		.card{
			margin: 0 auto;
			border-radius: 1em;
			display: flex;
		}
		
		.card  p{
			font-size: 20px;
			width: 100%;
		}	
		
		.card .rodape{
			font-size: 18px;
			color: gray;	
		}
		

    </style>
    
</head>

<body>



<div class="content mb-5">
                   <header>

                         <nav class="navbar navbar-expand navbar-dark">
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
                                <a class="dropdown-item" href="my-account?action=view"> <i class="fas fa-user-circle"></i> Minha Conta</a>
                      			<a class="dropdown-item" href="estatisticas"> <i class="fas fa-chart-pie"></i>  Estatísticas</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout"><i class="fas fa-sign-out-alt"></i>Logout</a>
                            </div>
</nav>
</header>


<!-- ALERT -->
				 	 <div class="fixed-top">
				 		<div class="alert alert-success" id="success-alert">
				   			 <button type="button" class="close" onclick="$('.alert').hide();">x</button>
				   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
				  		</div>
					</div>

	<div class="container mt-5 ">
		
		<div class="card p-5">
		
			<h2 class="text-center mb-4">Entre em contato</h2>
			
			<div class="form-group">
		    <label for="exampleInputEmail1">Seu Email</label>
		    <input type="email" name="email" id="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
		    </div>
    
			 <div class="form-group">
			    <label for="exampleFormControlTextarea1">Escreva aqui</label>
			    <textarea class="form-control" name="message" id="message" rows="7"></textarea>
			  </div>
			
			<div class="form-group">
			 <button type="submit" id="submit" class="btn btn-primary">Enviar</button>
			 </div>
					
					<div class="d-flex justify-content-center" >
					  <div class="spinner-border" role="status" id="loading">
					    <span class="sr-only">Enviando...</span>
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
                            <a href="about.jsp">Sobre  <i class="fas fa-info-circle"></i></a>
                        </h6>
                    </div>
                    <!-- Grid column -->

                    <!-- Grid column -->
                    <div class="col-md-2 mb-3">
                        <h6 class="text-uppercase font-weight-bold">
                            <a href="help.jsp">Ajuda <i class="fas fa-question-circle"></i></a>
                        </h6>
                    </div>
                    <!-- Grid column -->

                    <!-- Grid column -->
                    <div class="col-md-2 mb-3">
                        <h6 class="text-uppercase font-weight-bold">
                            <a>Contato <i class="fas fa-envelope"></i></a>
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


	<script src="resource/javascript/util/alert.js"></script>
	<script src="resource/javascript/ajax/emailAjax.js"></script>
	
</body>

</html>