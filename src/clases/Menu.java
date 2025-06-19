import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // datos de prueba (pueden estar precargados)
        GestorViajes gestor = new GestorViajes();
        Ciudad mendoza = new Ciudad("Mendoza", Provincia.MENDOZA);
        Ciudad cordoba = new Ciudad("Córdoba", Provincia.CORDOBA);
        LocalDate fecha = LocalDate.of(2025, 6, 24);

        Vehiculo colectivo = new Colectivo("AAA111", 50, true);
        Chofer chofer1 = new Chofer(12345678, "Juan", "Pérez", "ABC123", Categoria.COLECTIVO);
        chofer1.agregarHabilitacion(new ChoferCategoria(Categoria.COLECTIVO, LocalDate.of(2026, 1, 1)));

        // viaje válido ya cargado
        gestor.agregarViaje(
                new Viaje(fecha, LocalTime.of(10, 0), LocalTime.of(12, 0), chofer1, colectivo, mendoza, cordoba));

        int opcion;
        do {
            System.out.println("\nMENU");
            System.out.println("1 - Mostrar todos los viajes programados");
            System.out.println("2 - Mostrar viajes de un colectivo");
            System.out.println("3 - Mostrar cantidad de viajes por chofer");
            System.out.println("0 - Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    gestor.mostrarViajesProgramados();
                    break;
                case 2:
                    System.out.print("Ingrese la patente del colectivo: ");
                    String patente = sc.nextLine();
                    gestor.informarViajesDeColectivo(patente);
                    break;
                case 3:
                    gestor.informarCantidadViajesPorChofer();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }
}
