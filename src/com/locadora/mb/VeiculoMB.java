package com.locadora.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.locadora.dao.VeiculoDAO;
import com.locadora.entidade.Veiculo;

@ManagedBean
@ViewScoped
public class VeiculoMB {
	
	private Veiculo veiculoEmEdicao;
	private List<Veiculo> veiculos;
	private VeiculoDAO dao;
	
	@PostConstruct
	public void init() {
		dao = new VeiculoDAO();
		atualizaListaVeiculosParaExibicao();
	}
	
// métodos auxiliares
	
	private void atualizaListaVeiculosParaExibicao() {
		veiculos = dao.listarTodos();
		veiculoEmEdicao = new Veiculo();
	}

// métodos para acesso ao BD	
	
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
