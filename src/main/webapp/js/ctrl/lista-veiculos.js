$(function() {
	VeiculoProxy.selecionarTodos().done(buscarOk);
});
function buscarOk(veiculos) {
	var corpo = $('#veiculos tbody');
	corpo.empty();
	if (veiculos.length) {
		$.each(veiculos, function(i, veiculo) {
			corpo.append($('<tr>')
					.append(
							$('<td>').append(
									$('<a>').attr(
											'href',
											'editar-veiculo.html?id='
													+ veiculo.id).text(
															veiculo.placa)),
							$('<td>').text(veiculo.nomeProprietario),
							$('<td>').text(veiculo.dataEmplacamento),
							$('<td>').text(veiculo.valorIPVA)));
		});
	} else {
		corpo.append($('<tr>').append(
				$('<td>').attr('colspan', 4)
						.text('Nenhum registro encontrado!')));
	}
}