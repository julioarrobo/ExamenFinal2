package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Framework.AppException;

public class SQLiteDataHelper {
    private static String DBPathConnection = "jdbc:sqlite:C:database\\ArroboJulio.db"; // null;
    private static Connection conn = null;

    /**
     * Metodo permite realizar la coneccion a la DATABASE
     * 
     * @return Retorna el objeto Connection
     * @throws AppException
     */
    public static synchronized Connection jaOpenConnection() throws AppException {
        try {
            if (conn == null)
                conn = DriverManager.getConnection(DBPathConnection);
            // System.out.println("CONECCION ESTABLECIDA A LA BASE DE DATOS");
        } catch (SQLException e) {
            throw new AppException(e, "SQLiteDataHelper", "Fallo la coneccion a la base de datos");
        }
        return conn;
    }

    /**
     * Metodo permite cerrar la coneccion a la base de datos
     * 
     * @throws AppException
     */
    protected static void jaCloseConnection() throws AppException {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            throw new AppException(e, "SQLiteDataHelper", "Fallo la conección con la base de datos");
        }
    }
}
