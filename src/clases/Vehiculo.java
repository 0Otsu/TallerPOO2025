public abstract class Vehiculo {
    private final String patente;
    private final int capacidad;
    private int capacidadDisponible; // NUEVO

    public Vehiculo(String patente, int capacidad) {
        this.patente = patente;
        this.capacidad = capacidad;
        this.capacidadDisponible = capacidad; // Se inicia con la capacidad total
    }

    public String getPatente() {
        return patente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(int capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }

    @Override
    public String toString() {
        return "Patente: " + patente +
                ", Capacidad total: " + capacidad +
                ", Capacidad disponible: " + capacidadDisponible;
    }
}
