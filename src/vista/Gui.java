package vista;

import javax.swing.JComboBox;

public interface Gui {
	
	public void btnOperacionActionPerformed(java.awt.event.ActionEvent evt);
	
	public void actualizarTabla(String sql);

	public void obtenerRegistroTabla();
	
	public void limpiarCampos();
	
	public void setOps(JComboBox<String> caja);
	
	public boolean comprobarCampos();
	
	public String consulta();
	
	public void comboOperacionActionPerformed(java.awt.event.ActionEvent evt);
	
}
