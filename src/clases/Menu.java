import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorViajes gestor = new GestorViajes();

        // ciudades
        Ciudad mendoza = new Ciudad("Mendoza", Provincia.MENDOZA);
        Ciudad cordoba = new Ciudad("Cordoba", Provincia.CORDOBA); // sin tilde
        Ciudad monteCaseros = new Ciudad("Monte Caseros", Provincia.CORRIENTES);
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        ciudades.add(mendoza);
        ciudades.add(cordoba);
        ciudades.add(monteCaseros);

        // vehículos
        Vehiculo colectivo = new Colectivo("AAA111", 50, true);
        Vehiculo minibus = new Minibus("BBB222", 30, true, false);
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(colectivo);
        vehiculos.add(minibus);

        // choferes
        Chofer chofer1 = new Chofer(12345678, "Juan", "Pérez", "ABC123", Categoria.COLECTIVO);
        chofer1.agregarHabilitacion(new ChoferCategoria(Categoria.COLECTIVO, LocalDate.of(2026, 1, 1)));
        Chofer chofer2 = new Chofer(11122233, "Lucía", "García", "XYZ789", Categoria.MINIBUS);
        chofer2.agregarHabilitacion(new ChoferCategoria(Categoria.MINIBUS, LocalDate.of(2025, 12, 31)));
        ArrayList<Chofer> choferes = new ArrayList<>();
        choferes.add(chofer1);
        choferes.add(chofer2);

        // viaje precargado
        gestor.agregarViaje(new Viaje(LocalDate.of(2025, 6, 24), LocalTime.of(10, 0), LocalTime.of(12, 0), chofer1,
                colectivo, mendoza, cordoba));

        int opcion = 0;
        do {
            System.out.println("\n---------------------------");
            System.out.println("         MENU");
            System.out.println("---------------------------");
            System.out.println("1 - Cargar un nuevo viaje");
            System.out.println("2 - Mostrar todos los viajes programados");
            System.out.println("3 - Mostrar viajes de un colectivo");
            System.out.println("4 - Mostrar cantidad de viajes por chofer");
            System.out.println("0 - Salir");
            System.out.print("Opción: ");
            String entrada = sc.nextLine();

            try {
                opcion = Integer.parseInt(entrada);
                switch (opcion) {
                    case 1:
                        LocalDate fecha;
                        try {
                            System.out.print("\nIngrese la fecha (YYYY-MM-DD): ");
                            fecha = LocalDate.parse(sc.nextLine());
                            if (fecha.isBefore(LocalDate.now())) {
                                System.out.println("La fecha no puede ser anterior a hoy.");
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Use YYYY-MM-DD.");
                            break;
                        }

                        LocalTime salida;
                        try {
                            System.out.print("Hora de salida (HH:mm): ");
                            salida = LocalTime.parse(sc.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de hora inválido. Use HH:mm (ej: 08:00).");
                            break;
                        }

                        LocalTime llegada;
                        try {
                            System.out.print("Hora de llegada (HH:mm): ");
                            llegada = LocalTime.parse(sc.nextLine());
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de hora inválido. Use HH:mm (ej: 12:30).");
                            break;
                        }

                        if (!llegada.isAfter(salida)) {
                            System.out.println("La hora de llegada debe ser posterior a la de salida.");
                            break;
                        }

                        System.out.println("\nChoferes disponibles:");
                        for (Chofer c : choferes) {
                            System.out.println("- " + c.getNombre() + " " + c.getApellido() + " (Licencia: "
                                    + c.getNroLicencia() + ")");
                        }
                        System.out.println();
                        System.out.print("Ingrese número de licencia del chofer: ");
                        String lic = sc.nextLine();
                        Chofer ch = null;
                        for (Chofer c : choferes) {
                            if (c.getNroLicencia().equals(lic)) {
                                ch = c;
                            }
                        }
                        if (ch == null) {
                            System.out.println("Chofer no encontrado");
                            break;
                        }

                        System.out.println("\nVehículos disponibles:");
                        for (Vehiculo v : vehiculos) {
                            System.out.println("- Patente: " + v.getPatente() + ", Capacidad: " + v.getCapacidad());
                        }
                        System.out.println();
                        System.out.print("Ingrese patente del vehículo: ");
                        String pat = sc.nextLine();
                        Vehiculo v = null;
                        for (Vehiculo veh : vehiculos) {
                            if (veh.getPatente().equals(pat)) {
                                v = veh;
                            }
                        }
                        if (v == null) {
                            System.out.println("Vehículo no encontrado");
                            break;
                        }

                        System.out.println("\nCiudades disponibles:");
                        for (Ciudad ciu : ciudades) {
                            System.out.println("- " + ciu.getNombre() + " (" + ciu.getProvincia() + ")");
                        }
                        System.out.println();
                        System.out.print("Ingrese nombre de ciudad origen: ");
                        String nombreOrigen = sc.nextLine();
                        Ciudad origen = null;
                        for (Ciudad ciu : ciudades) {
                            if (ciu.getNombre().equalsIgnoreCase(nombreOrigen)) {
                                origen = ciu;
                            }
                        }
                        if (origen == null) {
                            System.out.println("Ciudad origen no encontrada");
                            break;
                        }

                        System.out.print("Ingrese nombre de ciudad destino: ");
                        String nombreDestino = sc.nextLine();
                        Ciudad destino = null;
                        for (Ciudad ciu : ciudades) {
                            if (ciu.getNombre().equalsIgnoreCase(nombreDestino)) {
                                destino = ciu;
                            }
                        }
                        if (destino == null) {
                            System.out.println("Ciudad destino no encontrada");
                            break;
                        }

                        Viaje nuevo = new Viaje(fecha, salida, llegada, ch, v, origen, destino);
                        gestor.agregarViaje(nuevo);
                        break;

                    case 2:
                        gestor.mostrarViajesProgramados();
                        break;
                    case 3:
                        System.out.println("Vehiculos disponibles:");
                        for (Vehiculo veh : vehiculos) {
                            if (veh instanceof Colectivo) {
                                System.out.println(veh.toString());
                            }
                        }
                        System.out.println();
                        System.out.print("Ingrese la patente del colectivo: ");
                        String patente = sc.nextLine();
                        System.out.println();
                        gestor.informarViajesDeColectivo(patente);
                        break;
                    case 4:
                        gestor.informarCantidadViajesPorChofer();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Ingrese un número de la lista:");
                opcion = -1;
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
                opcion = -1;
            }
        } while (opcion != 0);

        sc.close();
    }
}
