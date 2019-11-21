package servicio;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AlfrescoS extends Conexion {

    public int currentID() throws Exception {
        int id = -1;
        try {
            String sql = "SELECT MAX(id) FROM alf_node";
            PreparedStatement ps = this.conectarPostgre().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
            }

            rs.close();
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectarPostgre();
        }
        return id;
    }
    
    public void subirImagen() throws Exception{
        
    }

}
