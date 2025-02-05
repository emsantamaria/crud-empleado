package operations;
import java.util.TreeMap;

import model.Empleado;

/**
 * @author santiago
 * @version 1.0
 */

public interface Operations {
    boolean create(Empleado empleado);
    Empleado read(String identificador);
    Empleado read(Empleado empleado);
    boolean update(Empleado empleado);
    boolean delete(String identificador);
    boolean delete(Empleado empleado);
    TreeMap<String,Empleado> empleadosPorPuesto(String puesto);
    TreeMap<String,Empleado> empleadosPorEdad(String fechaInicio, String fechaFin);
}
