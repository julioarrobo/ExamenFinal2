package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.Coordenada;
import Framework.AppException;

public class CoordenadaDAC extends SQLiteDataHelper implements IAccionDAC {

    @Override
    public ResultSet jaGetAllDatos() throws AppException {
        String query = "SELECT * FROM JA_COORDENADAS";
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
     * @return List<Coordenada>: una lista de la clase Coordenada
     * @throws AppException
     */
    public List<Coordenada> getAllCoordenada() throws AppException {
        try {
            List<Coordenada> jaLstCoordenada = new ArrayList<Coordenada>();

            ResultSet rs = jaGetAllDatos();
            while (rs.next()) {
                Coordenada s = new Coordenada(rs.getInt("IdCoordenada"),
                        rs.getString("NombreCoordenada"));
                jaLstCoordenada.add(s);
            }
            return jaLstCoordenada;
        } catch (Exception e) {
            throw new AppException(e, getClass(), "GetAllCoordenada");
        }
    }

    /**
     * Metodo permite insertar los datos obtenidos del archivo y los almacena en la
     * base de datos
     * 
     * @param jaLstAllCordenada: lista de las clases coordenadas
     * @throws AppException
     */
    public void jaInsertarDatos(ArrayList<Coordenada> jaLstAllCordenada) throws AppException {
        jaCrearTabla();
        for (Coordenada jaCoordenada : jaLstAllCordenada) {
            String query = " INSERT INTO JA_COORDENADAS(NombreCoordenada) VALUES (?)";
            try {
                Connection conn = SQLiteDataHelper.jaOpenConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, jaCoordenada.getJaNombreCoordenada());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new AppException(e, getClass(), "insertHora()");
            }
        }
    }

    @Override
    public boolean jaCrearTabla() throws AppException {
        String query = "CREATE TABLE JA_COORDENADAS\n"
                + "(\n"
                + "IdCoordenada       INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "NombreCoordenada      VARCHAR(30)\n"
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

    /**
     * Metodo permite eliminar la Entidad creada en la DATABASE
     * 
     * @throws AppException
     */
    public void jaEliminarTabla() throws AppException {
        String query = "DROP TABLE JA_COORDENADAS";
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
