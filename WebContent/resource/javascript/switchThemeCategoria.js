

function categoriaThemeSwitch(colorTheme, animation) {

	console.log('animation = ', animation)

	var wall = document.getElementById("wall");
	wall.style.backgroundImage = "none"

	let btnList = document.getElementById('btn-new-list');
	let navbar = document.getElementById('navbar');
	let seccondNavbar = document.getElementById('seccond-navbar');

	changeClassName(btnList, navbar, seccondNavbar)


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
			wall.style.backgroundImage = ""
			wall.className = "wall galaxy";
			break;
		case 'DEFAULT':
			btnList.className = 'btn btn-success';
			navbar.className = 'navbar navbar-expand navbar-dark bg-primary';
			seccondNavbar.className = 'card card-signin';
			wall.className = "default";
			document.querySelector("link[href='resource/css/custom-categoria-theme.css']").href = "resource/css/default-categoria-theme.css";
			//aq
			break;
	}
	activeAnimation(animation);
}

/**
	muda o nome das classes e o css se houver necessidade
 */
function changeClassName(btnList, navbar, seccondNavbar) {

	if (wall.className == 'default' || wall.className == "default animate") { //está no defaultT-theme
		btnList.className = 'btn btn-outline-success';
		navbar.className = 'navbar navbar-expand navbar-dark';
		seccondNavbar.className = 'navbar navbar-expand navbar-dark justify-content-end';
		document.querySelector("link[href='resource/css/default-categoria-theme.css']").href = "resource/css/custom-categoria-theme.css";
	}//senao, nao tem necessidade de mudar, já que já estão no tema
}

function activeAnimation(animation) {
	if (animation) {
			
		let wall = document.getElementById("wall");
		wall.classList.add('animate');
		setTimeout(() => wall.classList.remove('animate'), 999);
	}
}





