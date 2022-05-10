package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GuiSupplier extends JFrame implements Gui{

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
		setBounds(100, 100, 768, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("ID:");
		lblNewLabel1.setBounds(10, 11, 33, 14);
		contentPane.add(lblNewLabel1);
		
		JLabel lblNewLabel2 = new JLabel("Nombre de compa\u00F1\u00EDa:");
		lblNewLabel2.setBounds(10, 36, 115, 14);
		contentPane.add(lblNewLabel2);
		
		JLabel lblNewLabel3 = new JLabel("Nombre de contacto:");
		lblNewLabel3.setBounds(10, 61, 115, 14);
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
		caja1.setBounds(121, 8, 86, 20);
		contentPane.add(caja1);
		caja1.setColumns(10);
		
		caja2 = new JTextField();
		caja2.setColumns(10);
		caja2.setBounds(121, 33, 344, 20);
		contentPane.add(caja2);
		
		caja3 = new JTextField();
		caja3.setColumns(10);
		caja3.setBounds(121, 58, 86, 20);
		contentPane.add(caja3);
		
		caja4 = new JTextField();
		caja4.setColumns(10);
		caja4.setBounds(121, 83, 86, 20);
		contentPane.add(caja4);
		
		caja5 = new JTextField();
		caja5.setColumns(10);
		caja5.setBounds(121, 108, 86, 20);
		contentPane.add(caja5);
		
		caja6 = new JTextField();
		caja6.setColumns(10);
		caja6.setBounds(121, 133, 86, 20);
		contentPane.add(caja6);
		
		caja7 = new JTextField();
		caja7.setColumns(10);
		caja7.setBounds(121, 158, 86, 20);
		contentPane.add(caja7);
		
		caja8 = new JTextField();
		caja8.setColumns(10);
		caja8.setBounds(121, 183, 86, 20);
		contentPane.add(caja8);
		
		caja9 = new JTextField();
		caja9.setColumns(10);
		caja9.setBounds(121, 208, 86, 20);
		contentPane.add(caja9);
		
		caja10 = new JTextField();
		caja10.setColumns(10);
		caja10.setBounds(121, 233, 86, 20);
		contentPane.add(caja10);
		
		caja11 = new JTextField();
		caja11.setColumns(10);
		caja11.setBounds(121, 258, 86, 20);
		contentPane.add(caja11);
		
		caja12 = new JTextField();
		caja12.setColumns(10);
		caja12.setBounds(121, 283, 86, 20);
		contentPane.add(caja12);
	}

	@Override
	public void btnOperacionActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarTabla(String sql) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void obtenerRegistroTabla() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpiarCampos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOps(JComboBox<String> caja) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean comprobarCampos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String consulta() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void comboOperacionActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
