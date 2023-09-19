package DataAccess;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.Arsenal;
import Framework.AppException;

public class ArsenalDAC extends SQLiteDataHelper implements IAccionDAC {

    /**
     * Metodo permite insertar datos leidos a una base de datos para almacenarlos
     * 
     * @param lstAllArsenal: Lista de clases Arsenal
     * @throws AppException
     */
    public void jaInsertarDatos(ArrayList<Arsenal> lstAllArsenal) throws AppException {
        jaCrearTabla();
        for (Arsenal jaArsenal : lstAllArsenal) {
            String query = " INSERT INTO JA_TIPO_ARSENAL(NombreTipoArsenal) VALUES (?)";
            try {
                Connection conn = SQLiteDataHelper.jaOpenConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, jaArsenal.getJaNombreArsenal());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new AppException(e, getClass(), "insert()");
            }
        }
    }

    @Override
    public boolean jaCrearTabla() throws AppException {
        String query = "CREATE TABLE JA_TIPO_ARSENAL\n"
                + "(\n"
                + "IdTipoArsenal       INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "NombreTipoArsenal   VARCHAR(30)\n"
                + ");";
        try {
            Connection conn = SQLiteDataHelper.jaOpenConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "crearTabla()");
        }
    }

    @Override
    public ResultSet jaGetAllDatos() throws AppException {
        String query = "SELECT * FROM JA_TIPO_ARSENAL";
        try {
            Connection conn = jaOpenConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "jaGetAllDatos()");
        }
    }

    /**
     * Metodo permite guardar los datos en cada clase
     * 
     * @return List<Arsenal>: una lista de la clase Arsenal
     * @throws AppException
     */
    public List<Arsenal> getAllArsenal() throws AppException {
        try {
            List<Arsenal> jaLstArsenal = new ArrayList<Arsenal>();

            ResultSet rs = jaGetAllDatos();
            while (rs.next()) {
                Arsenal s = new Arsenal(rs.getInt("IdTipoArsenal"),
                        rs.getString("NombreTipoArsenal"));
                jaLstArsenal.add(s);
            }
            return jaLstArsenal;
        } catch (Exception e) {
            throw new AppException(e, getClass(), "GetAllArsenal");
        }
    }

    /**
     * Metodo permite eliminar la Entidad creada en la DATABASE
     * 
     * @throws AppException
     */
    public void jaEliminarTabla() throws AppException {
        String query = "DROP TABLE JA_TIPO_ARSENAL";
        try {
            Connection conn = SQLiteDataHelper.jaOpenConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "crearTabla()");
        }
    }

    @Override
    public boolean jaGetHorarioArsenalDetalle() throws AppException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jaGetHorarioArsenalDetalle'");
    }

}
