package br.com.ceos.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Livro;
import br.com.ceos.model.Usuario;

public class MainControllerTest {

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCriarDiretorio() {
		MainController mainCtrl = new MainController();
		
		try{
			mainCtrl.criarDiretorio();
		}
		catch(Exception e){
			fail("Erro: "+e.getMessage());
		}
	}
	
//	@Test
//	public void testUsuarioLogado(){
//		UsuarioContext context = UsuarioContext.getInstance();
//		
//		Usuario usuario = new Usuario();
//		
//		usuario.setMatricula("1");
//		usuario.setNome("Teste");
//		usuario.setIdade("21");
//		usuario.setCargo("Teste");
//		usuario.setSenha("123");
//		usuario.setTipo(false);
//		
//		context.guardarDados(usuario);
//		
//		MainController main = new MainController();
//		
//		assertEquals(usuario.getMatricula(), main.usuarioLogado().getMatricula());
//		
//
//	}
//
//	@Test
//	public void testPrimarylistLivro() {
//		MainController mainCtrl = new MainController();
//		
//		try{
//			
//			List<Livro> lista = mainCtrl.primarylistLivro();
//			//System.out.println("\n\n");
//			
//			for(Livro livro : lista){
//				//System.out.println(livro.getTitulo());
//			}
//			
//		}
//		catch(Exception e){
//			fail("Erro: "+e.getMessage());
//		}
//	}
//
//	@Test
//	public void testListLivro() {
//MainController mainCtrl = new MainController();
//		
//		try{
//			
//			List<Livro> lista = mainCtrl.listLivro();
//			//System.out.println("\n\n");
//			
//			for(Livro livro : lista){
//				//System.out.println(livro.getTitulo());
//			}
//			
//		}
//		catch(Exception e){
//			fail("Erro: "+e.getMessage());
//		}
//	}

	@Test
	public void testBusca() {
		MainController mainCtrl = new MainController();
		
		String pesquisa = "Origem";

		try{
			
			List<Livro> lista = mainCtrl.busca(pesquisa);
			
			for(Livro livro : lista){
				System.out.println(livro.getTitulo());
			}
			
		}
		catch(Exception e){
			fail("Erro: "+e.getMessage());
		}
	}

}
