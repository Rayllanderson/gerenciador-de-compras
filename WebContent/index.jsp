<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">

<link href="resource/css/style3.css" type="text/css" rel="stylesheet" />

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Login</title>

<title>Login</title>
</head>
<body>

	<div id="login">
		<div class="container">
			<div id="login-row"
				class="row justify-content-center align-items-center">
				<div id="login-column" class="col-md-6">
					<div id="login-box" class="col-md-12">
						<form id="login-form" class="form" action="login" method="post"
							novalidate>
							<h3 class="text-center text-info">Login</h3>
							<div class="form-group">
								<label for="username" class="text-info">Username:</label><br>
								<input type="text" name="username" id="username"
									class="form-control" value="${username}">
							</div>
							<div class="form-group">
								<label for="password" class="text-info">Password:</label><br>
								<input type="password" name="password" id="password"
									class="form-control">
							</div>
							<div class="form-group">
								<input type="submit" name="submit" class="btn btn-info btn-md"
									value="submit" onclick="return validarDados()">
							</div>
								<label class="text-info">
								<span id="msgLogin"
									style="margin-left: 145px; margin-top:50%; color: red;">${error}
									</span>
									</label><br>

							<div id="register-link" class="text-right">
								<a href="#" class="text-info">Register here</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script src="resource/javascript/index.js"></script>


</body>
</html>