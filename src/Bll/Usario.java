package Bll;

import Dll.ControllerUsario;
import Repo.Hashing;
import Repo.Validaciones;

import javax.swing.*;

public abstract class Usario
{
    protected int id;
    protected String nombre;
    protected String email;
    protected String tipo;
    protected String password;
    protected static ControllerUsario controller = new ControllerUsario();

    public Usario(int id, String nombre, String email, String tipo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
        this.password = password;
    }

    public Usario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + tipo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static ControllerUsario getController(){return controller;}
    public static void setController(){Usario.controller = controller;}
    public static Usario login()
    {
        String nombre = "";
        while (nombre.isEmpty())
        {
            nombre = JOptionPane.showInputDialog("Ingrese nombre");
            if(nombre.isEmpty())
            {
                JOptionPane.showMessageDialog(null," Incorrecto ");
            }
        }
        String contrasena = "";
        while (contrasena.isEmpty())
        {
            contrasena = JOptionPane.showInputDialog("Ingrese contrasena");
            if(contrasena.isEmpty())
            {
                JOptionPane.showMessageDialog(null," Incorrecto ");
            }
        }
        return controller.login(nombre,contrasena);
    }

    public abstract void Menu();

    public static void registrarse(){
        String nombre = Validaciones.validarIngresoString("Ingrese nombre");
        String email = Validaciones.validarIngresoString("Ingrese email");
        String contrasena = Validaciones.validarIngresoString("Ingrese contrasena");
        String contrasenaOculta = Hashing.hash(contrasena);
        getController().agregarUsario(new Alumno(nombre,email,"Alumno",contrasenaOculta));
    }

}
