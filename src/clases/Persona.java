public abstract class Persona {
    private long dni;
    private String nombre;
    private String apellido;

    public Persona(long dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public abstract String toString(); // Forzamos a las subclases a definir cómo se muestran

}