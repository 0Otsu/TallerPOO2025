
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GestorViajes {
    private final ArrayList<Viaje> viajes;

    public GestorViajes() {
        viajes = new ArrayList<>();
    }

    public void agregarViaje(Viaje nuevo) {

        // ✅ 1. Validar que origen y destino no sean la misma ciudad
        // ¿Por qué? No tiene sentido hacer un viaje de una ciudad hacia la misma
        // ciudad.
        if (nuevo.getOrigen().equals(nuevo.getDestino())) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
        }
        // ✅ 2. Validar que el chofer no tenga otro viaje ese día con horarios
        // superpuestos
        // ¿Por qué? Un chofer no puede estar en dos lugares al mismo tiempo.
        for (Viaje v : viajes) {
            // Si el viaje ya existe con el mismo chofer y en la misma fecha
            if (v.getChofer().equals(nuevo.getChofer()) && v.getFecha().equals(nuevo.getFecha())) {

                // Obtenemos horarios de ambos viajes
                LocalTime salida1 = v.getHorarioSalida();
                LocalTime llegada1 = v.getHorarioLlegada();
                LocalTime salida2 = nuevo.getHorarioSalida();
                LocalTime llegada2 = nuevo.getHorarioLlegada();

                // Verificamos si los horarios se superponen
                boolean seSuperpone = !(llegada1.isBefore(salida2) || salida1.isAfter(llegada2));

                // Si se superponen, lanzamos excepción
                if (seSuperpone) {
                    throw new IllegalArgumentException("El chofer ya tiene un viaje ese día en ese horario.");
                }
            }
        }

        // ✅ 3. Validar que el vehículo no esté ocupado en otro viaje ese día
        // ¿Por qué? El mismo vehículo no puede estar en dos viajes a la vez.
        for (Viaje v : viajes) {
            if (v.getVehiculo().equals(nuevo.getVehiculo()) && v.getFecha().equals(nuevo.getFecha())) {
                // Obtenemos horarios de ambos viajes
                LocalTime salida1 = v.getHorarioSalida();
                LocalTime llegada1 = v.getHorarioLlegada();
                LocalTime salida2 = nuevo.getHorarioSalida();
                LocalTime llegada2 = nuevo.getHorarioLlegada();

                // Verificamos si los horarios se superponen
                boolean seSuperpone = !(llegada1.isBefore(salida2) || salida1.isAfter(llegada2));
                // Si se superponen, lanzamos excepción
                if (seSuperpone) {
                    throw new IllegalArgumentException(
                            "Vehiculo no disponible para ese horario(superposición de horarios)");
                }
            }
        }

        // ✅ 4. Validar que el chofer esté habilitado para el tipo de vehículo y que la
        // habilitación esté vigente
        // ¿Por qué? Un chofer no puede manejar un vehículo si no tiene la categoría
        // correspondiente o si está vencida.

        // Determinar qué categoría se necesita
        // Determinar qué categoría se necesita
        Categoria requerida;

        if (nuevo.getVehiculo() instanceof Colectivo) {
            requerida = Categoria.COLECTIVO;
        } else if (nuevo.getVehiculo() instanceof Minibus) {
            requerida = Categoria.MINIBUS;
        } else {
            throw new IllegalArgumentException("Tipo de vehículo desconocido.");
        }

        // Buscar entre las habilitaciones del chofer si tiene la adecuada y vigente
        boolean habilitado = false;

        for (ChoferCategoria h : nuevo.getChofer().getHabilitaciones()) {
            if (h.getCategoria() == requerida && h.getFechaVencimiento().isAfter(nuevo.getFecha())) {
                habilitado = true;
                break;
            }
        }

        if (!habilitado) {
            throw new IllegalArgumentException(
                    "El chofer no está habilitado o su licencia está vencida para este vehículo.");
        }

        // ✅ Si pasó todas las validaciones, agregamos el viaje
        viajes.add(nuevo);
        System.out.println(" Viaje agregado correctamente.");
    }

    public void mostrarViajesProgramados() {
        for (Viaje v : viajes) {
            System.out.println(v.toString());
        }
    }

    public void informarViajesDeColectivo(String patente) {
        for (Viaje v : viajes) {
            if (patente.equals(v.getVehiculo().getPatente())) {
                System.out.println(v.toString());
            }
        }
    }

    public void informarCantidadViajesPorChofer() {
        Map<Chofer, Integer> contador = new HashMap<>();

        for (Viaje v : viajes) {
            if (v.getVehiculo() instanceof Colectivo) {
                Chofer chofer = v.getChofer();
                contador.put(chofer, contador.getOrDefault(chofer, 0) + 1);
            }
        }

        for (Map.Entry<Chofer, Integer> entrada : contador.entrySet()) {
            Chofer c = entrada.getKey();
            System.out.println("Chofer: " + c.getNombre() + " " + c.getApellido()
                    + " - Viajes realizados: " + entrada.getValue());
        }
    }

}
