$(".alert").hide();


function saveAjax() {
	$('#exampleModal').modal('show')
	let nome = $('#recipient-name').val();
	let orcamento = $('#orcamento').val();
	let id = $('#id').val();
	let message = '';
	if (id == '') {
		message = "Sua lista foi criada com sucesso!"
	} else {
		message = "Sua lista foi editada com sucesso!"
	}
	

	if (nome.trim() != ''){
		
		$.ajax({
			method: "POST",
			url: "categorias?acao=salvar",
			data: {
				nome: nome,
				orcamento: orcamento,
				id: id
			}
		}).done(function() {	
			alertBoostrap(message, 'alert alert-success', "Sucesso")
			$('#exampleModal').modal('hide')
			$.get("categorias?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
				$("#divtable").html($(responseXml).find("data").html()); // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
			});
	
		}).fail(function(xhr) {
			alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger', "Erro")
			$('#exampleModal').modal('hide')
		});
	}else{
		alertBoostrap("O campo Nome não pode ser nulo", 'alert alert-danger', "Erro")
		$('#exampleModal').modal('hide')
	}
	}



