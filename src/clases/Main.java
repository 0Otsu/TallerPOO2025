import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        Ciudad mendoza = new Ciudad("Mendoza", Provincia.MENDOZA);
        Ciudad cordoba = new Ciudad("Córdoba", Provincia.CORDOBA);
        LocalDate fecha = LocalDate.of(2025, 6, 24);
        Vehiculo colectivo = new Colectivo("AAA111", 50, true);
        GestorViajes gestor = new GestorViajes();

        Chofer chofer1 = new Chofer(12345678, "Juan", "Pérez", "ABC123", Categoria.COLECTIVO);
        chofer1.agregarHabilitacion(new ChoferCategoria(Categoria.COLECTIVO, LocalDate.of(2026, 1, 1)));

        Chofer chofer2 = new Chofer(98765432, "Lucía", "García", "XYZ999", Categoria.MINIBUS);
        chofer2.agregarHabilitacion(new ChoferCategoria(Categoria.MINIBUS, LocalDate.of(2024, 1, 1)));

        System.out.println("Prueba 1: origen igual a destino (debería fallar)");
        try {
            Viaje viaje1 = new Viaje(fecha, LocalTime.of(8, 0), LocalTime.of(10, 0), chofer1, colectivo, mendoza,
                    mendoza);
            gestor.agregarViaje(viaje1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }

        System.out.println("Prueba 2: viaje válido (debería agregarse bien)");
        Viaje viajeValido = new Viaje(fecha, LocalTime.of(10, 0), LocalTime.of(12, 0), chofer1, colectivo, mendoza,
                cordoba);
        gestor.agregarViaje(viajeValido);

        System.out.println("Prueba 3: chofer ocupado (debería fallar)");
        try {
            Viaje viaje2 = new Viaje(fecha, LocalTime.of(11, 30), LocalTime.of(13, 0), chofer1,
                    new Colectivo("BBB222", 40, true), mendoza, cordoba);
            gestor.agregarViaje(viaje2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }

        System.out.println("Prueba 4: vehículo ocupado (debería fallar)");
        try {
            Chofer chofer3 = new Chofer(44444444, "Carlos", "Lopez", "LMN777", Categoria.COLECTIVO);
            chofer3.agregarHabilitacion(new ChoferCategoria(Categoria.COLECTIVO, LocalDate.of(2026, 5, 1)));

            Viaje viaje3 = new Viaje(fecha, LocalTime.of(11, 0), LocalTime.of(12, 30), chofer3, colectivo, mendoza,
                    cordoba);
            gestor.agregarViaje(viaje3);
        } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }

        System.out.println("Prueba 5: chofer sin habilitación válida (debería fallar)");
        try {
            Viaje viaje4 = new Viaje(fecha, LocalTime.of(14, 0), LocalTime.of(16, 0), chofer2, colectivo, mendoza,
                    cordoba);
            gestor.agregarViaje(viaje4);
        } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }

        System.out.println("");
        System.out.println("== VIAJES PROGRAMADOS ==");
        gestor.mostrarViajesProgramados();

        System.out.println("");
        System.out.println("== VIAJES DEL COLECTIVO AAA111 ==");
        gestor.informarViajesDeColectivo("AAA111");

        System.out.println("");
        System.out.println("== VIAJES POR CHOFER ==");
        gestor.informarCantidadViajesPorChofer();

    }
}
