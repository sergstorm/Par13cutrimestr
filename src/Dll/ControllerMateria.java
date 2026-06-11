package Dll;

import Bll.Materia;
import Bll.Usario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ControllerMateria
{
    private static Connection con = Conexion.getInstance().getConection();

    public void agregarMateria(Materia materia)
    {
        System.out.println(materia.getNombre()+"Nombre "+materia.getIdUsuario()+"  _____Id usuario  ");
        String sql = "INSERT INTO materia (nombre, usuario_id) VALUES (?, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, materia.getNombre());

            statement.setInt(2, materia.getIdUsuario());
            statement.setInt(2,materia.getId());
            // Corregido: getIdUsario -> getIdUsuario

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Agregada correctamente");
            }
        } catch (SQLException e) {
            // Validación de duplicados (Error 1062 en MySQL para entradas duplicadas)
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "La materia ya existe en la base de datos.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar la materia: " + e.getMessage());
            }
            e.printStackTrace(); // Útil para depurar en consola
        }
    }


    public LinkedList<Materia> mostrarMateria(Usario usuario) throws SQLException {
        LinkedList<Materia> materias = new LinkedList<Materia>();
        // 1. Filtrar por el ID del usuario en la consulta SQL
        String sql = "SELECT * FROM materia WHERE usuario_id = ?";

        // 2. Usar try-with-resources para cerrar recursos automáticamente
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            // 3. Pasar el ID del usuario como parámetro seguro
            stmt.setInt(1, usuario.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    int usuarioId = rs.getInt("usuario_id");

                    materias.add(new Materia(id, nombre, usuarioId));
                }
            }
        } catch (Exception e) {
            // 4. Imprimir la excepción real para poder debugear
            e.printStackTrace();
        }
        return materias;
    }


    public LinkedList<Materia> mostrarMateriasDeProfessor(Usario usario)
    {
        LinkedList<Materia> materias = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM materia WHERE usuario_id = ?");
            stmt.setInt(1,usario.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int id_usuario = rs.getInt("usuario_id");
                materias.add(new Materia(id,nombre,id_usuario));
            }
        }
         catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materias;
    }
}
