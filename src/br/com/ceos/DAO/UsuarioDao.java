package br.com.ceos.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ceos.model.Usuario;

public class UsuarioDao extends Conexao{
	
	public void adicionar(Usuario usuario) throws Exception{
		try{
			
			//abrirBanco();
			
			String sql = "INSERT INTO usuario VALUES(?, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getMatricula());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getIdade());
			stmt.setString(4, usuario.getCargo());
			stmt.setString(5, usuario.getSenha());
			stmt.setBoolean(6, usuario.getTipo());
			stmt.executeUpdate();
			
			//fecharBanco();
			
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		
	}
	
	public void atualizar(Usuario usuario, String matriculaAnterior) throws Exception{
		try{
		
			String sql = "UPDATE usuario SET matricula = ?, nome = ?, idade = ?, cargo = ?, senha = ?, tipo = ? where matricula = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getMatricula());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getIdade());
			stmt.setString(4, usuario.getCargo());
			stmt.setString(5, usuario.getSenha());
			stmt.setBoolean(6, usuario.getTipo());
			stmt.setString(7, matriculaAnterior);
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public void excluir(String matricula) throws Exception{
		
		try{
			
			String sql = "DELETE FROM usuario where matricula = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, matricula);
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		
	}
	
	public List<Usuario> listar() throws Exception{
		
		try{
			
			String sql = "SELECT * FROM usuario";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			List<Usuario> lista = new ArrayList<Usuario>(); 
			
			while(rs.next()){
				Usuario usuario = new Usuario();
				
				usuario.setMatricula(rs.getString("matricula"));
				usuario.setNome(rs.getString("nome"));
				usuario.setIdade(rs.getString("idade"));
				usuario.setCargo(rs.getString("cargo"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getBoolean("tipo"));
				
				lista.add(usuario);
			}
			
			rs.close();
			
			return lista;
			
		}
			catch(Exception e){
				e.getMessage();
				return null;
			}
		
	}
	
	public Usuario buscar(String matricula) throws Exception{
	
	Usuario usuario = null;
		
		try{
				
			String sql = "SELECT * FROM usuario where matricula = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, matricula);
			ResultSet rs = stmt.executeQuery();
			 
			
			if(rs.next()){
				
				usuario = new Usuario();
				
				usuario.setMatricula(rs.getString("matricula"));
				usuario.setNome(rs.getString("nome"));
				usuario.setIdade(rs.getString("idade"));
				usuario.setCargo(rs.getString("cargo"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipo(rs.getBoolean("tipo"));
				
			}
			
			rs.close();
			
		}
			catch(Exception e){
				e.getMessage();
			}
		
		return usuario;
		
	}

}
