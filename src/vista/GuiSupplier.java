package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexionBD.Conexion;
import controlador.SupplierDAO;
import modelo.Category;
import modelo.Supplier;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Font;

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
	private ResultSetTableModel modeloDatos = null;
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
		setBounds(100, 100, 1108, 481);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 255));
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel1 = new JLabel("ID:");
		lblNewLabel1.setBounds(10, 41, 33, 14);
		contentPane.add(lblNewLabel1);

		JLabel lblNewLabel2 = new JLabel("Nombre de compa\u00F1\u00EDa:");
		lblNewLabel2.setBounds(10, 66, 127, 14);
		contentPane.add(lblNewLabel2);

		JLabel lblNewLabel3 = new JLabel("Nombre de contacto:");
		lblNewLabel3.setBounds(10, 91, 127, 14);
		contentPane.add(lblNewLabel3);

		JLabel lblNewLabel4 = new JLabel("T\u00EDtulo del contacto:");
		lblNewLabel4.setBounds(10, 116, 115, 14);
		contentPane.add(lblNewLabel4);

		JLabel lblNewLabel5 = new JLabel("Direcci\u00F3n:");
		lblNewLabel5.setBounds(498, 44, 73, 14);
		contentPane.add(lblNewLabel5);

		JLabel lblNewLabel6 = new JLabel("Ciudad:");
		lblNewLabel6.setBounds(498, 69, 64, 14);
		contentPane.add(lblNewLabel6);

		JLabel lblNewLabel7 = new JLabel("Regi\u00F3n:");
		lblNewLabel7.setBounds(696, 69, 64, 14);
		contentPane.add(lblNewLabel7);

		JLabel lblNewLabel8 = new JLabel("C\u00F3digo postal:");
		lblNewLabel8.setBounds(498, 94, 89, 14);
		contentPane.add(lblNewLabel8);

		JLabel lblNewLabel9 = new JLabel("Pa\u00EDs:");
		lblNewLabel9.setBounds(696, 94, 54, 14);
		contentPane.add(lblNewLabel9);

		JLabel lblNewLabel10 = new JLabel("Tel\u00E9fono:");
		lblNewLabel10.setBounds(10, 188, 54, 14);
		contentPane.add(lblNewLabel10);

		JLabel lblNewLabel11 = new JLabel("Fax:");
		lblNewLabel11.setBounds(338, 188, 41, 14);
		contentPane.add(lblNewLabel11);

		JLabel lblNewLabel12 = new JLabel("P\u00E1gina:");
		lblNewLabel12.setBounds(20, 216, 54, 14);
		contentPane.add(lblNewLabel12);

		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionInt(evt,10,2147483647,caja1, caja2);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja1.setBounds(34, 38, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		caja1.setEditable(false);

		caja2 = new JTextField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,40,caja2, caja3);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja2.setColumns(10);
		caja2.setBounds(142, 63, 344, 20);
		contentPane.add(caja2);

		caja3 = new JTextField();
		caja3.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,30,caja3, caja4);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja3.setColumns(10);
		caja3.setBounds(142, 88, 258, 20);
		contentPane.add(caja3);

		caja4 = new JTextField();
		caja4.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,30,caja4, caja5);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja4.setColumns(10);
		caja4.setBounds(132, 113, 268, 20);
		contentPane.add(caja4);

		caja5 = new JTextField();
		caja5.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,60,caja5, caja6);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja5.setColumns(10);
		caja5.setBounds(566, 41, 504, 20);
		contentPane.add(caja5);

		caja6 = new JTextField();
		caja6.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,15,caja6, caja7);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja6.setColumns(10);
		caja6.setBounds(557, 66, 129, 20);
		contentPane.add(caja6);

		caja7 = new JTextField();
		caja7.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,15,caja7, caja8);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja7.setColumns(10);
		caja7.setBounds(741, 66, 129, 20);
		contentPane.add(caja7);

		caja8 = new JTextField();
		caja8.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,10,caja8, caja9);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja8.setColumns(10);
		caja8.setBounds(593, 91, 86, 20);
		contentPane.add(caja8);

		caja9 = new JTextField();
		caja9.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,15,caja9, caja10);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja9.setColumns(10);
		caja9.setBounds(741, 91, 129, 20);
		contentPane.add(caja9);

		caja10 = new JTextField();
		caja10.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,24,caja10, caja11);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja10.setColumns(10);
		caja10.setBounds(77, 185, 206, 20);
		contentPane.add(caja10);

		caja11 = new JTextField();
		caja11.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,24,caja11, caja12);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja11.setColumns(10);
		caja11.setBounds(387, 185, 206, 20);
		contentPane.add(caja11);

		caja12 = new JTextField();
		caja12.addKeyListener(new java.awt.event.KeyAdapter() {
			
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt,1000,caja12, caja1);
			}
			
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja12.setColumns(10);
		caja12.setBounds(77, 213, 516, 20);
		contentPane.add(caja12);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 244, 1072, 190);
		contentPane.add(scrollPane);

		table = new JTable();
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

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 51, 153));
		btnLimpiar.setBounds(615, 212, 89, 23);
		btnLimpiar.setToolTipText("Limpiar el formulario");
		
		contentPane.add(btnLimpiar);

		btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conexion cn = new Conexion(2);
				cn.getConexion();
				try {
					cn.guardar();
				} catch (SQLException ex) {
					System.out.println("No se pudieron guardar los cambios");
					ex.printStackTrace();
				}
			}
		});
		btnAplicar.setToolTipText("Aplicar los cambios realizados a la base de datos");
		btnAplicar.setForeground(new Color(255, 255, 255));
		btnAplicar.setBackground(new Color(0, 51, 153));
		btnAplicar.setBounds(981, 212, 89, 23);
		contentPane.add(btnAplicar);

		btnOperacion = new JButton("Insertar");
		btnOperacion.setToolTipText("Realizar la operaci\u00F3n indicada en este bot\u00F3n");
		btnOperacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOperacionActionPerformed(e);
			}
		});
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBackground(new Color(0, 51, 153));
		btnOperacion.setBounds(981, 168, 89, 23);
		contentPane.add(btnOperacion);

		comboOperacion = new JComboBox();
		comboOperacion.setToolTipText("Selecciona el tipo de operaci\u00F3n");
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setBackground(new Color(0, 51, 153));
		comboOperacion.setModel(new DefaultComboBoxModel(new String[] { "Insertar", "Modificar", "Borrar" }));
		comboOperacion.setBounds(931, 135, 139, 22);
		comboOperacion.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboOperacionActionPerformed(evt);
			}
		});
		contentPane.add(comboOperacion);

		comboFiltro = new JComboBox();
		comboFiltro.setToolTipText("Selecciona el tipo de busqueda, la b\u00FAsqueda amplia busca cualquier coincidencia en cualquier campo, la b\u00FAsqueda precisa busca que todos los campos coincidan");
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro.setBackground(new Color(0, 51, 153));
		comboFiltro
				.setModel(new DefaultComboBoxModel(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(931, 87, 139, 22);
		contentPane.add(comboFiltro);
		
		JLabel lblNewLabel = new JLabel("Datos generales");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(142, 11, 165, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblUbicacinFsica = new JLabel("Ubicaci\u00F3n f\u00EDsica");
		lblUbicacinFsica.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUbicacinFsica.setBounds(692, 11, 190, 23);
		contentPane.add(lblUbicacinFsica);
		
		JLabel lblContactoElectrnico = new JLabel("Contacto electr\u00F3nico");
		lblContactoElectrnico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContactoElectrnico.setBounds(200, 154, 206, 23);
		contentPane.add(lblContactoElectrnico);

		actualizarTabla("SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Direccion, City AS Ciudad, Region AS Region, PostalCode AS CP, Country AS Pais, Phone AS Telefono, Fax, HomePage AS Pagina FROM Suppliers ");
	}

	@Override
	public void actualizarTabla(String sql) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;"
				+ "user=vistaTablas;"
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
		if (caja1.getText().equals("")&&!btnOperacion.getText().equals("Insertar")) {
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
		String sql = "SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Direccion, City AS Ciudad, Region AS Region, PostalCode AS CP, Country AS Pais, Phone AS Telefono, Fax, HomePage AS Pagina FROM Suppliers ";
		setOps(comboFiltro);

		boolean primero = true;
		if (!caja1.getText().toString().equals("")) {
			if (!primero) {
				sql += op2;
			} else {
				sql += "WHERE ";
			}
			primero = false;
			sql += ("SupplierID " + op1 + " '" + op3 + caja1.getText().toString() + op3 + "'");
		}
		if (!btnOperacion.getText().contains("Modificar")) {
			if (!caja2.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("companyName " + op1 + " '" + op3 + caja2.getText().toString() + op3 + "'");
			}
			if (!caja3.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("ContactName " + op1 + " '" + op3 + caja3.getText().toString() + op3 + "'");
			}
			if (!caja4.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("ContactTitle " + op1 + " '" + op3 + caja4.getText().toString() + op3 + "'");
			}
			if (!caja5.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Address " + op1 + " '" + op3 + caja5.getText().toString() + op3 + "'");
			}
			if (!caja6.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("City " + op1 + " '" + op3 + caja6.getText().toString() + op3 + "'");
			}
			if (!caja7.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Region " + op1 + " '" + op3 + caja7.getText().toString() + op3 + "'");
			}
			if (!caja8.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("PostalCode " + op1 + " '" + op3 + caja8.getText().toString() + op3 + "'");
			}
			if (!caja9.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Country " + op1 + " '" + op3 + caja9.getText().toString() + op3 + "'");
			}
			if (!caja10.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Phone " + op1 + " '" + op3 + caja10.getText().toString() + op3 + "'");
			}
			if (!caja11.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Fax " + op1 + " '" + op3 + caja11.getText().toString() + op3 + "'");
			}
			if (!caja12.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("HomePage " + op1 + " '" + op3 + caja12.getText().toString() + op3 + "'");
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
		caja8.setText("");
		caja9.setText("");
		caja10.setText("");
		caja11.setText("");
		caja12.setText("");

		if(!btnOperacion.getText().equals("Insertar")){
			caja1.setEditable(true);
		}
		caja2.setEditable(true);
		caja3.setEditable(true);
		caja4.setEditable(true);
		caja5.setEditable(true);
		caja6.setEditable(true);
		caja7.setEditable(true);
		caja8.setEditable(true);
		caja9.setEditable(true);
		caja10.setEditable(true);
		caja11.setEditable(true);
		caja12.setEditable(true);

		String sql = consulta();
		actualizarTabla(sql);

	}

	@Override
	public void comboOperacionActionPerformed(ActionEvent evt) {
		btnOperacion.setText("" + comboOperacion.getSelectedItem());
		if(("" + comboOperacion.getSelectedItem()).equals("Insertar")) {
			caja1.setText("");
			caja1.setEditable(false);
		}else {
			caja1.setEditable(true);
			caja1.requestFocus();
			
		}
	}
	
	public Supplier createSupplier(boolean isForDeletion) {
		Supplier supplier = null;
		if (isForDeletion) {
			supplier = new Supplier(Integer.parseInt(caja1.getText()), "", "", "", "", "", "", "", "", "", "", "");
		} else if(btnOperacion.getText().equals("Insertar")) {
			supplier = new Supplier(
					1,
					caja2.getText(),
					caja3.getText(),
					caja4.getText(),
					caja5.getText(),
					caja6.getText(),
					caja7.getText(),
					caja8.getText(),
					caja9.getText(),
					caja10.getText(),
					caja11.getText(),
					caja12.getText());
		} else {
			supplier = new Supplier(
					Integer.parseInt(caja1.getText()),
					caja2.getText(),
					caja3.getText(),
					caja4.getText(),
					caja5.getText(),
					caja6.getText(),
					caja7.getText(),
					caja8.getText(),
					caja9.getText(),
					caja10.getText(),
					caja11.getText(),
					caja12.getText());
		}
		return supplier;
	}

	@Override
	public void btnOperacionActionPerformed(ActionEvent evt) {
		String operacion = btnOperacion.getText();
		ArrayList<Supplier> comprobacion = new ArrayList<Supplier>();
		SupplierDAO supplierDAO = new SupplierDAO();
		Supplier supplier = null;

		switch (operacion) {
			case "Borrar":
				if (caja1.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"No se esta especificando el codigo del proveedor a eliminar");
				} else {
					supplier = createSupplier(true);
					comprobacion = supplierDAO
							.buscar("SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Direccion, City AS Ciudad, Region AS Region, PostalCode AS CP, Country AS Pais, Phone AS Telefono, Fax, HomePage AS Pagina FROM Suppliers WHERE SupplierID = '"
									+ caja1.getText() + "'");
					if (comprobacion.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se pudo encontrar el proveedor a eliminar");
					} else {
						int reply = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar el proveedor?",
								"Alerta!", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							if (supplierDAO.borrarRegistro(supplier)) {
								JOptionPane.showMessageDialog(null, "Proveedor eliminada exitosamente");
								limpiarCampos();
							} else {
								JOptionPane.showMessageDialog(null, "No se pudo eliminar el proveedor");
							}
						}
					}
				}
				break;
			case "Modificar":
				if (comprobarCampos()) {
					supplier = createSupplier(false);
					
					comprobacion = supplierDAO
							.buscar("SELECT SupplierID AS ID, CompanyName AS Nombre, ContactName AS Contacto, ContactTitle AS Titulo, Address AS Direccion, City AS Ciudad, Region AS Region, PostalCode AS CP, Country AS Pais, Phone AS Telefono, Fax, HomePage AS Pagina FROM Suppliers WHERE SupplierID = '"
									+ caja1.getText() + "'");
					if (comprobacion.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se pudo encontrar el proveedor a modificar");
					} else {
						if (supplierDAO.modificarRegistro(supplier)) {
							JOptionPane.showMessageDialog(null, "Proveedor modificado exitosamente");
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo modificar el proveedor");
						}
					}
				}

				break;
			case "Insertar":
				if (comprobarCampos()) {
					supplier = createSupplier(false);
					if (supplierDAO.insertarRegistro(supplier)) {
						JOptionPane.showMessageDialog(null, "Proveedor agregado exitosamente");
					} else {
						JOptionPane.showMessageDialog(null,
								"No se pudo agregar el proveedor, quiza ya hay uno con el mismo ID");
					}
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
	
	private void validacionInt(java.awt.event.KeyEvent evt, int limite, int valorMaximo,JTextField caja, JTextField siguienteCaja) {
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
			caja.setEditable(true);
			siguienteCaja.requestFocus();
		} else if ((caja.getText().equals("") ? true
				: !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}
	
}
