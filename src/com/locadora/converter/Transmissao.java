package com.locadora.converter;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="transmissaoConverter")
public class Transmissao implements Converter {
	
	private int value;
	
	public Transmissao() {
		this.value = 0;
	}
	
	public Transmissao(int value) {
		this.value = value;
	}
	
	public Transmissao(String str) {
		if (str.equals("Automático"))
			this.value = 1;
		else if (str.equals("Manual"))
			this.value = 2;
		else if (str.equals("Tiptronic"))
			this.value = 3;
	}

	public String asString() {
		String s = new String();
		
		if (value == 1)
			s = "Automático";
		else if (value == 2)
			s = "Manual";
		else if (value == 3)
			s = "Tiptronic";

		return s;
		
	}
	
	private int primeiro() {
		return 1;
	}
	private int ultimo() {
		return 3;
	}
	
	public List<Transmissao> listaOpcoes() {
		List<Transmissao> lista = new ArrayList<Transmissao>();
		for (int i=primeiro(); i <= ultimo(); i++) {
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
