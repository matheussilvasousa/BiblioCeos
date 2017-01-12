package br.com.ceos.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.ceos.DAO.CargoDao;
import br.com.ceos.DAO.Conexao;
import br.com.ceos.VO.UsuarioVO;
import br.com.ceos.controller.AdmController;
import br.com.ceos.model.Cargo;
import br.com.ceos.model.Usuario;

public class CadastroUsuario {

	JFrame frame;
	private JTextField tfMatricula;
	private JTextField tfNome;
	private JTextField tfIdade;
	private JPasswordField pfSenha;
	private JPasswordField pfConfSenha;
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame;
					CadastroUsuario cadastro = new CadastroUsuario();
					cadastro.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
	

	
	
	/**
	 * Create the application.
	 */
	public CadastroUsuario() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.ICONIFIED);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblCadastro = new JLabel("CADASTRO USU\u00C1RIO");
		lblCadastro.setBounds(162, 11, 148, 14);
		panel.add(lblCadastro);
		
		JLabel lblNome = new JLabel("Matr\u00EDcula:");
		lblNome.setBounds(10, 46, 64, 14);
		panel.add(lblNome);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(145, 37, 94, 20);
		panel.add(tfMatricula);
		tfMatricula.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Nome:");
		lblNome_1.setBounds(10, 86, 64, 14);
		panel.add(lblNome_1);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(145, 77, 186, 20);
		panel.add(tfNome);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setBounds(10, 122, 64, 14);
		panel.add(lblIdade);
		
		tfIdade = new JTextField();
		tfIdade.setColumns(10);
		tfIdade.setBounds(145, 113, 49, 20);
		panel.add(tfIdade);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(10, 161, 64, 14);
		panel.add(lblCargo);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 201, 64, 14);
		panel.add(lblSenha);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 289, 64, 14);
		panel.add(lblTipo);
		
		JComboBox<String> cbCargo = new JComboBox<>();
		cbCargo.setModel(new DefaultComboBoxModel<String>(preencheComboBox()));
		cbCargo.setBounds(145, 152, 148, 20);
		panel.add(cbCargo);
		
		JLabel lblConfirmarSenha = new JLabel("Confirme sua senha:");
		lblConfirmarSenha.setBounds(10, 246, 148, 14);
		panel.add(lblConfirmarSenha);
		
		JCheckBox cbTipo = new JCheckBox("Administrador");
		cbTipo.setBounds(116, 285, 123, 23);
		panel.add(cbTipo);
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(145, 195, 123, 20);
		panel.add(pfSenha);
		
		pfConfSenha = new JPasswordField();
		pfConfSenha.setBounds(145, 240, 123, 20);
		panel.add(pfConfSenha);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
			
				String matricula, nome, idade, cargo, senha, confSenha;
				Boolean tipo;
				
				
				matricula = tfMatricula.getText();
				nome = tfNome.getText();
				idade = tfIdade.getText();
				cargo = String.valueOf(cbCargo.getSelectedItem());
				senha = String.valueOf(pfSenha.getPassword());
				confSenha = String.valueOf(pfConfSenha.getPassword());
				tipo = cbTipo.isSelected();
				
				
				if(!senha.equals(confSenha)){
					
					JOptionPane.showMessageDialog(null, "Verifique a sua senha");
					
				}
				else if(matricula.equals("")){
					
					JOptionPane.showMessageDialog(null, "Informe a matricula");
					
				}
				else{
					
					AdmController userCtrl = new AdmController();
					UsuarioVO usuarioVO = new UsuarioVO();
					
					usuarioVO.setMatricula(matricula);
					usuarioVO.setNome(nome);
					usuarioVO.setIdade(idade);
					usuarioVO.setCargo(cargo);
					usuarioVO.setSenha(senha);
					usuarioVO.setTipo(tipo);
					
					try{
						userCtrl.cadastrarUsuario(usuarioVO);
						JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
						
					}
					catch(Exception e){
						e.getMessage();
					}
					
					tfMatricula.setText("");
					tfNome.setText("");
					tfIdade.setText("");
					pfSenha.setText("");
					pfConfSenha.setText("");
					cbTipo.setSelected(false);
					
				}
				
			}
		});
		btnEnviar.setBounds(191, 372, 79, 36);
		panel.add(btnEnviar);
		
	}

	public String[] preencheComboBox(){
		
		CargoDao cargoDao = new CargoDao();
		List<Cargo> lista = new ArrayList<Cargo>();
		//String[] cargo = (String[]) lista.toArray();
		
		try{
			Conexao.abrirBanco();
			lista = cargoDao.listar();
			Conexao.fecharBanco();
			
			List<String> listaCheck = new ArrayList<String>();
			String[] check = null;
			
			for(Cargo c : lista){
				listaCheck.add(c.getCargo());
			}
			
			check = new String[listaCheck.size()];
			int i = 0;
			
			for(String s : listaCheck){
				check[i] = s;
				i++;
				//System.out.println(s);
			}
			
			return check;
			//return check;
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
}
