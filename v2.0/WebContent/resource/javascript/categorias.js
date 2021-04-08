$('#categoriaModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget) // Button that triggered the modal
	var title = button.data('title')
	var id = button.data('id') // Extract info from data-* attributes
	var recipientnome = button.data('nome')
	var recipientOrcamento = button.data('orcamento')
	// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
	// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
	var modal = $(this)
	modal.find('.modal-title').text(title)
	modal.find('#idCat').val(id)
	modal.find('#categoriaName').val(recipientnome)
	modal.find('#orcamento').val(recipientOrcamento)
})


$(document).ready(function() {
	$('#orcamento').mask('000.000.000.000.000,00', {
		reverse: true
	});
});

if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
	document.getElementById('search').style.marginLeft = "2%"
	document.getElementById('search').style.marginRight = "2%"
}

	if ( window.history.replaceState ) {
		  window.history.replaceState( null, null, window.location.href );
		}