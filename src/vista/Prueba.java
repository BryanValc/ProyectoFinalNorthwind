package vista;

import conexionBD.Conexion;
import modelo.*;
import controlador.*;

public class Prueba {

	public static void main(String[] args) {

		CategoryDAO ctDao = new CategoryDAO();
		ProductDAO pdDao = new ProductDAO();
		SupplierDAO suDao = new SupplierDAO();

		// Conexion cn = new Conexion(2);
		// cn.getConexion();

		Category categoria = new Category(9, "Categoria 2", "Descripcion 1");
		Product producto = new Product(78, "Producto 2", 1, 1, "Descripcion 1", 1, 1, 1, 1, true);
		Supplier proveedor = new Supplier(30, "atlass", "juan", "jefe en minas", "la loma", "jerez", "zacs", "99350",
				"Mexico", "(514) 555-2955", "(514) 555-2921",
				"http://www.microsoft.com/accessdev/sampleapps/gdaymate.htm");

		if (ctDao.borrarRegistro(categoria)) {
			System.out.println("Categoria eiminada");
		}
		if (pdDao.borrarRegistro(producto)) {
			System.out.println("Producto eliminado");
		}
		if (suDao.borrarRegistro(proveedor)) {
			System.out.println("Proveedor eliminado");
		}

	}

}
