package Bll;

import javax.swing.*;

public class Alumno extends Usario
{
    public Alumno(int id, String nombre, String email, String tipo, String password) {
        super(id, nombre, email, tipo, password);
    }

    public Alumno(String nombre, String email, String tipo, String password) {
        super(0, nombre, email, tipo, password);
    }

    public Alumno() {
        super();
    }

    public Alumno(String ingreseNombre, String ingreseEmail, String ingresePassword) {

    }

    @Override
    public String toString() {
        return "Alumno [to string"+super.toString()+"]";
    }

    @Override
    public void Menu() {
        String[] opciones = { "Ver usarios","Salir" };
        int opcion;
        do
        {
            opcion = JOptionPane.showOptionDialog(null,"Select en Opcion","",0,0,null
            ,opciones,opciones);
            switch (opcion)
            {
                case 0:
                    JOptionPane.showMessageDialog(null,this.getController().mostrarUsarios());
                default: break;
            }
        }while (opcion !=2 );
    }
}
