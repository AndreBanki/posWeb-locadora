package com.locadora.mb;

import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import com.locadora.dao.UsuarioDAO;
import com.locadora.entidade.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB {

	private List<Usuario> usuarios;
	private Usuario usuarioEmEdicao;
	private UsuarioDAO dao;
	
	public UsuarioMB(){
		dao = new UsuarioDAO();
		atualizaListaUsuariosParaExibicao();
	}
	
// métodos auxiliares	
	
	public void atualizaListaUsuariosParaExibicao() {
		usuarios = dao.lista();
		limpaUsuarioEmEdicao();
	}
	
	private void limpaUsuarioEmEdicao(){
		usuarioEmEdicao = new Usuario();
	}
	
	private Usuario usuarioMesmoEmail(Usuario usuario) {
		Usuario usuIgual = new Usuario();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext() && usuIgual.getId() == 0; ) {    
			Usuario u = (Usuario) iterator.next();    
			if (u.getNome().equals(usuario.getNome()))
				usuIgual = u;
		}
		return usuIgual;
	}		

// métodos CRUD	
	
	public void inserirUsuario() {
		Usuario usuarioMesmoEmail = usuarioMesmoEmail(usuarioEmEdicao);
		if (usuarioMesmoEmail.getId() != usuarioEmEdicao.getId()) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.addCallbackParam("jaExisteEmail", true);
		}
		else {
			if (usuarioEmEdicao.getId() == 0)
				dao.inserir(usuarioEmEdicao);
			else 
				dao.atualizar(usuarioEmEdicao);
			atualizaListaUsuariosParaExibicao();
		}
	}
	
	public void apagarUsuario(){
		dao.deletar(usuarioEmEdicao);
		atualizaListaUsuariosParaExibicao();		
	}
	
// getters e setters	
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuarioEmEdicao() {
		return usuarioEmEdicao;
	}
	public void setUsuarioEmEdicao(Usuario usuario) {
		this.usuarioEmEdicao = usuario;
	}
	
	
}
