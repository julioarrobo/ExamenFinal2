import java.util.Scanner;

import BusinessLogic.CargaHoraria.LeerArchivos;
import BusinessLogic.Entities.Coordenada;
import DataAccess.ArsenalDAC;
import DataAccess.CoordenadaDAC;
import DataAccess.HorarioDAC;
import Framework.AppException;
import Framework.Login;
import Utility.Utilitario;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static final String JA_NOMBRE_COMPLET0 = "JULIO CESAR ARROBO PINTO";
    private static final String JA_CEDULA = "2200223473";
    private static LeerArchivos jaLA = new LeerArchivos();
    private static HorarioDAC jaHo = new HorarioDAC();
    private static ArsenalDAC jaAr = new ArsenalDAC();
    private static CoordenadaDAC jaCoo = new CoordenadaDAC();

    public static void main(String[] args) throws Exception {
        Utilitario.clear();
        iniciarSesion();
        Utilitario.jaImpresionDatos(JA_NOMBRE_COMPLET0, JA_CEDULA);
        jaLA.jaLeerHorario();
        Utilitario.jaSistemaCarga();

        //METODOS PARA QUE AL TERMINAR EL SISTEMA SE ELIMINEN LAS ENTIDADES
        // jaHo.jaEliminarTabla();
        // jaAr.jaEliminarTabla();
        // jaCoo.jaEliminarTabla();

    }

    /**
     * Metodo ejecuta el inicio de sesion y verifica el usuario y contrase;a
     * 
     * @throws AppException
     */
    private static void iniciarSesion() throws AppException {
        int intentosRestantes = 3;
        while (intentosRestantes > 0) {
            System.out.print("\tIntroduce tu nombre de usuario: ");
            String username = scanner.nextLine();
            System.out.print("\tIntroduce tu contraseña: ");
            String password = scanner.nextLine();
            if (Login.jaAutenticacion(username, password)) {
                System.out.println("\t¡Inicio de sesión exitoso!\n\tBIENVENIDO: " + username + "\n");
                return; // Si la autenticación es exitosa, sale del método.
            } else {
                intentosRestantes--;
                if (intentosRestantes > 0) {
                    System.out.println("\tNombre de usuario o contraseña incorrectos. Te quedan " + intentosRestantes
                            + " intentos.");
                } else {
                    System.out.println("\tHas agotado tus intentos. El programa se cerrará.");
                    Utilitario.jaSistemaCarga();
                    System.exit(0); // Termina el programa.
                }
            }
        }
    }

    /**
     * Metodo ejecuta el nuevo registro de usuarios y contrase;ar
     * @throws AppException
     */
    private static void registrar() throws AppException {
        System.out.print("Introduce un nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Introduce una contraseña: ");
        String password = scanner.nextLine();
        if (Login.jaRegistroUser(username, password)) {
            System.out.println("¡Usuario registrado exitosamente!");
        } else {
            System.out.println("Hubo un error al registrar el usuario. Puede que el nombre de usuario ya exista.");
        }
    }

}
