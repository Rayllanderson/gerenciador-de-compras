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
<link href="resource/css/login2.css" type="text/css" rel="stylesheet" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>

<!--   <link href="resource/style.css" type="text/css" rel="stylesheet" />  -->

<title>Login</title>




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
			               <input type="text" name="username" id="username" value="${username}" class="ipt form-control user" placeholder="Username" style="border: none " required autofocus>
			                <label for="username">Username</label>
			              </div>
			              <div class="form-label-group">
			                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required style="border: none;">
			                <label for="password">Password</label>
			              </div>
			              <button class="btn btn-lg btn-block text-uppercase botao" id="btn-submit"  type="submit" >Login</button>
			              </form>
			              <hr class="my-4">
			              <div class="cadastro form-footer">
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