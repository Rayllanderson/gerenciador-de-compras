

function produtosThemeSwitch(colorTheme, animation) {

	var wall = document.getElementById("wall");
	let btnNew = document.getElementById('btn-new');
	let btnInfo = document.getElementById('btn-info');
	let navbar = document.getElementById('navbar');

	changeClassName(wall, btnNew, btnInfo, navbar)

	switch (colorTheme.toUpperCase()) {
		case 'PINK':
			//muda nome do class wall e carrega css 
			wall.className = "wall pinkP";
			break;
		case 'BLUE':
			wall.className = "wall blueP";
			break;
		case 'RED':
			wall.className = "wall redP";
			break;
		case 'PURPLE':
			wall.className = "wall purpleP";
			break;
		case 'CIAN':
			wall.className = "wall cianP";
			break;
		case 'GREEN':
			wall.className = "wall greenP";
			break;
		case 'BLACK':
			wall.className = "wall blackP";
			break;
		case 'ORANGE':
			wall.className = "wall orangeP";
			break;
		case 'GALAXY':
			wall.className = "wall galaxyP";
			break;
		case 'DEFAULT':
			wall.style.backgroundImage = ""
			btnNew.className = 'btn btn-success';
			btnInfo.className = 'btn btn-primary';
			navbar.className = 'navbar navbar-expand navbar-dark bg-primary';
			wall.className = "default";
			$("#tema").attr("href", 'resource/css/theme/produtos/DEFAULT.css')
			break;
	}
	$("#tema").attr("href", 'resource/css/theme/produtos/' + colorTheme.toUpperCase() + '.css');
	activeAnimation(animation);
}

/**
	muda o nome das classes e o css se houver necessidade
 */
function changeClassName(wall, btnNew, btnInfo, navbar) {
	if (wall.className == 'default' || wall.className == "default animate" || wall.className.includes('default')) { //está no default-theme
		btnNew.className = 'btn btn-outline-success';
		btnInfo.className = 'btn btn-outline-primary';
		navbar.className = 'navbar navbar-expand navbar-dark';
	}
}

function activeAnimation(animation) {
	if (animation) {
		let wall = document.getElementById("wall");
		wall.classList.add('animate');
		setTimeout(() => wall.classList.remove('animate'), 999);
	}
}





