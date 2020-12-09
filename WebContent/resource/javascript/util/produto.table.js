$(document).ready(function() {
	$('#tabela').DataTable({
		"language": {
			"decimal": ",",
			"thousands": "."
		},
		"order": [],
		columnDefs: [
			{ orderable: false, targets: 3 },
			{ orderable: false, targets: 4 },
			{ orderable: false, targets: 5 }
		],
	});
})