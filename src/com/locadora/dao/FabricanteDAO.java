package com.locadora.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.locadora.entidade.Fabricante;

public class FabricanteDAO extends BaseDAO {

// métodos auxiliares
	
	private void setDadosFabricanteFromResultSet(Fabricante fabricante, ResultSet rs) {
		try {
			fabricante.setId(rs.getInt("id"));
			fabricante.setNome(rs.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// métodos de busca
	
	public Fabricante buscaPorNome(String nome) {
		conectar();
		Fabricante fabricante = new Fabricante();
		try {
			ResultSet rs = comando.executeQuery("select * from fabricante where nome = '" + nome + "'");
			while (rs.next()) {
				setDadosFabricanteFromResultSet(fabricante, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return fabricante;
	}
	
	public Fabricante buscaId(int id) {
		conectar();
		Fabricante fabricante = new Fabricante();
		try {
			ResultSet rs = comando.executeQuery("select * from fabricante where id= "+id);
			while (rs.next()) {
				setDadosFabricanteFromResultSet(fabricante, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return fabricante;
	}
		
// métodos CRUD	
	
	public List<Fabricante> listarTodos() {
		conectar();
		List<Fabricante> lista = new ArrayList<Fabricante>();
		try {
			ResultSet rs = comando.executeQuery("select * from fabricante");
			while (rs.next()) {
				Fabricante fabricante = new Fabricante();
				setDadosFabricanteFromResultSet(fabricante, rs);
				lista.add(fabricante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}
		
	public void inserir(Fabricante fabricante) {
		conectar();
		try {
			comando.execute("insert into fabricante (nome) values ('" 
		                     + fabricante.getNome() + "')"
		                    );
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
		
	public void atualizar(Fabricante fabricante) {
		conectar();
		try{
			comando.execute("update fabricante set nome = '" + fabricante.getNome() 
			                 + "' where id = " + fabricante.getId()
			                );
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
		
	public void apagar(Fabricante fabricante) {
		conectar();
		try {
			comando.execute("delete from fabricante where id=" + fabricante.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

}
