public abstract class Vehiculo {
    private final String patente;
    private final int capacidad;

    public Vehiculo(String patente, int capacidad) {
        this.patente = patente;
        this.capacidad = capacidad;
    }

    public String getPatente() {
        return patente;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public abstract String toString();
}
