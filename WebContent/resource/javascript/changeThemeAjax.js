

 function swapColor(colorTheme){
			
                	let wall = document.getElementById("wall");
                	wall.style.backgroundImage = "none"
                	switch (colorTheme){
                	case 'pink':
                		wall.style.backgroundColor = "#e7008a";
                		break;
                	case 'blue':
                		wall.style.backgroundColor = "#0099ff";
                		break;
                	case 'red':
                		wall.style.backgroundColor = "#E72553";
                		break;
                	case 'purple':
                		wall.style.backgroundColor = "#5000ca";
                		break;
                	case 'cian':
                		wall.style.backgroundColor = "#3CD6F0";
                		break;
                	case 'green':
                		wall.style.backgroundColor = "#00cba9";
                		break;
                	case 'black':
                		wall.style.backgroundColor = "#273036";
                		break;
                	case 'orange':
                		wall.style.backgroundColor = "#ff5500";
                		break;
                	case 'galaxy':
                		wall.style.backgroundImage = "url('resource/img/galaxy.jpg')"
                		break;
                	case 'default':
                		break;
}

					$.ajax({
							method: "POST",
							url: "themes",
							data: {
								colorTheme: colorTheme,
							}
						}).done(function() {	
							
							//carrega o css via get? maybe
							
						}).fail(function(xhr) {
							
							//
							
						});
}

