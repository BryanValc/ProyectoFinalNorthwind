package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Product;
import conexionBD.ConexionBD;
import java.util.ArrayList;

public class ProductDAO {

    public boolean insertarRegistro(Product producto) {
        boolean resultado = false;
        resultado = ConexionBD.agregarRegistro(producto);
        return resultado;
    }

    public boolean modificarRegistro(Product producto) {
        boolean resultado = false;
        resultado=ConexionBD.actualizarRegistro(producto);
        return resultado;
    }

    public ArrayList<Product> buscar(String filtro) {
        ArrayList<Product> lista = new ArrayList<Product>();
        ResultSet rs = ConexionBD.ejecutarConsulta(filtro);
        try {
            if(rs.next()){
                do{
                    lista.add(new Product(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getBoolean(10)));
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.out.printf("Error al buscar");
        }
        return lista;
    }

    public boolean borrarRegistro(Product producto) {
        boolean resultado = false;
        String sql = "DELETE FROM Products WHERE ProductID = " + producto.getProductID();
        resultado=ConexionBD.eliminarRegistro(sql);
        return resultado;
    }

}
