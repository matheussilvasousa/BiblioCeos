package br.com.ceos.Teste;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.EmprestimoDao;

public class TesteEmprestimo {
	
	public static void main(String[] args){
	
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		
		String isbn = "12";
		String matricula = "12";
		
		try{
			Conexao.abrirBanco();
			emprestimoDao.adicionar(isbn, matricula);
			Conexao.fecharBanco();
		}
		catch(Exception e){
			e.getMessage();
		}
		
		System.out.println("Sucesso");
		
	}
	
	

}
