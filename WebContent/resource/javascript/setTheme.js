

function themeSwitch(colorTheme) {
	var wall = document.getElementById("wall");
	wall.style.backgroundImage = "none"
	
	let btnList = document.getElementById('btn-new-list');
	let navbar = document.getElementById('navbar');
	let seccondNavbar = document.getElementById('2navbar');
	
	console.log("hallo????" , colorTheme.toUpperCase())
	
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
			break;
		case 'DEFAULT':
			btnList.className = 'btn btn-success';
			navbar.className = 'navbar navbar-expand navbar-dark bg-primary';
			seccondNavbar.className = 'card card-signin';
			wall.className = "wall";
			document.querySelector("link[href='resource/css/custom-categoria-themes.css']").href = "resource/css/default-theme.css";
			break;
	}
}

/**
	muda o nome das classes e o css se houver necessidade
 */
function changeClassName(btnList, navbar, seccondNavbar){
		
		console.log(btnList.className)
		
	if(btnList.className != 'btn btn-outline-success'){ //está no defaultT-theme
		console.log('ola;;;')
		btnList.className = 'btn btn-outline-success';
		navbar.className = 'navbar navbar-expand navbar-dark';
		seccondNavbar.className = 'navbar navbar-expand navbar-dark justify-content-end';
		document.querySelector("link[href='resource/css/default-theme.css']").href = "custom-categoria-themes.css";	

		
	}//senao, nao tem necessidade de mudar, já que já estão no tema
}





