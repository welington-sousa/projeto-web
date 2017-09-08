package br.com.ws.projetoweb.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import br.com.ws.projetoweb.model.BaseModel;

@ApplicationScoped
public class Repositorio {

	private final Map<Class<?>, List<BaseModel>> dados = new Hashtable<Class<?>, List<BaseModel>>();

	public <T extends BaseModel> Long insere(T entidade) {
		synchronized (this) {
			List<BaseModel> lista = getLista(entidade.getClass());
			Long id = proximoId(lista);
			entidade.setId(id);
			lista.add(entidade);
			return id;
		}
	}

	public <T extends BaseModel> boolean atualiza(T entidade) {
		synchronized (this) {
			List<BaseModel> lista = getLista(entidade.getClass());
			int pos = lista.indexOf(entidade);
			if (pos >= 0) {
				lista.set(pos, entidade);
				return true;
			}
			return false;
		}
	}

	public <T extends BaseModel> T exclui(Class<T> clazz, Long id) {
		synchronized (this) {
			List<BaseModel> lista = getLista(clazz);
			T entidade = selecionar(clazz, id);
			if (lista.remove(entidade)) {
				return entidade;
			}
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel> List<T> seleciona(Class<T> clazz) {
		synchronized (this) {
			List<BaseModel> lista = getLista(clazz);
			return (List<T>) Collections.unmodifiableList(lista);
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseModel> T selecionar(Class<T> clazz, Long id) {
		synchronized (this) {
			List<BaseModel> lista = getLista(clazz);
			for (BaseModel model : lista) {
				if (model.getId().equals(id)) {
					return (T) model;
				}
			}
			return null;
		}
	}

	private List<BaseModel> getLista(Class<? extends BaseModel> clazz) {
		List<BaseModel> lista = dados.get(clazz);
		if (lista == null) {
			lista = new ArrayList<BaseModel>();
			dados.put(clazz, lista);
		}
		return lista;
	}

	private Long proximoId(List<BaseModel> lista) {
		Long proximo = 1L;
		for (BaseModel dado : lista) {
			if (dado.getId().compareTo(proximo) >= 0) {
				proximo = dado.getId() + 1;
			}
		}
		return proximo;
	}
}
