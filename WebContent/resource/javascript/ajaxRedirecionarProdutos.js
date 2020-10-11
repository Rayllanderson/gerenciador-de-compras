

$(document).on("click", "#teste", function() {            
   $.ajax({
				method: "POST",
				url: "produtos?acao=listar",
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
});