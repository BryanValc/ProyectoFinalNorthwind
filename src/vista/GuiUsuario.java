package vista;

import java.awt.Color;
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

import controlador.UsuarioDAO;
import modelo.Usuario;



public class GuiUsuario extends JFrame implements Gui {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField caja1;
	private JTextField caja2;
	private JComboBox<String> comboFiltro;
	private JComboBox<String> comboOperacion;
	private JButton btnOperacion;
	private JButton btnLimpiar;
	private JScrollPane scrollPane;
	private JTable table;
	private ResultSetTableModel modeloDatos = null;
	
	private Logger logger = Logger.getLogger("Log de GuiUsuario");

	/**
	 * Create the frame.
	 */
	private static GuiUsuario singleObject = null;

	public static GuiUsuario getInstance() {
		if (singleObject == null) {
			singleObject = new GuiUsuario();
		}
		return singleObject;
	}

	private GuiUsuario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiUsuario.class.getResource("/recursosVisuales/usuario.png")));
		setResizable(false);
		setTitle("Formulario usuarios");
		setBounds(100, 100, 407, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 11, 66, 14);
		contentPane.add(lblUsuario);

		JLabel lblContrasena = new JLabel("Contrase\u00F1a:");
		lblContrasena.setBounds(10, 36, 89, 14);
		contentPane.add(lblContrasena);

		caja1 = new JTextField();
		caja1.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 255, caja1, caja2);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased();
			}
		});
		caja1.setBounds(86, 8, 109, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);

		caja2 = new JTextField();
		caja2.addKeyListener(new java.awt.event.KeyAdapter() {

			@Override
			public void keyPressed(java.awt.event.KeyEvent evt) {
				validacionString(evt, 255, caja2, caja1);
			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent evt) {
				cajaKeyReleased();
			}
		});
		caja2.setColumns(10);
		caja2.setBounds(86, 33, 109, 20);
		contentPane.add(caja2);

		comboFiltro = new JComboBox<>();
		comboFiltro.setToolTipText(
				"Selecciona el tipo de b\u00FAsqueda, la b\u00FAsqueda \r\namplia busca cualquier coincidencia en \r\ncualquier campo, la b\u00FAsqueda precisa \r\nbusca que todos los campos coincidan");
		comboFiltro.setBackground(new Color(51, 102, 0));
		comboFiltro.setForeground(new Color(255, 255, 255));
		comboFiltro
		.setModel(new DefaultComboBoxModel<>(new String[] { "B\u00FAsqueda amplia", "B\u00FAsqueda precisa" }));
		comboFiltro.setBounds(250, 11, 131, 22);
		contentPane.add(comboFiltro);

		comboOperacion = new JComboBox<>();
		comboOperacion.setToolTipText("Seleccione el tipo de operaci\u00F3n");
		comboOperacion.setBackground(new Color(51, 102, 0));
		comboOperacion.setForeground(new Color(255, 255, 255));
		comboOperacion.setModel(new DefaultComboBoxModel<>(new String[] { "Insertar", "Borrar", "Modificar" }));
		comboOperacion.setBounds(250, 44, 131, 22);
		comboOperacion.addActionListener(this::comboOperacionActionPerformed);
		contentPane.add(comboOperacion);

		btnOperacion = new JButton("Insertar");
		btnOperacion.addActionListener(e -> 
			new Thread(() -> btnOperacionActionPerformed(e)).start()
		);
		btnOperacion.setToolTipText("Realizar la operaci\u00F3n indicada en este bot\u00F3n");
		btnOperacion.setBackground(new Color(51, 102, 0));
		btnOperacion.setForeground(new Color(255, 255, 255));
		btnOperacion.setBounds(292, 73, 89, 23);
		contentPane.add(btnOperacion);

		btnLimpiar = new JButton("");
		btnLimpiar.setIcon(new ImageIcon(GuiUsuario.class.getResource("/recursosVisuales/escoba.png")));
		btnLimpiar.addActionListener(e -> limpiarCampos());
		btnLimpiar.setToolTipText("Limpiar el formulario");
		btnLimpiar.setBackground(new Color(51, 102, 0));
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBounds(10, 61, 30, 30);
		contentPane.add(btnLimpiar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 371, 147);
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
		
		lblLupa = new JLabel("");
		lblLupa.setIcon(new ImageIcon(GuiUsuario.class.getResource("/recursosVisuales/lupa.png")));
		lblLupa.setBounds(210, 11, 30, 30);
		contentPane.add(lblLupa);

		actualizarTabla("SELECT * FROM Usuarios");
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
			logger.log(Level.SEVERE,"Error al interrumpir la ejecucion principal",e);
		    Thread.currentThread().interrupt();
		}

	}

	@Override
	public void obtenerRegistroTabla() {
		caja1.setText("" + table.getValueAt(table.getSelectedRow(), 0));
		caja2.setText("" + table.getValueAt(table.getSelectedRow(), 1));

	}

	@Override
	public boolean comprobarCampos() {
		if (caja1.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No se ha introducido el nombre del usuario");
			caja1.requestFocus();
			return false;
		}
		if (caja2.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "No se ha introducido la contraseña");
			caja2.requestFocus();
			return false;
		}
		return true;
	}

	String op1;
	String op2;
	String op3;
	private JLabel lblLupa;

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
		String sql = "Select username AS Usuario, password AS Contrasena FROM Usuarios ";
		setOps(comboFiltro);

		boolean primero = true;
		if (!caja1.getText().equals("")) {
			sql += "WHERE ";
			primero = false;
			sql += ("username " + op1 + " '" + op3 + caja1.getText() + op3 + "'");
		}
		if (!btnOperacion.getText().contains("Modificar")&&!caja2.getText().equals("")) {
				if (!primero) {
					sql += op2;
				} else {
					sql += "WHERE ";
				}
				sql += ("password " + op1 + " '" + op3 + caja2.getText() + op3 + "'");
		}
		return sql;
	}

	@Override
	public void limpiarCampos() {
		caja1.setText("");
		caja2.setText("");

		if (!btnOperacion.getText().equals("Insertar")) {
			caja1.setEditable(true);
		}
		caja2.setEditable(true);

		String sql = consulta();
		actualizarTabla(sql);

	}

	public Usuario createUsuario(boolean isForDeletion){
		Usuario usuario = null;
		if(isForDeletion){
			usuario = new Usuario(caja1.getText(), null);
		}else{
			usuario = new Usuario(caja1.getText(), caja2.getText());
		}
		return usuario;
	}

	@Override
	public void btnOperacionActionPerformed(ActionEvent evt) {
		String operacion = btnOperacion.getText();
		ArrayList<Usuario> comprobacion;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = null;

		switch (operacion) {
		case "Borrar":
			if (caja1.getText().equals("")) {
				JOptionPane.showMessageDialog(null,"No se esta especificando el usuario a eliminar");
				return;
			}
			usuario = createUsuario(true);
			comprobacion = usuarioDAO.buscar("SELECT * FROM Usuarios WHERE username = '"+ caja1.getText() + "'");
			if (comprobacion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar el usuario a eliminar");
				return;
			}
			int reply = JOptionPane.showConfirmDialog(null, "Seguro que deseas eliminar el usuario?",
					"Alerta!", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				return;
			}
			if (usuarioDAO.borrarRegistro(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente");
				limpiarCampos();
			}else {
				JOptionPane.showMessageDialog(null, "No se pudo eliminar el usuario");
			}
			break;
		case "Modificar":
			if (!comprobarCampos()) {
				return;
			}
			usuario = createUsuario(false);
			comprobacion = usuarioDAO
					.buscar("SELECT * FROM Usuarios WHERE username = '"
							+ caja1.getText() + "'");
			if (comprobacion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "No se pudo encontrar el usuario a modificar");
				return;
			}
			if (usuarioDAO.modificarRegistro(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuario modificado exitosamente");
			} else {
				JOptionPane.showMessageDialog(null, "No se pudo modificar el usuario");
			}
			break;
		case "Insertar":
			if (!comprobarCampos()) {
				return;
			}
			usuario = createUsuario(false);
			if (usuarioDAO.insertarRegistro(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuario agregado exitosamente");
			} else {
				JOptionPane.showMessageDialog(null,
						"No se pudo agregar el usuario, quiza ya hay uno con el mismo nombre");
			}
			break;
		default:
			break;
		}

		String sql = consulta();
		actualizarTabla(sql);

	}

	private void cajaKeyReleased() {
		String sql = consulta();
		actualizarTabla(sql);
	}

	private void validacionString(java.awt.event.KeyEvent evt, int limite, JTextField caja, JTextField siguienteCaja) {
		int code = evt.getKeyCode();
		if (code == KeyEvent.VK_ENTER) {
			caja.setEditable(true);
			siguienteCaja.requestFocus();
		}else if ((caja.getText().equals("") || !(caja.getText().charAt(caja.getText().length() - 1) == ' ' && code == KeyEvent.VK_SPACE))
				&& (caja.getText().length() < limite || code == KeyEvent.VK_BACK_SPACE)) {
			caja.setEditable(true);
		} else {
			caja.setEditable(false);
		}
	}

	@Override
	public void comboOperacionActionPerformed(ActionEvent evt) {
		btnOperacion.setText("" + comboOperacion.getSelectedItem());

		String sql = consulta();
		actualizarTabla(sql);
	}

}
