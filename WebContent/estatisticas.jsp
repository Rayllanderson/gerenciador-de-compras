<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	
	<%@page pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=0.9">

<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="resource/css/icon-perfil.css" type="text/css" rel="stylesheet" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>                    
<script src="resource/javascript/util/fa.js"></script>

<!-- canvas JS -->
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<!-- chart.js 2.9.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js" integrity="sha512-d9xgZrVZpmmQlfonhQUvTR7lMPtO7NkZMkA0ABN3PHCbKA5nqylQ/yWlFAyY6hYgdF1Qh6nYiuADWwKB4C2WSw==" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<style type="text/css">

html, body{
	height: 100%;
}

body{
background-image: url('resource/img/account.jpg');
background-repeat: no-repeat;
}


.content {
 min-height: 100%; 
 padding-bottom: 100px; 
}

footer{

}


nav{
box-shadow: 0.5rem 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);
}


.dropdown-item.active, .dropdown-item:active{
    color: #fff;
    background-color: #7500B3;
}



</style>

<title>My Account</title>
</head>
<body>

<div class="content">

                   <header>
                         <nav class="navbar navbar-expand navbar-dark">
                            <a class="navbar-brand" href="home.jsp">
                           <i class="fas fa-arrow-left fa-md"></i>
                          </a>
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample02" aria-controls="navbarsExample02" aria-expanded="false" aria-label="Toggle navigation">
      							 <span class="navbar-toggler-icon"></span>
     						 </button>
                            <div class="collapse navbar-collapse" id="navbarsExample02">
                                <ul class="navbar-nav mr-auto">
                                    <li class="nav-item">
                                        <a class="nav-link" href="home.jsp"> <i class="fas fa-home fa-sm"></i> Home</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" href="categorias"> <i class="fas fa-clipboard fa-sm"></i> Listas</a>
                                    </li>
                                    <li class="nav-item">
                                    	<a class="nav-link" href="produtos" ><i class="fas fa-shopping-cart fa-sm"></i> Produtos</a>
                                    </li>
                                </ul>
                            </div>
                            
                            
                            <c:if test="${!user.miniatura.isEmpty() && user.miniatura != null}">
                            		<div class="icon-perfil" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            			<input  class="img-perfil" type="image" src="${user.miniatura }" />
                            		</div>
                            </c:if>
                            
                            
                             <c:if test="${user.miniatura.isEmpty() || user.miniatura == null}">
                            <button type="button" class="btn btn-outline-light" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <!-- botao user -->
									<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
										  <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
										  <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
										  <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
									</svg>
							</button>
							</c:if>
							
                            <div class="dropdown-menu dropdown-menu-right">
                                <a class="dropdown-item" href="#">Minha Conta</a>
                                <a class="dropdown-item" href="#">Another action</a>
                                <a class="dropdown-item" href="#">Something else here</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>

</nav>
</header> 	<!--  END NAVBAR  -->
				
				
	<!--  

<hr>
<div class="container">
	
	
		<div class="card card-signin my-5" style="border-radius: 1em">
			<article class="card-body mx-auto">
			<h4 class="card-title mt-3 text-center">Informações Gerais</h4>
			<p id="pName">Olá, <strong> ${user.name}</strong>! </p>
			<p>Você possui ${tListas} listas no total </p>
			<p>Você comprou produtos de um total de ${tProdutos} </p>
			<p>Você já gastou ${tGasto} </p>
			<p>Você pretende gastar ${tEstipulado} </p>
			</article>
			</div>
			</div>
			
			
			 -->			
				


	<div class="container" style="margin-top: 5%">

		<div class="card mt-3 p-3" style="border-radius: 1em">
				<h4 class="card-title mt-3 text-center">Estatísticas</h4>
				<div class="text-center">
					<p>Olá, <strong> ${user.name}</strong>!</p>
					
				</div>
			<ul>
				<li> <p>Número total de listas: <span> ${tListas} </span> </p> </li>
			</ul>	
			
			<ul>
			 <li><p>Número total de produtos: <span> ${tProdutos} </span></p></li>
			</ul>		
						
			<div class="text-center">
					<p class="text-uppercase font-weight-bold">Quantidade de Produtos</p>
			</div>	
			<div id="chartContainer" style="height: 370px; width: 100%;"></div>
			
			<div class="mb-5"></div>
			
			<div class="text-center">
					<p class="text-uppercase font-weight-bold">Valores dos Produtos</p>
			</div>	
			
			<p>PS: o gráfico abaixo está levando em consideração apenas os produtos <a href='#'> marcados como comprados </a> </p>
			<canvas id="myChart"></canvas>
			
			<div class="mb-5"></div>
			
			<div class="text-center">
					<p class="text-uppercase font-weight-bold">Simulando Valores dos Produtos</p>
			</div>	
			
			<p>PS: o gráfico abaixo está levando em consideração todos os produtos. Caso já esteja marcado como comprado, leva em consideração o valor real. Caso não, leva em consideração
			o preço estipulado </p>
			<canvas id="myChart2"></canvas>
				
				
		</div>
		<!-- card.// -->
	</div>

</div>





<script>

var qtdProdutosComprados = parseInt('${nProdutosComprados}');
var total = parseInt("${tProdutos}");
var qtdProdutosNaoComprados = total - qtdProdutosComprados;
var valorReal = Number("${tGasto}");
var valorEstipulado = Number("${tEstip}");

console.log(valorEstipulado)

var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	exportEnabled: true,
	animationEnabled: true,
	title: {
		text: ""
	},
	data: [{
		type: "pie",
		startAngle: 25,
		toolTipContent: " <b>{label}</b>: {y} ",
		showInLegend: "true",
		legendText: "{label}",
		indexLabelFontSize: 14,
		indexLabel: " {label} {y} ",
		dataPoints: [
			{ y: qtdProdutosNaoComprados, label: "Não Comprados" },
			{ y: qtdProdutosComprados, label: "Comprados" }
		]
	}]
});
chart.render();
$(".canvasjs-chart-credit").hide();



var ctx = document.getElementById('myChart');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Valor Gasto', 'Valor Estipulado', 'Valor Economizado'],
        datasets: [{
            label: 'R$',
            data: [valorReal, valorEstipulado, valorEstipulado - valorReal],
            backgroundColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});

var ctx2 = document.getElementById('myChart2');
var myChart2 = new Chart(ctx2, {
    type: 'bar',
    data: {
        labels: ['Valor Gasto', 'Valor Estipulado', 'Valor Economizado'],
        datasets: [{
            label: 'R$',
            data: [10, 15, 15 - 10 ],
            backgroundColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});


</script>	
	
		


</body>
</html>