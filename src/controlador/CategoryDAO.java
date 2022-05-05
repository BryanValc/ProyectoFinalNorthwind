package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Category;
import conexionBD.ConexionBD;
import java.util.ArrayList;


public class CategoryDAO {

    public boolean insertarRegistro(Category categoria) {
        boolean resultado = false;
        resultado = ConexionBD.agregarRegistro(categoria);
        return resultado;
    }

    public boolean modificarRegistro(Category categoria) {
        boolean resultado = false;
        resultado=ConexionBD.actualizarRegistro(categoria);
        return resultado;
    }

    public ArrayList<Category> buscar(String filtro) {
        ArrayList<Category> lista = new ArrayList<Category>();
        ResultSet rs = ConexionBD.ejecutarConsulta(filtro);
        try {
            if(rs.next()){
                do{
                    lista.add(new Category(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)));
                }while(rs.next());
            }
        } catch (SQLException ex) {
            System.out.printf("Error al buscar");
        }
        return lista;
    }

    public boolean borrarRegistro(Category categoria) {
        boolean resultado = false;
        String sql = "DELETE FROM Categories WHERE CategoryID = " + categoria.getCategoryID();
        resultado=ConexionBD.eliminarRegistro(sql);
        return resultado;
    }

}
