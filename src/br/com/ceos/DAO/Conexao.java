package br.com.ceos.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;

public class Conexao {
	
	static Connection conexao;
	static PreparedStatement stmt;
	
	public static void abrirBanco() throws Exception{
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/biblio_ceos";
		String user = "root";
		String password = "";
	    conexao = DriverManager.getConnection(url, user, password);
		
	}
	
	public static void fecharBanco() throws Exception{
		if(conexao != null){
			conexao.close();
		}
		if(stmt != null){
			stmt.close();
		}
	}

}
