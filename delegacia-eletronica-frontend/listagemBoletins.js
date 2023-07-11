$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/delegacia-eletronica-api/boletins",
        type: 'GET',
        async: true,
        contentType: 'application/json',
        success: function(data) {
            let listagem = $('#listagem');
            let numRegistros = 0;
            data.slice(0, 100).forEach(function(item) {
                let novaLinha = $('<div>', {
                    class: 'table-row'
                });

                $('<p>').text(item.identificador).appendTo(novaLinha);
                $('<p>').text(item.dataOcorrencia).appendTo(novaLinha);
                $('<p>').text(item.periodoOcorrencia).appendTo(novaLinha);
                $('<p>').text(item.localOcorrencia.logradouro).appendTo(novaLinha);
                $('<p>').text(item.localOcorrencia.numero).appendTo(novaLinha);
                $('<p>').text(item.localOcorrencia.bairro).appendTo(novaLinha);
                $('<p>').text(item.localOcorrencia.cidade).appendTo(novaLinha);
                $('<p>').text(item.localOcorrencia.estado).appendTo(novaLinha);
                $('<p>').text(item.veiculoFurtado.emplacamento.placa).appendTo(novaLinha);
                $('<p>').text(item.veiculoFurtado.cor).appendTo(novaLinha);
                $('<p>').text(item.veiculoFurtado.marca).appendTo(novaLinha);
                $('<p>').text(item.veiculoFurtado.tipoVeiculo).appendTo(novaLinha);

                listagem.append(novaLinha);
                numRegistros++;
            });
        },
        error: function(xhr, status, error) {
            console.error('Erro na requisição: ' + status + ' - ' + error);
        }
    });

$(".filtrarBoletim").on('click', function(){
    if($(".form-select").val() == 'Escolha um filtro' || $("#filterValue").val() == ''){
        alert("Escolha um filtro!")
    }else{
        $.ajax({
            url: `http://localhost:8080/delegacia-eletronica-api/boletins?${$(".form-select").val()}=${$("#filterValue").val()}`,
            type: 'GET',
            async: true,
            contentType: 'application/json',
            success: function(data) {
                $('#listagem').html('');
                let listagem = $('#listagem');
                let numRegistros = 0;
                data.slice(0, 100).forEach(function(item) {
                    let novaLinha = $('<div>', {
                        class: 'table-row'
                    });
    
                    $('<p>').text(item.identificador).appendTo(novaLinha);
                    $('<p>').text(item.dataOcorrencia).appendTo(novaLinha);
                    $('<p>').text(item.periodoOcorrencia).appendTo(novaLinha);
                    $('<p>').text(item.localOcorrencia.logradouro).appendTo(novaLinha);
                    $('<p>').text(item.localOcorrencia.numero).appendTo(novaLinha);
                    $('<p>').text(item.localOcorrencia.bairro).appendTo(novaLinha);
                    $('<p>').text(item.localOcorrencia.cidade).appendTo(novaLinha);
                    $('<p>').text(item.localOcorrencia.estado).appendTo(novaLinha);
                    $('<p>').text(item.veiculoFurtado.emplacamento.placa).appendTo(novaLinha);
                    $('<p>').text(item.veiculoFurtado.cor).appendTo(novaLinha);
                    $('<p>').text(item.veiculoFurtado.marca).appendTo(novaLinha);
                    $('<p>').text(item.veiculoFurtado.tipoVeiculo).appendTo(novaLinha);
    
                    listagem.append(novaLinha);
                    numRegistros++;
                });
            },
            error: function(xhr, status, error) {
                console.error('Erro na requisição: ' + status + ' - ' + error);
            }
        });
    }
})


});
