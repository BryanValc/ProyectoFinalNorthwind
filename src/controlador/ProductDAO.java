package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Product;
import conexionBD.Conexion;
import java.util.ArrayList;

public class ProductDAO {

    Conexion cn = new Conexion(2);

    public boolean insertarRegistro(Product producto) {
    	cn.getConexion();
        boolean resultado = false;
        try {
			resultado = cn.agregarRegistro(producto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

    public boolean modificarRegistro(Product producto) {
    	cn.getConexion();
        boolean resultado = false;
        try {
			resultado = cn.actualizarRegistro(producto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

    public ArrayList<Product> buscar(String filtro) {
        cn.getConexion();
        ArrayList<Product> lista = new ArrayList<Product>();
        ResultSet rs = cn.ejecutarConsulta(filtro);
        try {
            if (rs.next()) {
                do {
                    lista.add(new Product(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getInt(6),
                            rs.getInt(7),
                            rs.getInt(8),
                            rs.getInt(9),
                            rs.getBoolean(10)));
                } while (rs.next());
            }
        } catch (SQLException ex) {
            System.out.printf("Error al buscar");
        }
        return lista;
    }

    public boolean borrarRegistro(Product producto) {
        cn.getConexion();
        boolean resultado = false;
        String sql = "DELETE FROM Products WHERE ProductID = " + producto.getProductID();
        try {
			resultado = cn.eliminarRegistro(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

}
