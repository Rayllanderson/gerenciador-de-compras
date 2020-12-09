$(".alert").hide();
//modal de editar categoria
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
	$('#real').mask('000.000.000.000.000,00', {
		reverse: true
	});
});
$(document).ready(function() {
	$('#estipulado').mask('#.##0,00', {
		reverse: true
	});
});


function tupi() {
	if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
		document.getElementById('tabela').className = "table table-sm";
		document.getElementById('search').style.marginLeft = "2%"
		document.getElementById('search').style.marginRight = "2%";
	} else {
		document.getElementById('tabela').className = "table";
	}

}


$(document).ready(function() {
	$('#orcamento').mask('000.000.000.000.000,00', { reverse: true });
});




function setCheckedIfTrue(funcao) {

	if (funcao == 'true') {
		document.getElementById("comprado").checked = true;
		myFunction();
	} else {
		document.getElementById("comprado").checked = false;
		myFunction();
	}
}

function myFunction() {
	var checkBox = document.getElementById("comprado");
	var text = document.getElementById("text");
	if (checkBox.checked == true) {
		text.innerHTML = 'Sim'
	} else {
		text.innerHTML = 'Não'
	}
}

function disableCheckBox() {
	document.getElementById("comprado").checked = false;
	myFunction();
}
