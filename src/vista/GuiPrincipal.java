package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.lowagie.text.pdf.codec.Base64.InputStream;

import conexionBD.Conexion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class GuiPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JPanel contentPane;
	private Logger logger = Logger.getLogger("Log de GuiPrincipal");
	private JasperViewer jasperView = null;


	/**
	 * Create the frame.
	 */
	public GuiPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiPrincipal.class.getResource("/recursosVisuales/northwind.png")));
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		Conexion cn = new Conexion(2);
		cn.getConexion();
		
		

		setTitle("Menu principal");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 660);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnCategory = new JButton("");
		btnCategory.setToolTipText("Gestionar categor\u00EDas");
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
		lblUsuarios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuarios.setBounds(404, 313, 256, 24);
		contentPane.add(lblUsuarios);

		JLabel lblProveedores = new JLabel("Proveedores");
		lblProveedores.setForeground(new Color(255, 255, 255));
		lblProveedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblProveedores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProveedores.setBounds(138, 313, 256, 24);
		contentPane.add(lblProveedores);

		JLabel lblProductos = new JLabel("Productos");
		lblProductos.setForeground(new Color(255, 255, 255));
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductos.setBounds(276, 11, 256, 24);
		contentPane.add(lblProductos);

		JLabel lblNewLabel = new JLabel("Categor\u00EDas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 256, 24);
		contentPane.add(lblNewLabel);
		btnCategory.setBounds(10, 46, 256, 256);
		ImageIcon iconoCategory=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/categoryy.png"));
        btnCategory.setIcon(resizeIcon(iconoCategory,btnCategory));
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
		btnProduct.setForeground(Color.WHITE);
		btnProduct.setBackground(new Color(255, 204, 102));
		btnProduct.setBounds(276, 46, 256, 256);
		ImageIcon iconoProduct=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/product.png"));
        btnProduct.setIcon(resizeIcon(iconoProduct,btnProduct));
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
		btnSupplier.setForeground(Color.WHITE);
		btnSupplier.setBackground(new Color(51, 102, 204));
		btnSupplier.setBounds(138, 349, 256, 256);
		ImageIcon iconoSupplier=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/supplier.png"));
        btnSupplier.setIcon(resizeIcon(iconoSupplier,btnSupplier));
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
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setBackground(new Color(153, 204, 51));
		btnUsuario.setBounds(404, 349, 256, 256);
		ImageIcon iconoUsuario=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/usuario.png"));
        btnUsuario.setIcon(resizeIcon(iconoUsuario,btnUsuario));
		contentPane.add(btnUsuario);

		JButton btnInventario = new JButton("");
		btnInventario.setToolTipText("Imprimir un inventario que tiene un litado de todos los productos existentes junto con los productos que est\u00E1n en camino");
		btnInventario.setBackground(new Color(153, 102, 255));
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(jasperView!=null){
						jasperView.setVisible(false);
					}
					JasperReport jaspe=(JasperReport)JRLoader.loadObject(GuiPrincipal.class.getResource("/reportes/reporteInv.jasper"));
					JasperPrint print=JasperFillManager.fillReport(jaspe, null,cn.getConexion());
					jasperView= new JasperViewer(print,false);
					jasperView.setVisible(true);
				} catch (Exception ex) {
					logger.log(Level.SEVERE,"Error al generar el reporte",ex);
				}
			}
		});
		
		btnInventario.setBounds(808, 46, 256, 256);
		ImageIcon iconoInventario=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/inventario.png"));
        btnInventario.setIcon(resizeIcon(iconoInventario,btnInventario));
		contentPane.add(btnInventario);

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
		btnListado.setToolTipText("Gestionar usuarios del sistema");
		btnListado.setForeground(Color.WHITE);
		btnListado.setBackground(new Color(255, 153, 102));
		btnListado.setBounds(542, 46, 256, 256);
		ImageIcon iconoListado=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/Listado.png"));
        btnListado.setIcon(resizeIcon(iconoListado,btnListado));
		contentPane.add(btnListado);

		JLabel lblListado = new JLabel("Listado de productos");
		lblListado.setHorizontalAlignment(SwingConstants.CENTER);
		lblListado.setForeground(Color.WHITE);
		lblListado.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblListado.setBounds(542, 11, 256, 24);
		contentPane.add(lblListado);
		
		JButton btnGraficaPastel = new JButton("");
		btnGraficaPastel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						GuiGrafica.getInstance().setVisible(true);
					}
				});
			}
		});
		btnGraficaPastel.setToolTipText("Ver una gr\u00E1fica con el stock de cada proveedor");
		btnGraficaPastel.setForeground(Color.WHITE);
		btnGraficaPastel.setBackground(new Color(204, 51, 51));
		btnGraficaPastel.setBounds(670, 349, 256, 256);
		ImageIcon iconoGraficaPastel=new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/graficaPastel.png"));
        btnGraficaPastel.setIcon(resizeIcon(iconoGraficaPastel,btnGraficaPastel));
		contentPane.add(btnGraficaPastel);
		
		JLabel lblStockDeCada = new JLabel("Stock de cada proveedor");
		lblStockDeCada.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockDeCada.setForeground(Color.WHITE);
		lblStockDeCada.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStockDeCada.setBounds(670, 313, 256, 24);
		contentPane.add(lblStockDeCada);
		
		JLabel lblImprimirInventario = new JLabel("Imprimir Inventario");
		lblImprimirInventario.setHorizontalAlignment(SwingConstants.CENTER);
		lblImprimirInventario.setForeground(Color.WHITE);
		lblImprimirInventario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblImprimirInventario.setBounds(808, 11, 256, 24);
		contentPane.add(lblImprimirInventario);
		
		JButton btnRefrescar = new JButton("");
		btnRefrescar.setForeground(Color.LIGHT_GRAY);
		btnRefrescar.setToolTipText("Se cierran todas las ventanas menos la principal");
		btnRefrescar.setIcon(new ImageIcon(GuiPrincipal.class.getResource("/recursosVisuales/refrescar.png")));
		btnRefrescar.setBackground(new Color(255, 255, 255));
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiCategory.getInstance().setVisible(false);
				GuiGrafica.getInstance().setVisible(false);
				GuiProduct.getInstance().setVisible(false);
				GuiSupplier.getInstance().setVisible(false);
				GuiUsuario.getInstance().setVisible(false);
				ListadoProductos.getInstance().setVisible(false);
				if(jasperView!=null){
					jasperView.setVisible(false);
				}
			}
		});
		btnRefrescar.setBounds(10, 411, 120, 120);
		contentPane.add(btnRefrescar);
	}
	
	private Icon resizeIcon(ImageIcon icon,JButton boton) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(boton.getWidth(), boton.getHeight(),  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
