package br.com.ceos.DAO;


import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import br.com.ceos.model.Emprestimo;


public class EmprestimoDao extends Conexao{
	
	final double MULTA_POR_DIA = 0.5;
	
	
	//ADICIONA UM TRANSAÇÃO DE EMPRESTIMO AO BANCO
	public void adicionar(String isbn, String matricula){
		try{
			//abrirBanco();
			
			String sql = "INSERT INTO emprestimo (livro, usuario, data_emprestimo, data_prevista, data_devolucao) VALUES(?, ?, ?, ?, ?)";
			
			LocalDateTime data = LocalDateTime.now();
			DateTimeFormatter  fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime dataPrevista = LocalDateTime.of(data.toLocalDate().plusDays(10), data.toLocalTime());
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, isbn);
			stmt.setString(2, matricula);
			stmt.setString(3, fmt.format(data));
			stmt.setString(4, fmt.format(dataPrevista));
			stmt.setString(5, "1970-01-01 00:00:00");
			stmt.executeUpdate();
			
			//fecharBanco();
		}
		catch(Exception e){
			e.getMessage();
		}
	}
	
	//LISTA TODOS OS EMPRESTIMOS REALIZADOS
	public List<Emprestimo> listar(){
		
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		
		
		try{
			
			String sql = "SELECT emprestimo.id, livro.titulo, usuario.nome, emprestimo.data_emprestimo, emprestimo.data_prevista,"
					+ " emprestimo.data_devolucao, emprestimo.multa"
					+ " FROM emprestimo JOIN livro JOIN usuario ON (livro = isbn and usuario = matricula)";
			
			stmt = conexao.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				Emprestimo emprestimo = new Emprestimo();
				
				emprestimo.setId(rs.getInt("id"));
				emprestimo.setLivro(rs.getString("titulo"));
				emprestimo.setUsuario(rs.getString("nome"));
				emprestimo.setDataEmprestimo(rs.getTimestamp("data_emprestimo").toLocalDateTime());
				emprestimo.setDataPrevista(rs.getTimestamp("data_prevista").toLocalDateTime());
				emprestimo.setDataDevolucao(rs.getTimestamp("data_devolucao").toLocalDateTime());
				emprestimo.setMulta(rs.getDouble("multa"));
				
				lista.add(emprestimo);
				
			}
			
			return lista;
			
		}
		catch(Exception e){
			e.getMessage();
			System.out.println("Erro: "+e.getMessage());
			return null;
		}
		
	}
	
	//RETORNA O HISTÓRICO DE UM USUARIO
	public List<Emprestimo> buscaUsuario(String matricula){
		
		List<Emprestimo> lista = new ArrayList<Emprestimo>();
		
		try{
			
			String sql = "SELECT emprestimo.id, emprestimo.usuario, livro.titulo, emprestimo.data_emprestimo,"
					+ " emprestimo.data_prevista, emprestimo.data_devolucao, emprestimo.multa FROM emprestimo JOIN livro"
					+ " ON (emprestimo.livro = livro.isbn) WHERE usuario = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, matricula);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				Emprestimo emprestimo = new Emprestimo();
				
				emprestimo.setId(rs.getInt("id"));
				emprestimo.setUsuario(rs.getString("usuario"));
				emprestimo.setLivro(rs.getString("titulo"));
				emprestimo.setDataEmprestimo(rs.getTimestamp("data_emprestimo").toLocalDateTime());
				emprestimo.setDataPrevista(rs.getTimestamp("data_prevista").toLocalDateTime());
				emprestimo.setDataDevolucao(rs.getTimestamp("data_devolucao").toLocalDateTime());
				emprestimo.setMulta(rs.getDouble("multa"));
				
				lista.add(emprestimo);
				
			}
			
			return lista;
			
		}
		catch(Exception e){
			e.getMessage();
			return null;
		}
		
	}
	
	public String[] dadosLivroEmprestado(String isbn){
		String[] dados = new String[2];
		
		try{
		
			String sql = "SELECT emprestimo.data_prevista, usuario.nome FROM emprestimo JOIN usuario ON"
					+ " (emprestimo.usuario = usuario.matricula) where (livro = ? and data_devolucao = '1970-01-01 00:00:00')";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, isbn);
			
			ResultSet rs = stmt.executeQuery();
			
			DateTimeFormatter  fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			if(rs.next()){
				dados[0] = fmt.format(rs.getTimestamp("data_prevista").toLocalDateTime());
				dados[1] = rs.getString("nome");
			}
			
			return dados;
			
		}
		catch(Exception e){
			System.out.println("Erro: "+e.getMessage());
			return null;
		}
		
	}

	public void devolverLivro(int id) {
		// TODO Auto-generated method stub	
		
		try{
			
			String sql = "UPDATE emprestimo SET data_devolucao = ? WHERE id = ?";
			
			LocalDateTime data = LocalDateTime.now();
			DateTimeFormatter  fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, fmt.format(data));
			stmt.setInt(2, id);
			stmt.executeUpdate();
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	//ATUALIZA TODAS AS MULTAS
	public void calcularMultas(){
		
		LocalDateTime hoje = LocalDateTime.now();
		DateTimeFormatter  fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		
		try{
			
			String sql = "SELECT * FROM emprestimo WHERE ((data_devolucao = '1970-01-01 00:00:00') and data_prevista <= ?)";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, fmt.format(hoje));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				
				LocalDateTime dataPrevista = rs.getTimestamp("data_prevista").toLocalDateTime();
				
				int dias = (int) ChronoUnit.DAYS.between(dataPrevista, hoje);
				
				double multa = dias * MULTA_POR_DIA;
				
				String update = "UPDATE emprestimo SET multa = ? WHERE id = ?";
				
				stmt = conexao.prepareStatement(update);
				
				stmt.setDouble(1, multa);
				stmt.setInt(2, rs.getInt("id"));
				
				stmt.executeUpdate();
				
			}
			
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public double multaIndividual(String matricula){
		
		double multa = 0;
		
		try{
			
			String sql = "SELECT SUM(multa) as total FROM emprestimo WHERE usuario = ?";
			
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1, matricula);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				multa = rs.getDouble("total");
				
			}
			
			
		}
		catch(Exception e){
			System.out.println("Erro: "+e.getMessage());
			
		}
		
		return multa;
		
	}
			
}