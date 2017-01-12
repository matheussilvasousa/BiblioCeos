package br.com.ceos.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.ceos.controller.EmprestimoController;
import br.com.ceos.controller.LivroPerfilController;
import br.com.ceos.model.Livro;

public class Empréstimo extends JFrame{
	public Empréstimo(Livro livro){
	    JPanel Panel             = new JPanel();
		JButton Button           = new JButton("Enter");
		JTextField textUser      = new JTextField(100);
		JTextField textAdm    	 = new JTextField(100);
		JPasswordField passUser  = new JPasswordField(100);
		JPasswordField passAdm   = new JPasswordField(100);
		JLabel textMUser         = new JLabel("Matrícula do Usuário:");
		JLabel textPUser         = new JLabel("Senha do Usuário:");
		JLabel textMAdm          = new JLabel("Matrícula do Adm:");
		JLabel textPAdm          = new JLabel("Senha do Adm:");
		JLabel Titulo 			 = new JLabel("Titulo: " + livro.getTitulo());
		LocalDate data = LocalDate.now();
		DateTimeFormatter  fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		JLabel Data 			 = new JLabel("Data: " + fmt.format(data));
		
		Panel.setLayout(null);
		
		Titulo.setBounds(40, 20,310,30);
		Panel.add(Titulo);
		
		Data.setBounds(40, 60,100,30);
		Panel.add(Data);
		
		textMUser.setBounds(40,100,150,30);
		Panel.add(textMUser);

		textPUser.setBounds(40,140,150,30);
		Panel.add(textPUser);
		
		textMAdm.setBounds(40,180,150,30);
		Panel.add(textMAdm);

		textPAdm.setBounds(40,220,150,30);
		Panel.add(textPAdm);
		
		
		textUser.setBounds(200,100,150,30);
		Panel.add(textUser);

		passUser.setBounds(200,140,150,30);
		Panel.add(passUser);
		
		textAdm.setBounds(200,180,150,30);
		Panel.add(textAdm);

		passAdm.setBounds(200,220,150,30);
		Panel.add(passAdm);
		
		MouseListener openEmprestimo = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	try {		    
		    		JFrame frame;
		    		Boolean emprestimo;
		    		EmprestimoController ec = new EmprestimoController();
		    		LivroPerfilController lpc = new LivroPerfilController();
					emprestimo = ec.realizarEmprestimo(textUser.getText(), String.valueOf(passUser.getPassword()), textAdm.getText(), String.valueOf(passAdm.getPassword()), livro.getIsbn());
					if (emprestimo == false){
						JOptionPane.showMessageDialog(null, "Este livro já foi emprestado, mas sua reserva foi feita com sucesso!");
					}
					else{
						JOptionPane.showMessageDialog(null, "Empréstimo realizado com sucesso!");
					}
					String foto = System.getProperty("user.home");
					foto += File.separator+"Documents"+File.separator+"BiblioCeos"+File.separator+livro.getImagem()+".jpg";
					String dados[] = new String[2];
					dados = lpc.dadosDevolucao(livro.getIsbn());
					new PerfilLivro(livro, dados, foto);
					dispose();
		    	} catch (Exception e1) {
		    		JOptionPane.showMessageDialog(null, e1.getMessage());
				}
		    } 
		};
		
		
		Button.setBounds(150,260,100,30);
		Button.addMouseListener(openEmprestimo);
		Panel.add(Button);
		
		getContentPane().add(Panel);

		setSize(400,350);

		setTitle("Empréstimo");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.ICONIFIED);
		setVisible(true);

		setResizable(false);
		}

}