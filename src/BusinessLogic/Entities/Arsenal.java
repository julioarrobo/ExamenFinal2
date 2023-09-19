package BusinessLogic.Entities;



public class Arsenal  {
    private int       jaIdArsenal;  
    private String    jaNombreArsenal;

    

    public Arsenal(int jaIdArsenal, String jaNombreArsenal) {
        this.jaIdArsenal = jaIdArsenal;
        this.jaNombreArsenal = jaNombreArsenal;
    }

    public Arsenal(String jaNombreArsenal) {
        this.jaNombreArsenal = jaNombreArsenal;
    }

    public Arsenal() {
    }


    public int getJaIdArsenal() {
        return jaIdArsenal;
    }

    public void setJaIdArsenal(int jaIdArsenal) {
        this.jaIdArsenal = jaIdArsenal;
    }

    public String getJaNombreArsenal() {
        return jaNombreArsenal;
    }

    public void setJaNombreArsenal(String jaNombreArsenal) {
        this.jaNombreArsenal = jaNombreArsenal;
    }

  

    
    
}
