import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            GestorViajes gestor = new GestorViajes();

            // ciudades
            Ciudad mendoza = new Ciudad("Mendoza", Provincia.MENDOZA);
            Ciudad cordoba = new Ciudad("Cordoba", Provincia.CORDOBA);
            Ciudad parana = new Ciudad("Parana", Provincia.ENTRE_RIOS);
            ArrayList<Ciudad> ciudades = new ArrayList<>();
            ciudades.add(mendoza);
            ciudades.add(cordoba);
            ciudades.add(parana);

            // vehículos
            Vehiculo colectivo1 = new Colectivo("AAA111", 50, true);
            Vehiculo minibus1 = new Minibus("BBB222", 30, true, false);
            Vehiculo colectivo2 = new Colectivo("CCC333", 60, false);
            ArrayList<Vehiculo> vehiculos = new ArrayList<>();
            vehiculos.add(colectivo1);
            vehiculos.add(minibus1);
            vehiculos.add(colectivo2);

            // choferes
            Chofer chofer1 = new Chofer(10000001, "Juan", "Aguirre", "AG001", Categoria.COLECTIVO);
            chofer1.agregarHabilitacion(new ChoferCategoria(Categoria.COLECTIVO, LocalDate.of(2026, 1, 1)));

            Chofer chofer2 = new Chofer(10000002, "Patricia", "Balbuena", "PB002", Categoria.MINIBUS);
            chofer2.agregarHabilitacion(new ChoferCategoria(Categoria.MINIBUS, LocalDate.of(2025, 12, 31)));

            Chofer chofer3 = new Chofer(10000003, "Elizabeth", "Silva", "ES003", Categoria.COLECTIVO);
            chofer3.agregarHabilitacion(new ChoferCategoria(Categoria.COLECTIVO, LocalDate.of(2025, 11, 30)));

            ArrayList<Chofer> choferes = new ArrayList<>();
            choferes.add(chofer1);
            choferes.add(chofer2);
            choferes.add(chofer3);

            // viajes precargados
            gestor.agregarViaje(new Viaje(LocalDate.of(2025, 6, 24), LocalTime.of(10, 0), LocalTime.of(20, 0), chofer1,
                    colectivo1, mendoza, cordoba));
            gestor.agregarViaje(new Viaje(LocalDate.of(2025, 6, 25), LocalTime.of(8, 30), LocalTime.of(15, 30), chofer2,
                    minibus1, cordoba, parana));
            gestor.agregarViaje(new Viaje(LocalDate.of(2025, 6, 26), LocalTime.of(9, 0), LocalTime.of(17, 30), chofer3,
                    colectivo2, parana, mendoza));

            int opcion;
            do {
                System.out.println("\n---------------------------");
                System.out.println("         MENU");
                System.out.println("---------------------------");
                System.out.println("1 - Cargar un nuevo viaje");
                System.out.println("2 - Mostrar todos los viajes programados");
                System.out.println("3 - Mostrar viajes de un colectivo");
                System.out.println("4 - Ver cuántos viajes hizo cada chofer de colectivo");
                System.out.println("0 - Salir");
                System.out.print("Opción: ");
                String entrada = sc.nextLine();

                try {
                    opcion = Integer.parseInt(entrada);
                    switch (opcion) {
                        case 1 -> {
                            try {
                                System.out.print("\nIngrese la fecha (YYYY-MM-DD): ");
                                LocalDate fecha = LocalDate.parse(sc.nextLine());
                                if (fecha.isBefore(LocalDate.now())) {
                                    System.out.println("La fecha no puede ser anterior a hoy.");
                                    break;
                                }

                                System.out.print("Hora de salida (HH:mm): ");
                                LocalTime salida = LocalTime.parse(sc.nextLine());

                                System.out.print("Hora de llegada (HH:mm): ");
                                LocalTime llegada = LocalTime.parse(sc.nextLine());

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
                                    if (c.getNroLicencia().equalsIgnoreCase(lic)) {
                                        ch = c;
                                        break;
                                    }
                                }
                                if (ch == null) {
                                    System.out.println("Chofer no encontrado");
                                    break;
                                }

                                System.out.println("\nVehículos disponibles:");
                                for (Vehiculo v : vehiculos) {
                                    if (v != null) {
                                        String tipo = (v instanceof Colectivo) ? "Colectivo" : "Minibús";
                                        System.out.println(" - Tipo: " + tipo
                                                + ", Patente: " + v.getPatente()
                                                + ", Capacidad total: " + v.getCapacidad()
                                                + ", Disponible: " + v.getCapacidadDisponible());
                                    }
                                }
                                System.out.println();
                                System.out.print("Ingrese patente del vehículo: ");
                                String pat = sc.nextLine();
                                Vehiculo vehiculo = null;
                                for (Vehiculo v : vehiculos) {
                                    if (v.getPatente().equalsIgnoreCase(pat)) {
                                        vehiculo = v;
                                        break;
                                    }
                                }
                                if (vehiculo == null) {
                                    System.out.println("Vehículo no encontrado");
                                    break;
                                }

                                System.out.println("\nCiudades disponibles:");
                                for (Ciudad ciu : ciudades) {
                                    System.out.println("- " + ciu.getNombre() + " (" + ciu.getProvincia() + ")");
                                }
                                System.out.println();
                                System.out.print("Ingrese ciudad de origen: ");
                                String origenStr = sc.nextLine();
                                Ciudad origen = null;
                                for (Ciudad ciu : ciudades) {
                                    if (ciu.getNombre().equalsIgnoreCase(origenStr)) {
                                        origen = ciu;
                                        break;
                                    }
                                }
                                if (origen == null) {
                                    System.out.println("Ciudad de origen no encontrada");
                                    break;
                                }

                                System.out.print("Ingrese ciudad de destino: ");
                                String destinoStr = sc.nextLine();
                                Ciudad destino = null;
                                for (Ciudad ciu : ciudades) {
                                    if (ciu.getNombre().equalsIgnoreCase(destinoStr)) {
                                        destino = ciu;
                                        break;
                                    }
                                }
                                if (destino == null) {
                                    System.out.println("Ciudad de destino no encontrada");
                                    break;
                                }
                                if (vehiculo.getCapacidadDisponible() <= 0) {
                                    System.out.println("x: No hay más lugares disponibles en este vehículo.");
                                    break;
                                }

                                Viaje nuevo = new Viaje(fecha, salida, llegada, ch, vehiculo, origen, destino);
                                gestor.agregarViaje(nuevo);
                                vehiculo.setCapacidadDisponible(vehiculo.getCapacidadDisponible() - 1);

                            } catch (Exception e) {
                                System.out.println("Error al cargar el viaje: " + e.getMessage());
                            }
                        }

                        case 2 -> {
                            System.out.println();
                            gestor.mostrarViajesProgramados();
                        }
                        case 3 -> {
                            System.out.println();
                            System.out.println("Vehiculos disponibles:");
                            ArrayList<String> patentesColectivos = new ArrayList<>();
                            for (Vehiculo veh : vehiculos) {
                                if (veh instanceof Colectivo) {
                                    System.out.println(veh.toString());
                                    patentesColectivos.add(veh.getPatente());
                                }
                            }

                            System.out.println();
                            System.out.print("Ingrese la patente del colectivo: ");
                            String patente = sc.nextLine();
                            System.out.println();

                            if (!patentesColectivos.contains(patente)) {
                                System.out.println("Patente no válida. No corresponde a ningún colectivo disponible.");
                                break;
                            }

                            gestor.informarViajesDeColectivo(patente);
                        }

                        case 4 -> {
                            System.out.println();
                            gestor.informarCantidadViajesPorChofer();
                        }
                        case 0 -> System.out.println("Saliendo del sistema...");
                        default -> System.out.println("Opción inválida");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida. Ingrese un número de la lista:");
                    opcion = -1;
                } catch (Exception e) {
                    System.out.println("Ocurrió un error: " + e.getMessage());
                    opcion = -1;
                }
            } while (opcion != 0);
        }
    }
}
