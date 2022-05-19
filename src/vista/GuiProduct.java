package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import controlador.CategoryDAO;
import controlador.ProductDAO;
import controlador.SupplierDAO;
import modelo.Category;
import modelo.Product;
import modelo.Supplier;

public class GuiProduct extends JFrame implements Gui {

	private JPanel contentPane;
	private JTextField caja1;
	private JTextField caja2;
	private JTextField caja3;
	private JTextField caja4;
	private JTextField caja5;
	private JTextField caja6;
	private JTextField caja7;
	JComboBox<String> comboDescontinuado;
	private JTable table;
	private ArrayList<Supplier> proveedores;
	private ArrayList<Category> categorias;
	private ResultSetTableModel modeloDatos = null;
	JComboBox<String> comboProveedores;
	JComboBox<String> comboCategorias;
	JComboBox<String> comboFiltro;
	JComboBox<String> comboOperacion;
	JButton btnOperacion;

	JScrollPane scrollPane;
	
	private Logger logger = Logger.getLogger("Log de GuiUsuario");

	public void llenarCombos() {
		comboProveedores.removeAllItems();
		comboCategorias.removeAllItems();

		proveedores = new ArrayList<>();
		SupplierDAO supplierDAO = new SupplierDAO();
		proveedores = supplierDAO.buscar(
				"SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Direccion, City AS Ciudad, Region AS Region, PostalCode AS CP, Country AS Pais, Phone AS Telefono, Fax, HomePage AS Pagina FROM Suppliers");

		ArrayList<String> proveedoresStr = new ArrayList<>();
		proveedoresStr.add(" ");
		for (Supplier supplier : proveedores) {
			proveedoresStr.add(/*supplier.getSupplierID() + "-" +*/ supplier.getCompanyName());
		}

		comboProveedores.setModel(new DefaultComboBoxModel(proveedoresStr.toArray()));

		categorias = new ArrayList<>();
		CategoryDAO categoryDAO = new CategoryDAO();
		categorias = categoryDAO
				.buscar("SELECT CategoryID AS ID , CategoryName AS Nombre, Description AS Descripcion FROM Categories");

		ArrayList<String> categoriasStr = new ArrayList<>();
		categoriasStr.add(" ");
		for (Category category : categorias) {
			categoriasStr.add(/*category.getCategoryID() + "-" +*/ category.getCategoryName());
		}

		comboCategorias.setModel(new DefaultComboBoxModel(categoriasStr.toArray()));

	}

	/**
	 * Create the frame.
	 */
	private static GuiProduct singleObject = null;

	public static GuiProduct getInstance() {
		if (singleObject == null) {
			singleObject = new GuiProduct();
		}
		return singleObject;
	}

