package controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Category;
import conexionBD.Conexion;
import java.util.ArrayList;

public class CategoryDAO {

    Conexion cn = new Conexion(2);

    public boolean insertarRegistro(Category categoria) {
    	cn.getConexion();
        boolean resultado = false;
        try {
			resultado = cn.agregarRegistro(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

    public boolean modificarRegistro(Category categoria) {
    	cn.getConexion();
        boolean resultado = false;
        try {
			resultado = cn.actualizarRegistro(categoria);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

    public ArrayList<Category> buscar(String filtro) {
    	cn.getConexion();
        ArrayList<Category> lista = new ArrayList<Category>();
        ResultSet rs = cn.ejecutarConsulta(filtro);
        try {
            if (rs.next()) {
                do {
                    lista.add(new Category(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3)));
                } while (rs.next());
            }
        } catch (SQLException ex) {
            System.out.printf("Error al buscar");
        }
        return lista;
    }

    public boolean borrarRegistro(Category categoria) {
        cn.getConexion();
        boolean resultado = false;
        String sql = "DELETE FROM Categories WHERE CategoryID = " + categoria.getCategoryID();
        try {
			resultado = cn.eliminarRegistro(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultado;
    }

}
