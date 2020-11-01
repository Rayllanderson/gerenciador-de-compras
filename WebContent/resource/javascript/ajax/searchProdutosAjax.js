function search(){
	const search = document.getElementById("search").value;
	$.ajax({
			method: "GET",
			url: "produtos?acao=search",
			data: { search : search }
		}).done(function() {
			$.get("produtos.jsp", function(responseXml) {          
				$("#tabela-produtos").html($(responseXml).find("data").html());
				tupi();
			});
		}).fail(function(xhr) {
			alertBoostrap(xhr.responseText, 'alert alert-danger')
		});
}