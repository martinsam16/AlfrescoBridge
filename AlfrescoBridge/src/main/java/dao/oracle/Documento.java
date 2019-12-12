package dao.oracle;

import dao.Conexion;
import dao.ICrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.alfresco.AlfContentM;
import modelo.alfresco.AlfNode;
import modelo.oracle.DocumentoM;

public class Documento extends Conexion implements ICrud<DocumentoM> {

    @Override
    public void registrar(DocumentoM modelo) throws Exception {
        String sql = "INSERT INTO DOCUMENTO (ASUDOC,UUID,EXTDOC) VALUES (?,?,?";
        try {
            PreparedStatement ps = this.conectarOracle().prepareStatement(sql);
            ps.setString(1, modelo.getASUDOC());
            ps.setString(2, uuidImagen());
            ps.setString(3, modelo.getEXTDOC());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar codigo imagen" + e.getMessage());
            throw e;
        }
    }

    public String uuidImagen() throws SQLException {
        String uuidUltimo = null;
        try {
            String sql = "SELECT\n"
                + "  n.uuid as codigo\n"
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
                + "  )\n"
                + "order by n.id DESC LIMIT 1;";
            PreparedStatement ps = this.conectarPostgre().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                uuidUltimo = rs.getString("codigo");
            }
            rs.close();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectarPostgre();
        }
        return uuidUltimo;
    }

    @Override
    public void editar(DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DocumentoM> listar() throws Exception {
        List<DocumentoM> listaDocumento = new ArrayList<>();
        try {
            String sql = "SELECT IDDOC, ASUDOC, UUID, EXTDOC FROM DOCUMENTO WHERE UUID IS NOT NULL";
            PreparedStatement ps = this.conectarOracle().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DocumentoM documento = new DocumentoM();
                AlfContentM content = new AlfContentM();
                AlfNode node = new AlfNode();

                documento.setIDDOC(rs.getInt(1));
                documento.setASUDOC(rs.getString(2));
                node.setUuid(rs.getString(3));
                documento.setEXTDOC(rs.getString(4).trim());

                content.setAlfNode(node);
                documento.setAlfContent(content);
                listaDocumento.add(documento);
            }

            rs.close();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectarOracle();
        }
        return listaDocumento;
    }

    @Override
    public List<DocumentoM> listar(DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoM obtenerModelo(DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
