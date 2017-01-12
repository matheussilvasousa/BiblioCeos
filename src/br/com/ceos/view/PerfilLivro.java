package br.com.ceos.view;

import java.awt.event.*;
import java.io.File;

//import java.awt.*;
import javax.swing.*;

import br.com.ceos.context.UsuarioContext;
import br.com.ceos.controller.LivroPerfilController;
import br.com.ceos.model.Emprestimo;
import br.com.ceos.model.Livro;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

public class PerfilLivro extends JFrame{
	
	public PerfilLivro(Livro livro, String[] dados, String foto) {
		
		//---ações e objetos da parte superior da tela, padrão ---
		JPanel Panel           = new JPanel();
		Panel.setBackground(Color.WHITE);
		Panel.setLayout(null);
		JLabel logo            = new JLabel();
		//JLabel perfillb		   = new JLabel("Perfil");
		
		MouseListener openMenu = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	new Menu();
		    	dispose();
		    } 
		};
		
		/*MouseListener openPerfil = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {
		    	UsuarioContext context = UsuarioContext.getInstance();
		    	new Perfil(0,0,0, context);
		    	dispose();
		    } 
		};*/
		

		
		/*perfillb.setBounds(550,30,70,30);
		perfillb.addMouseListener(openPerfil);
		Panel.add(perfillb);*/
		
		logo.setIcon(new ImageIcon(getClass().getResource("/br/com/ceos/Imagens/Image.png")));
		logo.setBounds(10, 10, 90, 120);
		logo.addMouseListener(openMenu);
		Panel.add(logo);
		//---ações e objetos da parte superior da tela, padrão ---
		
		//---Tela do perfil do livro e dados ----
		
		JLabel label2		   = new JLabel();
		label2.setBackground(new Color(0, 102, 255));
		ImageIcon icon = new ImageIcon(foto);   
		icon.setImage(icon.getImage().getScaledInstance(150, 180, 100));  
		label2.setIcon(icon);
		
		label2.setBounds(50, 170, 150, 180);
		Panel.add(label2);
		JLabel textlabel1      = new JLabel("Autor: " + livro.getAutor());
		textlabel1.setForeground(Color.WHITE);
		JLabel textlabel2      = new JLabel("Ano de lançamento: " + livro.getAno());
		textlabel2.setForeground(Color.WHITE);
		JLabel textlabel3      = new JLabel("Editora:");
		textlabel3.setForeground(Color.WHITE);
		JLabel textlabel4      = new JLabel("Categorias:");
		textlabel4.setForeground(Color.WHITE);

		String m;
		if (livro.getEstado() == true){
			m = "Original";
		}
		else{
			m = "Cópia";
		}
		JLabel textlabel5      = new JLabel("Estado: " + m);
		
 		textlabel5.setForeground(Color.WHITE);
		JLabel textlabel6      = new JLabel("Sinopse:");
		textlabel6.setForeground(Color.WHITE);
		String d1, d0;
		if ((dados[1] == null) && (dados[0] == null)){
			d1 = "";
			d0 = "";
		}
		else{
			d1 = dados[1];
			d0 = dados[0];
		}
		JLabel textlabel7      = new JLabel("Em mãos de: " + d1);
		textlabel7.setForeground(Color.WHITE);
		JLabel textlabel8      = new JLabel("Data de devolução: " + d0);
		textlabel8.setForeground(Color.WHITE);
		textlabel1.setBounds(210, 200, 206, 20);
		textlabel2.setBounds(210, 231, 231, 20);
		textlabel3.setBounds(210, 262, 70, 20);
		textlabel4.setBounds(210, 293, 70, 20);
		textlabel5.setBounds(50, 360, 134, 20);
		textlabel6.setBounds(50, 388, 70, 20);
		textlabel7.setBounds(50, 495, 215, 20);
		textlabel8.setBounds(50, 520, 215, 20);
		
		
		JLabel textlabel       = new JLabel(livro.getTitulo());
		textlabel.setBounds(260, 170, 265, 19);
		Panel.add(textlabel);
		textlabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textlabel.setForeground(Color.WHITE);
		Panel.add(textlabel1);
		Panel.add(textlabel2);
		Panel.add(textlabel3);
		Panel.add(textlabel4);
		Panel.add(textlabel5);
		Panel.add(textlabel6);
		Panel.add(textlabel7);
		Panel.add(textlabel8);
		
		//---Tela do perfil do livro e dados ----
		
		
		getContentPane().add(Panel);
		
		MouseListener openEmprestimo = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {
		    	new Empréstimo(livro);
		    } 
		};
		if(dados[1] != null){
			JButton realizarEmprestimo = new JButton("Reservar Livro");
			realizarEmprestimo.setBounds(375, 554, 168, 23);
			realizarEmprestimo.addMouseListener(openEmprestimo);
			Panel.add(realizarEmprestimo);
		}
		else{
			JButton realizarEmprestimo = new JButton("Realizar Empr\u00E9stimo");
			realizarEmprestimo.setBounds(375, 554, 168, 23);
			realizarEmprestimo.addMouseListener(openEmprestimo);
			Panel.add(realizarEmprestimo);
		}
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 255));
		panel.setBounds(22, 152, 575, 443);
		Panel.add(panel);
		
		
		setSize(625,650);
		setTitle("Perfil Livro");

		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
	}
}
