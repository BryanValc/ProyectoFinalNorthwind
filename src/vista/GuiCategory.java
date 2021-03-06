package vista;

import java.awt.Color;
import java.awt.Toolkit;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.CategoryDAO;
import modelo.Category;

public class GuiCategory extends JFrame implements Gui{

	private JPanel contentPane;
	private JTable table;
	private ResultSetTableModel modeloDatos = null;
	private JScrollPane scrollPane;
	private JButton btnLimpiar;
	private JButton btnOperacion;
	JComboBox<String> comboOperacion;
	JComboBox<String> comboFiltro;
	
	private Logger logger = Logger.getLogger("Log de GuiCategory");

	/**
	 * Create the frame.
	 */
	private static GuiCategory singleObject = null;

	public static GuiCategory getInstance() {
		if (singleObject == null) {
			singleObject = new GuiCategory();
		}
		return singleObject;
	}

	private GuiCategory() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiCategory.class.getResource("/recursosVisuales/categoryy.png")));
		setResizable(false);
		setTitle("Formulario categor\u00EDas");
		setBounds(100, 100, 458, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 426, 158);
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
		actualizarTabla("SELECT CategoryID AS ID, CategoryName AS Nombre, Description AS Descripcion FROM Categories");

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 36, 69, 14);
		contentPane.add(lblNombre);

		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 61, 91, 14);
		contentPane.add(lblDescripcin);

		btnLimpiar = new JButton("");
		btnLimpiar.setIcon(new ImageIcon(GuiCategory.class.getResource("/recursosVisuales/escoba.png")));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 102, 102));
		btnLimpiar.setToolTipText("Limpiar el formulario");
		btnLimpiar.addActionListener(e->limpiarCampos());
		btnLimpiar.setBounds(26, 86, 30, 30);
		contentPane.add(btnLimpiar);

		comboFiltro = new JComboBox<>();
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro.setBackground(new Color(0, 102, 102));
		comboFiltro.setToolTipText(
				"Selecciona el tipo de busqueda, la b\u00FAsqueda amplia busca cualquier coincidencia en cualquier campo, la b\u00FAsqueda precisa busca que todos los campos coincidan");
		comboFiltro
		.setModel(new DefaultComboBoxModel<>(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(298, 7, 138, 22);
		contentPane.add(comboFiltro);

		comboOperacion = new JComboBox<>();
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setBackground(new Color(0, 102, 102));
		comboOperacion.setModel(new DefaultComboBoxModel<>(new String[] { "Insertar", "Modificar", "Borrar" }));
		comboOperacion.setBounds(298, 40, 138, 22);
		comboOperacion.setToolTipText("Selecciona el tipo de operaci???n");
		comboOperacion.addActionListener(evt->comboOperacionActionPerformed(evt));
		contentPane.add(comboOperacion);

		btnOperacion = new JButton("Insertar");
		btnOperacion.setToolTipText("Realizar la operaci\u00F3n indicada en este bot\u00F3n");
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBackground(new Color(0, 102, 102));
		btnOperacion.addActionListener(evt-> 
		new Thread(() -> btnOperacionActionPerformed(evt)).start()
				);
		btnOperacion.setBounds(298, 65, 89, 23);
		contentPane.add(btnOperacion);

		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				caja1KeyPressed(evt);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja1.setBounds(108, 8, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		caja1.setEditable(false);

		caja2 = new JTextField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				caja2KeyPressed(evt);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		caja2.setColumns(10);
		caja2.setBounds(108, 33, 126, 20);
		contentPane.add(caja2);

		scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(111, 61, 161, 52);
		contentPane.add(scrollPane1);

		caja3 = new JTextArea();
		caja3.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased(evt);
			}
		});
		scrollPane1.setViewportView(caja3);
		
		JLabel lblLupa = new JLabel("");
		lblLupa.setIcon(new ImageIcon(GuiCategory.class.getResource("/recursosVisuales/lupa.png")));
		lblLupa.setBounds(256, 7, 30, 30);
		contentPane.add(lblLupa);
	}

	public Category createCategory(boolean isForDeletion) {
		Category category = null;
		if (isForDeletion) {
			category = new Category(Integer.parseInt(caja1.getText()), "", "");
		} else if(btnOperacion.getText().equals("Insertar")) {
			category = new Category(
					1,
					caja2.getText(),
					caja3.getText());
		} else {
			category = new Category(
					Integer.parseInt(caja1.getText()),
					caja2.getText(),
					caja3.getText());
		}
		return category;
	}

	public void btnOperacionActionPerformed(java.awt.event.ActionEvent evt) {
		String operacion = btnOperacion.getText();
		ArrayList<Category> comprobacion;
		CategoryDAO categoryDAO = new CategoryDAO();
		Category category = null;

		switch (operacion) {
		case "Borrar":
			if (caja1.getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"No se esta especificando el codigo de la categoria a eliminar");
				return;
			}
			category = createCategory(true);
			comprobacion = categoryDAO
					.buscar("SELECT CategoryID AS ID , CategoryName AS Nombre, Description AS Descripcion FROM Categories WHERE CategoryID = '"
							+ caja1.getText() + "'");
			if (comprobacion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar la categoria a eliminar");
				return;
			}
			int reply = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar la categoria?",
					"Alerta!", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				return;
			}
			if (categoryDAO.borrarRegistro(category)) {
				JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente");
				limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoria");
			}
			break;
		case "Modificar":
			if (!comprobarCampos()) {
				return;
			}
			category = createCategory(false);

			comprobacion = categoryDAO
					.buscar("SELECT CategoryID AS ID, CategoryName AS Nombre, Description AS Descripcion FROM Categories WHERE CategoryID = '"
							+ caja1.getText() + "'");
			if (comprobacion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar la categoria a modificar");
				return;
			}
			if (categoryDAO.modificarRegistro(category)) {
				JOptionPane.showMessageDialog(null, "Categoria modificada exitosamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo modificar la categoria");
			}
			
			

			break;
		case "Insertar":
			if (!comprobarCampos()) {
				return;
			}
			category = createCategory(false);
			if (categoryDAO.insertarRegistro(category)) {
				JOptionPane.showMessageDialog(null, "Categoria agregada exitosamente");
			} else {
				JOptionPane.showMessageDialog(null,
						"No se pudo agregar la categoria, quiza ya hay una con el mismo ID");
			}
			break;
		default:
			break;
		}

		String sql = consulta();
		actualizarTabla(sql);

	}

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
			logger.log(Level.SEVERE,"Error al interrumpir la ejecuci???n principal",e);
			Thread.currentThread().interrupt();
		}

	}

	public void obtenerRegistroTabla() {
		caja1.setText("" + table.getValueAt(table.getSelectedRow(), 0));
		caja2.setText("" + table.getValueAt(table.getSelectedRow(), 1));
		caja3.setText("" + table.getValueAt(table.getSelectedRow(), 2));
	}

	public void limpiarCampos() {
		caja1.setText("");
		caja2.setText("");
		caja3.setText("");

		if(!btnOperacion.getText().equals("Insertar")){
			caja1.setEditable(true);
		}
		caja2.setEditable(true);
		caja3.setEditable(true);


		String sql = consulta();
		actualizarTabla(sql);
	}

	private JTextField caja1;
	private JTextField caja2;
	private JTextArea caja3;
	private JScrollPane scrollPane1;

	String op1;
	String op2;
	String op3;

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

	public boolean comprobarCampos() {
		if (caja1.getText().equals("")&&!btnOperacion.getText().equals("Insertar")) {
			JOptionPane.showMessageDialog(null, "No has introducido el ID");
			caja1.requestFocus();
			return false;
		}
		if (caja2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido el nombre");
			caja2.requestFocus();
			return false;
		}
		if (caja3.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No has introducido la descripci??n");
			caja3.requestFocus();
			return false;
		}
		return true;
	}

	public String consulta() {
		String sql = "SELECT CategoryID AS ID, CategoryName AS Nombre, Description AS Descripcion FROM Categories ";
		setOps(comboFiltro);

		boolean primero = true;
		if (!caja1.getText().equals("")) {
			sql += "WHERE ";
			primero = false;
			sql += ("CategoryID " + op1 + " '" + op3 + caja1.getText() + op3 + "'");
		}
		if (!btnOperacion.getText().contains("Modificar")) {
			if (!caja2.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("CategoryName " + op1 + " '" + op3 + caja2.getText() + op3 + "'");
			}
			if (!caja3.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				sql += ("Description " + op1 + " '" + op3 + caja3.getText() + op3 + "'");
			}
		}
		return sql;
	}

	public void comboOperacionActionPerformed(java.awt.event.ActionEvent evt) {
		btnOperacion.setText("" + comboOperacion.getSelectedItem());
		if(("" + comboOperacion.getSelectedItem()).equals("Insertar")) {
			caja1.setText("");
			caja1.setEditable(false);
		}else {
			caja1.setEditable(true);
			caja1.requestFocus();

		}
	}

	private void caja1KeyPressed(java.awt.event.KeyEvent evt) {
		int code = evt.getKeyCode();
		int limite = 10;
		int valorMaximo = 2147483647;
		JTextField caja = caja1;
		if ((evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') && caja.getText().length() < limite) {
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
		} else if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			caja2.requestFocus();
		} else {
			caja.setEditable(false);
		}
	}

	private void caja2KeyPressed(java.awt.event.KeyEvent evt) {
		int code = evt.getKeyCode();
		int limite = 15;
		JTextField caja = caja2;
		if ((caja.getText().equals("") || !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			caja3.requestFocus();
		} else {
			caja.setEditable(false);
		}
	}

	private void cajaKeyReleased(java.awt.event.KeyEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}
	
}
