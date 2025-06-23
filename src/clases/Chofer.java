import java.util.ArrayList;
import java.util.List;

/**
 * Representa un chofer de la empresa, con datos personales, número de licencia
 * y una lista de habilitaciones para diferentes categorías de vehículos.
 */
public class Chofer extends Persona {
    private final String nroLicencia;
    private final ArrayList<ChoferCategoria> habilitaciones;

    /**
     * Crea un chofer con dni, nombre, apellido, número de licencia y una categoría
     * inicial.
     */

    public Chofer(long dni, String nombre, String apellido, String nroLicencia, Categoria categoria) {
        super(dni, nombre, apellido);
        this.nroLicencia = nroLicencia;
        this.habilitaciones = new ArrayList<>();
    }

    public String getNroLicencia() {
        return nroLicencia;
    }

    /**
     * Devuelve la lista de habilitaciones que posee el chofer.
     *
     * @return lista de objetos ChoferCategoria
     */
    public List<ChoferCategoria> getHabilitaciones() {
        return habilitaciones;
    }

    /**
     * Agrega una habilitación al chofer.
     *
     * @param h habilitación que se desea agregar
     */

    public void agregarHabilitacion(ChoferCategoria h) {
        habilitaciones.add(h);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Chofer chofer = (Chofer) o;
        return nroLicencia.equals(chofer.nroLicencia);
    }

    @Override
    public int hashCode() {
        return nroLicencia.hashCode();
    }

    @Override
    public String toString() {
        return "Chofer: " + getNombre() + " " + getApellido() + ", DNI: " + getDni() +
                ", Licencia: " + nroLicencia + ", Habilitaciones: " + habilitaciones;
    }

}
