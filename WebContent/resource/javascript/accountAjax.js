$(".alert").hide(); 


$('#change-pass').on('click', function (){
  	let old = $('#old').val();
	let new1 = $('#new1').val();
	let new2 = $('#new2').val();


		if (old && new1 && new2) {
			$.ajax({
				method: "POST",
				url: "my-account?action=change-password",
				data: {
					old : old,
					new1 : new1,
					new2 : new2
					}
			}).done(function() {
				alertBoostrap("Sua senha foi alterada com sucesso", 'alert alert-success', "Sucesso")	
				$('#exampleModal').modal('hide')
				$.get("my-account?action=view")
				
			}).fail(function(xhr) {
				alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
				$('#exampleModal').modal('hide')
		});
		} else {
			alertBoostrap("Um ou mais campos estão nulos", 'alert alert-danger', "Erro")
		}
});


if (error) {
		console.log("yes, fail", error)
		alertBoostrap(error, 'alert alert-danger')
		error= '';
	}

	if (success) {
		console.log("yes success", success)
		alertBoostrap(success, 'alert alert-success')
		success = '';
	}

	function alertBoostrap(msg, classe){
	  $(".alert").show();
	  document.getElementById('alertMsg').innerHTML = msg;
	  document.getElementById("success-alert").className = classe;
	  $("#success-alert").fadeTo(2700, 500).slideUp(500, function(){
	    $("#success-alert").slideUp(500);
	});
	}