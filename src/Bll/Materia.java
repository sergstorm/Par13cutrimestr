package Bll;

import Dll.ControllerMateria;
import Dll.ControllerUsario;
import Repo.Validaciones;

import java.util.LinkedList;

public class Materia
{
    private int id;
    private String nombre;
    private int usuario_id;
    private static ControllerMateria controller = new ControllerMateria();

    public Materia(int id, String nombre, int usuario_id) {
        this.id = id;
        this.nombre = nombre;
        this.usuario_id = usuario_id;
    }

    public Materia(int idUsario, String nombre) {
        this.usuario_id = idUsario;
        this.nombre = nombre;
    }

    public Materia(String nombre, int id) {
        this.nombre=nombre;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdUsuario() {
        return usuario_id;
    }

    public void setIdUsario(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public static ControllerMateria getController() {
        return controller;
    }

    public static void setController(ControllerMateria controller) {
        Materia.controller = controller;
    }

    @Override
    public String toString() {
        return "Materia{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idUsario=" + usuario_id +
                '}';
    }
    public static void CrearMateria(Usario usuario){
          String nombre = Validaciones.validarIngresoString("Ingrese nombre de materia");
          controller.agregarMateria(new Materia(nombre,usuario.getId()));
    }
    public static void moMateria(Usario usuario){
        String nombre = Validaciones.validarIngresoString("Ingrese nombre de materia");
        controller.agregarMateria(new Materia(nombre,usuario.getId()));
    }


    public static LinkedList<Materia> mostrarMateriasPropias(Usario usuario)
    {
        return controller.mostrarMateriasDeProfessor(usuario);
    }
}
