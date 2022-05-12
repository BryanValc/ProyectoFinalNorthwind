package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GuiPrincipal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPrincipal frame = new GuiPrincipal();
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
	public GuiPrincipal() {
		setTitle("Menu principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 562, 753);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCategory = new JButton("");
		btnCategory.setToolTipText("Gestionar categor\u00EDas");
		btnCategory.setIcon(new ImageIcon("C:\\Eclipse\\ProyectoFinalNorthwind\\archivos\\categoryy.png"));
		btnCategory.setForeground(new Color(255, 255, 255));
		btnCategory.setBackground(new Color(0, 153, 153));
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setForeground(new Color(255, 255, 255));
		lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblUsuarios.setBounds(276, 313, 256, 24);
		contentPane.add(lblUsuarios);
		
		JLabel lblProveedores = new JLabel("Proveedores");
		lblProveedores.setForeground(new Color(255, 255, 255));
		lblProveedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblProveedores.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblProveedores.setBounds(10, 313, 256, 24);
		contentPane.add(lblProveedores);
		
		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setForeground(new Color(255, 255, 255));
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblProductos.setBounds(276, 11, 256, 24);
		contentPane.add(lblProductos);
		
		JLabel lblNewLabel = new JLabel("Categor\u00EDas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 256, 24);
		contentPane.add(lblNewLabel);
		btnCategory.setBounds(10, 46, 256, 256);
		contentPane.add(btnCategory);
		
		JButton btnProduct = new JButton("");
		btnProduct.setToolTipText("Gestionar productos");
		btnProduct.setIcon(new ImageIcon("C:\\Eclipse\\ProyectoFinalNorthwind\\archivos\\product.png"));
		btnProduct.setForeground(Color.WHITE);
		btnProduct.setBackground(new Color(255, 204, 102));
		btnProduct.setBounds(276, 46, 256, 256);
		contentPane.add(btnProduct);
		
		JButton btnSupplier = new JButton("");
		btnSupplier.setToolTipText("Gestionar proveedores");
		btnSupplier.setIcon(new ImageIcon("C:\\Eclipse\\ProyectoFinalNorthwind\\archivos\\supplier.png"));
		btnSupplier.setForeground(Color.WHITE);
		btnSupplier.setBackground(new Color(51, 102, 204));
		btnSupplier.setBounds(10, 349, 256, 256);
		contentPane.add(btnSupplier);
		
		JButton btnUsuario = new JButton("");
		btnUsuario.setToolTipText("Gestionar usuarios del sistema");
		btnUsuario.setIcon(new ImageIcon("C:\\Eclipse\\ProyectoFinalNorthwind\\archivos\\usuario.png"));
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setBackground(new Color(153, 204, 51));
		btnUsuario.setBounds(276, 349, 256, 256);
		contentPane.add(btnUsuario);
	}
}
