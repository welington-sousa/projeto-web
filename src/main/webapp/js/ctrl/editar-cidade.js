$(function() {
	var estado = obterParametroDaUrlPorNome("estado");
	if(!estado){
		window.location.href = "lista-estados.html";
	}
	
	var id = obterParametroDaUrlPorNome('id');
	if (id) {
		CidadeProxy.selecionar(obterParametroDaUrlPorNome("id")).done(
				populaCidade);
	}

	$("#salvar").click(
			function(event) {
				limparMensagensErro();

				var cidade = {
					id : $("#id").val(),
					nome : $("#nome").val(),
					dataConstituicao: $("#dataConstituicao").val(),
					populacao : $("#populacao").val(),
					pib: $("#pib").val()
				};

				if (cidade.id) {
					CidadeProxy.atualizar(cidade.id, cidade).done(
							atualizarOk).fail(tratarErro);
				} else {
					CidadeProxy.inserir(estado, cidade).done(inserirOk).fail(
							tratarErro);
				}

			});

	$("#excluir").click(function(event) {
		var id = $("#id").val();
		CidadeProxy.excluir(id).done(excluirOk).fail(tratarErro);
	});
	
	$("#voltar").click(function(){
		window.location.href = "editar-estado.html?id="+obterParametroDaUrlPorNome("estado");
	});
});

function populaCidade(cidade) {
	$("#id").val(cidade.id);
	$("#nome").val(cidade.nome);
	$("#dataConstituicao").val(cidade.dataConstituicao);
	$("#populacao").val(cidade.populacao);
	$("#pib").val(cidade.pib);
}

function inserirOk(data, textStatus, jqXHR) {
	$("#id").val(data);
	$("#global-message").addClass("alert-success").text(
			"Cidade com id = " + data + " criado com sucesso.").show();
}

function atualizarOk(data, textStatus, jqXHR) {
	$("#global-message").addClass("alert-success").text(
			"Cidade atualizado com sucesso.").show();
}

function excluirOk(data, textStatus, jqXHR) {
	$("#formCidade").each(function() {
		this.reset();
	});

	$("#global-message").addClass("alert-success").text(
			"Cidade excluído com sucesso.").show();
}