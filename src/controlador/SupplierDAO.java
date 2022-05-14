package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Supplier;
import conexionBD.Conexion;
import java.util.ArrayList;

public class SupplierDAO {

    Conexion cn = new Conexion(2);

    public boolean insertarRegistro(Supplier proveedor) {
    	cn.getConexion();
        boolean resultado = false;
        try {
			resultado = cn.agregarRegistro(proveedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

    public boolean modificarRegistro(Supplier proveedor) {
    	cn.getConexion();
        boolean resultado = false;
        try {
			resultado = cn.actualizarRegistro(proveedor);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

    public ArrayList<Supplier> buscar(String filtro) {
        cn.getConexion();
        ArrayList<Supplier> lista = new ArrayList<Supplier>();
        ResultSet rs = cn.ejecutarConsulta(filtro);
        try {
            if (rs.next()) {
                do {
                    lista.add(new Supplier(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12)));
                } while (rs.next());
            }
        } catch (SQLException ex) {
            System.out.printf("Error al buscar");
        }
        return lista;
    }

    public boolean borrarRegistro(Supplier proveedor) {
        cn.getConexion();
        boolean resultado = false;
        String sql = "DELETE FROM Suppliers WHERE SupplierID = " + proveedor.getSupplierID();
        try {
			resultado = cn.eliminarRegistro(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

}
