package BusinessLogic.Entities;

public class Coordenada {

    private int jaIdCoordenada;
    private String jaNombreCoordenada;

    public Coordenada(String jaNombreCoordenada) {
        this.jaNombreCoordenada = jaNombreCoordenada;

    }

    public Coordenada(int jaIdCoordenada, String jaNombreCoordenada) {
        this.jaIdCoordenada = jaIdCoordenada;
        this.jaNombreCoordenada = jaNombreCoordenada;
    }

    public Coordenada() {
    }

    public int getJaIdCoordenada() {
        return jaIdCoordenada;
    }

    public void setJaIdCoordenada(int jaIdCoordenada) {
        this.jaIdCoordenada = jaIdCoordenada;
    }

    public String getJaNombreCoordenada() {
        return jaNombreCoordenada;
    }

    public void setJaNombreCoordenada(String jaNombreCoordenada) {
        this.jaNombreCoordenada = jaNombreCoordenada;
    }

}
