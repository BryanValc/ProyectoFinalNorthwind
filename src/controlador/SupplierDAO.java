package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Supplier;
import conexionBD.ConexionBD;
import java.util.ArrayList;

public class SupplierDAO {

    public boolean insertarRegistro(Supplier proveedor) {
        boolean resultado = false;
        resultado = ConexionBD.agregarRegistro(proveedor);
        return resultado;
    }

    public boolean modificarRegistro(Supplier proveedor) {
        boolean resultado = false;
        resultado=ConexionBD.actualizarRegistro(proveedor);
        return resultado;
    }

    public ArrayList<Supplier> buscar(String filtro) {
        ArrayList<Supplier> lista = new ArrayList<Supplier>();
        ResultSet rs = ConexionBD.ejecutarConsulta(filtro);
        try {
            if(rs.next()){
                do{
                    lista.add(new Supplier(
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

    public boolean borrarRegistro(Supplier proveedor) {
        boolean resultado = false;
        String sql = "DELETE FROM Suppliers WHERE SupplierID = " + proveedor.getSupplierID();
        resultado=ConexionBD.eliminarRegistro(sql);
        return resultado;
    }
    

}
