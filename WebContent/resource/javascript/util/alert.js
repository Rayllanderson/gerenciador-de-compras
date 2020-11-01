function alertBoostrap(msg, classe, titulo) {
	$(".alert").show();

	const title = document.getElementById('titulo');

	if(titulo != undefined){	
		if(title != null){
		title.innerHTML = titulo
		}
	}else{
		title.innerHTML = ''
	}
	document.getElementById('alertMsg').innerHTML = msg;
	document.getElementById("success-alert").className = classe;
	$("#success-alert").fadeTo(2500, 500).slideUp(500, function() {
		$("#success-alert").slideUp(500);
	});
}