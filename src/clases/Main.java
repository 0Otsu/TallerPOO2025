import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // Creamos una ciudad (origen y destino iguales)
        Ciudad ciudadorigen = new Ciudad("Mendoza", Provincia.MENDOZA);
       // Ciudad ciudadDestino = new Ciudad("corrientes", Provincia.CORRIENTES);
        // Creamos chofer (datos mínimos)
        Chofer chofer = new Chofer(12345678, "Juan", "Pérez", "ABC123", Categoria.COLECTIVO);

        // Creamos vehículo (un colectivo simple)
        Vehiculo colectivo = new Colectivo("AAA111", 50, true);

        // Fecha y horarios del viaje
        LocalDate fecha = LocalDate.of(2025, 6, 24);
        LocalTime salida = LocalTime.of(8, 0);
        LocalTime llegada = LocalTime.of(12, 30);

        // Creamos el viaje con origen = destino
        Viaje viajePrueba = new Viaje(fecha, salida, llegada, chofer, colectivo, ciudadorigen, ciudadorigen);

        // Creamos el gestor de viajes
        GestorViajes gestor = new GestorViajes();

        // Intentamos agregar el viaje
        try {
            gestor.agregarViaje(viajePrueba);

        } catch (IllegalArgumentException e) {
            System.out.println("PRIMERA EXCEPCION;");
            System.out.println(" Error: " + e.getMessage());
        }

    }
}