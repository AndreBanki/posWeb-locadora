package com.locadora.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.locadora.dao.FabricanteDAO;
import com.locadora.dao.VeiculoDAO;
import com.locadora.entidade.Fabricante;
import com.locadora.entidade.Veiculo;

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
		limpaFabricanteEmEdicao();
	}
	
	public void limpaFabricanteEmEdicao() {
		fabricanteEmEdicao = new Fabricante();
	}
	
	private boolean podeApagarFabricanteEmEdicao() {
		VeiculoDAO veiculoDao = new VeiculoDAO();
		List<Veiculo> veiculosDoFabricante = veiculoDao.listaPorFabricante(fabricanteEmEdicao.getId());
		return veiculosDoFabricante.isEmpty();
	}

// métodos para acesso ao BD	
	
	public void apagarFabricante() {
		if (podeApagarFabricanteEmEdicao()) {
			dao.apagar(fabricanteEmEdicao);
			atualizaListaFabricantesParaExibicao();
		}
		else {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("alert('Não é possível excluir este fabricante')");
			limpaFabricanteEmEdicao();
		}
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
