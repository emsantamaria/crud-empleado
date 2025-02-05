import file.FileOperations;
import model.Empleado;
import operations.Operations;

public class App {
    public static void main(String[] args) throws Exception {
        FileOperations fileOperations=new FileOperations();
        Empleado empleado=new Empleado("1","pedro","gerente",23,"02/04/11");
        Empleado empleado2=new Empleado("null", "null", "null", 0, "01/03/98");
        Empleado empleado3=new Empleado("2", "null", "null", 0, "01/01/2000");
        fileOperations.create(empleado);
        fileOperations.delete("1");
        fileOperations.create(empleado);
        fileOperations.create(empleado);
        fileOperations.create(empleado2);
        System.out.println(fileOperations.empleadosPorPuesto("null"));
        fileOperations.create(empleado3);
    }
}

      