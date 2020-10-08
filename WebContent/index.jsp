<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head lang="pt-br">
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link href="resource/css/login.css" type="text/css" rel="stylesheet" />

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<title>Login</title>

</head>
<body>

<body>
  <div class="container" >

    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign In</h5>
            <form class="form-signin" action="home" method="post">
              <div class="form-label-group">
               <input type="text" name="username" id="username" value="${username}" class="form-control" placeholder="Username" required autofocus>
                <label for="username">Username</label>
              </div>
              <div class="form-label-group">
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
                <label for="password">Password</label>
              </div>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" onclick="return validarDados()">Sign in</button>
              </form>
              <hr class="my-4">
              <div class="form-footer">
          <p>   Não tem uma conta? <a href="cadastro.jsp">Cadastre-se</a></p>
          </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>

<script type="text/javascript">
const msg = "${msg}"
	if (msg != null && msg != '') {
		console.log(msg)
		alert(msg)
	}	
</script>

<script src="resource/javascript/index.js"></script>

</body>
</html>