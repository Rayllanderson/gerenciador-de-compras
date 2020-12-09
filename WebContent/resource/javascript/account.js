function upload() {
	var target = document.querySelector("#target");
	var file = document.querySelector("#file").files[0];
	var reader = new FileReader();
	reader.onloadend = function() {
		target.src = reader.result;
		console.log('ola3')
	};
	if (file) {
		console.log('ola2')
		target.style.width = '260px';
		target.style.height = '260px';
		target.style.borderRadius = '50%';
		reader.readAsDataURL(file)
	} else {
		target.src = "resource/img/user.png"
	}
}



$('#exampleModal').on('show.bs.modal',
	function() {
		$("#alertE").hide();
	})


let menu = document.getElementById("menu");

$(window).on('click', function() {
	menu.style.opacity = 0;
});

$('#btn').on('click', function(event) {
	event.stopPropagation();
	menu.style.opacity = 1;
	menu.style.zIndex = 999;
});

if (window.history.replaceState) {
	window.history.replaceState(null, null, window.location.href);
}