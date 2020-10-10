function search(){
	const search = document.getElementById("search").value;
	$.ajax({
			method: "GET",
			url: "categorias?acao=search",
			data: { search : search }
		}).done(function(response) {
			$.get("categorias.jsp", function(responseXml) {          
				$("#divtable").html($(responseXml).find("data").html());
			});
		}).fail(function(xhr, status, errorThrown) {
			alertBoostrap(xhr.responseText, 'alert alert-danger')
		});

}