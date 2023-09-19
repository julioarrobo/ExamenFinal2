package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BusinessLogic.Entities.Horario;
import Framework.AppException;

public class HorarioDAC extends SQLiteDataHelper implements IAccionDAC {

    /**
     * Metodo permite insertar datos leidos a una base de datos para almacenarlos
     * 
     * @param lstAllArsenal: Lista de clases Horario
     * @throws AppException
     */
    public void jaInsertarHora(ArrayList<Horario> lstAllHorario) throws AppException {
        jaCrearTabla();
        for (Horario jaHorario : lstAllHorario) {
            String query = " INSERT INTO JA_HORARIO(DiaHorario, HoraHorario) VALUES (?, ?)";
            try {
                Connection conn = SQLiteDataHelper.jaOpenConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, jaHorario.getJaDia());
                pstmt.setString(2, jaHorario.getJaHora());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new AppException(e, getClass(), "insertHora()");
            }
        }
    }

    @Override
    public boolean jaCrearTabla() throws AppException {
        String query = "CREATE TABLE JA_HORARIO\n"
                + "(\n"
                + "IdHorario       INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "DiaHorario      VARCHAR(20) ,\n"
                + "HoraHorario     VARCHAR(10) \n"
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
        String query = "SELECT * FROM JA_HORARIO";
        try {
            Connection conn = jaOpenConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new AppException(e, getClass(), "jaGetAllDatos()");
        }
    }

    /**
     * Metodo permite guardar los datos extraidos de la Entidad Horario y
     * guardarlos en cada clase
     * 
     * @return
     * @throws AppException
     */
    public List<Horario> getAllHorario() throws AppException {
        try {
            List<Horario> jaLstHorarios = new ArrayList<Horario>();
            ResultSet rs = jaGetAllDatos();
            while (rs.next()) {
                Horario s = new Horario(rs.getInt("IdHorario"),
                        rs.getString("DiaHorario"),
                        rs.getString("HoraHorario"));

                jaLstHorarios.add(s);

            }

            return jaLstHorarios;
        } catch (Exception e) {
            throw new AppException(e, getClass(), "GetAllHorarioPresentar");
        }

    }

    public boolean jaEliminarTabla() throws AppException {
        String query = "DROP TABLE JA_HORARIO\n";
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
    public boolean jaGetHorarioArsenalDetalle() throws AppException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'jaGetHorarioArsenalDetalle'");
    }

}
