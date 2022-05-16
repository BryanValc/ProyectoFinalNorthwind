package vista;

import modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.UsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class GuiLogin extends JFrame {

	private JPanel contentPane;
	private JTextField caja1;
	private JPasswordField caja2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiLogin frame = new GuiLogin();
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
	public GuiLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiLogin.class.getResource("/recursosVisuales/usuario.png")));
		setResizable(false);
		setTitle("Inicio de sesi\u00F3n");
		setBounds(100, 100, 295, 547);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(153, 102, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon(GuiLogin.class.getResource("/recursosVisuales/usuario.png")));
		lblNewLabel.setBounds(10, 41, 256, 256);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Iniciar sesi\u00F3n");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(34, 11, 208, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(34, 288, 208, 32);
		contentPane.add(lblNewLabel_1_1);
		
		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				validacionString(evt,255,caja1,caja2);
			}
		});
		caja1.setToolTipText("Ingresa tu nombre de usuario");
		caja1.setFont(new Font("Tahoma", Font.BOLD, 20));
		caja1.setBounds(34, 331, 208, 32);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(34, 374, 208, 32);
		contentPane.add(lblNewLabel_1_1_1);
		
		caja2 = new JPasswordField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				jPasswordFieldKeyReleased(evt,255,caja1);
			}
		});
		caja2.setToolTipText("Ingresa tu contrase\u00F1a");
		caja2.setFont(new Font("Tahoma", Font.BOLD, 20));
		caja2.setColumns(10);
		caja2.setBounds(34, 417, 208, 32);
		contentPane.add(caja2);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}
		});
		btnNewButton.setToolTipText("Ingresar a la cuenta");
		btnNewButton.setBackground(new Color(102, 51, 204));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(34, 460, 208, 32);
		contentPane.add(btnNewButton);
	}
	
	private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (verificar()) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {             
					new GuiPrincipal().setVisible(true);
				}
			});
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		}
    } 
	
	private void validacionString(java.awt.event.KeyEvent evt, int limite, JTextField caja, JTextField siguienteCaja) {
		int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			siguienteCaja.requestFocus();
		} else if ((caja.getText().equals("") ? true
				: !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}
	
	private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt, int limite, JTextField caja) {                                            
		//        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
		//            if (verificar()) {
		//			SwingUtilities.invokeLater(new Runnable() {
		//				@Override
		//				public void run() {
		//                                    
		//					new GuiPrincipal().setVisible(true);
		//				}
		//			});
		//			setVisible(false);
		//		}else {
		//			JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
		//		}
		//           
		//        }
        
        int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			if (verificar()) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
	                                    
						new GuiPrincipal().setVisible(true);
					}
				});
				setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
			}
		} else if ((caja.getText().equals("") ? true
				: !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
        
    }
	
	public boolean verificar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			ArrayList<Usuario> listaUsuarios = usuarioDAO.buscar("SELECT * FROM Usuarios WHERE username = '"+caja1.getText()+"'");
			if (listaUsuarios.size()!=0) {
                Usuario usuario = listaUsuarios.get(0);
                return usuario.getPassword().equals(caja2.getText());
            }
		}	catch (NullPointerException e) {
            e.printStackTrace();
        }
		return false;
	}
}
