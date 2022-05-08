package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

public class GuiCategory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JFormattedTextField jtf1, jtf2, jtf3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiCategory frame = new GuiCategory();
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
	public GuiCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 86, 414, 169);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		actualizarTabla("SELECT CategoryID, CategoryName, Description FROM Categories");

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 36, 46, 14);
		contentPane.add(lblNombre);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n");
		lblDescripcin.setBounds(10, 61, 91, 14);
		contentPane.add(lblDescripcin);

		jtf1 = new JFormattedTextField();
		jtf1.setBounds(111, 11, 63, 20);
		contentPane.add(jtf1);

		jtf2 = new JFormattedTextField();
		jtf2.setBounds(111, 36, 91, 20);
		contentPane.add(jtf2);

		jtf3 = new JFormattedTextField();
		jtf3.setBounds(111, 61, 136, 20);
		contentPane.add(jtf3);
	}

	public void actualizarTabla(String sql) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;"
				+ "user=asd;"
				+ "password=c1s1g7o;"
				+ "encrypt=true;trustServerCertificate=true;";
		ResultSetTableModel modeloDatos = null;
		try {
			modeloDatos = new ResultSetTableModel("com.microsoft.sqlserver.jdbc.SQLServerDriver", url, sql);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		scrollPane.getViewport().remove(table);
		table = new JTable(modeloDatos);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				obtenerRegistroTabla();
			}
		});
		scrollPane.getViewport().add(table);

	}

	public void obtenerRegistroTabla() {
		/*
		 * caja1.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),0));
		 * caja2.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),1));
		 * caja3.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),2));
		 * caja4.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),3));
		 * caja5.setText(""+jTable1.getValueAt(jTable1.getSelectedRow(),4));
		 */
		jtf1.setText("" + table.getValueAt(table.getSelectedRow(), 0));
		jtf2.setText("" + table.getValueAt(table.getSelectedRow(), 1));
		jtf3.setText("" + table.getValueAt(table.getSelectedRow(), 2));
	}

}
