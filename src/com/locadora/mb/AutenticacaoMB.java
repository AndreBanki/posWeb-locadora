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
		//Retorna o contexto da aplicação
		FacesContext context = FacesContext.getCurrentInstance();
		if(!email.trim().equals("") && !senha.trim().equals("")){
			//Instância a classe UsuarioDAO para realizar consultas
			UsuarioDAO dao = new UsuarioDAO();
			//Chama o método buscaPorEmail(String email)
			//para retornar o objeto usuario
			Usuario u = dao.buscaPorEmail(email);
			
			//Verifica se buscou um usuário e se a senha é
			//igual ao digitado na tela index
			if(u.getId() != 0 && this.senha.equals(u.getSenha())){
				//Retorna a Sessão através do contexto da aplicação
				HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
				//Inclui o ID do usuário na sessão
				session.setAttribute("idUsuario", u.getId());
				session.setAttribute("emailUsuario", u.getEmail());
				
				//Direciona para página de veículo
				return "/pages/veiculo.jsf";
			}
		}
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				"E-mail ou senha inválidos!", ""));
		return "";
	}
	
	public String logout(){
		//Retorna o contexto da aplicação
		FacesContext context = FacesContext.getCurrentInstance();
		//Retorna a Sessão através do contexto da aplicação
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		//Invalida a sessão do usuário
		session.invalidate();

		//Retorna para página de index através da navegação
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
