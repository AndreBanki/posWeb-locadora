package com.locadora.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="perfilConverter")
public class PerfilUsuario implements Converter {
	
	private int value;
	
	public static int ADMIN = 1;
	public static int USER = 2;
	public static int GUEST = 3;
	
	public static List<String> listaOpcoes = new ArrayList<String>(Arrays.asList(
		"Admin", 
		"User", 
		"Guest"
	));
	
	private int nOptions() {
		return listaOpcoes.size();
	}
	
	public PerfilUsuario() {
		this.value = 1;
	}
	
	public PerfilUsuario(int value) {
		this.value = value;
	}
	
	public PerfilUsuario(String str) {
		for (int i=1; i <= nOptions(); i++) {
			if (listaOpcoes.get(i-1).equals(str))
				this.value = i;
		}
	}

	public String asString() {
		String s = new String();
		if (value <= nOptions())
			s = listaOpcoes.get(value-1);
		return s;
	}
	
	public List<PerfilUsuario> listaOpcoes() {
		List<PerfilUsuario> lista = new ArrayList<PerfilUsuario>();
		for (int i=1; i <= nOptions(); i++) {
			lista.add(new PerfilUsuario(i));
		}
		return lista;
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		PerfilUsuario t = new PerfilUsuario(arg2);
		return t;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		PerfilUsuario t = (PerfilUsuario)arg2;
		return t.asString();
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilUsuario other = (PerfilUsuario) obj;
		if (value != other.value)
			return false;
		return true;
	}
}
