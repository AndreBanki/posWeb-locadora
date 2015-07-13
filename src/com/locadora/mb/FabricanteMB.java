package com.locadora.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.locadora.dao.FabricanteDAO;
import com.locadora.entidade.Fabricante;

@ManagedBean
@ViewScoped
public class FabricanteMB {
	
	private Fabricante fabricanteEmEdicao;
	private List<Fabricante> fabricantes;
	private FabricanteDAO dao;
	
	@PostConstruct
	public void init() {
		dao = new FabricanteDAO();
		atualizaListaFabricantesParaExibicao();
	}
	
// métodos auxiliares
	
	public void atualizaListaFabricantesParaExibicao() {
		fabricantes = dao.listarTodos();
		fabricanteEmEdicao = new Fabricante();
	}

// métodos para acesso ao BD	
	
	public void apagarFabricante() {
		dao.apagar(fabricanteEmEdicao);
		atualizaListaFabricantesParaExibicao();
	}
	
	public void inserirFabricante() {
		if (fabricanteEmEdicao.getNome() != null) {
			if (fabricanteEmEdicao.getId() == 0) {
				dao.inserir(fabricanteEmEdicao);
			} else {
				dao.atualizar(fabricanteEmEdicao);
			}
			atualizaListaFabricantesParaExibicao();
		}
	}
	
// getters e setters	
	
	public List<Fabricante> getFabricantes(){		
		return fabricantes;
	}

	public Fabricante getFabricanteEmEdicao() {
		return fabricanteEmEdicao;
	}

	public void setFabricanteEmEdicao(Fabricante fabricante) {
		this.fabricanteEmEdicao = fabricante;
	}
}
