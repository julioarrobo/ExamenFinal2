package BusinessLogic.Entities;

public class Horario {
    private int jaIdHorario;
    private String jaHora;
    private String jaDia;

    public Horario(int jaIdHorario, String jaHora, String jaDia) {
        this.jaIdHorario = jaIdHorario;
        this.jaHora = jaHora;
        this.jaDia = jaDia;
    }

    public Horario(String jaHora, String jaDia) {
        this.jaHora = jaHora;
        this.jaDia = jaDia;
    }

    public Horario() {
    }

    public int getJaIdHorario() {
        return jaIdHorario;
    }

    public void setJaIdHorario(int jaIdHorario) {
        this.jaIdHorario = jaIdHorario;
    }

    public String getJaHora() {
        return jaHora;
    }

    public void setJaHora(String jaHora) {
        this.jaHora = jaHora;
    }

    public String getJaDia() {
        return jaDia;
    }

    public void setJaDia(String jaDia) {
        this.jaDia = jaDia;
    }

    public void jaEliminarTabla() {

    }

}
