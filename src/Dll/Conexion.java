package Dll;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion
{

    //#spring.datasource.url=jdbc:postgresql://localhost:5432/test
    //#spring.datasource.username=postgres
    //#spring.datasource.password=qweqwe1Q

    private static String URL ="jdbc:postgresql://localhost:5432/escuela";

    private static String USER ="postgres";
    private static String PASSWORD ="qwe";

    private static Connection conect;
    private static Conexion instance;

    private Conexion()
    {
        try {
            conect = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Se conncto");
        } catch (Exception e) {
            System.out.println("No se connecto");
        }
    }
    public static Conexion getInstance()
    {
        if (instance==null)
        {instance = new Conexion();}
        return instance;
    }
    public Connection getConection()
    {
        return conect;
    }
}
