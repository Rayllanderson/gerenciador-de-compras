$('.alert').hide();

$('.SearchBox-button').on('click', function() {
	const search = $('#search').val();
	if (search) {
		$.ajax({
			method: "GET",
			url: "all-products?action=search&home=true",
			data: { search: search },
			success: function() {
				window.location.replace("all-products.jsp")
			}, error: function(xhr) {
				alertBoostrap(xhr.responseText, 'alert alert-danger')
			}
		});
	}
});
