package projetoexpo;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class CancelaPedido extends JFrame {

	private JPanel contentPane;
	private static Timer timer;
	private String leitura = "";
	private AWTEventListener listener;
	private int idPedido;
	private Venda venda;
	private Pedido pedido;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelaPedido frame = new CancelaPedido(0);
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
	public CancelaPedido(int idPedido) {
		Connection conn = Conexao.getConnection();
		
		this.idPedido = idPedido;
		System.out.println(idPedido + " cancela");
		setTitle("Cancela Pedido");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 295, 316);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDesejaCancelarO = new JLabel("Deseja cancelar o pedido atual?");
		lblDesejaCancelarO.setBounds(20, 21, 228, 14);
		contentPane.add(lblDesejaCancelarO);
		
		JButton btnSim = new JButton("");
		btnSim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					conn.setAutoCommit(false);
					pedido = new Pedido();
					pedido.setId(idPedido);
					pedido.carregar(conn);
					
					if (pedido.getId() != -1) {
						
//						venda = new Venda();
//						venda.deletarTodasVenda(conn, idPedido);
//						
//						
//						pedido.deletarPedido(conn, idPedido);

						pedido.exclusaoLogica(conn, idPedido);
						conn.commit();
						
						JOptionPane.showMessageDialog(null, "Pedido cancelado com sucesso");
						
					} else {
						JOptionPane.showMessageDialog(null, "Pedido n�o foi aberto");
					}
					
					
					
					Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
					dispose();
				}
				catch (SQLException erro){
					JOptionPane.showMessageDialog(null, erro + "erro1");
			         if(conn != null){
			            try{
			               conn.rollback();
			            } 
			            catch(SQLException e1){
			            	JOptionPane.showMessageDialog(null, e1 + "erro2");
			            }
			         }
			      } 
			      finally{
			         if(conn != null){
			            try{
			               conn.close();
			            } 
			            catch(SQLException e1){
			            	JOptionPane.showMessageDialog(null, e1 + "erro3");
			            }
			         }      
			      }
			}
		});
		btnSim.setIcon(new ImageIcon(CancelaPedido.class.getResource("/ImageLib/SM.gif")));
		btnSim.setBounds(20, 86, 125, 65);
		contentPane.add(btnSim);
		
		JButton btnNao = new JButton("");
		btnNao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Toolkit.getDefaultToolkit().removeAWTEventListener(listener);
				dispose();
			}
		});
		btnNao.setIcon(new ImageIcon(CancelaPedido.class.getResource("/ImageLib/NO.gif")));
		btnNao.setBounds(20, 187, 125, 65);
		contentPane.add(btnNao);
		
		JLabel lblSim = new JLabel("SIM");
		lblSim.setBounds(20, 61, 46, 14);
		contentPane.add(lblSim);
		
		JLabel lblNo = new JLabel("N\u00C3O");
		lblNo.setBounds(20, 162, 46, 14);
		contentPane.add(lblNo);
		
		listener = new AWTEventListener() {
			  public void eventDispatched(AWTEvent event) {
			    try {
			      KeyEvent evt = (KeyEvent)event;
			      if(evt.getID() == KeyEvent.KEY_PRESSED) {
				      System.out.println("Tecla: "+evt.getKeyChar());
				      leitura += evt.getKeyChar();
				      if(leitura.length()==2) {
//				    	  lblCode.setText(leitura);
				    	  if(leitura.equals("SM")) {
				    		  System.out.println("Sim");
				    		  btnSim.doClick();
				    	  }else if(leitura.equals("NO")) {
				    		  System.out.println("nao");
				    		  btnNao.doClick();
				    	  }
				    	  
				    	  leitura = "";
				      }
				      
			      }
			     
			    }
			    catch(Exception e) {
			      e.printStackTrace();
			    }
			  }
			};
			
			Toolkit.getDefaultToolkit().addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);
			
	}
}
