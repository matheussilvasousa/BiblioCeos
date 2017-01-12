package br.com.ceos.DAO;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ceos.model.Emprestimo;

public class EmprestimoDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testAdicionar() {
//		EmprestimoDao emprestimoDao = new EmprestimoDao();
//		String isbn = "5";
//		String matricula = "1";
//		
//		try{
//			
//			Conexao.abrirBanco();
//			
//			emprestimoDao.adicionar(isbn, matricula);
//			
//			Conexao.fecharBanco();
//			
//		}
//		catch(Exception e){
//			e.getMessage();
//			fail("Not yet implemented");
//		}
//	}

//	@Test
//	public void testListar() {
//		EmprestimoDao emprestimoDao = new EmprestimoDao();
//		
//		List<Emprestimo> lista = new ArrayList<Emprestimo>();
//		
//		try{
//			
//			Conexao.abrirBanco();
//			
//			lista = emprestimoDao.listar();
//					
//			Conexao.fecharBanco();
//			
//			
//			for(Emprestimo emp : lista){
//				System.out.println("------------------------------------");
//				System.out.println(emp.getId());
//				System.out.println(emp.getLivro());
//				System.out.println(emp.getUsuario());
//				System.out.println(emp.getDataEmprestimo());
//				System.out.println(emp.getDataPrevista());
//				System.out.println(emp.getDataDevolucao());
//				System.out.println(emp.getMulta());
//				System.out.println("------------------------------------");
//			}
//			
//			
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro: "+e.getMessage());
//			
//		}
//		
//	}

//	@Test
//	public void testBuscaUsuario() {
//		
//		EmprestimoDao emprestimoDao = new EmprestimoDao();
//		List<Emprestimo> lista = new ArrayList<Emprestimo>();
//		
//		try{
//			
//			Conexao.abrirBanco();
//			
//			lista = emprestimoDao.buscaUsuario("1");
//			
//			Conexao.fecharBanco();
//			
//			for(Emprestimo emp : lista){
//				System.out.println("------------------------------------");
//				System.out.println(emp.getId());
//				System.out.println(emp.getLivro());
//				System.out.println(emp.getUsuario());
//				System.out.println(emp.getDataEmprestimo());
//				System.out.println(emp.getDataPrevista());
//				System.out.println(emp.getDataDevolucao());
//				System.out.println(emp.getMulta());
//				System.out.println("------------------------------------");
//			}
//					
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro");
//		}
//		
//	}

//	@Test
//	public void testDadosLivroEmprestado() {
//		
//		EmprestimoDao emprestimoDao = new EmprestimoDao();
//		
//		String[] dados = new String[2];
//		
//		try{
//			
//			Conexao.abrirBanco();
//			
//			dados = emprestimoDao.dadosLivroEmprestado("5");
//			
//			Conexao.fecharBanco();
//			
//			if(dados[0] == null){
//				System.out.println("nulo");
//			}
//			else{
//				
//				System.out.println(dados[0]);
//				System.out.println(dados[1]);
//				
//			}
//			
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro");
//		}
//		
//	}
	
//	@Test
//	public void testdevolverLivro(){
//		
//		EmprestimoDao emprestimoDao = new EmprestimoDao();
//				
//		try{
//			
//			Conexao.abrirBanco();
//			
//			emprestimoDao.devolverLivro(1);
//			
//			Conexao.fecharBanco();
//			
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro");
//		}
//	}
	
	@Test
	public void testCalcularMultas(){
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		
		try{
			
			Conexao.abrirBanco();
			
			emprestimoDao.calcularMultas();
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	@Test
	public void testMultaIndividual(){
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		double multa = 0;
		
		try{
			
			Conexao.abrirBanco();
			
			multa = emprestimoDao.multaIndividual("1");
			
			Conexao.fecharBanco();
			
			System.out.println("Valor: "+multa);
			
		}
		catch(Exception e){
			e.getMessage();
		}
	}

}
