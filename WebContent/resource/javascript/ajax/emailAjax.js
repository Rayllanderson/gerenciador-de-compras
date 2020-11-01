$(".alert").hide(); 

$('#submit').click(function(e){
  	let email = $('#email').val();
	let message = $('#message').val();
	console.log(email, message)
	if(email && message){
			$.ajax({
				method: "POST",
				url: "email",
				data: {
					email: email, 
					message: message
					}
			}).done(function() {
				alertBoostrap("Email enviado com sucesso! Obrigado!", 'alert alert-success', "Sucesso")			
			}).fail(function(xhr) {
				alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
		});
		} else {
			alertBoostrap("Um ou mais campos estão vazios", 'alert alert-danger', "Erro")
		}
		e.preventdefault();
});

var $loading = $('#loading').hide();
$(document)
  .ajaxStart(function () {
    $loading.show();
  })
  .ajaxStop(function () {
    $loading.hide();
  });




