<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Categorias</title>
</head>
<body>

<%
String message = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies != null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("message")) message = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>
<!-- pôr um botao de procurar um produto ou categoria -->
<h3>Login Success</h3>
<h4><%=message%></h4>
<h4>Session ID = <%=sessionID %></h4>
<br><br>
	<h1>Olá, ${user.name}, O que você quer fazer?!</h1>
	<form action="categorias?acao=listar" method="post">
		<input type="submit" value="Acessar suas listas"> 
	</form>

	<form action="logout" method="post"> <input type="submit" value="Logout"/></form>
</body>
</html>