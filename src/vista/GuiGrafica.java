package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class GuiGrafica extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	
	private static GuiGrafica singleObject = null;

	public static GuiGrafica getInstance() {
		if (singleObject == null) {
			singleObject = new GuiGrafica();
		}
		return singleObject;
	}

//	private static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GuiGrafica frame = new GuiGrafica();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public GuiGrafica() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiGrafica.class.getResource("/recursosVisuales/graficaPastel.png")));
		setTitle("Stock por cada proveedor");
		setResizable(false);
		setBounds(100, 100, 1050, 1050);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 204, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				establecerIcono();
			}
		});
		btnNewButton.setBackground(new Color(0, 153, 102));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(10, 5, 108, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 5, 1000, 1000);
		establecerIcono();
		contentPane.add(lblNewLabel);
	}
	
	public void establecerIcono() {
		GraficaPastel gp = new GraficaPastel();
		ImageIcon icono = gp.dibujar();
		
		if(icono!=null) {
			lblNewLabel.setIcon(icono);
		}
	}

}
