package vista;

import conexionBD.Conexion;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class GraficaPastel {
	
	Conexion cn = new Conexion(2);
	
	public ImageIcon dibujar() {
		ImageIcon icono = null;
		try {
			cn.getConexion();
			String sql = "EXEC [dbo].[sp_Suppliers_Stock]";
			
			ResultSet rs=cn.ejecutarConsulta(sql);
			DefaultPieDataset dataset = new DefaultPieDataset();
			DecimalFormat formateador = new DecimalFormat("####.##");
			try {
				while(rs.next()) {
					dataset.setValue(rs.getString("Proveedor")+"="+formateador.format(rs.getDouble("Valor en stock")), Float.parseFloat(rs.getString("Valor en stock")));
				}
			}catch(SQLException ex) {
				Logger.getLogger(GraficaPastel.class.getName()).log(Level.SEVERE, null, ex);
				return null;
			}
			
	        JFreeChart chart= ChartFactory.createPieChart("Stock en dólares por cada proveedor", dataset, true, true, false);
	        int x=1000;
	        int y=1000;
	        icono = new ImageIcon(chart.createBufferedImage(x, y));
			//File f = new File("Grafica.png");
			//ChartUtilities.saveChartAsPNG(f, chart, x, y);
		} catch (/*IO8*/Exception ex) {
            //Logger.getLogger(GraficaPastel.class.getName()).log(Level.SEVERE, null, ex);
            //return false;
        }
		return icono;
	}

}