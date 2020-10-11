function listar(url){
	//const search = document.getElementById("search").value;
	$.ajax({
			method: "GET",
			url: url,
		}).done(function(response) {

			if(response != '' && response.length < 60){
				alertBoostrap(response, 'alert alert-info')
			}
			
			$.get("produtos.jsp", function(responseXml) {          
				$("#tabela-produtos").html($(responseXml).find("data").html());
			});
		}).fail(function(xhr, status, errorThrown) {
			alertBoostrap(xhr.responseText, 'alert alert-danger')
		});

}