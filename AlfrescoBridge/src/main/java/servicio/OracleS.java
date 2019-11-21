package servicio;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleS extends Conexion {

    public int currentID() throws Exception {
        int id = -1;
        try {
            String sql = "SELECT MAX(IDDOC) FROM DOCUMENTO";
            PreparedStatement ps = this.conectarOracle().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectarOracle();
        }
        return id;
    }
}
