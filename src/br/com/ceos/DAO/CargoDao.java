package br.com.ceos.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.ceos.model.Cargo;

public class CargoDao extends Conexao{
	

	public List<Cargo> listar() throws Exception{
		
		try{
			
			String sql = "SELECT * FROM cargo";
			stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			 
			//REFATORAR, UTILIZAR ENUMERATION PARA PEGAR ID DO CARGO
			List<Cargo> cargo = new ArrayList<Cargo>();
			
			while(rs.next()){
				
				Cargo c = new Cargo();
				c.setId(rs.getInt("id"));
				c.setCargo(rs.getString("cargo"));
				
				cargo.add(c);
			}
			
			rs.close();
			
			return cargo;
			
		}
			catch(Exception e){
				e.getMessage();
				return null;
			}
		
	}
	

}
