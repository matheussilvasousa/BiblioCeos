package br.com.ceos.Teste;

import javax.swing.JOptionPane;

import br.com.ceos.VO.LivroVO;
import br.com.ceos.VO.UsuarioVO;
import br.com.ceos.controller.AdmController;

public class TesteEditarExcluir {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		
		//ATUALIZAR DADOS DO USUARIO
		//testeAtualUser();
		
		
		//DELETAR USUARIO
		//testeDelUser();
		
		//ATUALIZAR DADOS DO LIVRO
		//testeAtualLivro();
		
		//DELETAR LIVRO
		testeDelLivro();
		
		
		
		
		JOptionPane.showMessageDialog(null, "Sucesso!");

	}
	
	
	
	
	public static void testeAtualUser() throws Exception{
		
		AdmController admCtrl = new AdmController();
		UsuarioVO usuarioVO = new UsuarioVO();
		
		String matricula = "2";
		String nome = "Dimmy";
		String idade = "18";
		String cargo = "Trainee";
		String senha = "Dimmy123";
		Boolean tipo = false;
		String matriculaAnterior = "1234";
		
		usuarioVO.setMatricula(matricula);
		usuarioVO.setNome(nome);
		usuarioVO.setIdade(idade);
		usuarioVO.setCargo(cargo);
		usuarioVO.setSenha(senha);
		usuarioVO.setTipo(tipo);
		
		admCtrl.atualizarUsuario(usuarioVO, matriculaAnterior);
		
	}
	
	public static void testeDelUser() throws Exception{
		
		AdmController admCtrl = new AdmController();
		
		String matricula = "2";
		
		admCtrl.excluirUsuario(matricula);
		
	}
	
	public static void testeAtualLivro() throws Exception{
		
		AdmController admCtrl = new AdmController();
		LivroVO livroVO = new LivroVO();
		
		String isbn = "3";
		String titulo = "Cem Anos de Solidão";
		String autor = "Garcia Marquez";
		Boolean estado = true;
		String ano = "1967";
		String isbnAnterior = "2";
		String imagem = "";
		
		livroVO.setIsbn(isbn);
		livroVO.setTitulo(titulo);
		livroVO.setAutor(autor);
		livroVO.setEstado(estado);
		livroVO.setAno(ano);
		livroVO.setImagem(imagem);
		
		
		admCtrl.atualizarLivro(livroVO, isbnAnterior);
		
	}
	
	public static void testeDelLivro() throws Exception{
		
		AdmController admCrtl = new AdmController();
		
		String isbn = "3";
		
		admCrtl.excluirLivro(isbn);
	}

}

