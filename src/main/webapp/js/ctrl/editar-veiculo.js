$(function() {
	var id = obterParametroDaUrlPorNome('id');
	if (id) {
		VeiculoProxy.selecionar(obterParametroDaUrlPorNome("id")).done(
				populaVeiculo);
	}

	$("#salvar").click(
			function(event) {
				limparMensagensErro();

				var veiculo = {
					id : $("#id").val(),
					placa : $("#placa").val(),
					nomeProprietario : $("#nomeProprietario").val(),
					dataEmplacamento : $("#dataEmplacamento").val(),
					valorIPVA : $("#valorIPVA").val()
				};

				if (veiculo.id) {
					VeiculoProxy.atualizar(veiculo.id, veiculo).done(
							atualizarOk).fail(tratarErro);
				} else {
					VeiculoProxy.inserir(veiculo).done(inserirOk).fail(
							tratarErro);
				}

			});

	$("#excluir").click(function(event) {
		var id = $("#id").val();
		VeiculoProxy.excluir(id).done(excluirOk).fail(tratarErro);
	});

});

function populaVeiculo(veiculo) {
	$("#id").val(veiculo.id);
	$("#placa").val(veiculo.placa);
	$("#nomeProprietario").val(veiculo.nomeProprietario);
	$("#dataEmplacamento").val(veiculo.dataEmplacamento);
	$("#valorIPVA").val(veiculo.valorIPVA);
}

function inserirOk(data, textStatus, jqXHR) {
	$("#id").val(data);
	$("#global-message").addClass("alert-success").text(
			"Veículo com id = " + data + " criado com sucesso.").show();
}

function atualizarOk(data, textStatus, jqXHR) {
	$("#global-message").addClass("alert-success").text(
			"Veículo atualizado com sucesso.").show();
}

function excluirOk(data, textStatus, jqXHR) {
	$("#formVeiculo").each(function() {
		this.reset();
	});

	$("#global-message").addClass("alert-success").text(
			"Veículo excluído com sucesso.").show();
}

