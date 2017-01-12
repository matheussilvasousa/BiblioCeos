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
						
			//USUÁRIO INVÁLIDO
			throw new IllegalArgumentException("Usuário inválido");
			
		}
		else if(!usuario.getSenha().equals(senha)){
			
			//SENHA INVÁLIDA
			throw new IllegalArgumentException("Senha incorreta");
			
		}
		else{
			//ACESSO LIBERADO
			context.guardarDados(usuario);
			return true;
			
		}
		
	}

}
