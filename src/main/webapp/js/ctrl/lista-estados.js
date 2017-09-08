$(function() {
	EstadoProxy.selecionarTodos().done(buscarOk);
});
function buscarOk(estados) {
	var corpo = $('#estados tbody');
	corpo.empty();
	if (estados.length) {
		$.each(estados, function(i, estado) {
			corpo.append($('<tr>')
					.append(
							$('<td>').text(estado.id),
							$('<td>').text(estado.nome),
							$('<td>').text(estado.sigla),
							$('<td>').append(
									$('<a>').attr(
											'href',
											'editar-estado.html?id='
													+ estado.id).text("Editar"))));
		});
	} else {
		corpo.append($('<tr>').append(
				$('<td>').attr('colspan', 4)
						.text('Nenhum registro encontrado!')));
	}
}