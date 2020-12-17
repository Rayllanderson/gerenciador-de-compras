

function swapColor(colorTheme, animation, pagina) {

	$.ajax({
		method: "POST",
		url: "themes",
		data: {
			colorTheme: colorTheme,
		}
	}).done(function() {
		if (pagina == 'categoria')
			categoriaThemeSwitch(colorTheme, animation)
		 else
			produtosThemeSwitch(colorTheme, animation)

	});
}

