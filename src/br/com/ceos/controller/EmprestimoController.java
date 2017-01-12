


//TESTAR CLASSE


package br.com.ceos.controller;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.EmprestimoDao;
import br.com.ceos.DAO.UsuarioDao;
import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Usuario;

public class EmprestimoController {
	
	//PREENCHE DADOS DO USUARIO AUTOMATICAMENTE CASO ELE ESTEJA LOGADO
	public String[] preencheDadosUsuario(){
		UsuarioContext context = UsuarioContext.getInstance();
		
		if(context.usuarioLogado != null){
			String[] dados = new String[2];
			
			dados[0] = context.usuarioLogado.getMatricula();
			dados[1] = context.usuarioLogado.getSenha();
			
			return dados;
			
		}
		
		return null;
	}
	
	//REALIZAR EMPRESTIMO
	public Boolean realizarEmprestimo(String matriculaUser, String senhaUser, String matriculaAdm, String senhaAdm, String isbn) throws Exception{
		
		Usuario usuario = new Usuario();
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario adm = new Usuario();
		String[] dados = new String[2];
		
		try{
			
			Conexao.abrirBanco();
			
			usuario = usuarioDao.buscar(matriculaUser);
			adm = usuarioDao.buscar(matriculaAdm);
			
			Conexao.fecharBanco();
		}
		catch(Exception e){
			e.getMessage();
		}
		
		if(usuario == null){
			//USUÁRIO INVÁLIDO
			throw new IllegalArgumentException("Usuário inválido");
		}
		else if(!usuario.getSenha().equals(senhaUser)){
			//SENHA DE USUÁRIO INVÁLIDA
			throw new IllegalArgumentException("Senha de usuário inválida");
		}
		else if(adm == null){
			//USUÁRIO ADMINISTRADOR INVÁLIDO
			throw new IllegalArgumentException("Usuário administrador inválido");
		}
		else if(!adm.getTipo()){
			throw new IllegalArgumentException("É necessário um administrador para realizar o emprestimo");
		}
		else if(!adm.getSenha().equals(senhaAdm)){
			throw new IllegalAccessException("Senha do administador inválida");
		}
		else if(usuario.getMatricula().equals(adm.getMatricula())){
			throw new IllegalArgumentException("É necessário outro administador para realizar o emprestimo");
		}
		
		else{
			
			EmprestimoDao emprestimoDao = new EmprestimoDao();
			
			try{
				
				Conexao.abrirBanco();
				
				dados = emprestimoDao.dadosLivroEmprestado(isbn);
				
				if(dados[1] != null){
					return false;
				}
				else{	
					emprestimoDao.adicionar(isbn, matriculaUser);
				}
				Conexao.fecharBanco();
				
			}
			catch(Exception e){
				e.getMessage();
			}
			
			return true;
		}
	
	}
	
	public void devolverLivro(int id){
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		
		try{
		
			Conexao.abrirBanco();
			
			emprestimoDao.devolverLivro(id);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	public void calcularMultas(){
		
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
	
	public double multaIndividual(String matricula){
		
		double multa = 0;
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		
		try{
			
			Conexao.abrirBanco();
			
			multa = emprestimoDao.multaIndividual(matricula);
			
			Conexao.fecharBanco();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
		return multa;
		
	}

}
