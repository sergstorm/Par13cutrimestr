package Repo;

import Bll.Usario;

import java.util.LinkedList;
import java.util.List;

public interface UsarioRepo
{
    void agregarUsario(Usario usario);
    List<Usario> mostrarUsarios();
    <T> T login(String nombre, String password);
    LinkedList<Usario> mostrarAlumnos();
    void EliminarUsario(Usario usario);
}
