
import java.time.LocalDate;

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

    @Override
    public String toString() {
        return categoria + " (vence: " + fechaVencimiento + ")";
    }
}
