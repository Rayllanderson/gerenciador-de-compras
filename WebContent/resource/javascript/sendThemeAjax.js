

 function swapColor(colorTheme){

					$.ajax({
							method: "POST",
							url: "themes",
							data: {
								colorTheme: colorTheme,
							}
						}).done(function() {	
								//preciso dar um set em theme novamente e dar um get la no body
								console.log('hey.. ajax')	
								themeSwitch(colorTheme)	
						}).fail(function(xhr) {
							
							//
							
						});
}

