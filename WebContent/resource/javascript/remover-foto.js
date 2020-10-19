$('#excluir').on('click', function (){
			$.ajax({
				method: "DELETE",
				url: "my-account?action=remove-photo",
			}).done(function() {
				$('#exampleModalCenter').modal('hide')
				 	reloadPage()
			}).fail(function(xhr) {
				alertBoostrap(xhr.responseText, 'alert alert-danger', "Erro")
		});
});

function reloadPage(){
	setTimeout(function(){
			location.reload();
	}, 2000)
	 alertBoostrap("Foto removida com sucesso. Recarregando a página...", 'alert alert-success', "Sucesso")
}


let target = document.getElementsByClassName("without-photo");
if(target.length > 0){
	document.getElementById('remove').style.display = "none";
}else{
	console.log('false');
	document.getElementById('remove').style.display = "block";
}
