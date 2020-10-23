<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head lang="pt-br">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=0.53">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link href="resource/css/login.css" type="text/css" rel="stylesheet" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<link href="resource/style.css" type="text/css" rel="stylesheet" />

<title>Login</title>



<style type="text/css">

body {
  font-family: "Karla", sans-serif;
  background-color: #d0d0ce;
  background-image: url('resource/img/ola/51.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  }
  


.brand-wrapper {
  margin-bottom: 19px; }
  .brand-wrapper .logo {
    height: 100px; 
   }
   .logo{
    margin-left: -1%;
   }

.login-card {
  border: 0;
  border-radius: 27.5px;
  box-shadow: 0 10px 30px 0 rgba(172, 168, 168, 0.43);
  overflow: hidden; }
  .login-card-img {
    border-radius: 0;
    position: absolute;
    width: 100%;
    height: 100%;
    -o-object-fit: cover;
       object-fit: cover; }
  .login-card .card-body {
  	
    padding: 65px 100px 100px; }
   

.login-card-description {
    font-size: 25px;
    color: #000;
    font-weight: normal;
    margin-bottom: 23px; 
    }
    
    
    @media (max-width:900px)  { 
    	.login-card .card-body {
        padding: 60px 60px 60px; 
        transform: scale(1.3);
        }
        html, body {
 		 overflow-x: hidden;
		}
		body {
		  position: relative;
		}
      	
     @media (orientation: landscape) {
     	.login-card .card-body {
        padding: 10px 10px; 
        }
     }
     
     @media (max-width:500px)  { 
    	.login-card .card-body {
        padding: 10px 10px; 
        transform: scale(0.8);
        }
      	
     @media (orientation: landscape) {
     	.login-card .card-body {
        padding: 10px 10px; 
        }
     }
     
     
     #btn-submit{
     
		
	}

	 button.botao:hover{
		color: white;
		background-color: #5833e5;
		transition: 1s;
		
	}
	
	textarea:focus,
input[type="text"]:focus,
input[type="password"]:focus,
input[type="datetime"]:focus,
input[type="datetime-local"]:focus,
input[type="date"]:focus,
input[type="month"]:focus,
input[type="time"]:focus,
input[type="week"]:focus,
input[type="number"]:focus,
input[type="email"]:focus,
input[type="url"]:focus,
input[type="search"]:focus,
input[type="tel"]:focus,
input[type="color"]:focus,
.uneditable-input:focus {  
border-bottom: 0.5px solid #6e4af6; 
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset, 0 0 8px rgba(126, 239, 104, 0.6);
  outline: 0 none;
}
	
	input[type="text"], input[type="password"], select { 
    	border-bottom: 0.5px solid #6e4af6;
	}
	
	input:focus{
	border-bottom: 0.5px solid #6e4af6;
	}
    
    
 #username input{
 	border: none;
 }
</style>


</head>


 	

<body>

<!-- ALERT -->
				 	  <div class="fixed-top">
				 		<div class="alert alert-success" id="success-alert">
				   			 <button type="button" class="close"onclick="$('.alert').hide();">x</button>
				   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
				  		</div>
					</div>


<main class="d-flex align-items-center min-vh-100 py-3 py-md-0">
    <div class="container">
      <div class="card login-card">
        <div class="row no-gutters">
          <div class="col-md-5">
            <img src="resource/img/list2.jpg" alt="login" class="login-card-img">
          </div>
          <div class="col-md-7">
            <div class="card-body">
              <div class="brand-wrapper">
                <h3 class="logo">Gerencie suas compras aqui!</h3>
              </div>
              <p class="login-card-description">Faça Login em sua conta</p>
				
			            <form class="form-signin" action="home" method="post">
			              <div class="form-label-group">
			               <input type="text" name="username" id="username" value="${username}" class="form-control" placeholder="Username" style=" " required autofocus>
			                <label for="username">Username</label>
			              </div>
			              <div class="form-label-group">
			                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required style="border: hidden;">
			                <label for="password">Password</label>
			              </div>
			              <button class="btn btn-lg btn-block text-uppercase botao" id="btn-submit"  style="color: #6e4af6;" type="submit" onclick="return validarDados()">Login</button>
			              </form>
			              <hr class="my-4">
			              <div class="form-footer">
			          <p>   Não tem uma conta? <a class="text-primary" href="cadastro.jsp">Cadastre-se</a></p>
			          </div>
			          </div>
			        </div>
    </div>
  </div>
				
          </div>
      </main>




</body>

<script type="text/javascript">
$('.alert').hide();

const msg = "${msg}"
	if (msg != null && msg != '') {
		console.log(msg)
		alert(msg)
	}	
</script>

<script src="resource/javascript/index.js"></script>

</body>
</html>