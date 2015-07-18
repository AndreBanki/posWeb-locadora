package com.locadora.mb;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.locadora.dao.UsuarioDAO;
import com.locadora.entidade.Usuario;

@ManagedBean
public class AutenticacaoMB {

	private String email, senha;
	
	public String autentica() throws NoSuchAlgorithmException{
		//Retorna o contexto da aplica��o
		FacesContext context = FacesContext.getCurrentInstance();
		if(!email.trim().equals("") && !senha.trim().equals("")){
			//Inst�ncia a classe UsuarioDAO para realizar consultas
			UsuarioDAO dao = new UsuarioDAO();
			//Chama o m�todo buscaPorEmail(String email)
			//para retornar o objeto usuario
			Usuario u = dao.buscaPorEmail(email);
			
			//Verifica se buscou um usu�rio e se a senha �
			//igual ao digitado na tela index
			if(u.getId() != 0 && this.senha.equals(u.getSenha())){
				//Retorna a Sess�o atrav�s do contexto da aplica��o
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				//Inclui o ID do usu�rio na sess�o
				session.setAttribute("idUsuario", u.getId());
				session.setAttribute("emailUsuario", u.getEmail());
				
				//Direciona para p�gina de ve�culo
				return "/pages/veiculo.jsf";
			}
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				"E-mail ou senha inv�lidos!", ""));
		return "";
	}
	
	public String logout(){
		//Retorna o contexto da aplica��o
		FacesContext context = FacesContext.getCurrentInstance();
		//Retorna a Sess�o atrav�s do contexto da aplica��o
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		//Invalida a sess�o do usu�rio
		session.invalidate();

		//Retorna para p�gina de index atrav�s da navega��o
		//configurada no faces-config.xml
		return "logout";
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
	
	
}
