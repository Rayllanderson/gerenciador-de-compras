

function setTheme() {
	let wall = document.getElementById("wall");
	wall.style.backgroundImage = "none"
	
	//mudar btn-new-list para class="btn btn-outline-success"
	// mudar 2navbar para class = navbar navbar-expand navbar-dark justify-content-end
	// mudar navbar para class = navbar navbar-expand navbar-dark
	
	// mudando link do css  document.querySelector("link[href='u1.css']").href = "u2.css";
	
	switch (theme) {
		case 'PINK':
			//muda nome do class wall e carrega css 
			wall.style.backgroundColor = "#e7008a";
			break;
		case 'BLUE':
			wall.style.backgroundColor = "#0099ff";
			break;
		case 'RED':
			wall.style.backgroundColor = "#E72553";
			break;
		case 'PURPLE':
			wall.style.backgroundColor = "#5000ca";
			break;
		case 'CIAN':
			wall.style.backgroundColor = "#3CD6F0";
			break;
		case 'GREEN':
			wall.style.backgroundColor = "#00cba9";
			break;
		case 'BLACK':
			wall.style.backgroundColor = "#273036";
			break;
		case 'ORANGE':
			wall.style.backgroundColor = "#ff5500";
			break;
		case 'GALAXY':
			wall.style.backgroundImage = "url('resource/img/galaxy.jpg')"
			break;
		case 'DEFAULT':
			//mudar a class da #navbar para class="navbar navbar-expand navbar-dark bg-primary"
			//mudar a class da #2navbar para class="card card-signin"
			//mudar a class do btn-new-list para class="btn btn-success"
		
			break;
	}

}

