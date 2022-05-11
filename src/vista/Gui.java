package vista;

import javax.swing.JComboBox;

public interface Gui {
	
	public void actualizarTabla(String sql);
	
	public void obtenerRegistroTabla();
	
	public boolean comprobarCampos();
	
	public void setOps(JComboBox<String> caja);
	
	public String consulta();
	
	public void limpiarCampos();

	public void comboOperacionActionPerformed(java.awt.event.ActionEvent evt);
	
	public void btnOperacionActionPerformed(java.awt.event.ActionEvent evt);
	
}
