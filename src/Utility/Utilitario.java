package Utility;

public class Utilitario {

    /**
     * Metodo permite limpiar la consola para realizar la impresion de datos
     */
    public final static void clear() {
        try {
            String operatingSystem = System.getProperty("os.name"); // Check the current operating system

            if (operatingSystem.contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Metodo permite imprimir por consola los datos del estudiante
     * 
     * @param jaNombre: Nombre a presentar
     * @param jaCedula: Numero de cedula a presentar
     */
    public static void jaImpresionDatos(String jaNombre, String jaCedula) {

        System.out.println("\t-NOMBRE: " + jaNombre);
        System.out.println("\t-CEDULA: " + jaCedula);
        System.out.println("\t-COORDENADAS UCRANIANAS: " + "\n");

    }


    
    /**
     * Sistema de carga para el cierre del sistema
     */
    public static void jaSistemaCarga() {
        int jaTotalLineas = 0;

        char[] signos = { '\\', '|', '/', '-', '|' };
        for (int carga = 0; carga <= 100; carga++) {
            System.out.print("\r" + (jaTotalLineas == 0 ? "Loading" : "        ") + "| "
                    + (carga == 0 ? " " : carga + "%") + " [");
            System.out.print(signos[carga % signos.length] + "]");

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
