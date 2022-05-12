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
		setTitle("Inicio de sesi\u00F3n");
		setBounds(100, 100, 295, 547);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 102, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(153, 102, 255));
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setIcon(new ImageIcon("C:\\Eclipse\\ProyectoFinalNorthwind\\archivos\\usuario.png"));
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
				jPasswordFieldKeyReleased(evt);
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
	
	private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {                                            
        if(evt.getKeyChar()==KeyEvent.VK_ENTER){
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
