package br.com.ceos.controller;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.LivroDao;
import br.com.ceos.DAO.UsuarioDao;
import br.com.ceos.VO.LivroVO;
import br.com.ceos.VO.UsuarioVO;
import br.com.ceos.model.Livro;
import br.com.ceos.model.Usuario;

public class AdmController extends UsuarioPerfilController{
	
	
	//CADASTRAR USUÁRIO
	public void cadastrarUsuario(UsuarioVO usuarioVO){
		
		try{
			
			Usuario usuario = new Usuario();
			UsuarioDao usuarioDao = new UsuarioDao();
			
			usuario.setMatricula(usuarioVO.getMatricula());
			usuario.setNome(usuarioVO.getNome());
			usuario.setIdade(usuarioVO.getIdade());
			usuario.setCargo(usuarioVO.getCargo());
			usuario.setSenha(usuarioVO.getSenha());
			usuario.setTipo(usuarioVO.getTipo());
			
			Conexao.abrirBanco();
			
			usuarioDao.adicionar(usuario);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	//ATUALIAZAR DADOS DO USUÁRIO
	public void atualizarUsuario(UsuarioVO usuarioVO, String matriculaAnterior){

		try{
			
			Usuario usuario = new Usuario();
			UsuarioDao usuarioDao = new UsuarioDao();
			
			usuario.setMatricula(usuarioVO.getMatricula());
			usuario.setNome(usuarioVO.getNome());
			usuario.setIdade(usuarioVO.getIdade());
			usuario.setCargo(usuarioVO.getCargo());
			usuario.setSenha(usuarioVO.getSenha());
			usuario.setTipo(usuarioVO.getTipo());
			
			Conexao.abrirBanco();
			
			usuarioDao.atualizar(usuario, matriculaAnterior);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	
	public void excluirUsuario(String matricula){
		
		try{
			
			UsuarioDao usuarioDao = new UsuarioDao();
			
			Conexao.abrirBanco();
			
			usuarioDao.excluir(matricula);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	

	
	
	
	
	//CADASTRAR LIVRO
	public void cadastrarLivro(LivroVO livroVO){
		// TODO Auto-generated method stub
		
		try{
			
			Livro livro = new Livro();
			LivroDao livroDao = new LivroDao();
			
			livro.setIsbn(livroVO.getIsbn());
			livro.setTitulo(livroVO.getTitulo());
			livro.setAutor(livroVO.getAutor());
			livro.setEstado(livroVO.getEstado());
			livro.setAno(livroVO.getAno());
			livro.setImagem(livroVO.getImagem());
			
			Conexao.abrirBanco();
			
			livroDao.adicionar(livro);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		
		
	}
	
	
	//ATUALIZAR DADOS DO LIVRO
	public void atualizarLivro(LivroVO livroVO, String isbnAnterior){
		
		try{
			
			Livro livro = new Livro();
			LivroDao livroDao = new LivroDao();
			
			livro.setIsbn(livroVO.getIsbn());
			livro.setTitulo(livroVO.getTitulo());
			livro.setAutor(livroVO.getAutor());
			livro.setEstado(livroVO.getEstado());
			livro.setAno(livroVO.getAno());
			livro.setImagem(livroVO.getImagem());
			
			Conexao.abrirBanco();
			
			livroDao.atualizar(livro, isbnAnterior);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	
	//EXCLUIR LIVRO
	public void excluirLivro(String isbn){
		
		try{
			
			LivroDao livroDao = new LivroDao();
			
			Conexao.abrirBanco();
			
			livroDao.excluir(isbn);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}

}
