public class Chofer extends Persona {
    private String nroLicencia;
    private Categoria categoria;

    public Chofer(long dni, String nombre, String apellido, String nroLicencia, Categoria categoria) {
        super(dni, nombre, apellido);
        this.nroLicencia = nroLicencia;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Chofer: " + getNombre() + " " + getApellido() + ", DNI: " + getDni() +
                ", Licencia: " + nroLicencia + ", Categoría: " + categoria;
    }
}
