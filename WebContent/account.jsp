<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@page pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	
	
<!--   --><link href="resource/css/account.css" type="text/css" rel="stylesheet" />

<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>                    
<script src="https://kit.fontawesome.com/7b8bfaf036.js" crossorigin="anonymous"></script>

<style type="text/css">

body{
background-image: url('https://i.pinimg.com/originals/b9/c8/f8/b9c8f893c9a782033a01f47e0c0b1d6e.jpg');
}

.box {
}
.avatar::after {
    opacity: 0;
    content: "\f044";
    font-family: FontAwesome;
    color: #fff;
    font-size: 2.5rem;
    position: absolute;
    display: flex;
    justify-content: center;
    align-items: center;
    top: 17.4%;
    width: 260px;
    height: 260px;
    z-index: 2;
    background-color: rgba(0,0,0,0.5);
    border-radius: 50%;
    cursor: pointer;
    transition: 350ms ease-in-out;
}
.avatar:hover::after {
    opacity: 1;
}
.avatar {
    border: 4px;
    border-radius: 50%;
}





</style>

<title>My Account</title>
</head>
<body class="account">

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
                                    <li class="nav-item">
                                        <a class="nav-link" href="produtos">Produtos</a>
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
                                <a class="dropdown-item" href="my-account?action=view">Minha Conta</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>

</nav>
</header>



	<div class="container">

	  	<div class="alert alert-success" id="success-alert" style="margin-top: 1%; ">
   		 <button type="button" class="close" data-dismiss="alert">x</button>
   	<h4 id="titulo"></h4> <p id="alertMsg"></p>
  	</div>
  	
  	
		<div class="card card-signin my-5" style="border-radius: 1em">
			<article class="card-body mx-auto">
				<h4 class="card-title mt-3 text-center">Sua Conta</h4>
				<div class="text-center">
		  			
				</div>
				
					<form class="" action="my-account?action=editar"
		method="POST" id="formUser" enctype="multipart/form-data">			
		
		
		
			
		<article class="roll">					
			<div class="box">
			  		<label class="avatar" for="file">
			  		
			  		<!--  NAO POSSUI FOTO  -->
					<c:if test="${user.getFoto().isEmpty() || user.getFoto() == null}">	
						<img id="target" class="target" src="resource/img/user.png" width="260" height="260" style="border-radius: 50%; border: 1px solid gray;" title="Nova foto de perfil"
						 alt="Imagem de perfil"> 
					</c:if>

					<!--  USER POSSUI FOTO  -->
					<c:if test="${!user.getFoto().isEmpty() && user.getFoto() != null}">
			  			 <img id="target" src="<c:out value="${user.getFoto()}"/>" width="260" height="260" style="border-radius: 50%;"
						 alt="Imagem de perfil" title="Nova foto de perfil"> 
					</c:if>
					
					<input id="file" type="file" name="file" style="display: none" onchange="upload()" accept="image/*"/>
					
				</label>			
									
			</div>
			</article>
			
			<br>
			<div>
				
			<input value="${user.id}" name="id" hidden="true" id="id">
				<span class="input-text"> Nome </span>
					<div class="form-group input-group">
						<input name="nome" class="form-control" placeholder="Nome"
							type="text" value="${user.name}" name="nome" id="nome">
					</div>
					<!-- form-group// -->
					<span class="input-text"> Username</span>
					<div class="form-group input-group">
						<input name="username" class="form-control" placeholder="Username"
							type="text" value="${user.username}" required="required" name="username" id="username">
					</div>
					
					<div style="display: flex; justify-content: space-between;">
						<div class="text-left">
						
							<input id="" class="btn btn-success" type="submit" 				
							value="Salvar" />
						</div>
					
					
					
						<div class="text-right">
							<a href="index.jsp">Mudar senha</a>
						</div>
			</div>		
					</div>
					</form>
			</article>
			
			
		</div>
		<!-- card.// -->
	</div>
	


<hr>
<div class="container">
	
	
		<div class="card card-signin my-5" style="border-radius: 1em">
			<article class="card-body mx-auto">
			<h4 class="card-title mt-3 text-center">Informações Gerais</h4>
			<p id="pName">Olá, <strong> ${user.name}</strong>! </p>
			<p>Você possui ${tListas} listas no total </p>
			<p>Você comprou ${nProdutosComprados} produtos de um total de ${tProdutos} </p>
			<p>Você já gastou ${tGasto} </p>
			<p>Você pretende gastar ${tEstipulado} </p>
			</article>
			</div>
			</div>
			
			
	
	<script type="text/javascript">
	$(".alert").hide();

	let error = "${error}"
	let success = "${success}"

	
	if (error) {
		console.log("yes, fail", error)
		alertBoostrap(error, 'alert alert-danger')
		error= '';
	}

	if (success) {
		console.log("yes success", success)
		alertBoostrap(success, 'alert alert-success')
		success = '';
	}

	function alertBoostrap(msg, classe){
	  $(".alert").show();
	  document.getElementById('alertMsg').innerHTML = msg;
	  document.getElementById("success-alert").className = classe;
	  $("#success-alert").fadeTo(2700, 500).slideUp(500, function(){
	    $("#success-alert").slideUp(500);
	});
	}

	function setColorAlert(response){
		if (response == 'Nenhuma alteração foi detectada.'){
			alertBoostrap(response, 'alert alert-warning')
		}else{
			alertBoostrap(response, 'alert alert-success')
		}
	}
	
	</script>
	
	<script type="text/javascript">

	if ( window.history.replaceState ) {
		  window.history.replaceState( null, null, window.location.href );
		}
        	</script>
  
  <script type="text/javascript">
  
	function upload() {
	    console.log('ola1')
	    var target = document.querySelector("#target");
	    var file = document.querySelector("#file").files[0];
	    var reader = new FileReader();
	    reader.onloadend = function() {
	        target.src = reader.result;
		 console.log('ola3')
	    };
	    if (file) {
	        console.log('ola2')
	        target.style.width = '260px';
	        target.style.height = '260px';
	        target.style.borderRadius = '50%';
	        reader.readAsDataURL(file)
	    } else {
	        target.src = "resource/img/user.png"
	    }
	}
  </script>  



<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="resource/javascript/accountAjax.js"></script>   

</body>
</html>