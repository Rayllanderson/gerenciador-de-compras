

 function swapColor(colorTheme, animation){

					$.ajax({
							method: "POST",
							url: "themes",
							data: {
								colorTheme: colorTheme,
							}
						}).done(function() {	
								//preciso dar um set em theme novamente e dar um get la no body
								categoriaThemeSwitch(colorTheme, animation)	
						}).fail(function(xhr) {
							
							//
							
						});
}

