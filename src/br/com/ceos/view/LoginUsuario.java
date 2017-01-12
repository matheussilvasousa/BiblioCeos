package br.com.ceos.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.ceos.controller.LoginController;
import br.com.ceos.controller.MainController;
import br.com.ceos.model.Usuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUsuario{

	public JFrame frame;
	private JTextField tfMatricula;
	private JPasswordField pfSenha;

	/**
	 * Create the application.
	 */
	public LoginUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setSize(350,250);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.ICONIFIED);
		frame.setVisible(true);
		frame.setResizable(false);
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setBounds(40,40,70,30);
		panel.add(lblMatricula);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(40,80,70,30);
		panel.add(lblSenha);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(150,40,150,30);
		panel.add(tfMatricula);
		tfMatricula.setColumns(10);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(150,80,150,30);
		panel.add(pfSenha);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String matricula = tfMatricula.getText();
				String senha = String.valueOf(pfSenha.getPassword());
				
				LoginController loginCtrl = new LoginController();
				MainController main = new MainController();
				
				try{
					loginCtrl.autenticarLogin(matricula, senha);
					JOptionPane.showMessageDialog(null, "Seja bem-vindo!");
					Usuario usuario = main.usuarioLogado();
					new Perfil(0, 0, 0, usuario);
					frame.dispose();
				}
				catch(Exception except){
					JOptionPane.showMessageDialog(null, except.getMessage());
				}
				
				
			}
		});
		btnLogar.setBounds(125,150,100,30);
		panel.add(btnLogar);
	}
}
