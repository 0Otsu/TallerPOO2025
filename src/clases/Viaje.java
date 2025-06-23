import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Representa un viaje programado, con fecha, horario de salida y llegada,
 * chofer asignado,
 * vehículo utilizado, ciudad de origen y ciudad de destino.
 */
public class Viaje {
    private final LocalDate fecha;
    private final LocalTime horarioSalida;
    private final LocalTime horarioLlegada;
    private final Chofer chofer;
    private final Vehiculo vehiculo;
    private final Ciudad origen;
    private final Ciudad destino;

    /**
     * Crea un nuevo viaje con todos los datos necesarios.
     *
     * @param fecha          Fecha del viaje
     * @param horarioSalida  Hora de salida
     * @param horarioLlegada Hora de llegada
     * @param chofer         Chofer asignado
     * @param vehiculo       Vehículo utilizado
     * @param origen         Ciudad de origen
     * @param destino        Ciudad de destino
     */

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

    /**
     * Devuelve una descripción del viaje en formato legible,
     * incluyendo fecha, ciudades, horarios, vehículo y chofer.
     */

    @Override
    public String toString() {
        return """
                -----------------------------
                Viaje: """ + fecha +
                "\nDe: " + origen.getNombre() + " a " + destino.getNombre() +
                "\nSalida: " + horarioSalida + " | Llegada: " + horarioLlegada +
                "\nVehículo: " + vehiculo +
                "\nChofer: " + chofer.getNombre() + " " + chofer.getApellido();

    }

}
