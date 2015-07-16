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
	
	private List<Veiculo> veiculosDoFabricanteEmEdicao() {
		VeiculoDAO veiculoDao = new VeiculoDAO();
		List<Veiculo> veiculosDoFabricante = veiculoDao.listaPorFabricante(fabricanteEmEdicao.getId());
		return veiculosDoFabricante;
	}

// métodos para acesso ao BD	
	
	public void apagarFabricante() {
		List<Veiculo> veiculosDoFabricante = veiculosDoFabricanteEmEdicao();
		if (veiculosDoFabricante.isEmpty()) {
			dao.apagar(fabricanteEmEdicao);
			atualizaListaFabricantesParaExibicao();
		}
		else {
			RequestContext context = RequestContext.getCurrentInstance();
			String JScommand = "alert('Este fabricante possui " + veiculosDoFabricante.size() + " veículo(s) cadastrado(s). Não é possível excluí-lo.');";
			context.execute(JScommand);
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
