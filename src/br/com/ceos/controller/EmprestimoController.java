


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
			//USU�RIO INV�LIDO
			throw new IllegalArgumentException("Usu�rio inv�lido");
		}
		else if(!usuario.getSenha().equals(senhaUser)){
			//SENHA DE USU�RIO INV�LIDA
			throw new IllegalArgumentException("Senha de usu�rio inv�lida");
		}
		else if(adm == null){
			//USU�RIO ADMINISTRADOR INV�LIDO
			throw new IllegalArgumentException("Usu�rio administrador inv�lido");
		}
		else if(!adm.getTipo()){
			throw new IllegalArgumentException("� necess�rio um administrador para realizar o emprestimo");
		}
		else if(!adm.getSenha().equals(senhaAdm)){
			throw new IllegalAccessException("Senha do administador inv�lida");
		}
		else if(usuario.getMatricula().equals(adm.getMatricula())){
			throw new IllegalArgumentException("� necess�rio outro administador para realizar o emprestimo");
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
