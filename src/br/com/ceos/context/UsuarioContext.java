package br.com.ceos.context;

import br.com.ceos.model.Usuario;

public class UsuarioContext extends Usuario{

	private static UsuarioContext instance;
	public Usuario usuarioLogado;
	
	private UsuarioContext(){
	}
	
	public static synchronized UsuarioContext getInstance(){
		if (instance == null){
			instance = new UsuarioContext();
		}
		return instance;
	}
	
	public void guardarDados(Usuario usuario){
		usuarioLogado = usuario;
	}
}
