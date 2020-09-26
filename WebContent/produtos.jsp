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
<a href="categorias?acao=voltar">Voltar</a>
<h1>produtos</h1>
	<h2 style="text-align: center">Você está na lista ${categoria.name} </h2>
	<a href="add-produto.jsp">Novo produto</a>
	<c:forEach items="${produtos}" var="prod">
	<form action=""></form>
		<table>
			<tr>
				<td>
					<label>${prod.nome}</label>
					<input type="image" src="resource/img/edit.png" onclick="sendPost('produtos?acao=editar', {id: '${prod.id}'});" width="30px" height="30px" />
				</td>
			</tr>
		</table>
	</c:forEach>
	
	<script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>