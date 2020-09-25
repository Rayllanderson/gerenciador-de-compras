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
	<a href="categorias?acao=newList"
		style="border: 1px solid black; text-decoration: none; color: black; background-color: white;">Nova
		Lista </a>
	<c:forEach items="${categorias}" var="cat">
		<table>
			<tr>
				<td><input type="button"
					onclick="sendPost('categorias?acao=selecionar', {id: '${cat.id}'});"
					value="${cat.name}" />
					
					<input type="image"
					onclick="sendPost('categorias?acao=editar', {id: '${cat.id}'});"
					src="resource/img/edit.png" width="30px" height="30px" /> <!--  método POST pra redirecionar... KK azideia. mas foi o único jeito que eu achei de não passar o ID na URL, ok? -->
				</td>
			</tr>
		</table>
	</c:forEach>
	<script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>