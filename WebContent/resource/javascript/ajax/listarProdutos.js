function listar(url){
	$.ajax({
			method: "GET",
			url: url,
		}).done(function(response) {

			if(response != '' && response.length < 60){
				alertBoostrap(response, 'alert alert-info')
			}			
			$.get("produtos.jsp", function(responseXml) {          
				$("#tabela-produtos").html($(responseXml).find("data").html());
				tupi();
			});
		}).fail(function(xhr) {
			alertBoostrap(xhr.responseText, 'alert alert-danger')
		});

}