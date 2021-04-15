$('#tabela').bootstrapTable();
$('.fixed-table-toolbar').css('padding', '10px')

var searchBtn = $('.search-input');
searchBtn.css('border-radius', '0.5rem');
searchBtn.css('box-shadow', '0 0.5rem 1rem 0 rgba(0, 0, 0, 0.05)');

function buttons() {
    return {
        btnFilter: {
            text: 'Highlight Users',
            icon: 'fa-ellipsis-v',
            attributes: {
                title: 'Filtrar'
            }
        }
    }
}

function createDropdown() {
    const currentUrl = window.location.href;
    const urlContainsAll = currentUrl.includes("all");
    const listAllLink = urlContainsAll ? 'all-products?action=listar' : 'produtos?acao=listar';
    const listPurchasedLink = urlContainsAll ? 'all-products?action=comprados' : 'produtos?acao=comprados';
    const listNotPurchasedLink = urlContainsAll ? 'all-products?action=nao_comprados' : 'produtos?acao=nao_comprados';

    var drop = $('[name="btnFilter"]');
    const div = '<div class="dropdown" id="dropdown"></div>'
    $('.keep-open').after(div);
    var dropdown = $('#dropdown');
    dropdown.html(drop);
    drop.attr('data-toggle', 'dropdown');
    drop.after(`<div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-left" id="teste" style="
	border-radius: 1rem;
	box-shadow: 0 0.5rem 1rem 0 rgba(0, 0, 0, 0.1);">
	<a class="dropdown-item" type="button" onclick="listar('${listAllLink}')">Todos os Produtos</a>
	<a class="dropdown-item" type="button" onclick="listar('${listPurchasedLink}')">Produtos Comprados</a>
	<a class="dropdown-item" type="button" onclick="listar('${listNotPurchasedLink}')">Produtos Não Comprados</a>
</div>`)
}

function removeBorder() {
    $('[name="btnFilter"]').css('border-bottom-left-radius', '0')
    $('[name="btnFilter"]').css('border-top-left-radius', '0')
}


var onProductPage = !window.location.href.includes('categorias');
if (onProductPage) {
    $('[name="btnFilter"]').show();
    createDropdown();
    removeBorder();
} else {
    $('[name="btnFilter"]').hide();
}