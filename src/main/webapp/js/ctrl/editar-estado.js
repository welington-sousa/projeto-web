$(function() {
	var id = obterParametroDaUrlPorNome('id');
	if (id) {
		EstadoProxy.selecionar(obterParametroDaUrlPorNome("id")).done(
				populaEstado);
	}

	$("#salvar").click(
			function(event) {
				limparMensagensErro();

				var estado = {
					id : $("#id").val(),
					nome : $("#nome").val(),
					sigla : $("#sigla").val()
				};

				if (estado.id) {
					EstadoProxy.atualizar(estado.id, estado).done(
							atualizarOk).fail(tratarErro);
				} else {
					EstadoProxy.inserir(estado).done(inserirOk).fail(
							tratarErro);
				}

			});

	$("#excluir").click(function(event) {
		var id = $("#id").val();
		EstadoProxy.excluir(id).done(excluirOk).fail(tratarErro);
	});

	$("#novaCidade").click(function(){
		window.location.href = "editar-cidade.html?estado="+$("#id").val();
	});
});

function populaEstado(estado) {
	$("#id").val(estado.id);
	$("#nome").val(estado.nome);
	$("#sigla").val(estado.sigla);
	$(estado.cidades).each(function(i, cidade){
		$("#cidades tbody").append(
				$("<tr>").append(
						$("<td>").text(cidade.id)).append(
						$("<td>").text(cidade.nome)).append(
						$("<td>").text(cidade.dataConstituicao)).append(
						$("<td>").text(cidade.populacao)).append(
						$("<td>").text(cidade.pib)).append(
						$("<td>").append(
								$('<a>').attr(
										'href',
										'editar-cidade.html?id='
												+ cidade.id + '?estado='+estado.id).text("Editar"))));
	});
}

function inserirOk(data, textStatus, jqXHR) {
	$("#id").val(data);
	$("#global-message").addClass("alert-success").text(
			"Estado com id = " + data + " criado com sucesso.").show();
}

function atualizarOk(data, textStatus, jqXHR) {
	$("#global-message").addClass("alert-success").text(
			"Estado atualizado com sucesso.").show();
}

function excluirOk(data, textStatus, jqXHR) {
	$("#formEstado").each(function() {
		this.reset();
	});

	$("#global-message").addClass("alert-success").text(
			"Estado excluído com sucesso.").show();
}