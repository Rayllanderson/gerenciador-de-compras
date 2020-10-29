$('.alert').hide();

$('.SearchBox-button').on('click', function() {
	console.log('click..')
	const search = $('#search').val();
	console.log(search)
	$.ajax({
		method: "GET",
		url: "all-products?action=search",
		data: { search: search },
		success: function() {
		window.location.replace("all-products.jsp")
	}, error: function(xhr) {
		alertBoostrap(xhr.responseText, 'alert alert-danger')
	}
});
});
