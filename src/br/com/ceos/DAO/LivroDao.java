package br.com.ceos.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ceos.model.Livro;

public class LivroDao extends Conexao{
	

	public void adicionar(Livro livro) throws Exception{
		try{
			
			String sql = "INSERT INTO livro VALUES(?, ?, ?, ?, ?, ?)";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, livro.getIsbn());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getAutor());
			stmt.setBoolean(4, livro.getEstado());
			stmt.setString(5, livro.getAno());
			stmt.setString(6, livro.getImagem());
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	

	public void atualizar(Livro livro, String isbnAnterior) throws Exception{
		try{
			
			String sql = "UPDATE livro SET isbn = ?, titulo = ?, autor = ?, estado = ?, ano = ?, imagem = ? where isbn = ?";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, livro.getIsbn());
			stmt.setString(2, livro.getTitulo());
			stmt.setString(3, livro.getAutor());
			stmt.setBoolean(4, livro.getEstado());
			stmt.setString(5, livro.getAno());
			stmt.setString(6, livro.getImagem());
			stmt.setString(7, isbnAnterior);
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	public void excluir(String isbn) throws Exception{
		try{
			
			String sql = "DELETE FROM livro where isbn = ?";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, isbn);
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	public List<Livro> listar() throws Exception{
		
		try{
			
			String sql = "SELECT * FROM livro";
			
			stmt = conexao.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Livro> lista = new ArrayList<Livro>();
			
			while(rs.next()){
				Livro livro = new Livro();
				
				livro.setIsbn(rs.getString("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEstado(rs.getBoolean("estado"));
				livro.setAno(rs.getString("ano"));
				livro.setImagem(rs.getString("imagem"));
				
				lista.add(livro);
			}
			
			
			return lista;
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
		
	}
	
	public Livro umLivro(String isbn){
		
		Livro livro = new Livro();
		
		try{
		
			String sql = "SELECT * FROM livro WHERE isbn = ?";
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				livro.setIsbn(rs.getString("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEstado(rs.getBoolean("estado"));
				livro.setAno(rs.getString("ano"));
				livro.setImagem(rs.getString("imagem"));
			}
			
			return livro;
			
		}
		catch(Exception e){
			System.out.println("Erro: "+e.getMessage());
			return null;
		}
		
		
	}


	//FAZ UMA BUSCA NA COLUNA AUTOR E TITULO DO LIVRO DE UMA UNICA VEZ
	public List<Livro> buscar(String pesquisa) throws Exception{
		
		try{
			
			String sql = "SELECT * FROM livro where (titulo like ? or autor like ?)";
		
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%"+pesquisa+"%");
			stmt.setString(2, "%"+pesquisa+"%");
			
			ResultSet rs = stmt.executeQuery();
			
			List<Livro> lista = new ArrayList<Livro>();
			
			while(rs.next()){
				Livro livro = new Livro();
				
				livro.setIsbn(rs.getString("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEstado(rs.getBoolean("estado"));
				livro.setAno(rs.getString("ano"));
				livro.setImagem(rs.getString("imagem"));
				
				lista.add(livro);
			}
			
			return lista;
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
	}
	
	public List<Livro> primaryList(){
		
		try{
			
			String sql = "SELECT * FROM livro LIMIT 8";
			
			stmt = conexao.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			List<Livro> lista = new ArrayList<Livro>();
			
			while(rs.next()){
				Livro livro = new Livro();
				
				livro.setIsbn(rs.getString("isbn"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setAutor(rs.getString("autor"));
				livro.setEstado(rs.getBoolean("estado"));
				livro.setAno(rs.getString("ano"));
				livro.setImagem(rs.getString("imagem"));
				
				lista.add(livro);
			}
			
			
			return lista;
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
	}
	
	
}
