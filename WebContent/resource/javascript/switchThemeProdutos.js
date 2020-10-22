

function categoriaThemeSwitch(colorTheme, animation) {

	console.log('animation = ', animation)

	var wall = document.getElementById("wall");
	

	let btnNew = document.getElementById('btn-new');
	let btnInfo = document.getElementById('btn-info');
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
			wall.style.backgroundImage = "url('resource/img/galaxy.jpg')"
			wall.className = "wall galaxy";
			break;
		case 'DEFAULT':
			wall.style.backgroundImage = "none"
			//navbar para class="navbar navbar-expand navbar-dark bg-primary"
			//seccond-navbar = card card-signin
			//btn-new =  class="btn btn-success" 
			//btn-info = class="btn btn-primary
			
			//document.querySelector("link[href='resource/css/custom-categoria-themes.css']").href = "resource/css/default-theme.css";
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
		document.querySelector("link[href='resource/css/default-theme.css']").href = "resource/css/custom-categoria-themes.css";
	}//senao, nao tem necessidade de mudar, já que já estão no tema
}

function activeAnimation(animation) {
	if (animation) {
			
		let wall = document.getElementById("wall");
		wall.classList.add('animate');
		setTimeout(() => wall.classList.remove('animate'), 999);
	}
}





