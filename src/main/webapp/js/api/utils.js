function limparMensagensErro() {
	/* Limpa as mensagens de erro */
	$("#global-message").removeClass("alert-danger alert-success").empty()
			.hide();
	$(".control-label").parent().removeClass("has-success");
	$(".text-danger").parent().removeClass("has-error");
	$(".text-danger").hide();
}

function obterParametroDaUrlPorNome(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}

function tratarErro(request) {
	switch (request.status) {
	case 404:
		$("#global-message").addClass("alert-danger").text(
				"O registro solicitado não foi encontrado!").show();
		break;
	case 406:
		$("form input").each(function() {
			var id = $(this).attr("id");
			var message = null;
			$.each(request.responseJSON, function(index, value) {
				if (id == value.propriedade) {
					message = value.mensagem;
				}
			});
			if (message) {
				$("#" + id).parent().addClass("has-error");
				$("#" + id + "-message").html(message).show();
				$(this).focus();
			} else {
				$("#" + id).parent().removeClass("has-error");
				$("#" + id + "-message").hide();
			}
		});
		$("#global-message").addClass("alert-danger").text(
				"Verifique erros no formulário!").show();
		break;
	default:
		$("#global-message").addClass("alert-danger").text("Erro inesperado.")
				.show();
		break;
	}
}
