package com.locadora.mb;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIInput;

import com.locadora.dao.VeiculoDAO;
import com.locadora.entidade.Veiculo;

@ManagedBean
@ViewScoped
public class VeiculoMB {
	
	//Declara o atributo veículo
	private Veiculo veiculo;
	//Declara e cria a lista de veiculos
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	//Declara e cria DAO para acesso ao bando de dados
	private VeiculoDAO dao = new VeiculoDAO();
	
	public VeiculoMB(){
		//Chame método que instancia o objeto
		limpaVeiculo();

		//Busca os registros do BD através do veiculoDAO
		veiculos = dao.lista();
		
		limpaVeiculo();
	}
	
	public void deletar() {
		dao.deletar(veiculo);
		veiculos = dao.lista();
	}
	
	private void limpaVeiculo(){
		//Instancia o objeto veiculo
		veiculo= new Veiculo();
	}
	
	public void insereVeiculo(){
		if(veiculo.getId() == 0){
			dao.inserir(veiculo);
		}else{
			dao.atualizar(veiculo);
		}
		veiculos = dao.lista();
		//Chama o método para limpar o veículo;
		limpaVeiculo();
	}
	
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
