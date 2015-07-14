package com.locadora.mb;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
		// lista de todos os fabricantes (n�o muda durante a edi��o da p�gina)
		fabricanteDao = new FabricanteDAO();
		fabricantes = fabricanteDao.listarTodos();
	}
	
// m�todos auxiliares
	
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

// m�todos para acesso ao BD	
	
	public void apagarVeiculo() {
		dao.apagar(veiculoEmEdicao);
		atualizaListaVeiculosParaExibicao();
	}
	
	public void inserirVeiculo() {
		if (veiculoEmEdicao.getNome() != null) {
			if (veiculoEmEdicao.getId() == 0) {
				dao.inserir(veiculoEmEdicao);
			} else {
				dao.atualizar(veiculoEmEdicao);
			}
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
