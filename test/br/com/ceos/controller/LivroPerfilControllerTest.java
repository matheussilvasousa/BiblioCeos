package br.com.ceos.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LivroPerfilControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void testDadosLivro() {
//		LivroPerfilController perfil = new LivroPerfilController();
//		
//		try{
//			
//			Livro livro = perfil.dadosLivro("8");
//			
//			System.out.println(livro.getIsbn());
//			System.out.println(livro.getTitulo());
//			System.out.println(livro.getAutor());
//			System.out.println(livro.getAno());
//			
//		}
//		catch(Exception e){
//			System.out.println("Erro: "+e.getMessage());
//			fail("Erro");
//		}
//		
//	}

	@Test
	public void testDadosDevolucao() {
		
		LivroPerfilController perfil = new LivroPerfilController();
		String[] dados = new String[2];
		
		try{
			
			dados = perfil.dadosDevolucao("2");
			
			if(dados == null){
				assertNull(dados);
			}
			else{
				System.out.println(dados[0]);
				System.out.println(dados[1]);
				assertEquals("04/11/2015", dados[0]);
				assertEquals("Marx Planck", dados[1]);
			}
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}

}
