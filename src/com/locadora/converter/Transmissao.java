package com.locadora.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="transmissaoConverter")
public class Transmissao implements Converter {
	
	private int value;
	
	public static List<String> listaTransmissoes = new ArrayList<String>(Arrays.asList(
		"Automático", 
		"Manual", 
		"Tiptronic"
	));
	
	private int nTransmissoes() {
		return listaTransmissoes.size();
	}
	
	public Transmissao() {
		this.value = 1;
	}
	
	public Transmissao(int value) {
		this.value = value;
	}
	
	public Transmissao(String str) {
		for (int i=1; i <= nTransmissoes(); i++) {
			if (listaTransmissoes.get(i-1).equals(str))
				this.value = i;
		}
	}

	public String asString() {
		String s = new String();
		if (value <= nTransmissoes())
			s = listaTransmissoes.get(value-1);
		return s;
	}
	
	public List<Transmissao> listaOpcoes() {
		List<Transmissao> lista = new ArrayList<Transmissao>();
		for (int i=1; i <= nTransmissoes(); i++) {
			lista.add(new Transmissao(i));
		}
		return lista;
	}
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Transmissao t = new Transmissao(arg2);
		return t;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Transmissao t = (Transmissao)arg2;
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
		Transmissao other = (Transmissao) obj;
		if (value != other.value)
			return false;
		return true;
	}
	
	

}
