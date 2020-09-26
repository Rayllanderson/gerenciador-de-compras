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
	<a href="add-produto.jsp">Novo produto</a>
	<c:forEach items="${produtos}" var="prod">
	<table>
	<tr>
		<td>
			<input type="button" value="${prod.nome}"  onclick="sendPost('produtos?acao=selecionar', {id: '${prod.id}'});" />
		</td>
	</tr>
	</table>
	</c:forEach>
	
	<script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>