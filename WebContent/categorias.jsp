<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>
</head>
<body>
<a href="home.jsp">Voltar</a>
<h1>Listas</h1>
	<c:forEach items="${categorias}" var="cat">
	<table>
	<tr>
		<td>
		<input type="button" onclick="sendPost('categorias?acao=selecionar', {id: '${cat.id}'});" value="${cat.name}"/>
		</td>
	</tr>
	</table>
	</c:forEach>
	

	 <script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>