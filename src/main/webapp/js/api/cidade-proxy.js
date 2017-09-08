var CidadeProxy = {
	url : "api/cidade",
	inserir : function(estado, cidade) {
		return $.ajax({
			type : "POST",
			url : this.url + "/" + estado,
			data : JSON.stringify(cidade),
			contentType : "application/json"
		});
	},
	atualizar : function(id, cidade) {
		return $.ajax({
			type : "PUT",
			url : this.url + "/" + id,
			data : JSON.stringify(cidade),
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