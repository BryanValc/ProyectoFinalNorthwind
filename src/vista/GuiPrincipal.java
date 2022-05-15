package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexionBD.Conexion;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.util.JRLoader;
//import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		Conexion cn = new Conexion(2);
		cn.getConexion();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {

				int result = JOptionPane.showConfirmDialog(null,"¿Quieres aplicar los cambios?", "Cerrando programa",JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION){
					try {
						cn.guardar();
					} catch (SQLException e) {
						System.out.println("No se pudieron guardar los cambios");
						e.printStackTrace();
					}
				}else{
					try {
						cn.volver();
					} catch (SQLException e) {
						System.out.println("No se pudo regresar al estado anterior");
						e.printStackTrace();
					}
				}
			}
		});

		setTitle("Menu principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 753);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCategory = new JButton("");
		btnCategory.setToolTipText("Gestionar categor\u00EDas");
		btnCategory.setIcon(new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/categoryy.png")));
		btnCategory.setForeground(new Color(255, 255, 255));
		btnCategory.setBackground(new Color(0, 153, 153));
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						GuiCategory.getInstance().setVisible(true);
					}
				});
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
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						GuiProduct.getInstance().setVisible(true);
					}
				});
			}
		});
		btnProduct.setToolTipText("Gestionar productos");
		btnProduct.setIcon(new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/product.png")));
		btnProduct.setForeground(Color.WHITE);
		btnProduct.setBackground(new Color(255, 204, 102));
		btnProduct.setBounds(276, 46, 256, 256);
		contentPane.add(btnProduct);

		JButton btnSupplier = new JButton("");
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						GuiSupplier.getInstance().setVisible(true);
					}
				});
			}
		});
		btnSupplier.setToolTipText("Gestionar proveedores");
		btnSupplier.setIcon(new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/supplier.png")));
		btnSupplier.setForeground(Color.WHITE);
		btnSupplier.setBackground(new Color(51, 102, 204));
		btnSupplier.setBounds(10, 349, 256, 256);
		contentPane.add(btnSupplier);

		JButton btnUsuario = new JButton("");
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						GuiUsuario.getInstance().setVisible(true);
					}
				});
			}
		});
		btnUsuario.setToolTipText("Gestionar usuarios del sistema");
		btnUsuario.setIcon(new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/usuario.png")));
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setBackground(new Color(153, 204, 51));
		btnUsuario.setBounds(276, 349, 256, 256);
		contentPane.add(btnUsuario);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				//			        try {
				//			        	Conexion cn = new Conexion(2);
				//			        	//cn.getConexion();
				//			            String ruta=System.getProperty("user.dir")+"/src/vista/Leaf_Green.jasper";
				//			            JasperReport jaspe=(JasperReport)JRLoader.loadObjectFromFile(ruta);
				//			            JasperPrint print=JasperFillManager.fillReport(jaspe, null,cn.getConexion());
				//			            JasperViewer view= new JasperViewer(print,false);
				//			            view.setVisible(true);
				//			        } catch (Exception ex) {
				//			            System.err.println("Error al generar el reporte---->"+ex.getMessage());
				//			        }


			}
		});
		btnNewButton.setBounds(10, 616, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnListado = new JButton("");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						ListadoProductos.getInstance().setVisible(true);
					}
				});
			}
		});
		btnListado.setIcon(new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/Listado.png")));
		btnListado.setToolTipText("Gestionar usuarios del sistema");
		btnListado.setForeground(Color.WHITE);
		btnListado.setBackground(new Color(255, 153, 102));
		btnListado.setBounds(542, 46, 256, 256);
		contentPane.add(btnListado);

		JLabel lblListado = new JLabel("Listado de productos");
		lblListado.setHorizontalAlignment(SwingConstants.CENTER);
		lblListado.setForeground(Color.WHITE);
		lblListado.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblListado.setBounds(542, 11, 256, 24);
		contentPane.add(lblListado);
		
		JButton btnGraficaPastel = new JButton("");
		btnGraficaPastel.setToolTipText("Gestionar usuarios del sistema");
		btnGraficaPastel.setForeground(Color.WHITE);
		btnGraficaPastel.setBackground(new Color(51, 204, 153));
		btnGraficaPastel.setBounds(542, 349, 256, 256);
		contentPane.add(btnGraficaPastel);
	}
}
