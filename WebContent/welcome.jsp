<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="pt-br">
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=0.71">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="resource/javascript/util/fa.js"></script>
<link href="resource/css/style-login.css" type="text/css" rel="stylesheet" />

<link href="resource/css/login.css" type="text/css" rel="stylesheet" />
<link href="resource/css/login2.css" type="text/css" rel="stylesheet" />
<title>Login</title>
</head>


<body>

<!-- ALERT -->
				 	 <div class="fixed-top">
				 		<div class="alert alert-success" id="success-alert">
				   			 <button type="button" class="close" onclick="$('.alert').hide();">x</button>
				   				<h4 id="titulo"></h4> <p id="alertMsg"></p>
				  		</div>
					</div>
					
			
		
    <div class="container1">
        <div class="forms-container">
            <div class="signin-signup">
                <form id="form-login" class="sign-in-form form-signin">
                    <h2 class="title">Login</h2>
                   		 <div class="form-label-group">
			               <input type="text" name="username" id="username" value="${username}" class="ipt form-control user" placeholder="Username" required autofocus>
			                  <label for="username">  <i class="fas fa-user"></i> Username</label>
			                
			                 <span class="focus-border"></span>
			              </div>
                    <div class="form-label-group">
			                <input type="password" id="password" name="password" class="ipt form-control" placeholder="Password" required>
			                <label for="password">   <i class="fas fa-lock"></i> Password</label>
			                 <span class="focus-border"></span>
			              </div>
                    <button class="btn text-uppercase botao" id="btn-submit"  type="submit" >Login</button>
                </form>
               
               
                <form id="form-cadastro" action="#" class="sign-up-form form-signin">
                    <h2 class="title">Cadastre-se</h2>
                    <div class="input-field">
                        <i class="fas fa-user-circle"></i>
                        <input class="ipt" type="text"  name="name" id="name" placeholder="Nome" />
                    </div>

                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input class="ipt" type="text"  name="username-register" id="username-register" placeholder="Username" />
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input class="ipt" type="password"   name="password-register" id="password-register" placeholder="Senha" />
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input class="ipt" type="password" name="password-register2" id="password-register2" placeholder="Repita a Senha" />
                    </div>
                   <button class="btn text-uppercase botao" id="btn-register"  type="submit" >Registrar</button>
                </form>
            </div>
        </div>


        <div class="panels-container">
            <div class="panel left-panel">
                <div class="content">
                    <h3>Novo por aqui?</h3>
                    <p style="font-size: 15px;">
                        Junte-se a nós! Experimente gerenciar sua lista de compras aqui!
                    </p>
                    <button class="botao" id="sign-up-btn">
              Cadastre-se
            </button>
                </div>
                <img src="resource/img/list10.png" class="image" alt="" />
            </div>
            <div class="panel right-panel">
                <div class="content">
                    <h3>Já possui uma conta?</h3>
                    <p style="font-size: 15px;">
                        Clique abaixo para realizar seu login
                    </p>
                     <button class="botao" id="sign-in-btn">
              Login
            </button>
                </div>
                <img src="resource/img/register.svg" class="image" alt="" />
            </div>
        </div>
    </div>
 
 
 
 
</body>

<script src="resource/javascript/app.js"></script>
<script src="resource/javascript/ajax/loginAjax.js"></script>
<script src="resource/javascript/alert.js"></script>

</body>
</html>