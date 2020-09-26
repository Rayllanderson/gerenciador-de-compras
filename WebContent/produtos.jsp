<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>

<link href="resource/css/popup.css" type="text/css" rel="stylesheet" />
<link href="resource/css/table.css" type="text/css" rel="stylesheet" />
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
	<a href="categorias?acao=voltar">Voltar</a>
	<h1>produtos</h1>
	<a href="add-produto.jsp">Novo produto</a>



	<a href="javascript: abrir()">Funções Úteis</a>
	<a href="#" onmouseover="abrir()" onmouseout="fechar()">Efeitos com
		mouse</a>

	<div id="popup" class="popup">
		<p>ola</p>
		<a id="close" class="close" href="javascript: fechar()">×</a>
	</div>


	<table>
		<caption>${categoria.name}</caption>
		<thead>
			<tr>
				<th scope="col">Nome</th>
				<th scope="col">Preço Estipulado</th>
				<th scope="col">Preço Real</th>
				<th scope="col">Comprado</th>
				<th scope="col">Editar</th>
				<th scope="col">Excluir</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${produtos}" var="prod">
				<tr>
					<td data-label="Nome">${prod.nome}</td>
					<td data-label="Preço Estipulado">${prod.precoEstipulado}</td>
					<td data-label="Preço Real">${prod.precoReal}</td>
					<td data-label="Comprado">Não sei</td>
					<td data-label="Editar"><input type="image"
						src="resource/img/edit.png"
						onclick="sendPost('produtos?acao=editar', {id: '${prod.id}'});"
						width="30px" height="30px" /></td>

					<td data-label="Excluir"><input type="image"
						onclick="
					if(confirm('Você tem certeza que deseja excluir o produto ${prod.nome}?')){
						sendPost('produtos?acao=excluir', {id: '${prod.id}'});
					}"
						src="resource/img/excluir.png" width="30px" height="30px" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

<script src="resource/javascript/apenasNumeros.js"></script>
	<script src="resource/javascript/popup.js"></script>
	<script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>