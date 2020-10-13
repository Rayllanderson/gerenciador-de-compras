$(".alert").hide();

function ajax(){
	let nome = $('#nome').val();
	let id =  $('#id').val();
	let username =  $('#username').val();
	
	$.ajax({
	    method: "POST",
    	    url: "my-account?action=editar",
	    data: { nome : nome,
	    		username : username,
	    		id : id}
	}).done(function(response){
		document.getElementById('titulo').innerHTML="Sucesso!"
		setColorAlert(response);
		document.getElementById('pName').innerHTML = 'Olá, <strong>' +  nome  + '</strong>!';
	}).fail(function(xhr) {
		document.getElementById('titulo').innerHTML="Error"
		alertBoostrap(xhr.responseText, 'alert alert-danger');
	});
}

function alertBoostrap(msg, classe){
  $(".alert").show();
  document.getElementById('alertMsg').innerHTML = msg;
  document.getElementById("success-alert").className = classe;
  $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
    $("#success-alert").slideUp(500);
});
}

function setColorAlert(response){
	if (response == 'Nenhuma alteração foi detectada.'){
		alertBoostrap(response, 'alert alert-warning')
	}else{
		alertBoostrap(response, 'alert alert-success')
	}
}
