package com.locadora.entidade;

import com.locadora.converter.PerfilUsuario;

public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private PerfilUsuario perfil;
	
	public Usuario() {
		this.id = 0;
		this.nome = "";
		this.email = "";
		this.senha = "";
		this.perfil = new PerfilUsuario();
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
}
