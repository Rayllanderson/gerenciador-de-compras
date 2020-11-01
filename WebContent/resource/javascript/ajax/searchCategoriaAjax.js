function search(){
	const search = document.getElementById("search").value;
	$.ajax({
			method: "GET",
			url: "categorias?acao=search",
			data: { search : search }
		}).done(function() {
			$.get("categorias.jsp", function(responseXml) {          
				$("#divtable").html($(responseXml).find("data").html());
			});
		}).fail(function(xhr) {
			alertBoostrap(xhr.responseText, 'alert alert-danger')
		});

}