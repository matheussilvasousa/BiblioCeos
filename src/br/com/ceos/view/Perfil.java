package br.com.ceos.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import br.com.ceos.controller.EmprestimoController;
import br.com.ceos.controller.UsuarioPerfilController;
import br.com.ceos.model.Emprestimo;
import br.com.ceos.model.Usuario;

public class Perfil extends JFrame{

	/**
	 * @wbp.parser.constructor
	 */
	public Perfil(int x, int y, int z, Usuario usuario) {

		EmprestimoController ec = new EmprestimoController();
		UsuarioPerfilController upc = new UsuarioPerfilController();
		
		int hinfo = 150;
		int hhist = 180;
		int hadm = 210;
		
		JPanel Panel1		   = new JPanel();
		Panel1.setBackground(Color.WHITE);
		Panel1.setLayout(null);
		Panel1.setLocation (0,0);
		Panel1.setSize(625,650);
		Panel1.setVisible(true);

		
		//---ações e objetos da parte superior da tela, padrão ---
		
		JLabel logo            = new JLabel();
		
		/*JLabel perfillb        = new JLabel("Perfil");
		perfillb.setBounds(550,30,70,30);
		Panel1.add(perfillb);*/
		
		logo.setIcon(new ImageIcon(getClass().getResource("/br/com/ceos/Imagens/Image.png")));
		logo.setBounds(10, 10, 90, 120);
		
		MouseListener openMenu = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  { 
		    	new Menu();
		    	dispose();
		    } 
		};
		
