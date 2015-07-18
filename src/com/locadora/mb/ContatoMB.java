package com.locadora.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ContatoMB {

	private String to;
	private String subject;
	private String content;
	
	public String enviar() {
		SimpleEmail email = new SimpleEmail();
		email.setSSLOnConnect(true);
		email.setHostName("smtp.gmail.com");
		email.setSslSmtpPort("465");
		email.setSSLOnConnect(true);
		email.setAuthenticator(new DefaultAuthenticator("andre.banki@gmail.com", "SENHA_AQUI"));
		
		RequestContext context = RequestContext.getCurrentInstance();
		
		try{
			email.setDebug(true);
			email.setFrom(getFrom());

			email.addTo(to);
			email.setSubject(subject);
			email.setMsg(content);
			
			email.send();
			
			context.execute("alert('E-mail enviado com sucesso');");
		} catch (EmailException e) {
			e.printStackTrace();
			context.execute("alert('Problemas no envio do e-mail');");
		}
		
		return "/pages/veiculo.jsf";
	}
	
	public String getFrom() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		return (String)session.getAttribute("emailUsuario");
	}

	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
