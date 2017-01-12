package br.com.ceos.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.EmprestimoDao;
import br.com.ceos.DAO.UsuarioDao;
import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Emprestimo;
import br.com.ceos.model.Usuario;

public class UsuarioPerfilController {
	
	
	//VERIFICA SE O USUARIO É UM ADMINISTRADOR
	public Boolean administrador(){
		UsuarioContext context = UsuarioContext.getInstance();
		
		if(context.usuarioLogado.getTipo()){
			return true;
		}
		
		return false;
	}
	
	//DADOS DO USUARIO
	public Usuario dadosUsuario(){
		
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDao();
		UsuarioContext context = UsuarioContext.getInstance();
		
		try{
			
			Conexao.abrirBanco();
			
			usuario = usuarioDao.buscar(context.usuarioLogado.getMatricula());
			
			Conexao.fecharBanco();
			
			return usuario;
			
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
	}
	
	//ATUALIZAR DADOS DO USUARIO
	public void atualizarDados(Usuario usuario, String matriculaAnterior){
		
		UsuarioDao usuarioDao = new UsuarioDao();
		
		try{
			
			Conexao.abrirBanco();
			
			usuarioDao.atualizar(usuario, matriculaAnterior);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public List<Emprestimo> historicoUsuario(String matricula){
		//FAZER QUANDO TIVER A CLASSE EMPRESTIMODAO E A TABELA EMPRESTIMO COMPLETA
		//CRIA UM OBJETO EMPRESTIMODAO FAZ UMA BUSCA PELA MATRICULA DO USUARIO E RETORNA UMA LIST<EMPRESTIMO>
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		
		try{
			
			Conexao.abrirBanco();
			
			lista = emprestimoDao.buscaUsuario(matricula);
			
			Conexao.fecharBanco();
			
			return lista;
			
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
		
	}
	
}
