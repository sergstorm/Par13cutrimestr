package Dll;

import Bll.Alumno;
import Bll.Professor;
import Bll.Usario;
import Repo.Hashing;
import Repo.UsarioRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ControllerUsario <T extends Usario> implements UsarioRepo
{
    private static Connection con = Conexion.getInstance().getConection();
    public T login(String email, String password)
    {
        T usario = null;
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE email=?");
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String cont = rs.getString("password");
                String tipo = rs.getString("tipo");
                System.out.println("Password       "+password);

                if(Hashing.verificar(password , cont))
                {
                    switch (tipo.toLowerCase())
                    {
                        case "alumno": usario =(T) new Alumno(id,nombre,email,tipo,password); break;
                        case "profesor": usario =(T) new Professor(id,nombre,email,tipo,password); break;
                        default:
                            System.out.println("Tipo desconosido");break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usario;
    }

    @Override
    public void agregarUsario(Usario usario) {
            try {
                //PreparedStatement statement = con.prepareStatement(" INSERT INTO 'usario' (nombre, email, tipo, password) VALUES ?,?,?,?)");
                PreparedStatement statement = con.prepareStatement("INSERT INTO usuario (nombre, email, tipo, password) VALUES (?, ?, ?, ?)");

                statement.setString(1, usario.getNombre());
                statement.setString(2,usario.getEmail());
                statement.setString(3, usario.getTipo());
                statement.setString(4, usario.getPassword());

                int filas = statement.executeUpdate();
                if (filas > 0){
                    System.out.println("Agreagado");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    public List<Usario> mostrarUsarios() {
        LinkedList<Usario> usarios = new LinkedList<>();
        try
        {
           PreparedStatement stmt = con.prepareStatement("SELECT*FROM 'usario'");
           ResultSet rs = stmt.executeQuery();
           while (rs.next())
           {
              int id = rs.getInt("id");
              String nombre = rs.getString("nombre");
              String email = rs.getString("email");
              String tipo = rs.getString("tipo");
              String password = rs.getString("password");

              switch (tipo.toLowerCase())
              {
                  case "alumno":
                      usarios.add((T) new Alumno(id,nombre,email,tipo,password)); break;
                  case "profesor":
                      usarios.add((T) new Professor(id,nombre,email,tipo,password)); break;
                  default:
                      System.out.println("Tipo Desconosido  "+tipo); break;
              }

           }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return List.of();
    }

    public LinkedList<Usario> mostrarAlumnos()
    {
                   LinkedList<Usario> usarios = new LinkedList<>();
                   try
                   {
                       PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario WHERE tipo= 'Alumno'");
                       ResultSet rs = stmt.executeQuery();
                       while (rs.next())
                       {
                           int id = rs.getInt("id");
                           String nombre = rs.getString("nombre");
                           String email = rs.getString("email");
                           String tipo = rs.getString("tipo");
                           String password = rs.getString("password");
                           usarios.add((T) new Alumno(id,nombre,email,tipo,password));
                       }
                   }
                   catch (Exception e) {
                       throw new RuntimeException(e);
                   }
            return usarios;
    }

    @Override
    public void EliminarUsario(Usario usario)
    {
          try {
              PreparedStatement statement = con.prepareStatement("DELETE FROM usuario WHERE id=?");
              statement.setInt(1,usario.getId());
              int filas = statement.executeUpdate();
              if(filas>0) System.out.println("eleminado correctamente");
          }

          catch (SQLException e) {
              throw new RuntimeException(e);
          }
    }

    public void EditarUsario(Usario usario)
    {
          try {
              PreparedStatement statement = con.prepareStatement("UPDATE usuario SET nombre = ?,tipo = ?, password = ? WHERE id=?");
              statement.setString(1,usario.getNombre());
              statement.setString(2,usario.getTipo());
              statement.setString(3,usario.getPassword());
              statement.setInt(4,usario.getId());

              int filas = statement.executeUpdate();
              if (filas>0) System.out.println("Exitoso usario update");

          }
          catch (SQLException e) {
              throw new RuntimeException(e);
          }

    }
}
