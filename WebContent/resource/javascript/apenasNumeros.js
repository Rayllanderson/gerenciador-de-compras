function setInputFilter(textbox, inputFilter) {
	["input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop"].forEach(function(event) {
		textbox.addEventListener(event, function() {
			if (inputFilter(this.value)) {
				this.oldValue = this.value;
				this.oldSelectionStart = this.selectionStart;
				this.oldSelectionEnd = this.selectionEnd;
			} else if (this.hasOwnProperty("oldValue")) {
				this.value = this.oldValue;
				this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
			} else {
				this.value = "";
			}
		});
	});
}

const estipulado = document.getElementById("estipulado");
const real = document.getElementById("real");
const orcamento = document.getElementById("orcamento");


if (orcamento != null && orcamento != '') {
	setInputFilter(orcamento, function(value) {
		return /^\d*\.?\d*$/.test(value); // Allow digits and '.' only, using a RegExp
	});
} else {
	setInputFilter(estipulado, function(value) {
		return /^\d*\.?\d*$/.test(value); // Allow digits and '.' only, using a RegExp
	});

	setInputFilter(real, function(value) {
		return /^\d*\.?\d*$/.test(value); // Allow digits and '.' only, using a RegExp
	});
}

