package Framework;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccess.SQLiteDataHelper;

public class Login {


    /**
     * Metodo realiza la encryptacion del password
     * @param jaPassword: es la contrase;a que se va a encryptar
     * @return  String: retorna el valor ya encryptado
     * @throws AppException
     */
    public static String getMD5(String jaPassword) throws AppException{
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(jaPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo permite realizar la autenticacion del sistema de logeo extrayendo los datos de la base de datos y 
     * comparandolos
     * @param username: Usuario a verificar
     * @param password: contraseña a verificar
     * @return boolean: dato booleano que confirma la verificacion
     * @throws AppException
     */
    public static boolean jaAutenticacion(String username, String password) throws AppException {
        String md5Password = getMD5(password);
        Connection conn = SQLiteDataHelper.jaOpenConnection();

        String query = "SELECT PasswordUsuario FROM JA_USUARIOS WHERE NombreUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String storedPassword = rs.getString("PasswordUsuario");
                return storedPassword.equals(md5Password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Metodo toma el dato encryptado y el usuario y los ingresa a la base de datos para el registro de nuevos usuarios
     * @param username: usuario creada a guardar en la base de datos
     * @param password: password encryptado e ingresa a la base de datos a ser alamcenada
     * @return retorna un dato booleano para la confirmacion de usuario creado
     * @throws AppException
     */
    public static boolean jaRegistroUser(String username, String password) throws AppException {
        String md5Password = getMD5(password);
        Connection conn = SQLiteDataHelper.jaOpenConnection();
        String query = "INSERT INTO JA_USUARIOS(NombreUsuario,PasswordUsuario) VALUES(?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, md5Password);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}






