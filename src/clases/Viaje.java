
import java.time.LocalDate;
import java.time.LocalTime;

public class Viaje {
    private final LocalDate fecha;
    private final LocalTime horarioSalida;
    private final LocalTime horarioLlegada;
    private final Chofer chofer;
    private final Vehiculo vehiculo;
    private final Ciudad origen;
    private final Ciudad destino;

    public Viaje(LocalDate fecha, LocalTime horarioSalida, LocalTime horarioLlegada,
            Chofer chofer, Vehiculo vehiculo, Ciudad origen, Ciudad destino) {
        this.fecha = fecha;
        this.horarioSalida = horarioSalida;
        this.horarioLlegada = horarioLlegada;
        this.chofer = chofer;
        this.vehiculo = vehiculo;
        this.origen = origen;
        this.destino = destino;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHorarioSalida() {
        return horarioSalida;
    }

    public LocalTime getHorarioLlegada() {
        return horarioLlegada;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public Ciudad getOrigen() {
        return origen;
    }

    public Ciudad getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return " Viaje: " + fecha +
                "\n Salida: " + horarioSalida + " | Llegada: " + horarioLlegada +
                "\n Vehículo: " + vehiculo +
                "\n Chofer: " + chofer.getNombre() + " " + chofer.getApellido() +
                "\n De: " + origen + " → " + destino;
    }

}
