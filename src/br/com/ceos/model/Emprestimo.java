package br.com.ceos.model;

import java.time.LocalDateTime;


public class Emprestimo {
	private int id;
	private String livro;
	private String usuario;
	private LocalDateTime dataEmprestimo;
	private LocalDateTime dataPrevista;
	private LocalDateTime dataDevolucao;
	private Double Multa;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLivro() {
		return livro;
	}
	public void setLivro(String livro) {
		this.livro = livro;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public LocalDateTime getDataEmprestimo() {
		return dataEmprestimo;
	}
	public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
	public LocalDateTime getDataPrevista() {
		return dataPrevista;
	}
	public void setDataPrevista(LocalDateTime dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}
	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	public Double getMulta() {
		return Multa;
	}
	public void setMulta(Double multa) {
		Multa = multa;
	}
}
