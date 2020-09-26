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
	
	<form action="produtos?acao=salvar" method="post">
		
		<input type="text" id="nome" name="nome" placeholder="Nome do Produto"/> 
		<br>
		<input type="text" id="estipulado" name="estipulado" placeholder="Preço Estipulado"/> 
		<br>
		<input type="text" id="real" name="real" placeholder="Preço Real"/> 
		<br>
		<label for="comprado">Produto já foi comprado?</label>
		<input type="checkbox" id="comprado" name="comprado">
		<br>
		<input type="submit" value="Salvar"/> 
		<button onclick="window.location.replace('produtos.jsp'); return false;">Cancelar</button>
	</form>

	
</body>
</html>