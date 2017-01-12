package br.com.ceos.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.ceos.VO.LivroVO;
import br.com.ceos.controller.AdmController;

public class CadastroLivro {

	JFrame frame;
	private JTextField tfIsbn;
	private JTextField tfTitulo;
	private JTextField tfAutor;
	private JTextField tfAno;
	private JTextField tfFoto;
	JFileChooser chooser = new JFileChooser();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroLivro window = new CadastroLivro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public CadastroLivro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 440, 420);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.ICONIFIED);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CADASTRO LIVRO");
		lblNewLabel.setBounds(166, 26, 127, 14);
		panel.add(lblNewLabel);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(33, 73, 46, 14);
		panel.add(lblIsbn);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(33, 113, 46, 14);
		panel.add(lblTitulo);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(33, 153, 46, 14);
		panel.add(lblAutor);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(101, 70, 153, 20);
		panel.add(tfIsbn);
		tfIsbn.setColumns(10);
		
		tfTitulo = new JTextField();
		tfTitulo.setBounds(101, 110, 214, 20);
		panel.add(tfTitulo);
		tfTitulo.setColumns(10);
		
		tfAutor = new JTextField();
		tfAutor.setBounds(101, 150, 214, 20);
		panel.add(tfAutor);
		tfAutor.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(33, 193, 46, 14);
		panel.add(lblEstado);
		
		JCheckBox cbEstado = new JCheckBox("Original");
		cbEstado.setBounds(101, 190, 97, 23);
		panel.add(cbEstado);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(33, 233, 46, 14);
		panel.add(lblAno);
		
		tfAno = new JTextField();
		tfAno.setBounds(101, 230, 100, 20);
		panel.add(tfAno);
		tfAno.setColumns(10);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String isbn, titulo, autor, ano;
				Boolean estado;
				
				isbn = tfIsbn.getText();
				titulo = tfTitulo.getText();
				autor = tfAutor.getText();
				estado = cbEstado.isSelected();
				ano = tfAno.getText();
				
				if(isbn.equals("") && titulo.equals("") && autor.equals("") && ano.equals("")){
					JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
				}
				else{
					
					AdmController userCtrl = new AdmController();
					LivroVO livroVO = new LivroVO();
					
					
					try{
						String imagem = salvaImagem();
						
						livroVO.setIsbn(isbn);
						livroVO.setTitulo(titulo);
						livroVO.setAutor(autor);
						livroVO.setEstado(estado);
						livroVO.setAno(ano);
						livroVO.setImagem(imagem);
						
						userCtrl.cadastrarLivro(livroVO);
						JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
					}
					catch(Exception e){
						e.getMessage();
					}
					
					tfIsbn.setText("");
					tfTitulo.setText("");
					tfAutor.setText("");
					cbEstado.setSelected(false);
					tfAno.setText("");
					tfFoto.setText("");
					
				}
				
			}
		});
		btnEnviar.setBounds(177, 322, 90, 37);
		panel.add(btnEnviar);
		
		tfFoto = new JTextField();
		tfFoto.setEditable(false);
		tfFoto.setColumns(10);
		tfFoto.setBounds(101, 270, 140, 20);
		panel.add(tfFoto);
		
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(33, 273, 46, 14);
		panel.add(lblFoto);
		
		
	 	
		JButton btnInserirArquivo = new JButton("Arquivo");
		btnInserirArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {  
		        
		        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)  
		            return;
		        
		        tfFoto.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		});
		btnInserirArquivo.setBounds(251, 269, 97, 23);
		panel.add(btnInserirArquivo);
	}
	
	
	
	public String salvaImagem() throws Exception{
		
		if(tfFoto.getText().equals("")){
			return "";
		}
		
		Date data = new Date(); 
		
		String home = System.getProperty("user.home");
		home += File.separator+"Documents"+File.separator+"BiblioCeos";
		
		File diretorio = new File(home);
		
		diretorio.mkdirs();
		
        
        String pathOrigem = chooser.getSelectedFile().getAbsolutePath();
        String nomeImagem = String.valueOf(data.getTime());
        String pathDestinoCopiar = home+File.separator+nomeImagem+".jpg";
        File origem = new File(pathOrigem);
        File destino = new File(pathDestinoCopiar);
        
        
        FileInputStream fisOrigem = new FileInputStream(origem); 
        FileOutputStream fisDestino = new FileOutputStream(destino); 
        FileChannel fcOrigem = fisOrigem.getChannel();   
        FileChannel fcDestino = fisDestino.getChannel();   
        fcOrigem.transferTo(0, fcOrigem.size(), fcDestino);   
        fisOrigem.close();   
        fisDestino.close();
        
        return nomeImagem;
		
	}
	
	public static void main(String[] args){
		new CadastroLivro();
	}
}