		MouseListener openGUsuario = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	new GerenciarUsuario();
		    } 
		};
		
		MouseListener openGLivro = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	new GerenciarLivro();
		    } 
		};
		
		MouseListener openDevolucao = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	//ec.devolverLivro();
		    } 
		};
		
		MouseListener openCadastroLivro = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	CadastroLivro cadastro = new CadastroLivro();
		    	cadastro.frame.setVisible(true);
		    } 
		};
		
		MouseListener openCadastroUsuario = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	CadastroUsuario cadastro = new CadastroUsuario();
		    	cadastro.frame.setVisible(true);
		    } 
		};
		
		logo.addMouseListener(openMenu);
		Panel1.add(logo);
		
		//---ações e objetos da parte superior da tela, padrão ---
		
			if(x == 1){
				int labeli = 190;
				int labelf = 265;
				hhist = hhist + (labelf-labeli) + 45;
				hadm = hadm + (labelf-labeli) + 45;
				
				JLabel textlabel       = new JLabel("Nome: " + usuario.getNome());
				JLabel textlabel1      = new JLabel("Idade: " + usuario.getIdade());
				JLabel textlabel2      = new JLabel("Cargo: " + usuario.getCargo());
				JLabel textlabel3      = new JLabel("Matrícula: " + usuario.getMatricula());

				
				textlabel.setBounds(90, labeli, 200, 20);
				textlabel1.setBounds(90, 215, 200, 20);
				textlabel2.setBounds(90, 240, 200, 20);
				textlabel3.setBounds(90, labelf, 200, 20);
				
				Panel1.add(textlabel);
				Panel1.add(textlabel1);
				Panel1.add(textlabel2);
				Panel1.add(textlabel3);
			}
		
		JLabel info            = new JLabel("Informações pessoais");
		info.setForeground(Color.WHITE);
			info.setBounds(255, hinfo, 170, 30);
			Panel1.add(info);
			JPanel panel = new JPanel();
			panel.setBackground(new Color(51, 51, 255));
			panel.setBounds(79, hinfo, 460, 30);
			Panel1.add(panel);
			
		
		MouseListener openinfo = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	new Perfil(1,0,0, usuario);
		    	dispose(); 	
		    } 
		};
		info.addMouseListener(openinfo);
		
		
			if(y == 1){
				int tam = 90;
				double multa;
				ec.calcularMultas();
				multa = ec.multaIndividual(usuario.getMatricula());
				List<Emprestimo> lista = new ArrayList<Emprestimo>();
				lista = upc.historicoUsuario(usuario.getMatricula());
				int labeli = 210;
				int labelf = 260;
				
				hadm = hadm +(labelf-labeli)+ 45;

				JLabel textlabel       = new JLabel("Últimos Livros: ");
				int counthist = 0; //countatraso = 0;
				for (Emprestimo emprestimo: lista){
					JLabel texthist        = new JLabel(emprestimo.getLivro() + ", ");
					tam = tam + 90;
					texthist.setBounds(tam, labeli, 200, 20);
					//texthist.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					counthist++;
					
					if (counthist <= 2){
						Panel1.add(texthist);
					}
					/*if(emprestimo.getDataPrevista().isAfter(emprestimo.getDataDevolucao())){
						JLabel textatraso      = new JLabel(emprestimo.getLivro() + ", ");
						texthist.setBounds(tam, labelf, 200, 20);
						countatraso++;
						if (countatraso <= 2){
							Panel1.add(textatraso);
						}
					}*/
				}
				JLabel textlabel1      = new JLabel("Deve: "+ multa +" R$");
				JLabel textlabel2      = new JLabel("Entregas Atrasadas: ");

				
				textlabel.setBounds(90, labeli, 150, 20);
				textlabel1.setBounds(90, 235, 150, 20);
				textlabel2.setBounds(90, labelf, 150, 20);

				textlabel1.setForeground(Color.RED);
				Panel1.add(textlabel);
				Panel1.add(textlabel1);
				Panel1.add(textlabel2);

			}
		
		JLabel hist            = new JLabel("Histórico");
		hist.setForeground(Color.WHITE);
			hist.setBounds(280, hhist, 120, 30);
			Panel1.add(hist);
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(new Color(51, 51, 255));
			panel_1.setBounds(79, hhist, 460, 30);
			Panel1.add(panel_1);
		
		MouseListener openhist = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	new Perfil(0,1,0, usuario);
		    	dispose();
		    	
		   
		    } 
		};
		hist.addMouseListener(openhist);
		if(usuario.getTipo() == true){
		if(z == 1){			
				
			JButton Botão1       = new JButton("Gerenciar Livros");
			JButton Botão2       = new JButton("Gerenciar Usuários");
			//JLabel Botão3       = new JLabel("Fazer Empréstimos");
			JButton Botão3       = new JButton("Devolver Livro");
			JButton Botão4       = new JButton("Adicionar Livro");
			JButton Botão5       = new JButton("Adicionar Usuário");
				
			Botão1.setBounds(90, 245, 440, 25);
			Botão1.addMouseListener(openGLivro);
			Botão2.setBounds(90, 270, 440, 25);
			Botão2.addMouseListener(openGUsuario);
			Botão3.setBounds(90, 295, 440, 25);
			Botão3.addMouseListener(openDevolucao);
			Botão4.setBounds(90, 320, 440, 25);
			Botão4.addMouseListener(openCadastroLivro);
			Botão5.setBounds(90, 345, 440, 25);
			Botão5.addMouseListener(openCadastroUsuario);
			
			Panel1.add(Botão1);
			Panel1.add(Botão2);
			Panel1.add(Botão3);
			Panel1.add(Botão4);
			Panel1.add(Botão5);
			//Panel1.add(Botão3);
		}
			
		JLabel adm             = new JLabel("Administrador");
		adm.setForeground(Color.WHITE);
		adm.setBounds(267, hadm, 120, 30);
		Panel1.add(adm);
			
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(51, 51, 255));
		panel_2.setBounds(79, hadm, 460, 30);
		Panel1.add(panel_2);
		
		MouseListener openadm = new MouseAdapter()  {  
		    public void mouseClicked(MouseEvent e)  {  
		    	new Perfil(0,0,1, usuario);
		    	dispose();
		    } 
		};

		adm.addMouseListener(openadm);
		}
		add(Panel1);
		
		getContentPane().add(Panel1);
		
		setSize(625,650);
		setTitle("Perfil");

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setResizable(false);
	}

	public Perfil(Object setVisible) {
		// TODO Auto-generated constructor stub
	}
	
}
