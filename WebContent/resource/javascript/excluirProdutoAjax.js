$(".alert").hide();

var id1 = null;

$('#exampleModalCenter').on('show.bs.modal', function(event) {
	$('#exampleModalCenter').modal('show')
	var button = $(event.relatedTarget) // Button that triggered the modal
	var nome = button.data('nome')
	var modal = $(this)
	var idProd = button.data('id')
	modal.find('#nomeProd').text(nome)
	id1 = idProd;
	});
	
	$(document).on("click", "#excluir", function() {	
		console.log('dentro do metodo excluir: ', id1)
		$.ajax({
			method: "GET",
			url: "produtos?acao=excluir",
			data: { id1 : id1 }
		}).done(function(response) {
			$('#exampleModalCenter').modal('hide')
			alertBoostrap("Produto Excluído com Sucesso!", 'alert alert-success', "Sucesso")
			$.get("produtos?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
				$("#tabela-produtos").html($(responseXml).find("data").html()); 		  // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
			});
		}).fail(function(xhr, status, errorThrown) {
			alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger', "Erro")
			$('#exampleModal').modal('hide')
			$.get("produtos?acao=listar", function(responseXml) {          
				$("#tabela-produtos").html($(responseXml).find("data").html());
			});
		});
});
 




