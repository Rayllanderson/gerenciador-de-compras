$(".alert").hide();

// quando editar em produtos, 
function saveAjax() {

    let nome = $('#categoriaName').val();
    let orcamento = $('#orcamento').val();
    let id = $('#idCat').val();
    let message = '';
    if (id == '') {
        message = "Sua lista foi criada com sucesso!"
    } else {
        message = "Sua lista foi editada com sucesso!"
    }


    if (nome.trim() != '') {

        $.ajax({
            method: "POST",
            url: "categorias?acao=editar",
            data: {
                nome: nome,
                orcamento: orcamento,
                id: id
            }
        }).done(function () {
            alertBoostrap(message, 'alert alert-success', "Sucesso")
            $('#categoriaModal').modal('hide')
            $.get("produtos?acao=listar", function () {
                atualizarValores();
                atualizarCategoria();
            });

        }).fail(function (xhr) {
            alertBoostrap("Erro " + xhr.status + ": " + xhr.responseText, 'alert alert-danger', "Erro")
            $('#categoriaModal').modal('hide')
        });

    } else {
        alertBoostrap("O campo Nome não pode ser nulo", 'alert alert-danger', "Erro")
        $('#categoriaModal').modal('hide')
    }
}

function atualizarCategoria() {
    $('#catTitulo').load('resource/jsp/categoriaNome.jsp').html;
}