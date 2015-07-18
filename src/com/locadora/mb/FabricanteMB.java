package com.locadora.mb;

import java.util.Iterator;
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
	
	private Fabricante fabricanteMesmoNome(Fabricante fabricante) {
		Fabricante fabIgual = new Fabricante();
		for (Iterator<Fabricante> iterator = fabricantes.iterator(); iterator.hasNext() && fabIgual.getId() == 0; ) {    
			Fabricante f = (Fabricante) iterator.next();    
			if (f.getNome().equals(fabricante.getNome()))
				fabIgual = f;
		}
		return fabIgual;
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
		Fabricante fabricanteMesmoNome = fabricanteMesmoNome(fabricanteEmEdicao);
		if (fabricanteMesmoNome.getId() != 0) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteNome", true);
		}
		else {
			if (fabricanteEmEdicao.getId() == 0)
				dao.inserir(fabricanteEmEdicao);
			else 
				dao.atualizar(fabricanteEmEdicao);
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
