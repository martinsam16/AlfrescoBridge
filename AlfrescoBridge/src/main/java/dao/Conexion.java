package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    protected static Connection conexionPostgre = null, conexionOracle = null;

    public static Connection conectarPostgre() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            conexionPostgre = DriverManager
                    .getConnection(
                            "jdbc:postgresql://35.236.223.58/alfresco",
                            "alfresco",
                            "J5wmr-Qu"
                    );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexionPostgre;
    }

    public static void desconectarPostgre() throws SQLException {
        try {
            if (conexionPostgre.isClosed() == false) {
                conexionPostgre.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection conectarOracle() throws Exception {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conexionOracle = DriverManager.getConnection(
                    "jdbc:oracle:thin:@35.184.25.222:1521:XE",
                    "ALFRESCO",
                    "Alfresco2019");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexionOracle;
    }

    public static void desconectarOracle() throws SQLException {
        try {
            if (conexionOracle.isClosed() == false) {
                conexionOracle.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
