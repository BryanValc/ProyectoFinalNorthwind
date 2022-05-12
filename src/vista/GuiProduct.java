package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CategoryDAO;
import controlador.SupplierDAO;
import modelo.Category;
import modelo.Product;
import modelo.Supplier;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class GuiProduct extends JFrame implements Gui {

	private JPanel contentPane;
	private JTextField caja1;
	private JTextField caja2;
	private JTextField caja3;
	private JTextField caja4;
	private JTextField caja5;
	private JTextField caja6;
	private JTextField caja7;
	private JTextField caja8;
	private JTable table;
	JComboBox<String> combo1, combo2, comboFiltro, comboOperacion;
	JButton btnOperacion;

	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiProduct frame = new GuiProduct();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void llenarCombos() {
		combo1.removeAllItems();
		combo2.removeAllItems();

		ArrayList<Supplier> proveedores = new ArrayList<Supplier>();
		SupplierDAO supplierDAO = new SupplierDAO();
		proveedores = supplierDAO.buscar(
				"SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Direccion, City AS Ciudad, Region AS Region, PostalCode AS CP, Country AS Pais, Phone AS Telefono, Fax, HomePage AS Pagina FROM Suppliers");

		ArrayList<String> proveedoresStr = new ArrayList<String>();
		proveedoresStr.add(" ");
		for (Supplier supplier : proveedores) {
			proveedoresStr.add(supplier.getSupplierID() + "-" + supplier.getCompanyName());
		}

		combo1.setModel(new DefaultComboBoxModel(proveedoresStr.toArray()));

		ArrayList<Category> categorias = new ArrayList<Category>();
		CategoryDAO categoryDAO = new CategoryDAO();
		categorias = categoryDAO.buscar("SELECT CategoryID AS ID , CategoryName AS Nombre, Description AS Descripcion FROM Categories");

		ArrayList<String> categoriasStr = new ArrayList<String>();
		categoriasStr.add(" ");
		for (Category category : categorias) {
			categoriasStr.add(category.getCategoryID() + "-" + category.getCategoryName());
		}

		combo2.setModel(new DefaultComboBoxModel(categoriasStr.toArray()));

	}

	/**
	 * Create the frame.
	 */
	public GuiProduct() {
		setTitle("Formulario productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1074, 578);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboFiltro = new JComboBox();
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro.setBackground(new Color(204, 153, 0));
		comboFiltro.setToolTipText(
				"Selecciona el tipo de busqueda, la b\u00FAsqueda amplia busca cualquier coincidencia en cualquier campo, la b\u00FAsqueda precisa busca que todos los campos coincidan");
		comboFiltro
				.setModel(new DefaultComboBoxModel(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(455, 11, 138, 22);
		contentPane.add(comboFiltro);

		comboOperacion = new JComboBox();
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setBackground(new Color(204, 153, 0));
		comboOperacion.setToolTipText("Seleccione el tipo de operaci\u00F3n");
		comboOperacion.setModel(new DefaultComboBoxModel(new String[] { "Insertar", "Modificar", "Borrar" }));
		comboOperacion.setBounds(455, 44, 138, 22);
		contentPane.add(comboOperacion);

		btnOperacion = new JButton("Insertar");
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBackground(new Color(204, 153, 0));
		btnOperacion.setToolTipText("Realizar la operaci\u00F3n indicada en este bot\u00F3n");
		btnOperacion.setBounds(455, 77, 89, 23);
		contentPane.add(btnOperacion);

		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setForeground(new Color(255, 255, 255));
		btnAplicar.setBackground(new Color(204, 153, 0));
		btnAplicar.setToolTipText("Aplicar los cambios realizados a la base de datos");
		btnAplicar.setBounds(455, 107, 89, 23);
		contentPane.add(btnAplicar);

		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(204, 153, 0));
		btnLimpiar.setToolTipText("Limpiar el formulario");
		btnLimpiar.setBounds(455, 136, 89, 23);
		contentPane.add(btnLimpiar);

		JLabel lblNewLabel = new JLabel("ID producto:");
		lblNewLabel.setBounds(10, 15, 77, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 40, 77, 14);
		contentPane.add(lblNombre);

		JLabel lblIdProveedor = new JLabel("ID proveedor:");
		lblIdProveedor.setBounds(10, 65, 77, 14);
		contentPane.add(lblIdProveedor);

		JLabel lblIdCategora = new JLabel("ID categor\u00EDa:");
		lblIdCategora.setBounds(10, 90, 77, 14);
		contentPane.add(lblIdCategora);

		JLabel lblCantidadPorUnidad = new JLabel("Cantidad por unidad:");
		lblCantidadPorUnidad.setBounds(10, 115, 120, 14);
		contentPane.add(lblCantidadPorUnidad);

		JLabel lblPrecioPorUnidad = new JLabel("Precio por unidad:");
		lblPrecioPorUnidad.setBounds(10, 140, 120, 14);
		contentPane.add(lblPrecioPorUnidad);

		JLabel lblUnidadesEnExistencia = new JLabel("Unidades en existencia:");
		lblUnidadesEnExistencia.setBounds(10, 165, 138, 14);
		contentPane.add(lblUnidadesEnExistencia);

		JLabel lblUnidadesOrdenadas = new JLabel("Unidades ordenadas:");
		lblUnidadesOrdenadas.setBounds(10, 190, 138, 14);
		contentPane.add(lblUnidadesOrdenadas);

		JLabel lblNivelDePrioridad = new JLabel("Nivel de prioridad:");
		lblNivelDePrioridad.setBounds(10, 215, 138, 14);
		contentPane.add(lblNivelDePrioridad);

		JLabel lblDescontinuado = new JLabel("Descontinuado:");
		lblDescontinuado.setBounds(10, 240, 138, 14);
		contentPane.add(lblDescontinuado);

		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 10, 2147483647, caja1);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja1.setBounds(150, 11, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);

		caja2 = new JTextField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 40, caja2);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja2.setBounds(150, 36, 86, 20);
		contentPane.add(caja2);
		caja2.setColumns(10);

		combo1 = new JComboBox();
		combo1.setBounds(150, 61, 187, 22);
		/*combo1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboActionPerformed(evt);
			}
		});*/
		contentPane.add(combo1);

		combo2 = new JComboBox();
		combo2.setBounds(150, 86, 187, 22);
		/*combo2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboActionPerformed(evt);
			}
		});*/
		contentPane.add(combo2);

		AutoCompleteDecorator.decorate(combo1);
		AutoCompleteDecorator.decorate(combo2);
		llenarCombos();

		caja3 = new JTextField();
		caja3.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 20, caja3);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja3.setColumns(10);
		caja3.setBounds(150, 112, 86, 20);
		contentPane.add(caja3);

		caja4 = new JTextField();
		caja4.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionDouble(evt, 922337203685477.5807, 15, 4, caja4);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja4.setColumns(10);
		caja4.setBounds(150, 137, 86, 20);
		contentPane.add(caja4);

		caja5 = new JTextField();
		caja5.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 5, 32767, caja5);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja5.setColumns(10);
		caja5.setBounds(150, 162, 86, 20);
		contentPane.add(caja5);

		caja6 = new JTextField();
		caja6.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 5, 32767, caja6);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja6.setColumns(10);
		caja6.setBounds(150, 187, 86, 20);
		contentPane.add(caja6);

		caja7 = new JTextField();
		caja7.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 5, 32767, caja7);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja7.setColumns(10);
		caja7.setBounds(150, 212, 86, 20);
		contentPane.add(caja7);

		caja8 = new JTextField();
		caja8.addKeyListener(new java.awt.event.KeyAdapter() {

			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 5, caja8);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja8.setColumns(10);
		caja8.setBounds(150, 237, 86, 20);
		contentPane.add(caja8);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 265, 1038, 263);
		contentPane.add(scrollPane);

		table = new JTable();

		scrollPane.setViewportView(table);

		actualizarTabla(
				"SELECT ProductID AS 'ID Producto', ProductName AS Nombre, SupplierID AS 'ID Proveedor',"
						+ " CategoryID AS 'ID Categoria', QuantityPerUnit AS 'Cantidad por unidad', UnitPrice AS 'Precio unitario',"
						+ " UnitsInStock AS 'Unidades en existencia', UnitsOnOrder AS 'Unidades ordenadas', ReorderLevel AS 'Nivel de prioridad',"
						+ " Discontinued AS 'Descontinuado' FROM Products");
	}

	@Override
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
		table.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				obtenerRegistroTabla();
			}
		});
		scrollPane.setViewportView(table);

	}

	@Override
	public void obtenerRegistroTabla() {
		caja1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
		caja2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
		
		int indiceCombo1 = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
		combo1.setSelectedIndex(indiceCombo1);
		
		int indiceCombo2 = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
		combo2.setSelectedIndex(indiceCombo2);
		
		caja3.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
		caja4.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
		caja5.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
		caja6.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
		caja7.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
		caja8.setText(table.getValueAt(table.getSelectedRow(), 9).toString());

	}

	@Override
	public boolean comprobarCampos() {
		if (caja1.getText().equals("") && !btnOperacion.getText().equals("Insertar")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID del producto");
			caja1.requestFocus();
			return false;
		}
		if (caja2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el nombre del producto");
			caja2.requestFocus();
			return false;
		}
		if (combo1.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID del proveedor");
			combo1.requestFocus();
			return false;
		}
		if (combo2.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID de la categoria");
			combo2.requestFocus();
			return false;
		}
		if (caja3.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido la cantidad por unidad");
			caja3.requestFocus();
			return false;
		}
		if (caja4.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el precio unitario");
			caja4.requestFocus();
			return false;
		}
		if (caja5.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido las unidades en existencia");
			caja5.requestFocus();
			return false;
		}
		if (caja6.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido las unidades en ordenadas");
			caja6.requestFocus();
			return false;
		}
		if (caja7.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el nivel de prioridad");
			caja7.requestFocus();
			return false;
		}
		if (caja8.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el estado del producto");
			caja8.requestFocus();
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
		String sql = "SELECT ProductID AS 'ID Producto', ProductName AS Nombre, SupplierID AS 'ID Proveedor',"
				+ " CategoryID AS 'ID Categoria', QuantityPerUnit AS 'Cantidad por unidad', UnitPrice AS 'Precio unitario',"
				+ " UnitsInStock AS 'Unidades en existencia', UnitsOnOrder AS 'Unidades ordenadas', ReorderLevel AS 'Nivel de prioridad',"
				+ " Discontinued AS 'Descontinuado' FROM Products ";
		setOps(comboFiltro);

		boolean primero = true;

		if (!caja1.getText().toString().equals("")) {
			if (!primero) {
				sql += op2;
			} else {
				sql += "WHERE ";
			}
			primero = false;
			sql += ("ProductID " + op1 + " '" + op3 + caja1.getText().toString() + op3 + "'");
		}
		if (!btnOperacion.getText().contains("Modificar")) {
			if (!caja2.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("ProductName " + op1 + " '" + op3 + caja2.getText().toString().replaceAll("'", "'+CHAR(39)+'") + op3 + "'");
			}
			if (!combo1.getSelectedItem().toString().equals(" ")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				//String[] result = combo1.getSelectedItem().toString().split("-");
				sql += ("SupplierID " + op1 + " '" + op3 + combo1.getSelectedIndex() + op3 + "'");
			}
			if (!combo2.getSelectedItem().toString().equals(" ")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				//String[] result = combo2.getSelectedItem().toString().split("-");
				sql += ("CategoryID " + op1 + " '" + op3 + combo2.getSelectedIndex() + op3 + "'");
			}
			if (!caja3.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("QuantityPerUnit " + op1 + " '" + op3 + caja3.getText().toString().replaceAll("'", "'+CHAR(39)+'") + op3 + "'");
			}
			if (!caja4.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("UnitPrice " + op1 + " '" + op3 + caja4.getText().toString() + op3 + "'");
			}
			if (!caja5.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("UnitsInStock " + op1 + " '" + op3 + caja5.getText().toString() + op3 + "'");
			}
			if (!caja6.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("UnitsOnOrder " + op1 + " '" + op3 + caja6.getText().toString() + op3 + "'");
			}
			if (!caja7.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("ReorderLevel " + op1 + " '" + op3 + caja7.getText().toString() + op3 + "'");
			}
			if (!caja8.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Discontinued " + op1 + " '" + op3 + caja8.getText().toString() + op3 + "'");
			}

		}
		System.out.println(sql);
		return sql;
	}

	@Override
	public void limpiarCampos() {
		caja1.setText("");
		caja2.setText("");
		caja3.setText("");
		caja4.setText("");
		caja5.setText("");
		caja6.setText("");
		caja7.setText("");
		caja8.setText("");
		combo1.setSelectedIndex(-1);
		combo2.setSelectedIndex(-1);

		if (!btnOperacion.getText().contains("Insertar")) {
			caja1.setEditable(true);
		}
		caja2.setEditable(true);
		caja3.setEditable(true);
		caja4.setEditable(true);
		caja5.setEditable(true);
		caja6.setEditable(true);
		caja7.setEditable(true);
		caja8.setEditable(true);
		combo1.setEnabled(true);
		combo2.setEnabled(true);

		String sql = consulta();
		actualizarTabla(sql);

	}

	public Product createProduct(boolean isForDeletion) {
		Product product = null;
		if (isForDeletion) {
			product = new Product(Integer.parseInt(caja1.getText()), "", 0, 0, "", 0.0, 0, 0, 0, false);
		} else if (btnOperacion.getText().equals("Insertar")) {
			product = new Product(
					0,
					caja2.getText(),
					Integer.parseInt(combo1.getSelectedItem().toString()),
					Integer.parseInt(combo2.getSelectedItem().toString()),
					caja3.getText(),
					Double.parseDouble(caja4.getText()),
					Integer.parseInt(caja5.getText()),
					Integer.parseInt(caja6.getText()),
					Integer.parseInt(caja7.getText()),
					Boolean.parseBoolean(caja8.getText()));
		} else {
			product = new Product(
					Integer.parseInt(caja1.getText()),
					caja2.getText(),
					Integer.parseInt(combo1.getSelectedItem().toString()),
					Integer.parseInt(combo2.getSelectedItem().toString()),
					caja3.getText(),
					Double.parseDouble(caja4.getText()),
					Integer.parseInt(caja5.getText()),
					Integer.parseInt(caja6.getText()),
					Integer.parseInt(caja7.getText()),
					Boolean.parseBoolean(caja8.getText()));
		}
		return product;
	}

	@Override
	public void comboOperacionActionPerformed(ActionEvent evt) {
		btnOperacion.setText("" + comboOperacion.getSelectedItem());
		if (("" + comboOperacion.getSelectedItem()).equals("Insertar")) {
			caja1.setText("");
			caja1.setEditable(false);
		} else {
			caja1.setEditable(true);
			caja1.requestFocus();

		}
	}

	@Override
	public void btnOperacionActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub

	}

	private void cajaKeyReleased(java.awt.event.KeyEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

	public void comboActionPerformed(ActionEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

	private void validacionInt(java.awt.event.KeyEvent evt, int limite, int valorMaximo, JTextField caja) {
		int code = evt.getKeyCode();
		if (((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) && caja.getText().length() < limite) {
			try {
				int valorCaja = Integer.parseInt(caja.getText() + evt.getKeyChar());
				if (valorCaja <= valorMaximo) {
					caja.setEditable(true);
				} else {
					caja.setEditable(false);
				}
			} catch (Exception e) {
				caja.setEditable(false);
			}
		} else if (code == KeyEvent.VK_BACK_SPACE) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}

	private void validacionString(java.awt.event.KeyEvent evt, int limite, JTextField caja) {
		int code = evt.getKeyCode();
		if ((caja.getText().equals("") ? true
				: !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}

	int peCaja2 = 0;

	private void validacionDouble(java.awt.event.KeyEvent evt, double valorMaximo, int limite1, int limite2,
			JTextField caja) {
		int code = evt.getKeyCode();
		// int limite1 = 3;
		// int limite2 = 4;
		// double valorMaximo = 115.2;
		// JTextField caja = caja2;
		if (((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') && !caja.getText().contains("."))
				&& caja.getText().length() < (limite1) || (code == KeyEvent.VK_BACK_SPACE)) {
			// caja.setEditable(true);
			double valorCaja = Double.parseDouble(caja.getText() + evt.getKeyChar());
			if (valorCaja <= valorMaximo) {
				caja.setEditable(true);
			} else {
				caja.setEditable(false);
			}
		} else if ((caja.getText().length() < (limite1 + 1))
				&& (evt.getKeyChar() == '.' && !caja.getText().contains("."))) {
			peCaja2 = caja.getText().length();
			caja.setEditable(true);
		} else if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')
				&& (caja.getText().length() < (peCaja2 + limite2 + 1)) && caja.getText().contains(".")) {
			// caja.setEditable(true);
			double valorCaja = Double.parseDouble(caja.getText() + evt.getKeyChar());
			if (valorCaja <= valorMaximo) {
				caja.setEditable(true);
			} else {
				caja.setEditable(false);
			}
		} else {
			caja.setEditable(false);
		}
	}

}
