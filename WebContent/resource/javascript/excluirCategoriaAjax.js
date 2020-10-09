$(".alert").hide();

function alertBoostrap(msg, classe){
  $(".alert").show();
  document.getElementById('alertMsg').innerHTML = msg;
  document.getElementById("success-alert").className = classe;
  $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
}



$('#exampleModalCenter').on('show.bs.modal', function (event) {
	$('#exampleModalCenter').modal('show')
	var button = $(event.relatedTarget) // Button that triggered the modal
	var id = button.data('id') // Extract info from data-* attributes
	var nome = button.data('nome')	
	var modal = $(this)
	modal.find('#nomeCat').text(nome)
	$(document).on("click", "#excluir", function() {  
	$.ajax({
			    method: "GET",
		    	url: "categorias?acao=excluir",
			    data: { id : id}
			}).done(function(response){
				$('#exampleModalCenter').modal('hide')
				document.getElementById('titulo').innerHTML="Sucesso!"
				alertBoostrap("Lista Excluída com Sucesso!", 'alert alert-success')
				$.get("categorias?acao=listar", function(responseXml) {                // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response XML...
			        $("#divtable").html($(responseXml).find("data").html()); // Parse XML, find <data> element and append its HTML to HTML DOM element with ID "somediv".
			});
				
			}).fail(function(xhr, status, errorThrown) {
				document.getElementById('titulo').innerHTML="Error!"
				$('#exampleModalCenter').modal('hide')
				alertBoostrap("Ocorreu um erro inesperado. " + erroThrown, 'alert alert-danger')
			});
})

});


