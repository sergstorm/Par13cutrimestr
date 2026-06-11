package GUI;

import Bll.Alumno;
import Bll.Professor;
import Bll.Usario;
import Repo.Hashing;

import javax.swing.*;

public class Main
{
    public static void main(String[] args) {
        String contrasena = Hashing.hash("12345");
        JOptionPane.showMessageDialog(null,contrasena);
        JOptionPane.showMessageDialog(null,Hashing.verificar("12345",contrasena));

        String[] acciones = {"Login","Registrar","Salir"};
        int menu = 0;
        do
        {
            menu = JOptionPane.showOptionDialog(null,"Bienvenido",null,0,0,null,acciones,acciones[0]);
            switch (menu)
            {
                case 0:
                    Usario usario = Usario.login();
                    if(usario!=null)
                        if(usario instanceof Professor)
                        { JOptionPane.showMessageDialog(null,"Bienvenido Professor"+usario.getNombre());
                         usario.Menu();}
                        else if( usario instanceof Alumno)
                        {
                            JOptionPane.showMessageDialog(null,"Bienvenido Alumno"+usario.getNombre());
                        }
                        break;
                case 1:
                    Usario.registrarse();
                    break;
            }

        } while (menu!=2);

    }
}
