$(".alert").hide();


function login (){
$("#form-login").submit(function(e) {
    e.preventDefault();

    const username = $('#username').val();
	const password = $('#password').val();

	if (username && password) {
		$.ajax({
			method: 'POST',
			url: 'login',
			data: {
				username: username,
				password: password
			}
		}).done(function() {
			alertBoostrap("Login Realizado com sucesso", 'alert alert-success', "Sucesso")
			setTimeout(function() { window.location.replace("home.jsp") }, 200);
		}).fail(function() {
			alertBoostrap("Usuário ou senha inválidos", 'alert alert-danger', "Erro")
		});
	} else {
		alertBoostrap("Um ou mais campos estão vazios", 'alert alert-danger', "Erro")
	}
    
});
}

function cadastro (){
$("#form-cadastro").submit(function(e) {

    e.preventDefault(); 
	
	let name = $('#name').val();
	const username = $('#username-register').val();
    const password = $('#password-register').val();
	const password2 = $('#password-register2').val();
	
	if(name == '' || name == null){
		name = 'Convidado';
	}	
	
	if(username && password && password2){
		$.ajax({
			method: 'POST',
			url: 'cadastro',
			data: {
				nome: name,
				username: username,
				password: password,
				password2: password2
			}
		}).done(function() {
			alertBoostrap("Cadastro realizado com sucesso! Faça login para continuar", 'alert alert-success', "Sucesso")
			setTimeout(function() { $( "#sign-in-btn" ).trigger( "click" ); }, 500);
				setTimeout(function() { clearValues() }, 1500);
			
		}).fail(function(xhr) {
			alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
		});
	} else {
		alertBoostrap("Um ou mais campos estão vazios", 'alert alert-danger', "Erro")
	}
    
});
}

function clearValues(){
	$('#name').val('');
	$('#username-register').val('');
    $('#password-register').val('');
	$('#password-register2').val('');
}

login();
cadastro();
