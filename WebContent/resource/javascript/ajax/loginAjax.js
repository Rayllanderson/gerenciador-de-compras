$(".alert").hide();

$('#btn-submit').on('click', function () {

	const username = $('#username').val();
	const password = $('#password').val();
	
	$.ajax({
		method: 'POST',
		url: 'login',
		data: {
			username: username,
			password: password
		}
	}).done (function (){
		alertBoostrap("Login Realizado com sucesso", 'alert alert-success', "Sucesso")
		 setTimeout(function(){ window.location.replace("home.jsp") }, 1000);
	}).fail (function(xhr){
		alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger', "Erro")
	});

});