	private GuiProduct() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiProduct.class.getResource("/recursosVisuales/product.png")));
		setResizable(false);
		setTitle("Formulario productos");
		setBounds(100, 100, 1074, 515);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		comboFiltro = new JComboBox<>();
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro.setBackground(new Color(204, 153, 0));
		comboFiltro.setToolTipText(
				"Selecciona el tipo de busqueda, la b\u00FAsqueda amplia busca cualquier coincidencia en cualquier campo, la b\u00FAsqueda precisa busca que todos los campos coincidan");
		comboFiltro
		.setModel(new DefaultComboBoxModel<>(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(503, 11, 138, 22);
		contentPane.add(comboFiltro);

		comboOperacion = new JComboBox<>();
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setBackground(new Color(204, 153, 0));
		comboOperacion.setToolTipText("Seleccione el tipo de operaci\u00F3n");
		comboOperacion.setModel(new DefaultComboBoxModel<>(new String[] { "Insertar", "Modificar", "Borrar" }));
		comboOperacion.setBounds(723, 135, 138, 22);
		comboOperacion.addActionListener(evt -> comboOperacionActionPerformed(evt));

		contentPane.add(comboOperacion);

		btnOperacion = new JButton("Insertar");
		btnOperacion.addActionListener(evt -> 
		new Thread(() -> btnOperacionActionPerformed(evt)).start()
				);
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBackground(new Color(204, 153, 0));
		btnOperacion.setToolTipText("Realizar la operaci\u00F3n indicada en este bot\u00F3n");
		btnOperacion.setBounds(871, 135, 89, 23);
		contentPane.add(btnOperacion);

		JButton btnLimpiar = new JButton("");
		btnLimpiar.setIcon(new ImageIcon(GuiProduct.class.getResource("/recursosVisuales/escoba.png")));
		btnLimpiar.addActionListener(e -> limpiarCampos());
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(204, 153, 0));
		btnLimpiar.setToolTipText("Limpiar el formulario");
		btnLimpiar.setBounds(10, 135, 30, 30);
		contentPane.add(btnLimpiar);

		JLabel lblNewLabel = new JLabel("ID producto:");
		lblNewLabel.setBounds(10, 44, 77, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(181, 44, 77, 14);
		contentPane.add(lblNombre);

		JLabel lblIdProveedor = new JLabel("ID proveedor:");
		lblIdProveedor.setBounds(10, 72, 77, 14);
		contentPane.add(lblIdProveedor);

		JLabel lblIdCategora = new JLabel("ID categor\u00EDa:");
		lblIdCategora.setBounds(10, 97, 77, 14);
		contentPane.add(lblIdCategora);

		JLabel lblCantidadPorUnidad = new JLabel("Cantidad por unidad:");
		lblCantidadPorUnidad.setBounds(323, 73, 120, 14);
		contentPane.add(lblCantidadPorUnidad);

		JLabel lblPrecioPorUnidad = new JLabel("Precio por unidad:");
		lblPrecioPorUnidad.setBounds(323, 98, 120, 14);
		contentPane.add(lblPrecioPorUnidad);

		JLabel lblUnidadesEnExistencia = new JLabel("Unidades en existencia:");
		lblUnidadesEnExistencia.setBounds(597, 72, 138, 14);
		contentPane.add(lblUnidadesEnExistencia);

		JLabel lblUnidadesOrdenadas = new JLabel("Unidades ordenadas:");
		lblUnidadesOrdenadas.setBounds(597, 97, 138, 14);
		contentPane.add(lblUnidadesOrdenadas);

		JLabel lblNivelDePrioridad = new JLabel("Nivel de prioridad:");
		lblNivelDePrioridad.setBounds(833, 72, 138, 14);
		contentPane.add(lblNivelDePrioridad);

		JLabel lblDescontinuado = new JLabel("Descontinuado:");
		lblDescontinuado.setBounds(833, 97, 99, 14);
		contentPane.add(lblDescontinuado);

		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 10, 2147483647, caja1, caja2);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja1.setBounds(85, 41, 86, 20);
		caja1.setEditable(false);
		contentPane.add(caja1);
		caja1.setColumns(10);

		caja2 = new JTextField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 40, caja2, caja3);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja2.setBounds(236, 41, 155, 20);
		contentPane.add(caja2);
		caja2.setColumns(10);

		comboProveedores = new JComboBox<>();
		comboProveedores.setBounds(97, 72, 205, 22);
		contentPane.add(comboProveedores);

		comboCategorias = new JComboBox<>();
		comboCategorias.setBounds(97, 97, 205, 22);
		contentPane.add(comboCategorias);

		AutoCompleteDecorator.decorate(comboProveedores);
		AutoCompleteDecorator.decorate(comboCategorias);
		llenarCombos();

		caja3 = new JTextField();
		caja3.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 20, caja3, caja4);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja3.setColumns(10);
		caja3.setBounds(443, 69, 138, 20);
		contentPane.add(caja3);

		caja4 = new JTextField();
		caja4.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionDouble(evt, 922337203685477.5807, 15, 4, caja4, caja5);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja4.setColumns(10);
		caja4.setBounds(429, 94, 86, 20);
		contentPane.add(caja4);

		caja5 = new JTextField();
		caja5.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 5, 32767, caja5, caja6);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja5.setColumns(10);
		caja5.setBounds(737, 69, 86, 20);
		contentPane.add(caja5);

		caja6 = new JTextField();
		caja6.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 5, 32767, caja6, caja7);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja6.setColumns(10);
		caja6.setBounds(737, 94, 86, 20);
		contentPane.add(caja6);

		caja7 = new JTextField();
		caja7.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt, 5, 32767, caja7, caja1);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja7.setColumns(10);
		caja7.setBounds(942, 69, 86, 20);
		contentPane.add(caja7);

		comboDescontinuado = new JComboBox();
		comboDescontinuado.setModel(new DefaultComboBoxModel(new String[] {" ","S\u00ED", "No"}));
		comboDescontinuado.setBounds(942, 94, 54, 20);
		contentPane.add(comboDescontinuado);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 183, 1038, 284);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				obtenerRegistroTabla();
			}
		});
		table.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				obtenerRegistroTabla();
			}
		});

		scrollPane.setViewportView(table);

		lblNewLabel1 = new JLabel("Detalles generales");
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel1.setBounds(161, 5, 182, 26);
		contentPane.add(lblNewLabel1);

		lblNewLabel2 = new JLabel("Disponibilidad");
		lblNewLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel2.setBounds(750, 5, 182, 26);
		contentPane.add(lblNewLabel2);
		
		lblNewLabel3 = new JLabel("");
		lblNewLabel3.setIcon(new ImageIcon(GuiProduct.class.getResource("/recursosVisuales/lupa.png")));
		lblNewLabel3.setBounds(463, 11, 30, 30);
		contentPane.add(lblNewLabel3);

		actualizarTabla(
				"SELECT ProductID AS 'ID Producto', ProductName AS Nombre, SupplierID AS 'ID Proveedor',"
						+ " CategoryID AS 'ID Categoria', QuantityPerUnit AS 'Cantidad por unidad', UnitPrice AS 'Precio unitario',"
						+ " UnitsInStock AS 'Unidades en existencia', UnitsOnOrder AS 'Unidades ordenadas', ReorderLevel AS 'Nivel de prioridad',"
						+ " Discontinued AS 'Descontinuado' FROM Products");
	}

	@Override
	public void actualizarTabla(String sql) {
		String url = "jdbc:sqlserver://dbaas-prueba.database.windows.net:1433;database=Northwind;user=asd@dbaas-prueba;password=c1s1g7o$;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            
		new Thread(() -> {
			try {
				modeloDatos = new ResultSetTableModel("com.microsoft.sqlserver.jdbc.SQLServerDriver", url, sql);
				table.setModel(modeloDatos);
			} catch (ClassNotFoundException|SQLException e1) {
				logger.log(Level.SEVERE,"Error al actualizar la tabla",e1);
			}
		}).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			logger.log(Level.SEVERE,"Error al interrumpir la ejecuci�n principal",e);
			Thread.currentThread().interrupt();
		}

	}

	@Override
	public void obtenerRegistroTabla() {
		caja1.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
		caja2.setText(table.getValueAt(table.getSelectedRow(), 1).toString());

		int indiceCombo1 = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
		for (Supplier supplier : proveedores) {
			if(supplier.getSupplierID()==indiceCombo1) {
				comboProveedores.setSelectedItem(supplier.getCompanyName());
			}
		}
		

		int indiceCombo2 = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
		for (Category category : categorias) {
			if(category.getCategoryID()==indiceCombo2) {
				comboCategorias.setSelectedItem(category.getCategoryName());
			}
		}

		caja3.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
		caja4.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
		caja5.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
		caja6.setText(table.getValueAt(table.getSelectedRow(), 7).toString());
		caja7.setText(table.getValueAt(table.getSelectedRow(), 8).toString());
		
		String descontinuado = table.getValueAt(table.getSelectedRow(), 9).toString();
		if(descontinuado.equals("true")) {
			comboDescontinuado.setSelectedItem("S\u00ED");
		}else {
			comboDescontinuado.setSelectedItem("No");
		}

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
		if (comboProveedores.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID del proveedor");
			comboProveedores.requestFocus();
			return false;
		}
		if (comboCategorias.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID de la categoria");
			comboCategorias.requestFocus();
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
		if (comboDescontinuado.getSelectedItem().toString().equals(" ")) {
			JOptionPane.showMessageDialog(null, "No has introducido el estado del producto");
			comboDescontinuado.requestFocus();
			return false;
		}
		return true;
	}

	String op1;
	String op2;
	String op3;

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

		if (!caja1.getText().equals("")) {
			sql += "WHERE ";
			primero = false;
			sql += ("ProductID " + op1 + " '" + op3 + caja1.getText() + op3 + "'");
		}
		if (!btnOperacion.getText().contains("Modificar")) {
			if (!caja2.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("ProductName " + op1 + " '" + op3 + caja2.getText().replace("'", "'+CHAR(39)+'")
						+ op3 + "'");
			}
			if (!comboProveedores.getSelectedItem().toString().equals(" ")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				int supplierID = proveedores.get(comboProveedores.getSelectedIndex()-1).getSupplierID();
				sql += ("SupplierID " + op1 + " '" + op3 + supplierID + op3 + "'");
			}
			if (!comboCategorias.getSelectedItem().toString().equals(" ")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				int categoryID = categorias.get(comboCategorias.getSelectedIndex()-1).getCategoryID();
				sql += ("CategoryID " + op1 + " '" + op3 + categoryID + op3 + "'");
			}
			if (!caja3.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("QuantityPerUnit " + op1 + " '" + op3
						+ caja3.getText().replace("'", "'+CHAR(39)+'") + op3 + "'");
			}
			if (!caja4.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("UnitPrice " + op1 + " '" + op3 + caja4.getText() + op3 + "'");
			}
			if (!caja5.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("UnitsInStock " + op1 + " '" + op3 + caja5.getText() + op3 + "'");
			}
			if (!caja6.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("UnitsOnOrder " + op1 + " '" + op3 + caja6.getText() + op3 + "'");
			}
			if (!caja7.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("ReorderLevel " + op1 + " '" + op3 + caja7.getText() + op3 + "'");
			}
			if (!comboDescontinuado.getSelectedItem().toString().equals(" ")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				String descontinuado="";
				if(comboDescontinuado.getSelectedItem().toString().equals("S\u00ED")) {
					descontinuado = "true";
				}else if (comboDescontinuado.getSelectedItem().toString().equals("No")) {
					descontinuado = "false";
				}
				sql += ("Discontinued " + op1 + " '" + op3 + descontinuado + op3 + "'");
			}

		}
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
		comboDescontinuado.setSelectedIndex(0);
		comboProveedores.setSelectedIndex(0);
		comboCategorias.setSelectedIndex(0);

		if (!btnOperacion.getText().contains("Insertar")) {
			caja1.setEditable(true);
		}
		caja2.setEditable(true);
		caja3.setEditable(true);
		caja4.setEditable(true);
		caja5.setEditable(true);
		caja6.setEditable(true);
		caja7.setEditable(true);

		String sql = consulta();
		actualizarTabla(sql);

	}

	public Product createProduct(boolean isForDeletion) {
		Product product = null;
		int supplierID = proveedores.get(comboProveedores.getSelectedIndex()-1).getSupplierID();
		int categoryID = categorias.get(comboCategorias.getSelectedIndex()-1).getCategoryID();
		
		boolean descontinuado=true;
		if(comboDescontinuado.getSelectedItem().toString().equals("S\u00ED")) {
			descontinuado = true;
		}else if (comboDescontinuado.getSelectedItem().toString().equals("No")) {
			descontinuado = false;
		}
		 
		
		if (isForDeletion) {
			product = new Product(Integer.parseInt(caja1.getText()), "", 0, 0, "", 0.0, 0, 0, 0, false);
		} else if (btnOperacion.getText().equals("Insertar")) {
			product = new Product(
					0,
					caja2.getText(),
					supplierID,
					categoryID,
					caja3.getText(),
					Double.parseDouble(caja4.getText()),
					Integer.parseInt(caja5.getText()),
					Integer.parseInt(caja6.getText()),
					Integer.parseInt(caja7.getText()),
					descontinuado);
		} else {
			product = new Product(
					Integer.parseInt(caja1.getText()),
					caja2.getText(),
					supplierID,
					categoryID,
					caja3.getText(),
					Double.parseDouble(caja4.getText()),
					Integer.parseInt(caja5.getText()),
					Integer.parseInt(caja6.getText()),
					Integer.parseInt(caja7.getText()),
					descontinuado);
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
		String operacion = btnOperacion.getText();
		ArrayList<Product> comprobacion = new ArrayList<>();
		ProductDAO productDAO = new ProductDAO();
		Product product = null;

		switch (operacion) {
		case "Borrar":
			if (caja1.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"No se esta especificando el codigo del producto a eliminar");
				return;
			}
			product = createProduct(true);
			comprobacion = productDAO
					.buscar("SELECT * FROM Products WHERE ProductID = '"
							+ caja1.getText() + "'");
			if (comprobacion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto a eliminar");
				return;
			}
			int reply = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar el producto?",
					"Alerta!", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				return;
			}
			if (productDAO.borrarRegistro(product)) {
				JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente");
				limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto");
			}
			
			
			
			break;
		case "Modificar":
			if (!comprobarCampos()) {
				return;
			}
			product = createProduct(false);

			comprobacion = productDAO
					.buscar("SELECT * FROM Products WHERE ProductID = '"
							+ caja1.getText() + "'");
			if (comprobacion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto a modificar");
				return;
			}
			if (productDAO.modificarRegistro(product)) {
				JOptionPane.showMessageDialog(null, "Producto modificado exitosamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo modificar el producto");
			}
			
			

			break;
		case "Insertar":
			if (!comprobarCampos()) {
				return;
			}
			product = createProduct(false);
			if (productDAO.insertarRegistro(product)) {
				JOptionPane.showMessageDialog(null, "Producto agregado exitosamente");
			} else {
				JOptionPane.showMessageDialog(null,
						"No se pudo agregar el producto, quiza ya hay uno con el mismo ID");
			}
			
			break;
		default:
			break;
		}

		String sql = consulta();
		actualizarTabla(sql);

	}

	private void cajaKeyReleased(java.awt.event.KeyEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

	public void comboActionPerformed(ActionEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

	private void validacionInt(java.awt.event.KeyEvent evt, int limite, int valorMaximo, JTextField caja, JTextField siguienteCaja) {
		int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			siguienteCaja.requestFocus();
		} else if (((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) && caja.getText().length() < limite) {
			try {
				int valorCaja = Integer.parseInt(caja.getText() + evt.getKeyChar());
				if (valorCaja <= valorMaximo) {
					caja.setEditable(true);
				} else {
					caja.setEditable(false);
				}
			} catch (Exception e) {
				logger.log(Level.SEVERE,"Error al convertir de String a int en el formulario",e);
				caja.setEditable(false);
			}
		} else if (code == KeyEvent.VK_BACK_SPACE) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}

	private void validacionString(java.awt.event.KeyEvent evt, int limite, JTextField caja, JTextField siguienteCaja) {
		int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			if(caja==caja7&&btnOperacion.getText().equals("Insertar")) {
				caja.setEditable(true);
				caja2.requestFocus();
			}else {
				caja.setEditable(true);
				siguienteCaja.requestFocus();
			}
		} else if ((caja.getText().equals("") || !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}

	int peCaja2 = 0;
	private JLabel lblNewLabel1;
	private JLabel lblNewLabel2;
	private JLabel lblNewLabel3;

	private void validacionDouble(java.awt.event.KeyEvent evt, double valorMaximo, int limite1, int limite2,
			JTextField caja, JTextField siguienteCaja) {
		int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			siguienteCaja.requestFocus();
		} else if (((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') && !caja.getText().contains("."))
				&& caja.getText().length() < (limite1) || (code == KeyEvent.VK_BACK_SPACE)) {
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
