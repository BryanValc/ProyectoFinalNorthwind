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
        resultado = cn.agregarRegistro(producto);
        if (resultado) {
            System.out.println("Registro insertado correctamente");
        } else {
            System.out.println("Error al insertar el registro");
        }
        return resultado;
    }

    public boolean modificarRegistro(Product producto) {
        boolean resultado = false;
        resultado = cn.actualizarRegistro(producto);
        return resultado;
    }

    public ArrayList<Product> buscar(String filtro) {
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
        boolean resultado = false;
        String sql = "DELETE FROM Products WHERE ProductID = " + producto.getProductID();
        resultado = cn.eliminarRegistro(sql);
        return resultado;
    }

}
