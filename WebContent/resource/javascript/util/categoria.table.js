$(document).ready(function() {
	$('#tabela').DataTable({
		"language": {
			"decimal": ",",
			"thousands": "."
		},
		"order": [],
		columnDefs: [
			{ orderable: false, targets: 2 },
			{ orderable: false, targets: 3 }
		],
	});
})