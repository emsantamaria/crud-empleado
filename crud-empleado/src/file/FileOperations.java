package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;


import model.Empleado;
import operations.Operations;

public class FileOperations implements Operations{
    String path = "src/resources/empleado.txt";
    File fichero;
    public FileOperations() {
        fichero=new File(path);
        if (!fichero.isFile()) {
            throw new IllegalArgumentException("El recurso no es de tipo fichero " + path);
        }
        try {
            fichero.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Añade un empleado al archivo
     */

    @Override
    public boolean create(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        TreeMap<String,Empleado> empleados = read(fichero);
        if (empleados.containsKey(empleado.getIdentificador())) {
            return false;
        }
        return create(empleado.toString(), fichero);
    }

    private boolean create(String data, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(data);
            writer.newLine(); 
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Lee la informacion del archivo
     * @param file lugar de la informacion
     * @return Set<Empleado>
     */

    private TreeMap<String,Empleado> read(File file) {
        if (file == null) {
            return new TreeMap<>();
        }
        TreeMap<String,Empleado> empleados = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayLine = line.trim().split(",");
                Empleado empleado = new Empleado(arrayLine[0], arrayLine[1], arrayLine[2], Double.valueOf(arrayLine[3]), arrayLine[4]);
                empleados.put(empleado.getIdentificador(), empleado);
            }
        } catch (IOException e) {
            return new TreeMap<>();
        }
        return empleados;
    }

    /**
     * Metodo que actualiza la informacion de un empleado
     * @param empleado que van actualizar
     * @return true/false
     */

    @Override
    public boolean update(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        TreeMap<String,Empleado> empleados = read(fichero);
        if (!empleados.containsKey(empleado.getIdentificador())) {
            return false;
        }
        empleados.remove(empleado.getIdentificador()); 
        empleados.put(empleado.getIdentificador(), empleado); 
        return updateFile(empleados, fichero);
    }

    /**
     * Actualiza la informacion del fichero
     * @param empleados de la empresa
     * @param file lugar de la informacion
     * @return true/false
     */
    private boolean updateFile(TreeMap<String,Empleado> empleados, File file) {
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        for (Empleado empleado : empleados.values()) {
            create(empleado);
        }
        return true;
    }

    /**
     * Elimina a un empleado
     */

    @Override
    public boolean delete(Empleado empleado) {
        if (empleado == null || empleado.getIdentificador() == null || empleado.getIdentificador().isEmpty()) {
            return false;
        }
        TreeMap<String,Empleado> empleados = read(fichero);
        if (!empleados.containsKey(empleado.getIdentificador())) {
            return false;
        }
        empleados.remove(empleado.getIdentificador());
        return updateFile(empleados, fichero);
    }

    /**
     * Lee a un empleado por su identificador
     */

    @Override
    public Empleado read(String identificador) {
        return read(new Empleado(identificador));
    }

    /**
     * Lee a un empleado por su tipo objeto empleado
     */

    @Override
    public Empleado read(Empleado empleado) {
        TreeMap<String,Empleado> empleados = read(fichero);
        for (Empleado empleado2 : empleados.values()) {
            if (empleado2.equals(empleado)) {
                return empleado2;
            }
        }
        return null;
    }

    /**
     * retorna una lista de los empleados de un puesto indicado
     */

    @Override
    public TreeMap<String,Empleado> empleadosPorPuesto(String puesto) {
        if (puesto == null || puesto.isEmpty()) {
            return new TreeMap<>();
        }
        TreeMap<String,Empleado> empleados = read(fichero);
        TreeMap<String,Empleado> porPuesto = new TreeMap<>();
        for (Empleado empleado : empleados.values()) {
            if (empleado.getPuesto().trim().equals(puesto.trim())) {
                porPuesto.put(empleado.getIdentificador(),empleado);
            }
        }
        return porPuesto;
    }

    /**
     * Lista los empleados por su edad
     */
    
    @Override
    public TreeMap<String ,Empleado> empleadosPorEdad(String fechaInicio, String fechaFin) {
        if (fechaInicio == null || fechaInicio.isEmpty() || fechaFin == null || fechaFin.isEmpty()) {
            return new TreeMap<>();
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate inicio = LocalDate.parse(fechaInicio, formato);
        LocalDate fin = LocalDate.parse(fechaFin, formato);
        TreeMap<String,Empleado> empleados = read(fichero);
        TreeMap<String,Empleado> porEdad = new TreeMap<>();
        for (Empleado empleado : empleados.values()) {
            LocalDate cumpleanio = LocalDate.parse(empleado.getFechaNacimiento(), formato);
            if (cumpleanio.isAfter(inicio) && cumpleanio.isBefore(fin)) {
                porEdad.put(empleado.getIdentificador(), empleado);
            }
        }
        return porEdad;
    }
    /**
     * Metodo que permite eliminar a un empleado mediante su identficador
     * @param identificador
     * @return
     */
    @Override
    public boolean delete(String identificador) {
        if(identificador==null||identificador.isEmpty()){
            return false;
        }
        Empleado empleado=new Empleado(identificador);
        return delete(empleado);
    }
}
