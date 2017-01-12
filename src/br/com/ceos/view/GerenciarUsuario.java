package br.com.ceos.view;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

import br.com.ceos.DAO.CargoDao;
import br.com.ceos.DAO.Conexao;
import br.com.ceos.VO.UsuarioVO;
import br.com.ceos.controller.AdmController;
import br.com.ceos.model.Cargo;

public class GerenciarUsuario extends JFrame{
	
	private JTextField tfMatricula;
	private JTextField tfNome;
	private JTextField tfIdade;
	private JPasswordField pfSenha;
	private JPasswordField pfConfSenha;
	
	public GerenciarUsuario(){
		AdmController adm = new AdmController();
	    
		JPanel Panel             = new JPanel();
	    JPanel Panel2 = new JPanel();
		Panel2.setBounds(18, 156, 446, 204);
		Panel.add(Panel2);
	    
	    // botões e espaço pra busca, acho que vão ter mais campos de texto, mas n lembrava quantos e quais
		JButton Listar           = new JButton("Listar");
		JButton Adcionar         = new JButton("Adcionar");
		JButton Editar           = new JButton("Editar");
		JButton Excluir          = new JButton("Excluir");
		JTextField UserName      = new JTextField(100);
		JTextField UserMatricula = new JTextField(100);
		JLabel Name          	 = new JLabel("Nome:");
		JLabel Matricula     	 = new JLabel("Matrícula:");
		
		// tabela que lista todos ou a procura
		String [] colunas = {"ID", "Nome", "Matrícula"};
		Object [][] dados = { {"1", "Marcos", "351274"}, 
				{"2", "Matheus", "352461"},
				{"3", "Paulo", "354085"},};
		
		int scrolldh = (dados.length) * 16;
		
		//dentro de uma scrollpane
		/*JScrollPane Panel2 = new JScrollPane();
		Panel2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		Panel2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Panel2.setBounds(10, 140, 450, 200);
		Panel2.setVisible(false);
		
		JTable tabela = new JTable(dados,colunas);
		tabela.setPreferredSize(new Dimension(450, scrolldh));
		Panel2.setViewportView(tabela);
		
		getContentPane().add(Panel2);*/
		
		//ação do botão listar
		
		ActionListener actionListar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Panel2.setVisible(true);
			}
		};
		    	Panel2.setLayout(null);
		
		/*MouseListener adicionarUsuario = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  { 
		    	JLabel lblNome = new JLabel("Matr\u00EDcula:");
				lblNome.setBounds(7, 8, 47, 14);
				Panel2.add(lblNome);
				
				tfMatricula = new JTextField();
				tfMatricula.setBounds(59, 5, 86, 20);
				Panel2.add(tfMatricula);
				tfMatricula.setColumns(10);
				
				JLabel lblNome_1 = new JLabel("Nome:");
				lblNome_1.setBounds(150, 8, 31, 14);
				Panel2.add(lblNome_1);
				
				tfNome = new JTextField();
				tfNome.setColumns(10);
				tfNome.setBounds(186, 5, 86, 20);
				Panel2.add(tfNome);
				
				JLabel lblIdade = new JLabel("Idade:");
				lblIdade.setBounds(277, 8, 32, 14);
				Panel2.add(lblIdade);
				
				tfIdade = new JTextField();
				tfIdade.setColumns(10);
				tfIdade.setBounds(314, 5, 86, 20);
				Panel2.add(tfIdade);
				
				JLabel lblCargo = new JLabel("Cargo:");
				lblCargo.setBounds(405, 8, 33, 14);
				Panel2.add(lblCargo);
				
				JLabel lblSenha = new JLabel("Senha:");
				lblSenha.setBounds(27, 34, 34, 14);
				Panel2.add(lblSenha);
				
				JLabel lblTipo = new JLabel("Tipo:");
				lblTipo.setBounds(66, 34, 24, 14);
				Panel2.add(lblTipo);
				
				JComboBox<String> cbCargo = new JComboBox<>();
				cbCargo.setModel(new DefaultComboBoxModel<String>(preencheComboBox()));
				cbCargo.setBounds(95, 31, 28, 20);
				Panel2.add(cbCargo);
				
				JLabel lblConfirmarSenha = new JLabel("Confirme sua senha:");
				lblConfirmarSenha.setBounds(128, 34, 99, 14);
				Panel2.add(lblConfirmarSenha);
				
				JCheckBox cbTipo = new JCheckBox("Administrador");
				cbTipo.setBounds(232, 30, 91, 23);
				Panel2.add(cbTipo);
				
				pfSenha = new JPasswordField();
				pfSenha.setBounds(328, 31, 6, 20);
				Panel2.add(pfSenha);
				
				pfConfSenha = new JPasswordField();
				pfConfSenha.setBounds(339, 31, 6, 20);
				Panel2.add(pfConfSenha);
				
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
				btnEnviar.setBounds(350, 30, 69, 23);
				Panel2.add(btnEnviar);
				
			}
		};*/
		
		MouseListener excluirUsuario = new MouseAdapter()  {  
			public void mouseClicked(MouseEvent e)  { 
				Panel2.removeAll();
				Panel2.repaint();
				Panel2.revalidate();
				Panel2.setLayout(null);
					
				JLabel exclusaoUsuario = new JLabel("Voc\u00EA tem certeza que deseja excluir o usuário " + UserName.getText() + "?");
				exclusaoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
				exclusaoUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
				exclusaoUsuario.setBounds(21, 40, 402, 15);	
				Panel2.add(exclusaoUsuario);
					
				MouseListener exclusaoBotao = new MouseAdapter()  {  
				    public void mouseClicked(MouseEvent e)  {  
				    	adm.excluirLivro(UserMatricula.getText());
						JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
						Panel2.removeAll();
						Panel2.repaint();
						Panel2.revalidate();
					}
				};
					
				JButton botaoExcluir = new JButton("Excluir");
				botaoExcluir.addMouseListener(exclusaoBotao);
				botaoExcluir.setBounds(62, 102, 90, 37);
				Panel2.add(botaoExcluir);
			
				JButton botaoCancelar = new JButton("Cancelar");
				botaoCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Panel2.removeAll();
						Panel2.repaint();
						Panel2.revalidate();
					}
				});
				
				botaoCancelar.setBounds(290, 102, 90, 37);
				Panel2.add(botaoCancelar);
			}
		};
		
		Panel.setLayout(null);
		Panel.setSize(480,100);
		Name.setBounds(40, 20, 100,30);
		Panel.add(Name);
		
		// de resto só sentando os botoes e labels
		Matricula.setBounds(40, 60, 100,30);
		Panel.add(Matricula);
		
		UserName.setBounds(150, 20, 300,30);
		Panel.add(UserName);
		
		UserMatricula.setBounds(150, 60, 100,30);
		Panel.add(UserMatricula);
		
		Listar.setBounds(20,100,100,30);
		Panel.add(Listar);
		Listar.addActionListener(actionListar);
		Adcionar.setBounds(130,100,100,30);
		Panel.add(Adcionar);
		//Adcionar.addMouseListener(adicionarUsuario);
		
		Editar.setBounds(240,100,100,30);
		Panel.add(Editar);
		
		Excluir.setBounds(350,100,100,30);
		Panel.add(Excluir);
		Excluir.addMouseListener(excluirUsuario);

		getContentPane().add(Panel);

		setSize(480,400);

		setTitle("Gerenciar Usuario");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.ICONIFIED);
		setVisible(true);

		setResizable(false);
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
	
	public static void main(String[] args){
		new GerenciarUsuario();
	}
}
