package br.com.ceos.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Usuario;

public class EmprestimoControllerTest {

	@Before
	public void setUp() throws Exception {
		UsuarioContext context = UsuarioContext.getInstance();
		Usuario usuario = new Usuario();
		
		
		usuario.setMatricula("3232");
		usuario.setNome("Teste");
		usuario.setCargo("Teste");
		usuario.setIdade("21");
		usuario.setSenha("123321");
		usuario.setTipo(false);
		
		context.guardarDados(usuario);
		
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testPreencheDadosUsuario() {
//		
//		EmprestimoController empCtrl = new EmprestimoController();
//		String[] dados = new String[2];
//		
//		dados = empCtrl.preencheDadosUsuario();
//		
//		System.out.println(dados[0]);
//		System.out.println(dados[1]);
//		
//		assertEquals("3232", dados[0]);
//		assertEquals("123321", dados[1]);
//		
//	}

//	@Test
//	public void testRealizarEmprestimo() {
//
//		EmprestimoController empCtrl = new EmprestimoController();
//		
//		try{
//			
//			empCtrl.realizarEmprestimo("1", "marx", "123", "123", "6");
//			
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro");
//		}
//		
//	}

//	@Test
//	public void testDevolverLivro() {
//		
//		EmprestimoController empCtrl = new EmprestimoController();
//		
//		try{
//			
//			empCtrl.devolverLivro(4);
//			
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro");
//		}
//		
//	}
	
	@Test
	public void testCalcularMultas(){
		
		EmprestimoController empCtrl = new EmprestimoController();
		
		try{
			
			empCtrl.calcularMultas();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	@Test
	public void testMultaIndividual(){
		
		EmprestimoController empCtrl = new EmprestimoController();
		
		try{
			
			double multa = empCtrl.multaIndividual("1");
			
			System.out.println(multa);
			
		}
		catch(Exception e){
			System.out.println("Erro"+e.getMessage());
			fail("Erro");
		}
		
	}

}
