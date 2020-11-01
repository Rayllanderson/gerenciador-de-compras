$(".alert").hide();


function saveAjax() {
	
	let nome = $('#categoriaName').val();
	let orcamento = $('#orcamento').val();
	let id = $('#idCat').val();
	let message = '';
	if (id == '') {
		message = "Sua lista foi criada com sucesso!"
	} else {
		message = "Sua lista foi editada com sucesso!"
	}
	
	console.log(nome, orcamento, id)

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
			$('#categoriaModal').modal('hide')
			$.get("categorias?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
				$("#divtable").html($(responseXml).find("data").html()); // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
			});
	
		}).fail(function(xhr) {
			alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger', "Erro")
			$('#categoriaModal').modal('hide')
		});
		
	}else{
		alertBoostrap("O campo Nome não pode ser nulo", 'alert alert-danger', "Erro")
		$('#categoriaModal').modal('hide')
	}
	}
