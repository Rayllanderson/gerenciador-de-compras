<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ray.model.entities.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>
</head>
<body>
<h1>Listas</h1>
	
	<form action="produtos?acao=salvar" method="post"> 
		<input type="hidden" id="id" name="id" value="${produto.id}">
		
		<label for="categoria">Nome do Produto</label>
		<input type="text" id="nome" name="nome" placeholder="Nome do Produto" value="${produto.nome}"/> 
		<br>
		
		<label for="categoria">Preço Estipulado</label>
		<input type="text" id="estipulado" name="estipulado" placeholder="Preço Estipulado" value="${produto.precoEstipulado}"/> 
		<br>
		
		<label for="categoria">Preço Real</label>
		<input type="text" id="real" name="real" placeholder="Preço Real" value="${produto.precoReal}"/> 
		<br>
		
		<label for="comprado">Produto já foi comprado?</label>
		<input type="checkbox" id="comprado" name="comprado" onclick="myFunction()"/> 
		<span id="text"></span>
		<br>
		
		<label for="categoria">Lista</label>
		<select id="categoria">
		<c:forEach items="${categorias}" var="cat"  >
 			 <option value="${cat.id}" id="${cat.id}" 
 			 <c:if test="${cat.id == produto.categoria.id}">
 			 
 			 	<c:out value="selected=selected"/>
 			 
 			 </c:if>
 			 >${cat.name}</option>
 		</c:forEach>
		</select>
		

		<br>
		<input type="submit" value="Salvar"/> 
		<button onclick="window.location.replace('produtos.jsp'); return false;">Cancelar</button>
	</form>

	
<script>

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