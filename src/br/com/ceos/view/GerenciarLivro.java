package br.com.ceos.view;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.awt.*;
import javax.swing.*;

import br.com.ceos.VO.LivroVO;
import br.com.ceos.controller.AdmController;

public class GerenciarLivro extends JFrame{
	
	private JTextField tfAutor;
	private JTextField tfAno;
	private JTextField tfFoto;
	JFileChooser chooser = new JFileChooser();
	
	public GerenciarLivro(){
		/*
		 IGUAL ao GerenciarUsuario, alguns campos faltando e um erro ao setar a scrollpane para Visible(true),
		 se tiverem esse erro aí, me avisem, ainda n entendi o pq, já q os dois são iguais e funciona em um
		 e no outro não.
		 */
		
		AdmController adm = new AdmController();
		
	    JPanel Panel             = new JPanel();
	    JPanel Panel2 = new JPanel();
		Panel2.setBounds(18, 156, 446, 204);
		Panel.add(Panel2);
		
		JButton Listar           = new JButton("Listar");
		JButton Adcionar         = new JButton("Adicionar");
		JButton Editar           = new JButton("Editar");
		JButton Excluir          = new JButton("Excluir");
		JTextField tfTitulo      = new JTextField();
		JTextField tfIsbn        = new JTextField();
		JLabel Título          	 = new JLabel("Título:");
		JLabel ISBN     	 	 = new JLabel("ISBN:");

		
		String [] colunas = {"ID", "ISBN", "Título"};
		Object [][] dados = { {"1", "Harry Potter"}, 
				{"2", "654321", "Senhor dos Anéis"},
				{"3", "123654", "O Hobbit"},};
		
		int scrolldh = (dados.length) * 16;
		
		ActionListener actionListar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				//Panel2.setVisible(true);
			}
		};	
		
		MouseListener adicionarLivro = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  { 
		    	Panel2.removeAll();
		    	Panel2.repaint();
				Panel2.revalidate();
		    	Panel2.setLayout(null);
				
		    	JLabel lblAutor = new JLabel("Autor:");
		    	lblAutor.setBounds(21, 9, 46, 14);
		    	Panel2.add(lblAutor);
		
		    	tfTitulo.setColumns(10);
		    	tfIsbn.setColumns(10);
		
		    	tfAutor = new JTextField();
		    	tfAutor.setBounds(82, 6, 214, 20);
		    	Panel2.add(tfAutor);
		    	tfAutor.setColumns(10);
		
		    	JLabel lblEstado = new JLabel("Estado:");
		    	lblEstado.setBounds(21, 49, 46, 14);
		    	Panel2.add(lblEstado);
		
		    	JCheckBox cbEstado = new JCheckBox("Original");
		    	cbEstado.setBounds(82, 45, 97, 23);
		    	Panel2.add(cbEstado);
		
		    	JLabel lblAno = new JLabel("Ano:");
		    	lblAno.setBounds(21, 83, 46, 14);
		    	Panel2.add(lblAno);
		
		    	tfAno = new JTextField();
		    	tfAno.setBounds(82, 80, 100, 20);
		    	Panel2.add(tfAno);
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
				
		    			if(isbn.equals("") || titulo.equals("") || autor.equals("") || ano.equals("")){
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
		    					Panel2.removeAll();
		    					Panel2.repaint();
		    					Panel2.revalidate();
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
		
		    	btnEnviar.setBounds(167, 167, 90, 37);
		    	Panel2.add(btnEnviar);
		
		    	tfFoto = new JTextField();
		    	tfFoto.setEditable(false);
		    	tfFoto.setColumns(10);
		    	tfFoto.setBounds(82, 121, 140, 20);
		    	Panel2.add(tfFoto);
		
		    	JLabel lblFoto = new JLabel("Foto:");
		    	lblFoto.setBounds(21, 124, 46, 14);
		    	Panel2.add(lblFoto);
		
		    	JButton btnInserirArquivo = new JButton("Arquivo");
		    	btnInserirArquivo.addActionListener(new ActionListener() {
		    		public void actionPerformed(ActionEvent arg0) {  
		        
		    			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)  
		    				return;
		        
		    			tfFoto.setText(chooser.getSelectedFile().getAbsolutePath());
		    		}
		    	});
		    	
		    	btnInserirArquivo.setBounds(246, 120, 97, 23);
		    	Panel2.add(btnInserirArquivo);
		    }
		};
		
		MouseListener excluirLivro = new MouseAdapter()  {  
		   public void mouseClicked(MouseEvent e)  { 
		    	Panel2.removeAll();
				Panel2.repaint();
				Panel2.revalidate();
				Panel2.setLayout(null);
				
				JLabel exclusaoLivro = new JLabel("Voc\u00EA tem certeza que deseja excluir o livro " + tfTitulo.getText() + "?");
				exclusaoLivro.setHorizontalAlignment(SwingConstants.CENTER);
				exclusaoLivro.setFont(new Font("Tahoma", Font.PLAIN, 12));
				exclusaoLivro.setBounds(21, 40, 402, 15);	
				Panel2.add(exclusaoLivro);
				
				MouseListener exclusaoBotao = new MouseAdapter()  {  
				    public void mouseClicked(MouseEvent e)  {  
				    	adm.excluirLivro(tfIsbn.getText());
						JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");
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
		Título.setBounds(40, 20, 100,30);
		Panel.add(Título);
		
		ISBN.setBounds(40, 61, 100,30);
		Panel.add(ISBN);
		
		tfTitulo.setBounds(150, 20, 214, 20);
		Panel.add(tfTitulo);
		
		tfIsbn.setBounds(150, 60, 153, 20);
		Panel.add(tfIsbn);

		Listar.setBounds(18,102,100,30);
		Panel.add(Listar);
		Listar.addActionListener(actionListar);
		Adcionar.setBounds(128,102,100,30);
		Panel.add(Adcionar);
		Adcionar.addMouseListener(adicionarLivro);
		
		Editar.setBounds(240,102,100,30);
		Panel.add(Editar);
		
		Excluir.setBounds(350,102,100,30);
		Panel.add(Excluir);
		Excluir.addMouseListener(excluirLivro);
		
		

		getContentPane().add(Panel);
		
		

		setSize(480,400);

		setTitle("Gerenciar Livro");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.ICONIFIED);
		setVisible(true);

		setResizable(false);
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
		new GerenciarLivro();
	}
}