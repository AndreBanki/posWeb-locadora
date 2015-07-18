package com.locadora.entidade;

import com.locadora.converter.Transmissao;

public class Veiculo {

	private int id;
	private String nome;
	private String fabricante;
	private Transmissao transmissao;
	private int ano;
	
	public Veiculo() {
		this.id = 0;
		this.nome = "";
		this.fabricante = "";
		this.transmissao = new Transmissao();
		this.ano = 2015;
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
	
	public Transmissao getTransmissao() {
		return transmissao;
	}
	
	public void setTransmissao(Transmissao transmissao) {
		this.transmissao = transmissao;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
