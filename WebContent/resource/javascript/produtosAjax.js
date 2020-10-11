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
	
	//passando de obj pra string
	if(comprado == true){
		comprado = 'true'
	}else{
		comprado = 'false'
	}

	console.log(nome)
	console.log(estipulado)
	console.log(real)
	console.log(comprado)
	console.log(cat_id)
	
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
			}).done(function(response) {
				alertBoostrap(setMessageAlert(id, nome), 'alert alert-success', "Sucesso")	
				$('#exampleModal').modal('hide')
				$.get("produtos?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
					$("#tabela-produtos").html($(responseXml).find("data").html()); // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
				}); 
	
			}).fail(function(xhr, status, errorThrown) {
				alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger', "Erro")
				$('#exampleModal').modal('hide')
					$.get("produtos?acao=listar", function(responseXml) {          
					$("#tabela-produtos").html($(responseXml).find("data").html());
			});
		});
		} else {
			alertBoostrap("O campo Nome não pode ser nulo", 'alert alert-danger', "Erro")
			$('#exampleModal').modal('hide')
		}
}



