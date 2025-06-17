import java.util.ArrayList;
import java.util.List;

public class Chofer extends Persona {
    private final String nroLicencia;
    private final ArrayList<ChoferCategoria> habilitaciones;

    public Chofer(long dni, String nombre, String apellido, String nroLicencia, Categoria categoria) {
        super(dni, nombre, apellido);
        this.nroLicencia = nroLicencia;
        this.habilitaciones = new ArrayList<>();
    }

    public String getNroLicencia() {
        return nroLicencia;
    }

    public List<ChoferCategoria> getHabilitaciones() {
        return habilitaciones;
    }

    public void agregarHabilitacion(ChoferCategoria h) {
        habilitaciones.add(h);
    }

    @Override
    public String toString() {
        return "Chofer: " + getNombre() + " " + getApellido() + ", DNI: " + getDni() +
                ", Licencia: " + nroLicencia + ", Habilitaciones: " + habilitaciones;
    }
}
