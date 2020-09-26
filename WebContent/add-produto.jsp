<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ray.model.entities.Product"%>
<%@ page import= "java.io.IOException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Seus produtos</title>

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>

	<h1>Produtos</h1>
	<form action="produtos?acao=salvar" method="post">

		<input type="text" id="nome" name="nome" placeholder="Nome do Produto"
			required="required" value="${nome}" /> <br> <input type="text"
			id="estipulado" name="estipulado" placeholder="Preço Estipulado" />
		<br>
		<input type="text" id="real" name="real"
			placeholder="Preço Real" />
		<br>
		<label for="comprado">Produto
			já foi comprado?</label>
		<input type="checkbox" id="comprado" name="comprado" onclick="myFunction()">
		<span id="text"></span>
		<br> 
		<input type="submit" value="Salvar" />
		<button
			onclick="window.location.replace('produtos.jsp'); return false;">Cancelar</button>
	</form>
	<script src="resource/javascript/apenasNumeros.js"></script>
	<script type="text/javascript">
		const msg = "${error}"
		console.log(msg)
		if (msg != null && msg != '') {
			alert(msg)
		}
		
		myFunction();
		
		function myFunction() {
			var checkBox = document.getElementById("comprado");
			var text = document.getElementById("text");
			if (checkBox.checked == true) {
				text.innerHTML = 'Sim'
			} else {
				text.innerHTML = 'Não'
			}
		}
	</script>
</body>
</html>