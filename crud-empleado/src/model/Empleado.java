package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @version 1.0.0
 * @author emsantamaria
 */
public class Empleado {
     private String identificador;
    private String nombre;
    private String puesto;
    private double salario;
    private String fechaNacimiento;

    /**
     * Constructor vacío.
     */
    public Empleado() {

    }
    /**
     * Constructor unicamente con identificador
     * @param identificador
     */
    public Empleado(String identificador){
        this.identificador=identificador;
    }
    /**
     * Constructor entero.
     * @param identificador
     * @param nombre
     * @param puesto
     * @param salario
     * @param fechaNacimiento
     * 
     */
    public Empleado(String identificador, String nombre, String puesto, double salario, String fechaNacimiento) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaNacimiento = fechaNacimiento;
    }

    //Constructor con los Getters y Setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
/**
 * Metodo que permite calucular la edad del empleado
 * @return anios del empleado
 */
    public int getEdad() {
        LocalDate birthDate = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return birthDate.compareTo(LocalDate.now());
    }

    @Override
    public String toString() {
        return identificador + ", " + nombre + ", " + puesto + ", " + salario + ", " + fechaNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado)) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(identificador, empleado.identificador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }
}
