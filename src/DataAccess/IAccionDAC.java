package DataAccess;

import java.sql.ResultSet;

import Framework.AppException;

public interface IAccionDAC {

    /**
     * Metodo extrae la infromacion de la DATABASE
     * 
     * @return ResultSet: Los datos extraidos
     * @throws AppException
     */
    public ResultSet jaGetAllDatos() throws AppException;

    /**
     * Metodo permite crear las Entidades en la DATABASE
     * 
     * @return
     * @throws AppException
     */
    public boolean jaCrearTabla() throws AppException;

    /**
     * Metodo permite realizar la presentacion por consola de la onformacion
     * obtenida de la base de datos
     * 
     * @return
     * @throws AppException
     */
    public boolean jaGetHorarioArsenalDetalle() throws AppException;

}
