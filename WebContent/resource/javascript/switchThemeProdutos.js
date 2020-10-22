

function produtosThemeSwitch(colorTheme, animation) {

	var wall = document.getElementById("wall");
	let btnNew = document.getElementById('btn-new');
	let btnInfo = document.getElementById('btn-info');
	let navbar = document.getElementById('navbar');
	let seccondNavbar = document.getElementById('seccond-navbar');

	changeClassName(btnNew, btnInfo, navbar, seccondNavbar)


	switch (colorTheme.toUpperCase()) {
		case 'PINK':
			//muda nome do class wall e carrega css 
			wall.className = "wall pink";
			break;
		case 'BLUE':
			wall.className = "wall blue";
			break;
		case 'RED':
			wall.className = "wall red";
			break;
		case 'PURPLE':
			wall.className = "wall purple";
			break;
		case 'CIAN':
			wall.className = "wall cian";
			break;
		case 'GREEN':
			wall.className = "wall green";
			break;
		case 'BLACK':
			wall.className = "wall black";
			break;
		case 'ORANGE':
			wall.className = "wall orange";
			break;
		case 'GALAXY':
		
			wall.className = "wall galaxy";
			break;
		case 'DEFAULT':
			wall.style.backgroundImage = ""
			btnNew.className = 'btn btn-success';
			btnInfo.className = 'btn btn-primary';
			navbar.className = 'navbar navbar-expand navbar-dark bg-primary';
			seccondNavbar.className = 'card card-signin';
			wall.className = "default";
			document.querySelector("link[href='resource/css/custom-produtos-themes.css']").href = "resource/css/default-theme-produto.css";
			break;
	}
	activeAnimation(animation);
}

/**
	muda o nome das classes e o css se houver necessidade
 */
function changeClassName(btnNew, btnInfo, navbar, seccondNavbar) {

	if (wall.className == 'default' || wall.className == "default animate") { //está no defaultT-theme
		btnNew.className = 'btn btn-outline-success';
		btnInfo.className = 'btn btn-outline-primary';
		navbar.className = 'navbar navbar-expand navbar-dark';
		seccondNavbar.className = 'navbar navbar-expand navbar-dark justify-content-end';
		document.querySelector("link[href='resource/css/default-theme-produto.css']").href = "resource/css/custom-produtos-themes.css";
	}
}

function activeAnimation(animation) {
	if (animation) {
		let wall = document.getElementById("wall");
		wall.classList.add('animate');
		setTimeout(() => wall.classList.remove('animate'), 999);
	}
}





