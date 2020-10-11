function search(){
	const search = document.getElementById("search").value;
	$.ajax({
			method: "GET",
			url: "produtos?acao=search",
			data: { search : search }
		}).done(function(response) {
			$.get("produtos.jsp", function(responseXml) {          
				$("#tabela-produtos").html($(responseXml).find("data").html());
			});
		}).fail(function(xhr) {
			alertBoostrap(xhr.responseText, 'alert alert-danger')
		});

}