import java.time.LocalDate;

/**
 * Clase que vincula una categoría de vehículo con una fecha de vencimiento.
 * Utilizada para verificar si un chofer está habilitado para cierto tipo de
 * vehículo.
 */
public class ChoferCategoria {
    private final Categoria categoria;
    private final LocalDate fechaVencimiento;

    public ChoferCategoria(Categoria categoria, LocalDate fechaVencimiento) {
        this.categoria = categoria;
        this.fechaVencimiento = fechaVencimiento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Devuelve la categoría y la fecha de vencimiento en formato legible.
     */

    @Override
    public String toString() {
        return categoria + " (vence: " + fechaVencimiento + ")";
    }
}
