package br.com.ceos.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Emprestimo;
import br.com.ceos.model.Usuario;

public class UsuarioPerfilControllerTest {

	@Before
	public void setUp() throws Exception {
		
		UsuarioContext context = UsuarioContext.getInstance();
		Usuario usuario = new Usuario();
		
		
		usuario.setMatricula("123");
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
//	public void testAdministrador() {
//		
//		UsuarioPerfilController perfil = new UsuarioPerfilController();
//		
//		assertFalse(perfil.administrador());
//		
//	}

//	@Test
//	public void testDadosUsuario() {
//		
//		UsuarioPerfilController perfil = new UsuarioPerfilController();
//		Usuario usuario = new Usuario();
//		
//		try{
//			
//			usuario = perfil.dadosUsuario();
//			
//			System.out.println(usuario.getMatricula());
//			System.out.println(usuario.getNome());
//			
//			assertEquals("123", usuario.getMatricula());
//			assertEquals("Franz Kafka", usuario.getNome());
//			
//		}
//		catch(Exception e){
//			e.getMessage();
//			fail("Erro");
//		}
//	}

//	@Test
//	public void testAtualizarDados() {
//		
//		UsuarioPerfilController perfil = new UsuarioPerfilController();
//		Usuario usuario = new Usuario();
//		String matriculaAnterior = "1";
//		
//		usuario.setMatricula("1");
//		usuario.setNome("Marx Planck");
//		usuario.setIdade("32");
//		usuario.setCargo("Diretor de RH");
//		usuario.setSenha("m1");
//		usuario.setTipo(false);
//		
//		try{
//			
//			perfil.atualizarDados(usuario, matriculaAnterior);
//			
//			assertTrue(true);
//			
//		}
//		catch(Exception e){
//			e.getMessage();
//			fail("Erro");
//		}
//		
//	}

	@Test
	public void testHistoricoUsuario() {
		
		UsuarioPerfilController perfil = new UsuarioPerfilController();
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		
		try{
			
			lista = perfil.historicoUsuario("1");
			
			for(Emprestimo emp : lista){
				System.out.println("---------------------------------");
				System.out.println(emp.getId());
				System.out.println(emp.getLivro());
				System.out.println(emp.getUsuario());
				System.out.println(emp.getDataEmprestimo());
				System.out.println(emp.getDataPrevista());
				System.out.println(emp.getDataDevolucao());
				System.out.println(emp.getMulta());
				System.out.println("---------------------------------");
			}
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}

}
