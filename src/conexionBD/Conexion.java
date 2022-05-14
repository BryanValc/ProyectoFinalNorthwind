package conexionBD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import modelo.*;

public class Conexion {

    private static PreparedStatement pstm = null;
    private static CallableStatement cs;
    private static ResultSet rs;
    private static Savepoint sp = null;

    private static Conexion conexionBD;
    private static Connection conexion = null;

    public Conexion(int valor) {
    }

    private Conexion() {
        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String URL = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;"
                    + "user=asd;"
                    + "password=c1s1g7o;"
                    + "encrypt=true;trustServerCertificate=true;";
            try {
                conexion = DriverManager.getConnection(URL);
                System.out.println("--Conexion efectuada correctamente--");
                conexion.setAutoCommit(false);
                sp = conexion.setSavepoint("Inicio");
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

    public static Connection getConexion() {

        if (conexion == null) {
            new Conexion();
        }

        return conexion;
    }

    public static void cerrarConexion() {
        try {
            cs.close();
            pstm.close();
            conexion.close();
        } catch (SQLException e) {
            System.out.printf("Error al cerrar la conexion");
        }

    }

    public static boolean eliminarRegistro(String sql) throws SQLException {
        try {
            pstm = conexion.prepareStatement(sql);
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            //System.out.printf("Error al eliminar el registro");
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean Transaccion(String instruccion) {
        try {
        	//conexion.setAutoCommit(false);
            //pstm = conexion.prepareStatement(instruccion);
            //pstm.execute();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public static void guardar() throws SQLException {
    	conexion.commit();
    	//conexion.releaseSavepoint(sp);
    }
    
    public static void volver() throws SQLException {
    	conexion.rollback(sp);
    }

    public static void llamada() {
        try {
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

    public static boolean actualizarRegistro(Category categoria) throws SQLException {
        try {
            pstm = conexion
                    .prepareStatement("UPDATE Categories SET categoryName = ?, description = ? WHERE categoryID = ? ");
            pstm.setString(1, categoria.getCategoryName());
            pstm.setString(2, categoria.getDescription());
            pstm.setInt(3, categoria.getCategoryID());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al modificar la categoria");
        }
        return false;

    }

    public static boolean actualizarRegistro(Product producto) throws SQLException{
        try {
            pstm = conexion.prepareStatement("UPDATE Products SET productName = ?, supplierID = ?, categoryID = ?, quantityPerUnit = ?, unitPrice = ?, "
                    + "unitsInStock = ?, unitsOnOrder = ?, reorderLevel = ?, discontinued = ? WHERE productID = ?");
            pstm.setString(1, producto.getProductName());
            pstm.setInt(2,producto.getSupplierID());
            pstm.setInt(3,producto.getCategoryID());
            pstm.setString(4, producto.getQuantityPerUnit());
            pstm.setDouble(5, producto.getUnitPrice());
            pstm.setInt(6, producto.getUnitsInStock());
            pstm.setInt(7, producto.getUnitsOnOrder());
            pstm.setInt(8, producto.getReorderLevel());
            pstm.setBoolean(9, producto.isDiscontinued());
            pstm.setInt(10, producto.getProductID());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al modificar el producto");
        }
        return false;

    }

    public static boolean actualizarRegistro(Supplier proveedor) throws SQLException {
        try {
            // "UPDATE Messages SET description = ?, author = ? WHERE id = ? AND seq_num =
            // ?");
            pstm = conexion.prepareStatement("UPDATE Suppliers SET companyName = ?, contactName = ?, contactTitle = ?, "
                    + "address = ?, city = ?, region = ?, postalCode = ?, country = ?, phone = ?, fax = ?, homePage = ? WHERE supplierID = ?");
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
            pstm.setInt(12, proveedor.getSupplierID());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al modificar el proveedor");
        }
        return false;
    }
    
    public static boolean actualizarRegistro(Usuario usuario) throws SQLException{
        try {
            pstm = conexion.prepareStatement("UPDATE Usuarios SET password = ? WHERE username = ?");
            pstm.setString(1, usuario.getPassword());
            pstm.setString(2, usuario.getUsername());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al modificar el usuario");
        }
        return false;
    }

    public static boolean insertarRegistro(Usuario usuario) throws SQLException {
        try {
            pstm = conexion.prepareStatement("INSERT \"Usuarios\"(\"username\",\"password\") VALUES (?, ?)");
            pstm.setString(1, usuario.getUsername());
            pstm.setString(2, usuario.getPassword());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al insertar el usuario");
        }
        return false;
    }

    public static boolean agregarRegistro(Category categoria) throws SQLException {
        try {
            pstm = conexion.prepareStatement("INSERT \"Categories\"(\"CategoryName\",\"Description\") VALUES (?, ?)");
            // pstm.setInt(1, categoria.getCategoryID());
            pstm.setString(1, categoria.getCategoryName());
            pstm.setString(2, categoria.getDescription());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al agregar la categoria");
        }
        return false;
    }

    public static boolean agregarRegistro(Product producto) throws SQLException {
        try {
            pstm = conexion.prepareStatement(
                    "INSERT \"Products\"(\"ProductName\",\"SupplierID\",\"CategoryID\",\"QuantityPerUnit\",\"UnitPrice\",\"UnitsInStock\",\"UnitsOnOrder\",\"ReorderLevel\",\"Discontinued\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // pstm.setInt(1, producto.getProductID());
            pstm.setString(1, producto.getProductName());
            pstm.setInt(2, producto.getSupplierID());
            pstm.setInt(3, producto.getCategoryID());
            pstm.setString(4, producto.getQuantityPerUnit());
            pstm.setDouble(5, producto.getUnitPrice());
            pstm.setInt(6, producto.getUnitsInStock());
            pstm.setInt(7, producto.getUnitsOnOrder());
            pstm.setInt(8, producto.getReorderLevel());
            pstm.setBoolean(9, producto.isDiscontinued());
            pstm.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al agregar el producto");
        }
        return false;
    }

    public static boolean agregarRegistro(Supplier proveedor) throws SQLException {
        try {
            pstm = conexion.prepareStatement(
                    "INSERT \"Suppliers\"(\"CompanyName\",\"ContactName\",\"ContactTitle\",\"Address\",\"City\",\"Region\",\"PostalCode\",\"Country\",\"Phone\",\"Fax\",\"HomePage\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            // pstm.setInt(1, proveedor.getSupplierID());
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
            conexion.commit();
            return true;
        } catch (Exception ex) {
        	conexion.rollback(sp);
            System.out.printf("Error al agregar el proveedor");
        }
        return false;
    }

}