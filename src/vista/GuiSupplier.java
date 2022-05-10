package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GuiSupplier extends JFrame implements Gui{

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
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
