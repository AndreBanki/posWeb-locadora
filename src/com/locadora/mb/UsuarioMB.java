package com.locadora.mb;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.locadora.dao.UsuarioDAO;
import com.locadora.entidade.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB {

	private List<Usuario> usuarios;
	private Usuario usuario;
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	public UsuarioMB(){
		usuarios = new ArrayList<Usuario>();
		limpaUsuario();
	}
	
	public void salvar() throws NoSuchAlgorithmException{

		usuarioDAO.inserir(usuario);
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
		
		
		limpaUsuario();		
	}
	
	public void excluir(){
		usuarioDAO.deletar(usuario);
		limpaUsuario();		
	}
	
	private void limpaUsuario(){
		usuario = new Usuario();
		usuarios = usuarioDAO.lista();
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
