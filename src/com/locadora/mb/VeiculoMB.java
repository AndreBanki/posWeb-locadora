package com.locadora.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.locadora.dao.VeiculoDAO;
import com.locadora.entidade.Veiculo;

@ManagedBean
@ViewScoped
public class VeiculoMB {
	
	private Veiculo veiculo;
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
		veiculo = new Veiculo();
	}

// métodos para acesso ao BD	
	
	public void apagaVeiculo() {
		dao.apagar(veiculo);
		atualizaListaVeiculosParaExibicao();
	}
	
	public void insereVeiculo() {
		if (veiculo.getId() == 0) {
			dao.inserir(veiculo);
		} else {
			dao.atualizar(veiculo);
		}
		atualizaListaVeiculosParaExibicao();
	}
	
// getters e setters	
	
	public List<Veiculo> getVeiculos(){		
		return veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}
