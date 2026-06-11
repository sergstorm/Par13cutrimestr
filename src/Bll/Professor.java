package Bll;

import Repo.Hashing;
import Repo.Validaciones;

import javax.swing.*;
import javax.xml.validation.Validator;
import java.util.LinkedList;

public class Professor extends Usario implements Validaciones
{
    public Professor(int id, String nombre, String email, String tipo, String password) {
        super(id, nombre, email, tipo, password);
    }

    @Override
    public String toString() {
        return "Professor[to String() = " +
                "nombre='" +super.toString() +
                "]";
    }

    @Override
    public void Menu() {
             String[] opciones =
                     {
                             "Agregar alumnos","Ver Alumno","Eliminar alumno","Editar alumno",
                             "AgregarMateria","Ver Materias","Inscribir a Materia","Ver inscripciones","Salir"
                     };
             int opcion;
             do
             {
                 opcion = JOptionPane.showOptionDialog(null,"Elija option","",
                         0,0,null
                 ,opciones,opciones);
                 switch (opcion){
                     case 0:getController().agregarUsario(new Alumno(Validaciones.validarIngresoString("Ingrese nombre"),
                             Validaciones.validarIngresoString("Ingrese Email"),Validaciones.validarIngresoString("Ingrese Password")));
                     break;
                     case 1: JOptionPane.showMessageDialog(null,this.getController().mostrarAlumnos());break;
                     case 2: Alumno elegido = BusarAlumno();
                     int confir = JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar a :" + elegido);
                     if (confir == JOptionPane.YES_OPTION)
                     {
                         this.getController().EliminarUsario(elegido);
                     } break;
                     case 3: elegido = BusarAlumno(); String[] datos = {"Nombre","Tipo","Contrasena","Editar"}; int elegir;
                     do
                     {
                         elegir = JOptionPane.showOptionDialog(null,"Informacion actual" + elegido+ "\n Elija que campo quiere editar","",
                                 0,0,null,datos,datos[0]);
                         switch (elegir)
                         {
                             case 0: elegido.setNombre(JOptionPane.showInputDialog("Ingresar Nombre"));
                                 break;
                             case  1: elegido.setTipo(JOptionPane.showInputDialog("Ingresar Tipo"));
                                 break;
                             case  2: elegido.setPassword(Hashing.hash(JOptionPane.showInputDialog("Ingresar Contrasena")));
                                 break;
                             default:break;
                         }
                     } while (elegir !=3);
                     this.getController().EditarUsario(elegido);break;
                     default:break;
                     case 4: Materia.CrearMateria((this));break;
                     case 5:LinkedList<Materia> materias = Materia.mostrarMateriasPropias(this);
                     JOptionPane.showMessageDialog(null,materias.isEmpty()?"no hay materias": materias);break;
                     case 6: materias = Materia.mostrarMateriasPropias(this);
                     String []stringMaterias = new String[materias.size()];
                         for (int i = 0; i < stringMaterias.length; i++)
                         {
                           stringMaterias[i] = materias.get(i).getNombre();
                         }
                         int materiaElegida = JOptionPane.showOptionDialog(null,"","",0,0,null,stringMaterias,stringMaterias[0]);
                         Materia materiaSelec = materias.get(materiaElegida);
                         Alumno encontrado = BusarAlumno();
                         Inscripcion.Inscribir(materiaSelec, encontrado);
                         JOptionPane.showMessageDialog(null, materias.isEmpty()?"No hay materia": materias);break;
                     case 7: materias = Materia.mostrarMateriasPropias(this);

                     stringMaterias = new String[materias.size()];
                         for (int i = 0; i < stringMaterias.length; i++) {
                             stringMaterias[i] = materias.get(i).getNombre();
                         }
                         materiaElegida = JOptionPane.showOptionDialog(null,"","",0,0,null
                         ,stringMaterias,stringMaterias[0]);
                         materiaSelec = materias.get(materiaElegida);
                         JOptionPane.showMessageDialog(null,Inscripcion.verInscriptos(materiaSelec));break;
                 }
             }
             while (opcion!=8);
    }

    public Alumno BusarAlumno()
    {
        LinkedList<Alumno> alumnos = (LinkedList<Alumno>) this.getController().mostrarAlumnos();
        String[] mails = new String[alumnos.size()];
        for (int i = 0; i < mails.length; i++)
        {
            mails[i] = alumnos.get(i).getEmail();
        }
        int elegido = JOptionPane.showOptionDialog(null,"selecione mail","Tiile",0,0,null,mails,mails[0]);
        return alumnos.get(elegido);
    }

    private void agregarAlumno()
    {

    }
}
