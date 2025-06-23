import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase encargada de gestionar los viajes registrados en el sistema.
 * Permite agregar viajes con validaciones y generar informes según los
 * requerimientos de la empresa.
 */
public class GestorViajes {
    private final ArrayList<Viaje> viajes;

    public GestorViajes() {
        viajes = new ArrayList<>();
    }

    /**
     * Agrega un nuevo viaje al sistema realizando todas las validaciones:
     * - Que el origen y destino sean distintos
     * - Que el chofer y vehículo estén disponibles
     * - Que el chofer esté habilitado para el tipo de vehículo
     *
     * @param nuevo el viaje a agregar
     * @throws IllegalArgumentException si falla alguna validación
     */

    public void agregarViaje(Viaje nuevo) {

        // Validar que origen y destino sean distintos

        if (nuevo.getOrigen().equals(nuevo.getDestino())) {
            throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
        }
        // Verifica que el chofer no tenga otro viaje con horarios que se superpongan

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

        // Verifica que el vehículo no tenga asignado otro viaje que se superponga en
        // horario
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

        // Verifica que el chofer tenga una habilitación vigente para el tipo de
        // vehículo
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

        // Si paso todas las validaciones, se agrega el viaje
        viajes.add(nuevo);
        System.out.println(" Viaje agregado correctamente.");
    }

    /**
     * Muestra todos los viajes cargados en el sistema.
     */

    public void mostrarViajesProgramados() {
        for (Viaje v : viajes) {
            System.out.println(v.toString());
            System.out.println("");
        }
    }

    /**
     * Muestra todos los viajes asignados a un colectivo según su patente.
     *
     * @param patente patente del colectivo a consultar
     */

    public void informarViajesDeColectivo(String patente) {
        for (Viaje v : viajes) {
            if (patente.equals(v.getVehiculo().getPatente())) {
                System.out.println(v.toString());
                System.out.println();
            }
        }
    }

    /**
     * Muestra un informe con la cantidad de viajes realizados por cada chofer de
     * colectivo.
     * Solo se cuentan viajes hechos con vehículos del tipo Colectivo.
     */

    public void informarCantidadViajesPorChofer() {
        Map<Chofer, Integer> contador = new HashMap<>();

        for (Viaje v : viajes) {
            if (v.getVehiculo() instanceof Colectivo) {
                Chofer chofer = v.getChofer();
                contador.put(chofer, contador.getOrDefault(chofer, 0) + 1);
            }
        }

        // Encabezado
        System.out.printf("%-20s %-20s %s%n", "Nombre", "Apellido", "Viajes realizados");
        System.out.println("---------------------------------------------------------");

        // Datos
        for (Map.Entry<Chofer, Integer> entrada : contador.entrySet()) {
            Chofer c = entrada.getKey();
            System.out.printf("%-20s %-20s %d%n",
                    c.getNombre(), c.getApellido(), entrada.getValue());
        }

    }

}
