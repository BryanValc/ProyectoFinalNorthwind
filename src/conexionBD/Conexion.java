package conexionBD;

import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.logging.Level;

import modelo.*;

public class Conexion {

    private static PreparedStatement pstm = null;
    private static CallableStatement cs;
    private static Savepoint sp = null;

    private static Connection conexionBD = null;
    private static Logger logger = Logger.getLogger("Log de conexion");

    public Conexion(int valor) {
    }

    private Conexion() {
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;"
                    + "user=asd;"
                    + "password=c1s1g7o;"
                    + "encrypt=true;trustServerCertificate=true;";
            
            conexionBD = DriverManager.getConnection(url);
            
            conexionBD.setAutoCommit(false);
            sp = conexionBD.setSavepoint("Inicio");
           
        } catch (SQLException e) {
        	logger.log(Level.SEVERE,"Error de Conexion",e);
        }
    }

    public static Connection getConexion() {

        if (conexionBD == null) {
            new Conexion();
        }

        return conexionBD;
    }

    public static void cerrarConexion() {
        try {
            cs.close();
            pstm.close();
            conexionBD.close();
        } catch (SQLException e) {
        	logger.log(Level.SEVERE,"Error al cerrar la conexion",e);
        }

    }

    public static boolean eliminarRegistro(String sql) throws SQLException {
        try {
            pstm = conexionBD.prepareStatement(sql);
            pstm.executeUpdate();
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
            ex.printStackTrace();
        }
        return false;
    }

    public static ResultSet ejecutarConsulta(String sql) {
        try {
            String consulta = sql;
            pstm = conexionBD.prepareStatement(consulta);
            return pstm.executeQuery();
        } catch (Exception e) {
        	logger.log(Level.SEVERE,"Error al ejecutar la consulta",e);
        }
        return null;
    }

    public static boolean actualizarRegistro(Category categoria) throws SQLException {
        try {
            pstm = conexionBD
                    .prepareStatement("UPDATE Categories SET categoryName = ?, description = ? WHERE categoryID = ? ");
            pstm.setString(1, categoria.getCategoryName());
            pstm.setString(2, categoria.getDescription());
            pstm.setInt(3, categoria.getCategoryID());
            pstm.executeUpdate();
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al actualizar la categoria",ex);
        }
        return false;

    }

    public static boolean actualizarRegistro(Product producto) throws SQLException{
        try {
            pstm = conexionBD.prepareStatement("UPDATE Products SET productName = ?, supplierID = ?, categoryID = ?, quantityPerUnit = ?, unitPrice = ?, "
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
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al modificar el producto",ex);
        }
        return false;

    }

    public static boolean actualizarRegistro(Supplier proveedor) throws SQLException {
        try {
            pstm = conexionBD.prepareStatement("UPDATE Suppliers SET companyName = ?, contactName = ?, contactTitle = ?, "
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
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al modificar el proveedor",ex);
        }
        return false;
    }
    
    public static boolean actualizarRegistro(Usuario usuario) throws SQLException{
        try {
            pstm = conexionBD.prepareStatement("UPDATE Usuarios SET password = ? WHERE username = ?");
            pstm.setString(1, usuario.getPassword());
            pstm.setString(2, usuario.getUsername());
            pstm.executeUpdate();
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al modificar el usuario",ex);
        }
        return false;
    }

    public static boolean insertarRegistro(Usuario usuario) throws SQLException {
        try {
            pstm = conexionBD.prepareStatement("INSERT \"Usuarios\"(\"username\",\"password\") VALUES (?, ?)");
            pstm.setString(1, usuario.getUsername());
            pstm.setString(2, usuario.getPassword());
            pstm.executeUpdate();
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al insertar el usuario",ex);
        }
        return false;
    }

    public static boolean agregarRegistro(Category categoria) throws SQLException {
        try {
            pstm = conexionBD.prepareStatement("INSERT \"Categories\"(\"CategoryName\",\"Description\") VALUES (?, ?)");
            pstm.setString(1, categoria.getCategoryName());
            pstm.setString(2, categoria.getDescription());
            pstm.executeUpdate();
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al insertar la categoria",ex);
        }
        return false;
    }

    public static boolean agregarRegistro(Product producto) throws SQLException {
        try {
            pstm = conexionBD.prepareStatement(
                    "INSERT \"Products\"(\"ProductName\",\"SupplierID\",\"CategoryID\",\"QuantityPerUnit\",\"UnitPrice\",\"UnitsInStock\",\"UnitsOnOrder\",\"ReorderLevel\",\"Discontinued\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al insertar el producto",ex);
        }
        return false;
    }

    public static boolean agregarRegistro(Supplier proveedor) throws SQLException {
        try {
            pstm = conexionBD.prepareStatement(
                    "INSERT \"Suppliers\"(\"CompanyName\",\"ContactName\",\"ContactTitle\",\"Address\",\"City\",\"Region\",\"PostalCode\",\"Country\",\"Phone\",\"Fax\",\"HomePage\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            conexionBD.commit();
            return true;
        } catch (Exception ex) {
        	conexionBD.rollback(sp);
        	logger.log(Level.SEVERE,"Error al insertar el proveedor",ex);
        }
        return false;
    }

}