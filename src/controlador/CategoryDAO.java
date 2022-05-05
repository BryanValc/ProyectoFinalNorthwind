package controlador;

import modelo.Category;
import conexionBD.ConexionBD;

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
                    lista.add(new Category(rs.get, categoryName, description))
                }

}
