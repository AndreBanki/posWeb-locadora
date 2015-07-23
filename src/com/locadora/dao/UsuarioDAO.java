package com.locadora.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.locadora.entidade.Usuario;
import com.locadora.util.Cripto;

public class UsuarioDAO extends BaseDAO {

	public Usuario buscaPorEmail(String email){
		conectar();
		Usuario usuario = new Usuario();
		
		try{
			ResultSet rs;
			rs = comando.executeQuery("select * from usuario where email = '"+email+"'");
			while(rs.next()){
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.getPerfil().setValue(rs.getInt("perfil"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			fechar();
		}
		return usuario;
	}

	public List<Usuario> lista(){
		conectar();
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			ResultSet rs;
			rs = comando.executeQuery("select * from usuario");

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.getPerfil().setValue(rs.getInt("perfil"));
				lista.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return lista;
	}
	
	public Usuario buscaId(int id){
		conectar();
		Usuario usuario = new Usuario();
		try {
			ResultSet rs;
			rs = comando.executeQuery("select * from usuario where id= "+id);

			while (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.getPerfil().setValue(rs.getInt("perfil"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
		return usuario;
	}
	
	private String criptografaSenha(Usuario usuario) {
		String senha = new String();
		Cripto c = new Cripto();
		try {
			senha = c.encript(usuario.getSenha());
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		return senha;
	}	
	
	public void inserir(Usuario usuario){
		conectar();
		try {
			comando.execute("insert into usuario (nome, email, senha, perfil) values ('" + 
		                    usuario.getNome() + "', '" + 
					        usuario.getEmail() + "', '" + 
					        criptografaSenha(usuario) + "', " +
                            usuario.getPerfil().getValue() + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
	
	public void atualizar(Usuario usuario){
		conectar();
		try{
			comando.execute("update usuario set nome = '" + usuario.getNome() + 
					        "', email = '" + usuario.getEmail() +
					        "', senha = '" + criptografaSenha(usuario) +
					        "', perfil = " + usuario.getPerfil().getValue() +
					        " where id = "+usuario.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			fechar();
		}
	}
	
	
	public void deletar(Usuario usuario){
		conectar();
		try {
			comando.execute("delete from usuario where id="+usuario.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			fechar();
		}
	}

}
