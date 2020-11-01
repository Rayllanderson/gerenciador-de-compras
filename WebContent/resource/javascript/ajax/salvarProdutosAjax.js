$(".alert").hide();



// setando mensagem pro alert
function setMessageAlert(id, nome) {
	if (id == '') {
		return `Produto ${nome} criado com sucesso!`
	} else {
		return `Produto ${nome} editado com sucesso!`
	}
}


function saveProduct() {
	
	let id = $('#id').val();
	let nome = $('#recipient-name').val();
	let estipulado = $('#estipulado').val();
	let real = $('#real').val();
	var comprado = $('#comprado').is(':checked');
	let cat_id = $('#categoria').val();
	
	//passando de obj pra string
	if(comprado == true){
		comprado = 'true'
	}else{
		comprado = 'false'
	}
	
		// AJAX
		if (nome != '') {
			$.ajax({
				method: "POST",
				url: "produtos?acao=salvar",
				data: {
					id: id,
					nome: nome,
					estipulado: estipulado,
					real: real,
					comprado: comprado,
					cat_id: cat_id
					}
			}).done(function() {
				alertBoostrap(setMessageAlert(id, nome), 'alert alert-success', "Sucesso")	
				$('#exampleModal').modal('hide')
				$.get("produtos?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
					$("#tabela-produtos").html($(responseXml).find("data").html());
					tupi();
					atualizarValores()
				}); 
	
			}).fail(function(xhr) {
				alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
				$('#exampleModal').modal('hide')
		});
		} else {
			alertBoostrap("O campo 'Nome' não pode ser nulo", 'alert alert-danger', "Erro")
			$('#exampleModal').modal('hide')
		}
}

function atualizarValores(){
	$('#disponivel').load('resource/jsp/disponivel.jsp').html;
					$('#economizado').load('resource/jsp/economizado.jsp').html;
					$('#gerais').load('resource/jsp/gerais.jsp').html;
					$('#total').load('resource/jsp/total.jsp').html;
					$('#vtEstipulado').load('resource/jsp/vtEstipulado.jsp').html;
}


  
function tupi(){
	if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)){
                		document.getElementById('tabela').className = "table table-sm";
                		document.getElementById('search').style.marginLeft = "2%"
                		document.getElementById('search').style.marginRight = "2%"
                	}else{
                		document.getElementById('tabela').className = "table";
                	}
                
}




