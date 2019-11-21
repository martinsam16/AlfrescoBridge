package dao.alfresco;

import dao.Conexion;
import dao.ICrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.alfresco.AlfContentUrl;
import modelo.alfresco.AlfNode;
import modelo.alfresco.AlfNodeProperties;
import modelo.alfresco.AlfContentM;

public class AlfContent extends Conexion implements ICrud<AlfContentM> {

    @Override
    public List<AlfContentM> listar() throws Exception {
        List<AlfContentM> listaContenido = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "  n.id,\n"
                    + "  npn.string_value as filename,\n"
                    + "  cu.content_size,\n"
                    + "  cu.content_url,\n"
                    + "  n.uuid,\n"
                    + "  n.audit_created\n"
                    + "FROM alf_node as n\n"
                    + "  join alf_node_properties npn on\n"
                    + "     (npn.node_id=n.id and npn.actual_type_n=6 and npn.qname_id in\n"
                    + "       (select id from alf_qname where local_name='name'))\n"
                    + "  join alf_node_properties npc on\n"
                    + "     (npc.node_id=n.id and npc.actual_type_n=21 and npc.qname_id in\n"
                    + "       (select id from alf_qname where local_name='content'))\n"
                    + "  join alf_content_data cd on (cd.id = npc.long_value)\n"
                    + "  join alf_content_url cu on (cd.content_url_id = cu.id)\n"
                    + "where\n"
                    + "  n.type_qname_id in (\n"
                    + "    select id from alf_qname where local_name='content'\n"
                    + "  )";
            PreparedStatement ps = this.conectarPostgre().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AlfContentM contenido = new AlfContentM();
                AlfNode alfNode = new AlfNode();
                AlfContentUrl alfContentUrl = new AlfContentUrl();
                AlfNodeProperties alfNodeProperties = new AlfNodeProperties();

                alfNode.setId(rs.getInt(1));
                alfNodeProperties.setString_value(rs.getString(2));
                alfContentUrl.setContent_size(rs.getInt(3));
                alfContentUrl.setContent_url(rs.getString(4));
                alfNode.setUuid(rs.getString(5));
                alfNode.setAudit_created(rs.getString(6));

                contenido.setAlfContentUrl(alfContentUrl);
                contenido.setAlfNode(alfNode);
                contenido.setAlfNodeProperties(alfNodeProperties);
                listaContenido.add(contenido);
            }
            rs.close();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectarPostgre();
        }
        return listaContenido;
    }

    @Override
    public void registrar(AlfContentM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(AlfContentM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(AlfContentM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AlfContentM> listar(AlfContentM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AlfContentM obtenerModelo(AlfContentM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
