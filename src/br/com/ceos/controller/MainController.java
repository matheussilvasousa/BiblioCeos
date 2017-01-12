package br.com.ceos.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.LivroDao;
import br.com.ceos.DAO.UsuarioDao;
import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Livro;
import br.com.ceos.model.Usuario;


public class MainController {
	
	//CRIA A PASTA DO BIBLIOCEOS
	public void criarDiretorio(){
		
		String home = System.getProperty("user.home");
		home += File.separator+"Documents"+File.separator+"BiblioCeos";
		
		File diretorio = new File(home);
		
		if(!diretorio.exists()){
			diretorio.mkdirs();
		}
	}
	
	//VERIFICA SE HÁ UM USUÁRIO LOGADO E QUAL É ESSE USUÁRIO
	public Usuario usuarioLogado(){
		
		UsuarioContext context = UsuarioContext.getInstance();
		
		if(context.usuarioLogado != null){
			Usuario usuario = new Usuario();
			
			usuario.setMatricula(context.usuarioLogado.getMatricula());
			usuario.setNome(context.usuarioLogado.getNome());
			usuario.setIdade(context.usuarioLogado.getIdade());
			usuario.setCargo(context.usuarioLogado.getCargo());
			usuario.setSenha(context.usuarioLogado.getSenha());
			usuario.setTipo(context.usuarioLogado.getTipo());
			
			return usuario;
		}
		
		return null;
		
	}
	
	
	//RETORNA UMA LISTA COM 8 LIVROS DO BANCO DE DADOS
	public List<Livro> primarylistLivro(){
		
		LivroDao livroDao = new LivroDao();
		
		List<Livro> lista = new ArrayList<Livro>();
		lista = null;
		
		try{
			
			Conexao.abrirBanco();
			
			lista =  livroDao.primaryList();

			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		return lista;
		
	}
	
	//LISTA TODOS OS LIVROS
	public List<Livro> listLivro(){
		
		LivroDao livroDao = new LivroDao();
		
		List<Livro> lista = new ArrayList<Livro>();
		lista = null;
		
		try{
			
			Conexao.abrirBanco();
			
			lista = livroDao.listar();
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		return lista;
		
	}
	
	//FAZ UMA BUSCA NA COLUNA TITULO E AUTOR
	public List<Livro> busca(String pesquisa){
		
		LivroDao livroDao = new LivroDao();
		
		List<Livro> lista = new ArrayList<Livro>();
		
		try{
			
			Conexao.abrirBanco();
			
			lista = livroDao.buscar(pesquisa);
			
			Conexao.fecharBanco();
			
			return lista;
			
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
	
	}
	
	public List<Usuario> listUsuario(){
		
		UsuarioDao usuarioDao = new UsuarioDao();
		
		List<Usuario> lista = new ArrayList<Usuario>();
		lista = null;
		
		try{
			
			Conexao.abrirBanco();
			
			lista = usuarioDao.listar();
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		return lista;
		
	}

}
