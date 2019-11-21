package dao.oracle;

import dao.Conexion;
import dao.ICrud;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.alfresco.AlfContentM;
import modelo.alfresco.AlfNode;
import modelo.oracle.DocumentoM;

public class Documento extends Conexion implements ICrud<DocumentoM> {

    @Override
    public void registrar(DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
