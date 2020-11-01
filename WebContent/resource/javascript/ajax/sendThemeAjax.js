

 function swapColor(colorTheme, animation, pagina){

					$.ajax({
							method: "POST",
							url: "themes",
							data: {
								colorTheme: colorTheme,
							}
						}).done(function() {	
								//preciso dar um set em theme novamente e dar um get la no body
								if(pagina == 'categoria'){
									categoriaThemeSwitch(colorTheme, animation)	
								}else{
									produtosThemeSwitch(colorTheme, animation)	
								}
						}).fail(function(xhr) {
							
							//
							
						});
}

