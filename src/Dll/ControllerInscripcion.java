package Dll;

import Bll.Alumno;
import Bll.Inscripcion;
import Bll.Materia;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class ControllerInscripcion
{
    private static Connection con = Conexion.getInstance().getConection();

    public void agregarInscripcion(Materia materia, Alumno alumno, LocalDate now) {
          try {
              PreparedStatement statement = con.prepareStatement(
                      "INSERT INTO inscripcion (fecha, materia_id ,usuario_id) VALUES (?, ? , ?) "
              );
              statement.setDate(1, Date.valueOf(now));
              statement.setInt(2, materia.getId());
              statement.setInt(3, alumno.getId());
              int filas = statement.executeUpdate();
              if (filas > 0) {
                  System.out.println("Iscripcion agregada correctamente");
              }
          }catch (SQLIntegrityConstraintViolationException e)
          {
              System.out.println(" No se puede crear Inscripcion existente");
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
    }


    public LinkedList<Inscripcion> mostrarInscripcionsDeProfessor(Materia materia) {
        LinkedList<Inscripcion> inscripcions = new LinkedList<>();

        // Consulta limpia para PostgreSQL (sensible a mayúsculas/minúsculas en nombres de tablas)
        String query = "SELECT m.nombre AS materia, u.nombre AS usuario, i.fecha " +
                "FROM inscripcion i " +
                "JOIN materia m ON i.materia_id = m.id " +
                "JOIN usuario u ON i.usuario_id = u.id " +
                "WHERE m.id = ?";

        // Try-with-resources garantiza el cierre de conexiones en PostgreSQL
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, materia.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // PostgreSQL mapea el tipo DATE directamente a LocalDate con getObject
                    LocalDate fecha = rs.getObject("fecha", LocalDate.class);
                    String nombreMateria = rs.getString("materia");
                    String nombreUsuario = rs.getString("usuario");

                    inscripcions.add(new Inscripcion(fecha, nombreMateria, nombreUsuario));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error en la base de datos PostgreSQL: " + e.getMessage(), e);
        }
        return inscripcions;
    }



}
