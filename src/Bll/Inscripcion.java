package Bll;

import Dll.ControllerInscripcion;

import java.time.LocalDate;
import java.util.LinkedList;

public class Inscripcion
{
    private static ControllerInscripcion controller = new ControllerInscripcion();

    private int id;
    private int idAlumno;
    private int idMateria;
    private LocalDate fecha;
    private String nombreMateria;
    private String nombreUsario;
    // Con esto

    public Inscripcion(int id, int idAlumno, int idMateria, LocalDate fecha) {
        super();
        this.id = id;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
        this.fecha = fecha;
    }

    public Inscripcion(LocalDate fecha, String nombreMateria, String nombreUsario) {
        this.fecha = fecha;
        this.nombreMateria = nombreMateria;
        this.nombreUsario = nombreUsario;
    }

    public static ControllerInscripcion getController() {
        return controller;
    }

    public static void setController(ControllerInscripcion controller) {
        Inscripcion.controller = controller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getNombreUsario() {
        return nombreUsario;
    }

    public void setNombreUsario(String nombreUsario) {
        this.nombreUsario = nombreUsario;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "fecha=" + fecha +
                ", nombreMateria='" + nombreMateria + '\'' +
                ", nombreUsario='" + nombreUsario + '\'' +
                '}';
    }

    public static void Inscribir(Materia materia, Alumno alumno)
    {
        controller.agregarInscripcion(materia, alumno, LocalDate.now());
    }
    public static LinkedList<Inscripcion> verInscriptos(Materia materia)
    {
        return controller.mostrarInscripcionsDeProfessor(materia);
    }
}
