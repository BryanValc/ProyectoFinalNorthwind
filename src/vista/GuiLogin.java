package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import controlador.UsuarioDAO;
import modelo.Usuario;

public class GuiLogin extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField caja1;
	private JPasswordField caja2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GuiLogin frame = new GuiLogin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
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
		
		JLabel lblNewLabe2 = new JLabel("Iniciar sesi\u00F3n");
		lblNewLabe2.setForeground(Color.WHITE);
		lblNewLabe2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabe2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabe2.setBounds(34, 11, 208, 32);
		contentPane.add(lblNewLabe2);
		
		JLabel lblNewLabel3 = new JLabel("Usuario:");
		lblNewLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel3.setForeground(Color.WHITE);
		lblNewLabel3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel3.setBounds(34, 288, 208, 32);
		contentPane.add(lblNewLabel3);
		
		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				validacionString(evt,255,caja1,caja2);
			}
		});
		caja1.setToolTipText("Ingresa tu nombre de usuario");
		caja1.setFont(new Font("Tahoma", Font.BOLD, 20));
		caja1.setBounds(34, 331, 208, 32);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		JLabel lblNewLabel4 = new JLabel("Contrase\u00F1a:");
		lblNewLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel4.setForeground(Color.WHITE);
		lblNewLabel4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel4.setBounds(34, 374, 208, 32);
		contentPane.add(lblNewLabel4);
		
		caja2 = new JPasswordField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
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
		btnNewButton.addActionListener(this::jButtonActionPerformed);
		btnNewButton.setToolTipText("Ingresar a la cuenta");
		btnNewButton.setBackground(new Color(51, 102, 0));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(34, 460, 208, 32);
		contentPane.add(btnNewButton);
	}
	
	private void jButtonActionPerformed(java.awt.event.ActionEvent evt) { 
        if (verificar()) {
			SwingUtilities.invokeLater(() -> {new GuiPrincipal().setVisible(true);});
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(null, "Usuario o contrase\u00F1a incorrectos");
		}
    } 
	
	private void validacionString(java.awt.event.KeyEvent evt, int limite, JTextField caja, JTextField siguienteCaja) {
		int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			siguienteCaja.requestFocus();
		} else if ((caja.getText().equals("") || !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}
	
	private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt, int limite, JTextField caja) {                                            
        
        int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			if (verificar()) {
				SwingUtilities.invokeLater(() -> new GuiPrincipal().setVisible(true));
				setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
			}
		} else if ((caja.getText().equals("") || !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
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
			if (!listaUsuarios.isEmpty()) {
                Usuario usuario = listaUsuarios.get(0);
                return usuario.getPassword().equals(caja2.getText());
            }
		}	catch (NullPointerException e) {
            e.printStackTrace();
        }
		return false;
	}
}
