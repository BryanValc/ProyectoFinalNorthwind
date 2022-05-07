package conexionBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.*;

public class Conexion {

    private static PreparedStatement pstm;
    private static CallableStatement cs;
    private static ResultSet rs;

    private static Conexion conexionBD;
	private static Connection conexion = null;
    

    public Conexion(int valor) {}
    private Conexion() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String URL = "jdbc:sqlserver://localhost:1433;databaseName=Empresa;"
                    + "user=asd;"
                    + "password=c1s1g7o;"
                    + "encrypt=true;trustServerCertificate=true;";
            try {
                conexion= DriverManager.getConnection(URL);
                System.out.println("--Conexion efectuada correctamente--");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error de DRIVER");
        }
    }

    private static synchronized Connection getInstance() {
        if (conexion == null) {
            new Conexion();
        }
        return conexion;
    }

    public static Connection getConexion(){

         if (conexion == null){
             new Conexion();
         }

         return conexion;
    }

    static void cerrarConexion() {
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
            pstm = conexion.prepareStatement("INSERT \"Products\"(\"ProductID\",\"ProductName\",\"SupplierID\",\"CategoryID\",\"QuantityPerUnit\",\"UnitPrice\",\"UnitsInStock\",\"UnitsOnOrder\",\"ReorderLevel\",\"Discontinued\") VALUES (?, ?, ?, ?, ?, ?, ?)");
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
            pstm = conexion.prepareStatement("INSERT \"Suppliers\"(\"SupplierID\",\"CompanyName\",\"ContactName\",\"ContactTitle\",\"Address\",\"City\",\"Region\",\"PostalCode\",\"Country\",\"Phone\",\"Fax\",\"HomePage\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            pstm.setString(12, proveedor.getSupplierID());
            pstm.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.printf("Error al agregar el registro");
        }
        return false;
    }

}