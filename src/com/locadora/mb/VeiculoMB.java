package com.locadora.mb;

import java.util.ArrayList;
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
public class VeiculoMB {
	
	private Veiculo veiculoEmEdicao;
	private List<Veiculo> veiculos;
	private VeiculoDAO dao;
	
	private List<Fabricante> fabricantes;
	private FabricanteDAO fabricanteDao;
	
	@PostConstruct
	public void init() {
		dao = new VeiculoDAO();
		atualizaListaVeiculosParaExibicao();
		// lista de todos os fabricantes (não muda durante a edição da página)
		fabricanteDao = new FabricanteDAO();
		fabricantes = fabricanteDao.listarTodos();
	}
	
// métodos auxiliares
	
	public void atualizaListaVeiculosParaExibicao() {
		veiculos = dao.listarTodos();
		veiculoEmEdicao = new Veiculo();
	}
	
	public List<String> listaNomesFabricantes() {
		List<String> nomes = new ArrayList<String>();
		for (Iterator<Fabricante> iterator = fabricantes.iterator(); iterator.hasNext(); ) {    
			Fabricante f = (Fabricante) iterator.next();    
			nomes.add(f.getNome());    
		}  
		return nomes;
	}
	
	private Veiculo veiculoMesmoNome(Veiculo veiculo) {
		Veiculo veiculoIgual = new Veiculo();
		for (Iterator<Veiculo> iterator = veiculos.iterator(); iterator.hasNext() && veiculoIgual.getId() == 0; ) {    
			Veiculo v = (Veiculo) iterator.next();    
			if (v.getNome().equals(veiculo.getNome()))
				veiculoIgual = v;
		}
		return veiculoIgual;
	}	

// métodos para acesso ao BD	
	
	public void apagarVeiculo() {
		dao.apagar(veiculoEmEdicao);
		atualizaListaVeiculosParaExibicao();
	}
	
	public void inserirVeiculo() {
		Veiculo veiculoMesmoNome = veiculoMesmoNome(veiculoEmEdicao);
		if (veiculoMesmoNome.getId() != 0) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteNome", true);
		}
		else {
			if (veiculoEmEdicao.getId() == 0) 
				dao.inserir(veiculoEmEdicao);
			else 
				dao.atualizar(veiculoEmEdicao);
			atualizaListaVeiculosParaExibicao();
		}
	}
	
// getters e setters	
	
	public List<Veiculo> getVeiculos(){		
		return veiculos;
	}

	public Veiculo getVeiculoEmEdicao() {
		return veiculoEmEdicao;
	}

	public void setVeiculoEmEdicao(Veiculo veiculo) {
		this.veiculoEmEdicao = veiculo;
	}

}
