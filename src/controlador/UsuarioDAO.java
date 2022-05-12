package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexionBD.Conexion;
import modelo.Usuario;

public class UsuarioDAO {
	
	Conexion cn = new Conexion(2);

    public boolean insertarRegistro(Usuario usuario) {
    	cn.getConexion();
        boolean resultado = false;
        resultado = cn.insertarRegistro(usuario);
        return resultado;
    }

    public boolean modificarRegistro(Usuario usuario) {
    	cn.getConexion();
        boolean resultado = false;
        resultado = cn.actualizarRegistro(usuario);
        return resultado;
    }

    public ArrayList<Usuario> buscar(String filtro) {
        cn.getConexion();
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        ResultSet rs = cn.ejecutarConsulta(filtro);
        try {
            if (rs.next()) {
                do {
                    lista.add(new Usuario(
                            rs.getString(1),
                            rs.getString(2)));
                } while (rs.next());
            }
        } catch (SQLException ex) {
            System.out.printf("Error al buscar");
        }
        return lista;
    }

    public boolean borrarRegistro(Usuario usuario) {
        cn.getConexion();
        boolean resultado = false;
        String sql = "DELETE FROM Usuarios WHERE username = '" + usuario.getUsername()+"'";
        resultado = cn.eliminarRegistro(sql);
        return resultado;
    }

}
