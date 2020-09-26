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
<h1>Listas</h1>
	
	<form action="categorias?acao=salvar" method="post"> 
		<input type="hidden" id="id" name="id" value="${cat.id}">
		
		<label>Nome da Lista</label>
		<input type="text" id="nomeLista" name="nomeLista" placeholder="Nome da Lista" value="${cat.name}"/> 
		<br>
		
		<label>Orçamento</label>
		<input type="text" id="orcamento" name="orcamento" placeholder="Orçamento" value="${cat.orcamento}"/> 
		<br>
		
		<input type="submit" value="Salvar"/> 
		<button onclick="window.location.replace('categorias.jsp'); return false;">Cancelar</button>
	</form>

	
</body>
</html>