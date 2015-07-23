package com.locadora.mb;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.locadora.dao.UsuarioDAO;
import com.locadora.entidade.Usuario;

@ManagedBean
@ViewScoped
public class PerfilMB {

	private Usuario usuarioEmEdicao;
	private UsuarioDAO dao;
	
	public PerfilMB(){
		dao = new UsuarioDAO();
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		String email = (String)session.getAttribute("emailUsuario");
		usuarioEmEdicao = dao.buscaPorEmail(email);
	}
	
	public void atualizarUsuario() {
		dao.atualizar(usuarioEmEdicao);
		
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Cadastro alterado com sucesso"));
	}

	public Usuario getUsuarioEmEdicao() {
		return usuarioEmEdicao;
	}

	public void setUsuarioEmEdicao(Usuario usuarioEmEdicao) {
		this.usuarioEmEdicao = usuarioEmEdicao;
	}

}
