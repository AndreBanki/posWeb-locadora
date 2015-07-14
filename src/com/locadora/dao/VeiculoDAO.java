package com.locadora.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.locadora.entidade.Fabricante;
import com.locadora.entidade.Veiculo;

public class VeiculoDAO extends BaseDAO {
	
	FabricanteDAO fabricanteDao;

// métodos auxiliares
	
	private void setDadosVeiculoFromResultSet(Veiculo veiculo, ResultSet rs) {
		try {
			veiculo.setId(rs.getInt("id"));
			veiculo.setNome(rs.getString("nome"));
			veiculo.setFabricante(rs.getString("nomefabricante"));
			veiculo.setTransmissao(rs.getInt("transmissao"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// métodos de busca
	
	public Veiculo buscaPorNome(String nome) {
		conectar();
		Veiculo veiculo = new Veiculo();
		try {
			ResultSet rs = comando.executeQuery("select * from veiculo where nome = '" + nome + "'");
			while (rs.next()) {
				setDadosVeiculoFromResultSet(veiculo, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return veiculo;
	}
	
	public Veiculo buscaId(int id) {
		conectar();
		Veiculo veiculo = new Veiculo();
		try {
			ResultSet rs = comando.executeQuery("select * from veiculo where id= "+id);
			while (rs.next()) {
				setDadosVeiculoFromResultSet(veiculo, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return veiculo;
	}
		
// métodos CRUD	
	
	public List<Veiculo> listarTodos() {
		conectar();
		List<Veiculo> lista = new ArrayList<Veiculo>();
		try {
			ResultSet rs = comando.executeQuery("select veiculo.id, veiculo.nome, fabricante.nome as nomefabricante, veiculo.transmissao"
					                          + " from veiculo inner join fabricante"
					                          + " on veiculo.idfabricante=fabricante.id");
			while (rs.next()) {
				Veiculo veiculo = new Veiculo();
				setDadosVeiculoFromResultSet(veiculo, rs);
				lista.add(veiculo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
		return lista;
	}
		
	public void inserir(Veiculo veiculo) {
		FabricanteDAO fabricanteDao = new FabricanteDAO();
		Fabricante fabricante = fabricanteDao.buscaPorNome(veiculo.getFabricante());
		
		conectar();
		try {
			comando.execute("insert into veiculo (nome, idfabricante, transmissao) values ('" 
		                     + veiculo.getNome() + "', "
					         + fabricante.getId() + ", "
		                     + veiculo.getTransmissao() + ")"
		                    );
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
		
	public void atualizar(Veiculo veiculo) {
		FabricanteDAO fabricanteDao = new FabricanteDAO();
		Fabricante fabricante = fabricanteDao.buscaPorNome(veiculo.getFabricante());

		conectar();
		try{
			comando.execute("update veiculo set nome = '" + veiculo.getNome() 
			                 + "', idfabricante = " + fabricante.getId()
			                 + ", transmissao = " + veiculo.getTransmissao()
			                 + " where id = " + veiculo.getId()
			                );
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}
		
	public void apagar(Veiculo veiculo) {
		conectar();
		try {
			comando.execute("delete from veiculo where id=" + veiculo.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fechar();
		}
	}

}
