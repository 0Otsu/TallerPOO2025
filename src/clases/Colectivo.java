public class Colectivo extends Vehiculo {
    private final boolean pisoDoble;

    public Colectivo(String patente, int capacidad, boolean pisoDoble) {
        super(patente, capacidad);
        this.pisoDoble = pisoDoble;
    }

    public boolean getPisoDoble() {
        return pisoDoble;
    }

    @Override
    public String toString() {
        return "Colectivo - Patente: " + getPatente() +
                ", Capacidad: " + getCapacidad() +
                ", Piso doble: " + (pisoDoble ? "Sí" : "No");
    }
}
