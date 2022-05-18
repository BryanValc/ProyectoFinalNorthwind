package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListadoProductos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ResultSetTableModel modeloDatos = null;
	JScrollPane scrollPane;


	/**
	 * Create the frame.
	 */
	private static ListadoProductos singleObject = null;

	public static ListadoProductos getInstance() {
		if (singleObject == null) {
			singleObject = new ListadoProductos();
		}
		return singleObject;
	}

	private ListadoProductos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListadoProductos.class.getResource("/recursosVisuales/Listado.png")));
		setResizable(false);
		setTitle("Listado de productos");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 630, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 594, 415);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Listado de productos por categor\u00EDa");
		lblNewLabel.setForeground(new Color(255, 255, 204));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 446, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Actualizar tabla");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarTabla();
			}
		});
		btnNewButton.setBackground(new Color(255, 51, 51));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(466, 15, 138, 30);
		contentPane.add(btnNewButton);
		
		actualizarTabla();
	}
	
	public void actualizarTabla() {
		String sql = "SELECT * FROM \"Products by Category\";";
		
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;"
				+ "user=tablas;"
				+ "password=c1s1g7o;"
				+ "encrypt=true;trustServerCertificate=true;";
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					modeloDatos = new ResultSetTableModel("com.microsoft.sqlserver.jdbc.SQLServerDriver", url, sql);
					if(modeloDatos!=null) {
						table.setModel(modeloDatos);
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
