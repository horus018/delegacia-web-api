$("#cadastrar").on('click', function () {
    cadastrar("http://localhost:8080/delegacia-eletronica-api/boletins");
});

let cadastrar = function (url) {
    let bo = {
        identificador: $("#identificador").val(),
        dataOcorrencia: $("#data").val(),
        periodoOcorrencia: $("#periodo").val(),
        parte: {
            nome: $("#nome").val(),
            email: $("#email").val(),
            telefone: $("#telefone").val(),
        },
        localOcorrencia: {
            logradouro: $("#logradouro").val(),
            numero: $("#numero").val(),
            bairro: $("#bairro").val(),
            cidade: $("#cidade").val(),
            estado: $("#estado").val()
        },
        veiculoFurtado: {
            emplacamento: {
                placa: $("#placa").val(),
                estado: $("#estadoVeiculo").val(),
                cidade: $("#cidadeVeiculo").val() 
            },
            anoFabricacao: $("#ano").val(),
            cor: $("#cor").val(),
            marca: $("#marca").val(),
            tipoVeiculo: $("#tipo").val()
        }
    };

    $.ajax({
        url: url,
        type: 'POST',
        async: true,
        contentType: 'application/json',
        data: JSON.stringify(bo),
        success: function (boCadastrado) {
            //nao ta caindo aq quando consegue cadastrar, 
            //algum problema na api com status é 200
        },
        error: function (xhr, status, error) {
            if(xhr.responseText == "Boletim cadastrado"){
                alert("Boletim cadastrado")
            }
            console.log(xhr.responseText);
        }
    });
};