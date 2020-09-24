
function validarDados() {
	const login = document.getElementById("username").value;
	let senha = ''
	if (document.getElementById("password") != null) {
		senha = document.getElementById("password").value;
	}
	return validarNull(login, senha);
}

function validarNull(login, senha) {
	if (login.trim() == '' || senha == '') {
		alert("Um ou mais campos estao vazios")
		return false
	}
	return true;
}