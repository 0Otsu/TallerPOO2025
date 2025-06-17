public class Minibus extends Vehiculo {
    private final boolean bodega;
    private final boolean aireAcondicionado;

    public Minibus(String patente, int capacidad, boolean bodega, boolean aireAcondicionado) {
        super(patente, capacidad);
        this.bodega = bodega;
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean getBodega() {
        return bodega;
    }

    public boolean getAireAcondicionado() {
        return aireAcondicionado;
    }

    @Override
    public String toString() {
        return "Minibús - Patente: " + getPatente() +
                ", Capacidad: " + getCapacidad() +
                ", Bodega: " + (bodega ? "Sí" : "No") +
                ", Aire Acondicionado: " + (aireAcondicionado ? "Sí" : "No");
    }
}
