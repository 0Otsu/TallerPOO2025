
import java.time.LocalDate;

public class ChoferCategoria {
    private Categoria categoria;
    private LocalDate fechaVencimiento;

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
