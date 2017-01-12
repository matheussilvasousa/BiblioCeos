package br.com.ceos.Teste;

import java.util.List;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.LivroDao;
import br.com.ceos.DAO.UsuarioDao;
import br.com.ceos.model.Livro;
import br.com.ceos.model.Usuario;

public class Teste {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDao();
		
		Livro livro = new Livro();
		LivroDao livroDao = new LivroDao();
		
	////ADICIONA USUARIO
//		usuario.setMatricula("14");
//		usuario.setNome("Vendetta");
//		usuario.setIdade("21");
//		usuario.setCargo("marketing");
//		usuario.setSenha("123456");
//		usuario.setTipo("comum");
//		
//		usuarioDao.adicionar(usuario);
		
		
		
//		ALTERA DADOS USUARIO
//		String matriculaAnterior = "3";
//		
//		usuario.setMatricula("1");
//		usuario.setNome("João Grilo");
//		usuario.setIdade("23");
//		usuario.setCargo("programador");
//		usuario.setSenha("123");
//		usuario.setTipo("comum");
//		
//		usuarioDao.atualizar(usuario, matriculaAnterior);
		
		
//		EXCLUIR USUARIO
//		usuario.setMatricula("1");
//		usuarioDao.excluir(usuario);
//		
		

		
		
//		//LISTA OS USUARIOS
//		List<Usuario> lista = usuarioDao.listar();
//		
//		for (Usuario user : lista) {
//			System.out.println("--------------------------------------");
//			System.out.println("Matricula: "+user.getMatricula());
//			System.out.println("Nome: "+user.getNome());
//			System.out.println("Idade: "+user.getIdade());
//			System.out.println("Cargo: "+user.getCargo());
//			System.out.println("Senha: "+user.getSenha());
//			System.out.println("Tipo: "+user.getTipo());
//			System.out.println("--------------------------------------");
//		}
//		
		
		
		//ADICIONA LIVRO
//		livro.setIsbn("1313245");
//		livro.setTitulo("A Origem da Desigualdade entre os Homens");
//		livro.setAutor("Jean Jacques Russeau");
//		livro.setEstado("0");
//		livro.setAno("1730");	
//		livroDao.adicionar(livro);
		
		
		
//		//ATUALIZAR LIVRO
//		String isbnAnterior = "123";
//		livro.setIsbn("123");
//		livro.setTitulo("O Manifesto Comunista");
//		livro.setAutor("Karl Marx e Friedrich Engels");
//		livro.setEstado("0");
//		livro.setAno("1910");
//		
//		livroDao.atualizar(livro, isbnAnterior);
		
		
//		//EXCLUIR LIVRO
//		livro.setIsbn("123");
//		livroDao.excluir(livro);
		
		
//		//LISTA DE LIVROS
//		List<Livro> lista2 = livroDao.listar();
//		
//		for(Livro livro2 : lista2){
//			System.out.println("-------------------------------");
//			System.out.println("ISBN: "+livro2.getIsbn());
//			System.out.println("Titulo: "+livro2.getTitulo());
//			System.out.println("Autor: "+livro2.getAutor());
//			System.out.println("Estado: "+livro2.getEstado());
//			System.out.println("Ano: "+livro2.getAno());
//			System.out.println("-------------------------------");
//		}
		
//		Conexao.abrirBanco();
//		List<Livro> lista3 = livroDao.buscar("titulo", "Desigualdade");
//		Conexao.fecharBanco();
//		for(Livro livro2 : lista3){
//			System.out.println("-------------------------------");
//			System.out.println("ISBN: "+livro2.getIsbn());
//			System.out.println("Titulo: "+livro2.getTitulo());
//			System.out.println("Autor: "+livro2.getAutor());
//			System.out.println("Estado: "+livro2.getEstado());
//			System.out.println("Ano: "+livro2.getAno());
//			System.out.println("-------------------------------");
//		}
		
		
		
		System.out.println("Sucesso!!!");
	}
		

}
