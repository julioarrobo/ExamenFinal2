package BusinessLogic.CargaHoraria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import BusinessLogic.Entities.Arsenal;
import BusinessLogic.Entities.Coordenada;
import BusinessLogic.Entities.Horario;
import DataAccess.ArsenalDAC;
import DataAccess.CoordenadaDAC;
import DataAccess.HorarioDAC;
import Framework.AppException;

public class LeerArchivos {
    private ArrayList<Horario> jaLstAllHorario = new ArrayList<>();
    private ArrayList<Arsenal> jaLstAllArsenal = new ArrayList<>();
    private ArrayList<Coordenada> jaLstAllCordenada = new ArrayList<>();
    private ArsenalDAC jaArsenal = new ArsenalDAC();
    private HorarioDAC jaHorario = new HorarioDAC();
    private CoordenadaDAC jaCoordenada = new CoordenadaDAC();
    

    /**
     * Metodo ejecuta la estraccion de datos de un archivo txt
     * 
     * @param jaHorarioRuta: Ingresa el url del archivo a leer
     * @throws AppException
     */
    public void jaLeerHorario() throws AppException {
        String[] jaRutasArchivo = { "utilities\\doc\\ArroboJulio.txt" };
        String[] jaDias = { "", "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "" };
        String jaLinea = "-";
        String[] datos;
        int nroLine = 0;
        System.out.println("Loading|       Geoposición|   LUNES    |   Martes   |  Miércoles |   Jueves   |  Viernes   | Tipo Arsenal");
        for (String rutaArchivo : jaRutasArchivo) {
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
                while ((jaLinea = br.readLine()) != null) {
                    if (nroLine++ == 0)
                        continue;
                    datos = jaLinea.split(";");
                    jaPresentacionAnimacion(datos);
                    for (int i = 0; i < datos.length; i++) {
                        if ((i > 0) && (i < 6)) {
                            if (datos[i] != "") {

                                jaLstAllHorario.add(new Horario(datos[i].trim(), jaDias[i].trim()));
                            }
                        }
                    }
                    jaLstAllCordenada.add(new Coordenada(datos[0].trim()));
                    jaLstAllArsenal.add(new Arsenal(datos[6].trim()));
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo " + rutaArchivo + ": " + e.getMessage());
            }
        }

        jaHorario.jaInsertarHora(jaLstAllHorario);
        jaArsenal.jaInsertarDatos(jaLstAllArsenal);
        jaCoordenada.jaInsertarDatos(jaLstAllCordenada);
    }

    /**
     * Metodo presenta una animacion de como va leyendo el archivo txt
     * @param datos: datos del archivo leido
     */
    private void jaPresentacionAnimacion(String[] datos) {
        int jaTotalLineas = 0;
        
        char[] signos = { '\\', '|', '/', '-', '|' };
        for (int carga = 0; carga <= 100; carga++) {
            System.out.print("\r" + (jaTotalLineas == 0 ? "Loading" : "        ") + "| "
                    + (carga == 0 ? " " : carga + "%") + " [");
            System.out.print(signos[carga % signos.length] + "]");

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < datos.length; i++) {
           // System.out.print( datos[i]);

            if ((datos[i].equals(""))&&(i>0)) {
                System.out.print("      ;      ");
            } else {
                if (i < datos.length -1) {
                    System.out.print( datos[i]+" ;      " );
                } else {
                    System.out.print( datos[i]);
                }
                
                
            }
        }
        System.out.println();

    }

}
