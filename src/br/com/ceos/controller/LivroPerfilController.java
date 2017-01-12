package br.com.ceos.controller;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.EmprestimoDao;
import br.com.ceos.DAO.LivroDao;
import br.com.ceos.model.Livro;

public class LivroPerfilController {
	
	public Livro dadosLivro(String isbn){
		
		Livro livro = new Livro();
		LivroDao livroDao = new LivroDao();
		
		try{
			
			Conexao.abrirBanco();
			
			livro = livroDao.umLivro(isbn);
			
			Conexao.fecharBanco();
			
			return livro;
			
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
	}
	
	//RETORNA A PESSOA COM QUEM ESTÁ O LIVRO E A DATA PREVISTA DE DEVOLUÇÃO, SE DADOS FOR NULL O LIVRO NÃO ESTÁ EMPRESTADO
	public String[] dadosDevolucao(String isbn){
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		String dados[] = new String[2];
		
		try{
			
			Conexao.abrirBanco();
			
			dados = emprestimoDao.dadosLivroEmprestado(isbn);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		//SE DADOS FOR NULL O LIVRO NÃO ESTÁ EMPRESTADO
		return dados;
	}

}
