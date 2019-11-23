package projetoexpo;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MaisOpcoes extends TelaPrincipal {

	private JPanel contentPane;
	private JTextField txtScaneia;
	private static Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaisOpcoes frame = new MaisOpcoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public MaisOpcoes() {
		
		setTitle("Mais Opções");
		
	    Toolkit.getDefaultToolkit().addAWTEventListener(
	            new AWTEventListener() {
	              @Override
	              public void eventDispatched(AWTEvent event) {
	                Object source = event.getSource();
	                if (source instanceof Component) {
	                  Component comp = (Component) source;
	                  Window win = null;
	                  if (comp instanceof Window) {
	                    win = (Window) comp;
	                  } else {
	                    win = SwingUtilities.windowForComponent(comp);
	                  }
	                  if (win == win) {
	                    timer.restart();
	                    }
	                }
	              }
	            },
	            AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK
	                | AWTEvent.MOUSE_MOTION_EVENT_MASK
	                | AWTEvent.MOUSE_WHEEL_EVENT_MASK);

	        timer = new Timer(5000, new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	            dispose();
	          }
	        });
	        timer.start();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 371, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCadastrar = new JButton("");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codigo = "";
				JFrame cadastro = new Cadastro(codigo,false);
				cadastro.setVisible(true);
				dispose();
				
			}
		});
		btnCadastrar.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/Cadastrar.jpg")));
		btnCadastrar.setBounds(21, 22, 133, 56);
		contentPane.add(btnCadastrar);
		
		JButton btnDesativar = new JButton("");
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame desativa = new DesativaProduto();
				desativa.setVisible(true);
				dispose();
			}
		});
		btnDesativar.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/Desativar.jpg")));
		btnDesativar.setBounds(21, 103, 133, 56);
		contentPane.add(btnDesativar);
		
		JButton btnCancelarProduto = new JButton("");
		btnCancelarProduto.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/CancelarProduto.jpg")));
		btnCancelarProduto.setBounds(21, 183, 133, 56);
		contentPane.add(btnCancelarProduto);
		
		JButton btnAlterarPreco = new JButton("");
		btnAlterarPreco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame alterar = new AlteraPreco();
				alterar.setVisible(true);
				dispose();
			}
		});
		btnAlterarPreco.setIcon(new ImageIcon(MaisOpcoes.class.getResource("/ImageLib/AlterarPreco.jpg")));
		btnAlterarPreco.setBounds(21, 262, 133, 56);
		contentPane.add(btnAlterarPreco);
		
		txtScaneia = new JTextField();
		txtScaneia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtScaneia.getText().equalsIgnoreCase("04")) {
					btnCadastrar.doClick();
				}
				
				else if (txtScaneia.getText().equalsIgnoreCase("05")) {
					btnDesativar.doClick();
				}
				
				else if (txtScaneia.getText().equalsIgnoreCase("06")) {
					btnCancelarProduto.doClick();
				}
				
				else if (txtScaneia.getText().equalsIgnoreCase("07")) {
					btnAlterarPreco.doClick();
				}
				
			}
		});
		txtScaneia.setBounds(331, 22, 14, 20);
		contentPane.add(txtScaneia);
		txtScaneia.setColumns(10);
		txtScaneia.setVisible(true);
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCadastrar.setBounds(185, 22, 133, 29);
		contentPane.add(lblCadastrar);
		
		JLabel lblDesativar = new JLabel("Desativar");
		lblDesativar.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDesativar.setBounds(185, 103, 133, 29);
		contentPane.add(lblDesativar);
		
		JLabel lblCancelarProduto = new JLabel("Cancelar Produto");
		lblCancelarProduto.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCancelarProduto.setBounds(185, 183, 133, 29);
		contentPane.add(lblCancelarProduto);
		
		JLabel lblAlterarPreco = new JLabel("Alterar Pre\u00E7o");
		lblAlterarPreco.setFont(new Font("Arial", Font.PLAIN, 12));
		lblAlterarPreco.setBounds(185, 262, 133, 29);
		contentPane.add(lblAlterarPreco);
		
		addWindowListener( new WindowAdapter() {
			   public void windowOpened( WindowEvent e ){
			        txtScaneia.requestFocus();
			     }
			   } );

	}
}
