package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class GuiSupplier extends JFrame implements Gui {

	private JPanel contentPane;
	private JTextField caja1;
	private JTextField caja2;
	private JTextField caja3;
	private JTextField caja4;
	private JTextField caja5;
	private JTextField caja6;
	private JTextField caja7;
	private JTextField caja8;
	private JTextField caja9;
	private JTextField caja10;
	private JTextField caja11;
	private JTextField caja12;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnLimpiar = new JButton("Limpiar");
	private JButton btnAplicar = new JButton("Aplicar");
	private JButton btnOperacion = new JButton("Aplicar");
	private JComboBox<String> comboOperacion = new JComboBox();
	private JComboBox<String> comboFiltro = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiSupplier frame = new GuiSupplier();
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
	public GuiSupplier() {
		setTitle("Gui proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1108, 551);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 255));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel1 = new JLabel("ID:");
		lblNewLabel1.setBounds(10, 11, 33, 14);
		contentPane.add(lblNewLabel1);

		JLabel lblNewLabel2 = new JLabel("Nombre de compa\u00F1\u00EDa:");
		lblNewLabel2.setBounds(10, 36, 127, 14);
		contentPane.add(lblNewLabel2);

		JLabel lblNewLabel3 = new JLabel("Nombre de contacto:");
		lblNewLabel3.setBounds(10, 61, 127, 14);
		contentPane.add(lblNewLabel3);

		JLabel lblNewLabel4 = new JLabel("T\u00EDtulo del contacto:");
		lblNewLabel4.setBounds(10, 86, 115, 14);
		contentPane.add(lblNewLabel4);

		JLabel lblNewLabel5 = new JLabel("Direcci\u00F3n:");
		lblNewLabel5.setBounds(10, 111, 115, 14);
		contentPane.add(lblNewLabel5);

		JLabel lblNewLabel6 = new JLabel("Ciudad:");
		lblNewLabel6.setBounds(10, 136, 115, 14);
		contentPane.add(lblNewLabel6);

		JLabel lblNewLabel7 = new JLabel("Regi\u00F3n:");
		lblNewLabel7.setBounds(10, 161, 115, 14);
		contentPane.add(lblNewLabel7);

		JLabel lblNewLabel8 = new JLabel("C\u00F3digo postal:");
		lblNewLabel8.setBounds(10, 186, 115, 14);
		contentPane.add(lblNewLabel8);

		JLabel lblNewLabel9 = new JLabel("Pa\u00EDs:");
		lblNewLabel9.setBounds(10, 211, 115, 14);
		contentPane.add(lblNewLabel9);

		JLabel lblNewLabel10 = new JLabel("Tel\u00E9fono:");
		lblNewLabel10.setBounds(10, 236, 115, 14);
		contentPane.add(lblNewLabel10);

		JLabel lblNewLabel11 = new JLabel("Fax:");
		lblNewLabel11.setBounds(10, 261, 115, 14);
		contentPane.add(lblNewLabel11);

		JLabel lblNewLabel12 = new JLabel("P\u00E1gina:");
		lblNewLabel12.setBounds(10, 286, 115, 14);
		contentPane.add(lblNewLabel12);

		caja1 = new JTextField();
		caja1.setBounds(138, 11, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);

		caja2 = new JTextField();
		caja2.setColumns(10);
		caja2.setBounds(138, 36, 344, 20);
		contentPane.add(caja2);

		caja3 = new JTextField();
		caja3.setColumns(10);
		caja3.setBounds(138, 61, 258, 20);
		contentPane.add(caja3);

		caja4 = new JTextField();
		caja4.setColumns(10);
		caja4.setBounds(138, 86, 268, 20);
		contentPane.add(caja4);

		caja5 = new JTextField();
		caja5.setColumns(10);
		caja5.setBounds(138, 111, 516, 20);
		contentPane.add(caja5);

		caja6 = new JTextField();
		caja6.setColumns(10);
		caja6.setBounds(138, 136, 129, 20);
		contentPane.add(caja6);

		caja7 = new JTextField();
		caja7.setColumns(10);
		caja7.setBounds(138, 161, 129, 20);
		contentPane.add(caja7);

		caja8 = new JTextField();
		caja8.setColumns(10);
		caja8.setBounds(138, 186, 86, 20);
		contentPane.add(caja8);

		caja9 = new JTextField();
		caja9.setColumns(10);
		caja9.setBounds(138, 211, 129, 20);
		contentPane.add(caja9);

		caja10 = new JTextField();
		caja10.setColumns(10);
		caja10.setBounds(138, 236, 206, 20);
		contentPane.add(caja10);

		caja11 = new JTextField();
		caja11.setColumns(10);
		caja11.setBounds(138, 261, 206, 20);
		contentPane.add(caja11);

		caja12 = new JTextField();
		caja12.setColumns(10);
		caja12.setBounds(138, 286, 516, 20);
		contentPane.add(caja12);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 1072, 190);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 51, 153));
		btnLimpiar.setBounds(680, 282, 89, 23);
		contentPane.add(btnLimpiar);

		btnAplicar = new JButton("Aplicar");
		btnAplicar.setForeground(new Color(255, 255, 255));
		btnAplicar.setBackground(new Color(0, 51, 153));
		btnAplicar.setBounds(779, 282, 89, 23);
		contentPane.add(btnAplicar);

		btnOperacion = new JButton("Aplicar");
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBackground(new Color(0, 51, 153));
		btnOperacion.setBounds(779, 257, 89, 23);
		contentPane.add(btnOperacion);

		comboOperacion = new JComboBox();
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setBackground(new Color(0, 51, 153));
		comboOperacion.setModel(new DefaultComboBoxModel(new String[] { "Insertar", "Modificar", "Borrar" }));
		comboOperacion.setBounds(779, 221, 139, 22);
		contentPane.add(comboOperacion);

		comboFiltro = new JComboBox();
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro.setBackground(new Color(0, 51, 153));
		comboFiltro
				.setModel(new DefaultComboBoxModel(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(779, 182, 139, 22);
		contentPane.add(comboFiltro);

		actualizarTabla("");
	}

	@Override
	public void actualizarTabla(String sql) {
		//sql = "SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Dirección, City AS Ciudad, Region AS Región, PostalCode AS CP, Country AS País, Phone AS Teléfono, Fax, HomePage AS Página FROM Suppliers";
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
		scrollPane.setViewportView(table);

	}

	@Override
	public void obtenerRegistroTabla() {
		caja1.setText("" + table.getValueAt(table.getSelectedRow(), 0));
		caja2.setText("" + table.getValueAt(table.getSelectedRow(), 1));
		caja3.setText("" + table.getValueAt(table.getSelectedRow(), 2));
		caja4.setText("" + table.getValueAt(table.getSelectedRow(), 3));
		caja5.setText("" + table.getValueAt(table.getSelectedRow(), 4));
		caja6.setText("" + table.getValueAt(table.getSelectedRow(), 5));
		caja7.setText("" + table.getValueAt(table.getSelectedRow(), 6));
		caja8.setText("" + table.getValueAt(table.getSelectedRow(), 7));
		caja9.setText("" + table.getValueAt(table.getSelectedRow(), 8));
		caja10.setText("" + table.getValueAt(table.getSelectedRow(), 9));
		caja11.setText("" + table.getValueAt(table.getSelectedRow(), 10));
		caja12.setText("" + table.getValueAt(table.getSelectedRow(), 11));

	}

	@Override
	public boolean comprobarCampos() {
		if (caja1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID");
			caja1.requestFocus();
			return false;
		}
		if (caja2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el nombre de la empresa");
			caja2.requestFocus();
			return false;
		}
		if (caja3.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el nombre del contacto");
			caja3.requestFocus();
			return false;
		}
		if (caja4.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el titulo del contacto");
			caja4.requestFocus();
			return false;
		}
		if (caja5.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido la dirección");
			caja5.requestFocus();
			return false;
		}
		if (caja6.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido la ciudad");
			caja6.requestFocus();
			return false;
		}
		if (caja7.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido la región");
			caja7.requestFocus();
			return false;
		}
		if (caja8.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el código postal");
			caja8.requestFocus();
			return false;
		}
		if (caja9.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el país");
			caja9.requestFocus();
			return false;
		}
		if (caja10.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el teléfono");
			caja10.requestFocus();
			return false;
		}
		if (caja11.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el fax");
			caja11.requestFocus();
			return false;
		}
		if (caja12.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido la página");
			caja12.requestFocus();
			return false;
		}
		return true;
	}

	String op1, op2, op3;

	@Override
	public void setOps(JComboBox<String> caja) {
		switch ("" + caja.getSelectedItem()) {
			case "B\u00FAsqueda precisa":
				op1 = "= ";
				op2 = " AND ";
				op3 = "";
				break;
			case "B\u00FAsqueda amplia":
				op1 = "LIKE ";
				op2 = " OR ";
				op3 = "%";
				break;
			default:
				break;
		}

	}

	@Override
	public String consulta() {
		String sql = "SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Dirección, City AS Ciudad, Region AS Región, PostalCode AS CP, Country AS País, Phone AS Teléfono, Fax, HomePage AS Página FROM Suppliers WHERE ";
		return null;
	}

	@Override
	public void limpiarCampos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void comboOperacionActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void btnOperacionActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

}
