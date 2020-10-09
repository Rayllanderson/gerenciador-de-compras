$(".alert").hide();

function alertBoostrap(msg, classe) {
	$(".alert").show();
	document.getElementById('alertMsg').innerHTML = msg;
	document.getElementById("success-alert").className = classe;
	$("#success-alert").fadeTo(2000, 500).slideUp(500, function() {
		$("#success-alert").slideUp(500);
	});
}


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
	

if (nome != ''){
		
	$.ajax({
		method: "POST",
		url: "categorias?acao=salvar",
		data: {
			nome: nome,
			orcamento: orcamento,
			id: id
		}
	}).done(function(response) {
		document.getElementById('titulo').innerHTML = "Sucesso!"
		alertBoostrap(message, 'alert alert-success')
		$('#exampleModal').modal('hide')
		$.get("categorias?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
			$("#divtable").html($(responseXml).find("data").html()); // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
		});

	}).fail(function(xhr, status, errorThrown) {
		document.getElementById('titulo').innerHTML = "Error!"
		alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger')
		$('#exampleModal').modal('hide')
		$.get("categorias?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
			$("#divtable").html($(responseXml).find("data").html());
		});
	});
}else{
	document.getElementById('titulo').innerHTML = "Error!"
	alertBoostrap("Um ou mais campos estão vazios", 'alert alert-danger')
	$('#exampleModal').modal('hide')
}
}



