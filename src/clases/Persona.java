/**
 * Clase base que representa una persona con nombre, apellido y DNI.
 * Es extendida por la clase Chofer.
 */
public abstract class Persona {
    private final long dni;
    private final String nombre;
    private final String apellido;

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

    @Override
    public abstract String toString();

}