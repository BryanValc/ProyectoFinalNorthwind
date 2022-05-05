package conexionBD;

import java.sql.*;
import modelo.*;

public class ConexionBD {

    private static PreparedStatement pstm;
    private static CallableStatement cs;
    private static ResultSet rs;
	
    private static ConexionBD conexionBD;
    private static Connection conexion= null;
    

    private ConexionBD() {
        /*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String URL = "jdbc:mysql://localhost:3306/world";
            
            conexion = DriverManager.getConnection(URL,"BryanValc","c1s1g7o");
            
            System.out.println("Conexion establecida");
			
        } catch (ClassNotFoundException e) {
                System.out.printf("Error de Driver");
        } catch (SQLException e) {
                System.out.printf("Error de conexion a MySQL o de la BD");
        }
        */
    }

    private static synchronized ConexionBD getInstance() {
        if (conexionBD == null) {
            new ConexionBD();
        }
        return conexionBD;
    }

    public static Connection getConexion() {
        if (conexionBD == null) {
            new ConexionBD();
        }
        return conexion;
    }

    static void cerrarConnexion() {
        try {
            cs.close();
            pstm.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.printf("Error al cerrar la conexion");
        }
    }

    public static boolean eliminarRegistro(String sql) {
        try {
            String consulta = sql;
            pstm = conexion.prepareStatement(consulta);
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al eliminar el registro");
        }
        return false;
        }

    public static boolean Transaccion(String instruccion){
        try{
                pstm = conexion.prepareStatement(instruccion);
                pstm.executeUpdate();
                return true;
            } catch (Exception ex) {
                System.out.printf("Error al ejecutar la transaccion");
        }
            return false;
        }
    
    public static void llamada(){
            try{
                String simpleProc = "{sp_CantidadDePaises()}";
                cs = conexion.prepareCall(simpleProc);
                cs.executeUpdate();
            } catch (Exception ex) {
                System.out.printf("Error al ejecutar la llamada");
        }
    }

    public static ResultSet ejecutarConsulta(String sql) {
        try {
                String consulta = sql;
                pstm = conexion.prepareStatement(consulta);
                return pstm.executeQuery();
        } catch (Exception e) {
                System.out.printf("Error al ejecutar la consulta");
            }
            return null;
    }

    public static boolean actualizarRegistro(Category categoria) {
        try {
            pstm = conexion.prepareStatement("UPDATE category SET categoryName = ?, description = ? WHERE categoryID = " + categoria.getCategoryID());
            pstm.setString(1, categoria.getCategoryName());
            pstm.setString(2, categoria.getDescription());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al actualizar el registro");
        }
        return false;

    }

    public static boolean actualizarRegistro(Product producto) {
        try {
            pstm = conexion.prepareStatement("UPDATE product SET productName = ?, quantityPerUnit = ?, unitPrice = ?, "
                    + "unitsInStock = ?, unitsOnOrder = ?, reorderLevel = ?, discontinued = ? WHERE productID = " + producto.getProductID());
            pstm.setString(1, producto.getProductName());
            pstm.setString(2, producto.getQuantityPerUnit());
            pstm.setDouble(3, producto.getUnitPrice());
            pstm.setInt(4, producto.getUnitsInStock());
            pstm.setInt(5, producto.getUnitsOnOrder());
            pstm.setInt(6, producto.getReorderLevel());
            pstm.setBoolean(7, producto.isDiscontinued());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al actualizar el registro");
        }
        return false;

    }

    public static boolean actualizarRegistro(Supplier proveedor) {
        try {
            pstm = conexion.prepareStatement("UPDATE supplier SET companyName = ?, contactName = ?, contactTitle = ?, "
                    + "address = ?, city = ?, region = ?, postalCode = ?, country = ?, phone = ?, fax = ?, homePage = ? WHERE supplierID = " + proveedor.getSupplierID());
            pstm.setString(1, proveedor.getCompanyName());
            pstm.setString(2, proveedor.getContactName());
            pstm.setString(3, proveedor.getContactTitle());
            pstm.setString(4, proveedor.getAddress());
            pstm.setString(5, proveedor.getCity());
            pstm.setString(6, proveedor.getRegion());
            pstm.setString(7, proveedor.getPostalCode());
            pstm.setString(8, proveedor.getCountry());
            pstm.setString(9, proveedor.getPhone());
            pstm.setString(10, proveedor.getFax());
            pstm.setString(11, proveedor.getHomePage());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al actualizar el registro");
        }
        return false;
    }

    public static boolean agregarRegistro(Category categoria) {	
        try {
            pstm = conexion.prepareStatement("INSERT INTO category (categoryName, description) VALUES (?, ?)");
            pstm.setString(1, categoria.getCategoryName());
            pstm.setString(2, categoria.getDescription());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al agregar el registro");
        }
        return false;
    }

    public static boolean agregarRegistro(Product producto) {
        try {
            pstm = conexion.prepareStatement("INSERT INTO product (productName, quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reorderLevel, discontinued) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, producto.getProductName());
            pstm.setString(2, producto.getQuantityPerUnit());
            pstm.setDouble(3, producto.getUnitPrice());
            pstm.setInt(4, producto.getUnitsInStock());
            pstm.setInt(5, producto.getUnitsOnOrder());
            pstm.setInt(6, producto.getReorderLevel());
            pstm.setBoolean(7, producto.isDiscontinued());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al agregar el registro");
        }
        return false;
    }

    public static boolean agregarRegistro(Supplier proveedor) {
        try {
            pstm = conexion.prepareStatement("INSERT INTO supplier (companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homePage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, proveedor.getCompanyName());
            pstm.setString(2, proveedor.getContactName());
            pstm.setString(3, proveedor.getContactTitle());
            pstm.setString(4, proveedor.getAddress());
            pstm.setString(5, proveedor.getCity());
            pstm.setString(6, proveedor.getRegion());
            pstm.setString(7, proveedor.getPostalCode());
            pstm.setString(8, proveedor.getCountry());
            pstm.setString(9, proveedor.getPhone());
            pstm.setString(10, proveedor.getFax());
            pstm.setString(11, proveedor.getHomePage());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al agregar el registro");
        }
        return false;
    }


}
