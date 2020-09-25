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
	
	<form action="categorias?acao=saveList" method="post">
		
		<input type="text" id="nomeLista" name="nomeLista" placeholder="Nome da Lista"/> 
		<br>
		<input type="text" id="orcamento" name="orcamento" placeholder="Orçamento"/> 
		<br>
		<input type="submit" value="Salvar"/> 
	</form>

	
</body>
</html>