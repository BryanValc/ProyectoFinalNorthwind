package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.CategoryDAO;
import modelo.Category;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;

public class GuiCategory extends JFrame implements Gui{

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnLimpiar, btnOperacion;
	JComboBox<String> comboOperacion, comboFiltro;

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
		setTitle("Categor\u00EDas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 414, 158);
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

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 102, 102));
		btnLimpiar.setToolTipText("Limpiar el formulario");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiar.setBounds(10, 85, 89, 23);
		contentPane.add(btnLimpiar);

		comboFiltro = new JComboBox();
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro.setBackground(new Color(0, 102, 102));
		comboFiltro.setToolTipText(
				"Selecciona el tipo de busqueda, la b\u00FAsqueda amplia busca cualquier coincidencia en cualquier campo, la b\u00FAsqueda precisa busca que todos los campos coincidan");
		comboFiltro
				.setModel(new DefaultComboBoxModel(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(298, 7, 126, 22);
		contentPane.add(comboFiltro);

		comboOperacion = new JComboBox();
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setBackground(new Color(0, 102, 102));
		comboOperacion.setModel(new DefaultComboBoxModel(new String[] { "Insertar", "Modificar", "Borrar" }));
		comboOperacion.setBounds(298, 32, 126, 22);
		comboOperacion.setToolTipText("Selecciona el tipo de operaci�n");
		comboOperacion.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboOperacionActionPerformed(evt);
			}
		});
		contentPane.add(comboOperacion);

		btnOperacion = new JButton("Insertar");
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBackground(new Color(0, 102, 102));
		btnOperacion.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnOperacionActionPerformed(evt);
			}
		});
		btnOperacion.setBounds(298, 57, 89, 23);
		contentPane.add(btnOperacion);

		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setForeground(new Color(255, 255, 255));
		btnAplicar.setBackground(new Color(0, 102, 102));
		btnAplicar.setToolTipText("Aplicar los cambios realizados a la base de datos");
		btnAplicar.setBounds(298, 85, 89, 23);
		contentPane.add(btnAplicar);

		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				caja1KeyPressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				caja1KeyReleased(evt);
			}
		});
		caja1.setBounds(108, 8, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		caja1.setEditable(false);

		caja2 = new JTextField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				caja2KeyPressed(evt);
			}

			public void keyReleased(java.awt.event.KeyEvent evt) {
				caja2KeyReleased(evt);
			}
		});
		caja2.setColumns(10);
		caja2.setBounds(108, 33, 126, 20);
		contentPane.add(caja2);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(111, 61, 161, 52);
		contentPane.add(scrollPane_1);

		caja3 = new JTextArea();
		caja3.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				caja3KeyReleased(evt);
			}
		});
		scrollPane_1.setViewportView(caja3);
	}

	public Category createCategory(boolean isForDeletion) {
		Category category = null;
		if (isForDeletion) {
			category = new Category(Integer.parseInt(caja1.getText()), "", "");
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
		ArrayList<Category> comprobacion = new ArrayList<Category>();
		CategoryDAO categoryDAO = new CategoryDAO();
		Category category = null;

		switch (operacion) {
			case "Borrar":
				if (caja1.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"No se esta especificando el codigo de la categoria a eliminar");
				} else {
					category = createCategory(true);
					comprobacion = categoryDAO
							.buscar("SELECT CategoryID, CategoryName, Description FROM Categories WHERE CategoryID = '"
									+ caja1.getText() + "'");
					if (comprobacion.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se pudo encontrar la categoria a eliminar");
					} else {
						int reply = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar la categoria?",
								"Alerta!", JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							if (categoryDAO.borrarRegistro(category)) {
								JOptionPane.showMessageDialog(null, "Categoria eliminada exitosamente");
								limpiarCampos();
							} else {
								JOptionPane.showMessageDialog(null, "No se pudo eliminar la categoria");
							}
						}
					}
				}
				break;
			case "Modificar":
				if (comprobarCampos()) {
					category = createCategory(false);
					
					comprobacion = categoryDAO
							.buscar("SELECT CategoryID, CategoryName, Description FROM Categories WHERE CategoryID = '"
									+ caja1.getText() + "'");
					if (comprobacion.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se pudo encontrar la categoria a eliminar");
					} else {
						if (categoryDAO.modificarRegistro(category)) {
							JOptionPane.showMessageDialog(null, "Categoria modificada exitosamente");
						} else {
							JOptionPane.showMessageDialog(null, "No se pudo modificar la categoria");
						}
					}
				}

				break;
			case "Insertar":
				if (comprobarCampos()) {
					category = createCategory(false);
					if (categoryDAO.insertarRegistro(category)) {
						JOptionPane.showMessageDialog(null, "Categoria agregada exitosamente");
					} else {
						JOptionPane.showMessageDialog(null,
								"No se pudo agregar la categoria, quiza ya hay una con el mismo ID");
					}
				}
				break;
			default:
				break;
		}

		String sql = consulta();
		actualizarTabla(sql);

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
		scrollPane.setViewportView(table);

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
		String sql = consulta();
		actualizarTabla(sql);
	}

	private JTextField caja1;
	private JTextField caja2;
	private JTextArea caja3;
	private JScrollPane scrollPane_1;

	String op1, op2, op3;

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
		if (caja1.getText().equals("")) {
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
			JOptionPane.showMessageDialog(null, "No has introducido la descripción");
			caja3.requestFocus();
			return false;
		}
		return true;
	}

	public String consulta() {
		String sql = "SELECT CategoryID, CategoryName, Description FROM Categories ";
		setOps(comboFiltro);

		boolean primero = true;
		if (!caja1.getText().toString().equals("")) {
			if (!primero) {
				sql += op2;
			} else {
				sql += "WHERE ";
			}
			primero = false;
			sql += ("CategoryID " + op1 + " '" + op3 + caja1.getText().toString() + op3 + "'");
		}
		if (!btnOperacion.getText().contains("Modificar")) {
			if (!caja2.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("CategoryName " + op1 + " '" + op3 + caja2.getText().toString() + op3 + "'");
			}
			if (!caja3.getText().toString().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				primero = false;
				sql += ("Description " + op1 + " '" + op3 + caja3.getText().toString() + op3 + "'");
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

	private void caja2KeyPressed(java.awt.event.KeyEvent evt) {
		int code = evt.getKeyCode();
		int limite = 15;
		JTextField caja = caja2;
		if ((caja.getText().equals("") ? true
				: !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}

	private void caja1KeyReleased(java.awt.event.KeyEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

	private void caja2KeyReleased(java.awt.event.KeyEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

	private void caja3KeyReleased(java.awt.event.KeyEvent evt) {
		String sql = consulta();
		actualizarTabla(sql);
	}

}
