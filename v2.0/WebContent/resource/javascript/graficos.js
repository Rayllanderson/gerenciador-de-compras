
const blue = 'rgba(54, 162, 235, 1)';
const pink = 'rgba(232, 62, 140, 1)';
const red = 'rgba(255, 98, 94, 1)';
const yellow = "rgba(241, 200, 14, 1)"
const dark = 'rgba(55, 70, 73, 1)';
const green = 'rgba(2, 184, 171, 1)';


var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light2", // "light1", "light2", "dark1", "dark2"
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
			{ y: numProdutosNaoComprados, label: "Não Comprados" },
			{ y: numProdutosComprados, label: "Comprados" }
		]
	}]
});
chart.render();
$(".canvasjs-chart-credit").hide();





//apenas produdos comprados

var ctx = document.getElementById('myChart');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Valor Gasto', 'Valor Estipulado', 'Valor Economizado'],
        datasets: [{
            label: 'R$',
            data: [valorGastoComprados, valorEstipuladoComprados, valorEconomizadoComprados],
            backgroundColor: [
            	blue,
                pink,
                getColor(valorEconomizadoComprados)
            ],
            borderColor: [
            	blue,
            	pink,
            	 getColor(valorEconomizadoComprados)
            ],
            borderWidth: 1
        }]
    },
    options: {
        
        scales: {
            yAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }
    }
});





//todos os produtos
var ctx2 = document.getElementById('myChart2').getContext('2d');
var myChart2 = new Chart(ctx2, {
    type: 'horizontalBar',
    data: {
        labels: ['Economizado', 'Falta Gastar', 'Valor Gasto', 'V. Estipulado', 'Valor Total'],
        datasets: [{
            label: 'R$',
            data: [economizado, restante, valorGasto, valorEstipulado, total],
            backgroundColor: [
            	getColor(economizado),
                yellow,
                blue,
                pink,
                dark 
            ],
            borderColor: [
            	getColor(economizado),
                yellow,
                blue,
                pink,
                dark  
            ],
            borderWidth: 1
        }]
    },
    options: {
    	maintainAspectRatio: false,
        scales: {
            xAxes: [{
                ticks: {
                    beginAtZero: true
                }
            }]
        }   
    }
});


function getColor(economizado){
	return economizado >= 0 ? green : red;
}


$('.marcado-comprado').on('click', function () {
//	$('#titulo-modal').html('Um produto é marcado como comprado desta forma: <br>');
	$("#img").attr("src","resource/img/estatisticas/marcado-comprado.png");
	
});

$('.valor-real').on('click', function () {
//	$('#titulo-modal').html('Um produto é marcado como comprado desta forma: <br>');
	$("#img").attr("src","resource/img/estatisticas/preco-real.png");
});