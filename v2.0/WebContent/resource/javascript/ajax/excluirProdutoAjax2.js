$(".alert").hide();

var id1 = null;
var catId = null;

$('#exampleModalCenter').on('show.bs.modal', function(event) {
	$('#exampleModalCenter').modal('show')
	var button = $(event.relatedTarget) // Button that triggered the modal
	var nome = button.data('nome')
	var modal = $(this)
	var idProd = button.data('id')
	const cat_id = button.data('cat_id')
	
	modal.find('#nomeProd').text(nome)
	id1 = idProd;
	catId = cat_id;
	});
	
	$(document).on("click", "#excluir", function() {	
		$.ajax({
			method: "GET",
			url: "all-products?action=delete",
			data: { id1 : id1,
			cat_id: catId}
		}).done(function() {
			$('#exampleModalCenter').modal('hide')
			alertBoostrap("Produto Excluído com Sucesso!", 'alert alert-success', "Sucesso")
			$.get("all-products", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
				$("#tabela-produtos").html($(responseXml).find("data").html()); 		  // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
				atualizarValoresAll()
				tupi(); //kkk tupi guarani
			});
		}).fail(function(xhr) {
			$('#exampleModalCenter').modal('hide')
			alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
		});
});
 //carregar as paginas certas aq
function atualizarValoresAll(){
	$('#total').load('resource/jsp/all-products/total2.jsp').html;
	$('#vtEstipulado').load('resource/jsp/all-products/vtEstipulado2.jsp').html;
	$('#gerais').load('resource/jsp/all-products/gerais2.jsp').html;
}



