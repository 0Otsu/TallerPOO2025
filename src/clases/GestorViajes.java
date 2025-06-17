
import java.time.LocalTime;
import java.util.ArrayList;

public class GestorViajes {
    private final ArrayList<Viaje> viajes;

    public GestorViajes() {
        viajes = new ArrayList<>();
    }

    public void agregarViaje(Viaje nuevo) {

        // 1. Validar origen y destino distintos
        if (nuevo.getOrigen().equals(nuevo.getDestino())) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser la misma ciudad");
        }

        // 2. Validar que el chofer no tenga superposición de horarios
        for (Viaje v : viajes) {
            if (v.getChofer().equals(nuevo.getChofer()) && v.getFecha().equals(nuevo.getFecha())) {
                LocalTime salida1 = v.getHorarioSalida();
                LocalTime llegada1 = v.getHorarioLlegada();
                LocalTime salida2 = nuevo.getHorarioSalida();
                LocalTime llegada2 = nuevo.getHorarioLlegada();

                boolean seSuperpone = !(llegada1.isBefore(salida2) || llegada2.isBefore(salida1));
                if (seSuperpone) {
                    throw new IllegalArgumentException("El chofer ya tiene un viaje en ese horario.");
                }
            }
        }

        // 3. Validar que el chofer esté habilitado para el tipo de vehículo
        Categoria tipoVehiculo;
        if (nuevo.getVehiculo() instanceof Colectivo) {
            tipoVehiculo = Categoria.COLECTIVO;
        } else if (nuevo.getVehiculo() instanceof Minibus) {
            tipoVehiculo = Categoria.MICROBUS;
        } else {
            throw new IllegalArgumentException("❌ Tipo de vehículo desconocido.");
        }
        boolean habilitado = nuevo.getChofer().getHabilitaciones().stream()
                .anyMatch(h -> h.getCategoria() == tipoVehiculo &&
                        !h.getFechaVencimiento().isBefore(nuevo.getFecha()));

        if (!habilitado) {
            throw new IllegalArgumentException(
                    " El chofer no está habilitado para manejar ese tipo de vehículo en esa fecha.");
        }

        // Si todo se valida, agregamos el viaje
        viajes.add(nuevo);
        System.out.println("✅ Viaje agregado correctamente.");
    }
}
