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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class GuiCategory extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnLimpiar,btnOperacion;
	JComboBox comboOperacion, comboFiltro;

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
		btnLimpiar.setToolTipText("Limpiar el formulario");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});
		btnLimpiar.setBounds(10, 85, 89, 23);
		contentPane.add(btnLimpiar);
		
		comboFiltro = new JComboBox();
		comboFiltro.setToolTipText("Selecciona el tipo de busqueda, la b\u00FAsqueda amplia busca cualquier coincidencia en cualquier campo, la b\u00FAsqueda precisa busca que todos los campos coincidan");
		comboFiltro.setModel(new DefaultComboBoxModel(new String[] {"B\u00FAsqueda amplia", "B\u00FAsqueda precisa"}));
		comboFiltro.setBounds(298, 7, 126, 22);
		contentPane.add(comboFiltro);
		
		comboOperacion = new JComboBox();
		comboOperacion.setModel(new DefaultComboBoxModel(new String[] {"Insertar", "Modificar", "Borrar"}));
		comboOperacion.setBounds(298, 32, 126, 22);
		comboOperacion.setToolTipText("Selecciona el tipo de operaci�n");
        comboOperacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOperacionActionPerformed(evt);
            }
        });
		contentPane.add(comboOperacion);
		
		btnOperacion = new JButton("Insertar");
		btnOperacion.setBounds(298, 57, 89, 23);
		contentPane.add(btnOperacion);
		
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setToolTipText("Aplicar los cambios realizados a la base de datos");
		btnAplicar.setBounds(298, 85, 89, 23);
		contentPane.add(btnAplicar);
		
		caja1 = new JTextField();
		caja1.setBounds(108, 8, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		caja2 = new JTextField();
		caja2.setColumns(10);
		caja2.setBounds(108, 33, 126, 20);
		contentPane.add(caja2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(111, 61, 161, 52);
		contentPane.add(scrollPane_1);
		
		caja3 = new JTextArea();
		scrollPane_1.setViewportView(caja3);
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
	}
	
	String op1,op2,op3;
	private JTextField caja1;
	private JTextField caja2;
	private JTextArea caja3;
	private JScrollPane scrollPane_1;
    public void setOps(JComboBox<String> caja) {
		switch (""+caja.getSelectedItem()) {
		case "B�squeda precisa":
	            op1="= ";
	            op2=" AND ";
	            op3="";
	            break;
		case "B�squeda amplia":
	            op1="LIKE ";
	            op2=" OR ";
	            op3="%";
	            break;
		default:
			break;
		}
    }

	public String consulta() {
		String sql = "SELECT CategoryID, CategoryName, Description FROM Categories ";
		setOps(comboFiltro);
			
		boolean primero=true;
		if(!caja1.getText().equals("")) {
				if (!primero) {sql+=op2;}else {sql+="WHERE ";}
				primero=false;
				sql+=("CategoryID "+op1+" '"+op3+caja1.getText()+op3+"'");
		}
		if(!btnOperacion.getText().contains("Modificar")){
			if(!caja2.getText().equals("")) {
				if (!primero) {sql+=op2;}else {sql+="WHERE ";}
				primero=false;
				sql+=("CategoryName "+op1+" '"+op3+caja2.getText()+op3+"'");
			}
			if(!caja3.getText().equals("")) {
				if (!primero) {sql+=op2;}else {sql+="WHERE ";}
				primero=false;
				sql+=("Description "+op1+" '"+op3+caja3.getText()+op3+"'");
			}
		}
		System.out.println(sql);
		return sql;
	}
	
	private void comboOperacionActionPerformed(java.awt.event.ActionEvent evt) {                                               
        btnOperacion.setText(""+comboOperacion.getSelectedItem());
    }
}
