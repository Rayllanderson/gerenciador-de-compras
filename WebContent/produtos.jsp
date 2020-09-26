<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Suas Listas</title>

<link href="resource/css/popup.css" type="text/css" rel="stylesheet" />

</head>
<body>
<a href="categorias?acao=voltar">Voltar</a>
<h1>produtos</h1>
	<h2 style="text-align: center">Voc� est� na lista ${categoria.name} </h2>
	<a href="add-produto.jsp">Novo produto</a>
	
	
	
	<a href="javascript: abrir()">Fun��es �teis</a>
    <a href="#" onmouseover="abrir()" onmouseout="fechar()">Efeitos com mouse</a>
	
	<div id="popup" class="popup">
        <p>ola</p>
        <a id="close" class="close" href="javascript: fechar()">�</a>
    </div>
    
    
    
	<c:forEach items="${produtos}" var="prod">
		<table>
			<tr>
				<td>
					<label>${prod.nome}</label>
					<input type="image" src="resource/img/edit.png" onclick="sendPost('produtos?acao=editar', {id: '${prod.id}'});" width="30px" height="30px" />
					
					
					<input type="image" onclick="
					if(confirm('Voc� tem certeza que deseja excluir o produto ${prod.nome}?')){
						sendPost('produtos?acao=excluir', {id: '${prod.id}'});
					}"src="resource/img/excluir.png" width="30px" height="30px" />
				</td>
			</tr>
		</table>
	</c:forEach>
	
	<script src="resource/javascript/popup.js"></script>
	<script src="resource/javascript/esconderUrl.js"></script>
</body>
</html>