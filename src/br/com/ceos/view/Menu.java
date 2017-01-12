package br.com.ceos.view;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import javax.swing.*;

import br.com.ceos.model.Usuario;
import br.com.ceos.context.UsuarioContext;
import br.com.ceos.controller.LivroPerfilController;
import br.com.ceos.controller.MainController;
import br.com.ceos.model.Livro;

public class Menu extends JFrame{
	
	public Menu() {

		MainController main = new MainController();
		LivroPerfilController lpc = new LivroPerfilController(); 
		
	    int scrollh = 0;
	    int scrolldh = 0;
		
		JPanel Panel1		   = new JPanel();
		Panel1.setBackground(Color.WHITE);
			Panel1.setLayout(null);
			Panel1.setLocation (0,0);
			Panel1.setSize(625,160);
			Panel1.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVisible(true);
		
		
		JLabel logo 		   = new JLabel();

		JTextField SearchField = new JTextField(200);
		JButton textlabel1       = new JButton("Buscar");

		JPanel Panel2 = new JPanel();
		Panel2.setBackground(new Color(51, 51, 255));
		
		//---ações e objetos da parte superior da tela, padrão ---
			
		ActionListener closeLogin = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JOptionPane.showMessageDialog(null, "Até qualquer hora!");
				UsuarioContext context = UsuarioContext.getInstance();
				context.usuarioLogado = null;
				new Menu();
				dispose();
			}
		};
		
		MouseListener openSearch = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	List<Livro> lista = new ArrayList<Livro>();
				lista = main.busca(SearchField.getText());
				Panel2.removeAll();
				Panel2.revalidate();
				Panel2.repaint();
				
		        int x = 0;
		        int scrollh, scrolldh = 0;
		        
		        if (lista.size()%4 != 0){
					x = (lista.size()/4)+1;
				}
	         
				else{
					x = lista.size()/4;
				}
	         
				if (x < 5){
					scrollh = 135 * x;
				}
	        
				else{
					scrollh = 600;
					scrolldh = 135 * x;
				}
	        
				scrollPane.setBounds(130, 170, 420, scrollh);
				Panel2.setPreferredSize(new Dimension(390, scrolldh));
				for(Livro livro: lista){
					JLabel labelLivro = new JLabel();
					labelLivro.setBounds(100, 100, 0, 0);
					String foto = System.getProperty("user.home");
					foto += File.separator+"Documents"+File.separator+"BiblioCeos"+File.separator+livro.getImagem()+".jpg";
					ImageIcon img = new ImageIcon(foto);
					img.setImage(img.getImage().getScaledInstance(90, 120, 100));
					labelLivro.setIcon(img);

					MouseListener openPERFILLIVRO = new MouseAdapter()  {  
					    public void mouseClicked(MouseEvent e)  {
							String foto = System.getProperty("user.home");
							foto += File.separator+"Documents"+File.separator+"BiblioCeos"+File.separator+livro.getImagem()+".jpg";
							String dados[] = new String[2];
							dados = lpc.dadosDevolucao(livro.getIsbn());
					    	new PerfilLivro(livro, dados, foto);
					    } 
					};
					
					Panel2.add(labelLivro);
					labelLivro.addMouseListener(openPERFILLIVRO);
					scrollPane.setViewportView(Panel2);
					
				}
		    } 
		};
		
		MouseListener openPerfil = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {
		    	EventQueue.invokeLater(new Runnable() {
		    		public void run() {
		    			try {
		    				Usuario usuario = main.usuarioLogado();
		    				if (usuario == null){
		    					LoginUsuario window = new LoginUsuario();
		    					window.frame.setVisible(true);
		    				}
		    				else{
		    					new Perfil(0, 0, 0, usuario);
		    					//dispose();
		    				}
		    				dispose();
		    			} catch (Exception e) {
		    				e.printStackTrace();
		    			}
		    		}
		    	});
		    }
		};
		
		Usuario usuario = main.usuarioLogado();
		if (usuario == null){
			JButton perfillb        = new JButton("Login");
			perfillb.setBounds(525,30,70,30);
			Panel1.add(perfillb);
			perfillb.addMouseListener(openPerfil);
		}
		else{
			JButton loginbt        = new JButton("Logout");
			loginbt.setBounds(450,30,75,30);
			Panel1.add(loginbt);
			loginbt.addActionListener(closeLogin);
			JButton perfillb = new JButton("Perfil");
			perfillb.setBounds(525,30,70,30);
			Panel1.add(perfillb);
			perfillb.addMouseListener(openPerfil);
		}
		
		SearchField.setBounds(130, 130, 370, 25);
		Panel1.add(SearchField);
		
		textlabel1.setBounds(510, 130, 80, 25);
		Panel1.add(textlabel1);
		textlabel1.addMouseListener(openSearch);
		
		logo.setIcon(new ImageIcon(getClass().getResource("/br/com/ceos/Imagens/Image.png")));
		logo.setBounds(10, 10, 90, 120);
		Panel1.add(logo);
		
		//---ações e objetos da parte superior da tela, padrão ---
		
		
		
		//---tudo isso vai ser automatizado------

		List<Livro> lista = new ArrayList<Livro>();
		lista = main.primarylistLivro();
		int x = 0;

		if (lista.size()%4 != 0){
	    	x = (lista.size()/4)+1;
		}
      
		else{
			x = lista.size()/4;
		}
      
		if (x < 5){
			scrollh = 130 * x;
		}
     
		else{
			scrollh = 600;
			scrolldh = 130 * x;
		}
     
		scrollPane.setBounds(130, 170, 420, scrollh);
		Panel2.setPreferredSize(new Dimension(390, scrolldh));
	     
		for(Livro livro: lista){
			
			JLabel labelLivro = new JLabel();
			String foto = System.getProperty("user.home");
			foto += File.separator+"Documents"+File.separator+"BiblioCeos"+File.separator+livro.getImagem()+".jpg";
			ImageIcon img = new ImageIcon(foto);
			img.setImage(img.getImage().getScaledInstance(90, 120, 100));
			labelLivro.setIcon(img);
							
			MouseListener openPERFILLIVRO = new MouseAdapter()  {  
				public void mouseClicked(MouseEvent e)  {
					String foto = System.getProperty("user.home");
					foto += File.separator+"Documents"+File.separator+"BiblioCeos"+File.separator+livro.getImagem()+".jpg";
					String dados[] = new String[2];
					dados = lpc.dadosDevolucao(livro.getIsbn());
					new PerfilLivro(livro, dados, foto);
				} 
			};
			
			Panel2.add(labelLivro);
			labelLivro.addMouseListener(openPERFILLIVRO);
			scrollPane.setViewportView(Panel2);

		}

			

		//---tudo isso vai ser automatizado------
		
        scrollPane.setViewportView(Panel2);
        Panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(scrollPane);
		getContentPane().add(Panel1);



		setSize(625,850);

		setTitle("Menu");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
	}
	
	public static void main(String[] args) {

		new Menu();
		
	}		

}