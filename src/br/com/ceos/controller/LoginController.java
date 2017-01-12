package br.com.ceos.controller;

import br.com.ceos.DAO.Conexao;
import br.com.ceos.DAO.UsuarioDao;
import br.com.ceos.context.UsuarioContext;
import br.com.ceos.model.Usuario;

public class LoginController {
	
	public Boolean autenticarLogin(String matricula, String senha) throws Exception{
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = new Usuario();
		UsuarioContext context = UsuarioContext.getInstance();
		
		try{
		
			Conexao.abrirBanco();
		
			usuario = usuarioDao.buscar(matricula);
			
			Conexao.fecharBanco();
		}
		catch(Exception e){
			e.getMessage();
		}
		
		if(usuario == null){
						
			//USU�RIO INV�LIDO
			throw new IllegalArgumentException("Usu�rio inv�lido");
			
		}
		else if(!usuario.getSenha().equals(senha)){
			
			//SENHA INV�LIDA
			throw new IllegalArgumentException("Senha incorreta");
			
		}
		else{
			//ACESSO LIBERADO
			context.guardarDados(usuario);
			return true;
			
		}
		
	}

}
