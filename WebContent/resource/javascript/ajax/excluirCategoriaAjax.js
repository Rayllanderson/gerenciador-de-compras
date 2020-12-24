$(".alert").hide();

var id1 = null;

$('#exampleModalCenter').on('show.bs.modal', function(event) {
	$('#exampleModalCenter').modal('show')
	var button = $(event.relatedTarget) // Button that triggered the modal
	var nome = button.data('nome')
	var modal = $(this)
	var idCat = button.data('id')
	modal.find('#nomeCat').text(nome)
	id1 = idCat;
	});
	
	$(document).on("click", "#excluir", function() {	
		$.ajax({
			method: "GET",
			url: "categorias?acao=excluir",
			data: { id1 : id1 }
		}).done(function() {
			$('#exampleModalCenter').modal('hide')
			alertBoostrap("Lista Excluída com Sucesso!", 'alert alert-success', "Sucesso")
			$.get("categorias?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
				$("#divtable").html($(responseXml).find("data").html()); 		  // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
			});
		}).fail(function(xhr) {
			$('#exampleModalCenter').modal('hide')
			alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
		});
});