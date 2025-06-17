
import java.util.ArrayList;

public class GestorViajes {
    private final ArrayList<Viaje> viajes;

    public GestorViajes() {
        viajes = new ArrayList<>();
    }

    public void agregarViaje(Viaje nuevo) {
       
    // ✅ 1. Validar que origen y destino no sean la misma ciudad
    // ¿Por qué? No tiene sentido hacer un viaje de una ciudad hacia la misma ciudad.
        if(nuevo.getOrigen().equals(nuevo.getDestino())){
            throw new IllegalArgumentException("El origen y el destino no pueden ser iguales.");
        }
     // ✅ 2. Validar que el chofer no tenga otro viaje ese día con horarios superpuestos
    // ¿Por qué? Un chofer no puede estar en dos lugares al mismo tiempo.
        
     // ✅ 3. Validar que el vehículo no esté ocupado en otro viaje ese día
    // ¿Por qué? El mismo vehículo no puede estar en dos viajes a la vez.

     // ✅ 4. Validar que el chofer esté habilitado para el tipo de vehículo y que la habilitación esté vigente
    // ¿Por qué? Un chofer no puede manejar un vehículo si no tiene la categoría correspondiente o si está vencida.

     // ✅ Si pasó todas las validaciones, agregamos el viaje
        viajes.add(nuevo);
        System.out.println("✅ Viaje agregado correctamente.");
    }
}
