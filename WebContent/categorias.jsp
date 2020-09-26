<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>
<link href="resource/css/table.css" type="text/css" rel="stylesheet" />
<link href="resource/css/btn.css" type="text/css" rel="stylesheet" />

</head>
<body>
	<a href="home.jsp">Voltar</a>
	<h1>Listas</h1>
	<a href="categorias?acao=newList"
		style="border: 1px solid black; text-decoration: none; color: black; background-color: white;">Nova
		Lista </a>
		
		

		
	<table>
		<caption>Listas</caption>
		<thead>
			<tr>
				<th scope="col">Nome</th>
				<th scope="col">Orçamento</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${categorias}" var="cat">
				<tr>
					<td data-label="Nome">
					<input type="button" class="btn-1"
					onclick="sendPost('categorias?acao=selecionar', {id: '${cat.id}'});"
					value="${cat.name}" />
					</td>
					
					<td data-label="Orçamento">${cat.orcamento}</td>
					
					<td data-label="Editar"><input type="image"
					onclick="sendPost('categorias?acao=editar', {id: '${cat.id}'});"
					src="resource/img/edit.png" width="30px" height="30px" /></td>

					<td data-label="Excluir"><input type="image"
					onclick="
					if(confirm('Você tem certeza que deseja excluir a lista ${cat.name}?')){
						sendPost('categorias?acao=excluir', {id: '${cat.id}'});
					}" src="resource/img/excluir.png" width="30px" height="30px" />
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>