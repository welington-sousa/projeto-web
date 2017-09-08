var VeiculoProxy = {
	url : "../api/veiculo",
	inserir : function(veiculo) {
		return $.ajax({
			type : "POST",
			url : this.url,
			data : JSON.stringify(veiculo),
			contentType : "application/json"
		});
	},
	atualizar : function(id, veiculo) {
		return $.ajax({
			type : "PUT",
			url : this.url + "/" + id,
			data : JSON.stringify(veiculo),
			contentType : "application/json"
		});
	},
	excluir : function(id) {
		return $.ajax({
			type : "DELETE",
			url : this.url + "/" + id
		});
	},
	selecionar : function(id) {
		return $.ajax({
			type : "GET",
			url : this.url + "/" + id
		});
	},
	selecionarTodos : function() {
		return $.ajax({
			type : "GET",
			url : this.url
		});
	}
};