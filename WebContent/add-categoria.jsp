<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>

<!--  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>  -->

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"
	src="C:\Users\rayll\Documents\jquery.mask.min.js"></script>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.orcamento').mask('#.##0,00', {
				reverse : true
			});
		})

		$(".teste").submit(function() {

			alert($(".money").unmask())
		});
	</script>
	
</head>
<body>
	<h1>Listas</h1>

	<input type="text" class="t" id="money">

	<form class="teste" action="categorias?acao=salvar" method="post">

		<input type="text" id="nomeLista" name="nomeLista"
			placeholder="Nome da Lista" /> <br> <input type="text"
			class="orcamento" id="orcamento" name="orcamento"
			placeholder="Orçamento" /> <br> <input type="submit"
			value="Salvar" />
		<button
			onclick="window.location.replace('categorias.jsp'); return false;">Cancelar</button>
	</form>

	<!-- 	<script src="resource/javascript/apenasNumeros.js"></script>  -->




</body>
</html>