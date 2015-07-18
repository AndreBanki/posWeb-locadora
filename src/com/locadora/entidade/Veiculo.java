package com.locadora.entidade;

public class Veiculo {

	private int id;
	private String nome;
	private String fabricante;
	private int transmissao;
	private int ano;
	
// lógica de negócio
	
	public String nomeTransmissao1() { return "Automático"; }
	public String nomeTransmissao2() { return "Manual"; }
	public String nomeTransmissao3() { return "Tiptronic"; }

	public String getNomeTransmissao() {
		if      (transmissao == 1) return nomeTransmissao1();
		else if (transmissao == 2) return nomeTransmissao2();
		else if (transmissao == 3) return nomeTransmissao3();
		else                       return "Valor inválido";
	}

// setters e getters
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	
	public int getTransmissao() {
		return transmissao;
	}
	
	public void setTransmissao(int transmissao) {
		this.transmissao = transmissao;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
