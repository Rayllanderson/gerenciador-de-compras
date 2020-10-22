

function produtosThemeSwitch(colorTheme, animation) {

	var wall = document.getElementById("wall");
	let btnNew = document.getElementById('btn-new');
	let btnInfo = document.getElementById('btn-info');
	let navbar = document.getElementById('navbar');
	let seccondNavbar = document.getElementById('seccond-navbar');

	changeClassName(wall, btnNew, btnInfo, navbar, seccondNavbar)


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
			console.log('AAAAAA')
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
			seccondNavbar.className = 'card card-signin';
			wall.className = "default";
			document.querySelector("link[href='resource/css/custom-produto-theme.css']").href = "resource/css/default-produto-theme.css";
			break;
	}
	activeAnimation(animation);
}

/**
	muda o nome das classes e o css se houver necessidade
 */
function changeClassName(wall, btnNew, btnInfo, navbar, seccondNavbar) {
	if (wall.className == 'default' || wall.className == "default animate") { //está no defaultT-theme
		console.log('ce ta de sacanagem?')
		btnNew.className = 'btn btn-outline-success';
		btnInfo.className = 'btn btn-outline-primary';
		navbar.className = 'navbar navbar-expand navbar-dark';
		seccondNavbar.className = 'navbar navbar-expand navbar-dark justify-content-end';
		document.querySelector("link[href='resource/css/default-produto-theme.css']").href = "resource/css/custom-produto-theme.css";
	}
}

function activeAnimation(animation) {
	if (animation) {
		let wall = document.getElementById("wall");
		wall.classList.add('animate');
		setTimeout(() => wall.classList.remove('animate'), 999);
	}
}





