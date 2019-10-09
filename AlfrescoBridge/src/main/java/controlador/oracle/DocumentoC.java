package controlador.oracle;

import dao.oracle.Documento;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import modelo.oracle.DocumentoM;

@Named(value = "documentoC")
@SessionScoped
public class DocumentoC implements Serializable {

    @Getter
    @Setter
    DocumentoM documento;

    @Getter
    @Setter
    List<DocumentoM> listaDocumento, listaDocumentoFiltrado;

    Documento documentoDao;

    public DocumentoC() {
        documentoDao = new Documento();
        documento = new DocumentoM();
        listaDocumento = new ArrayList<>();
    }
    
    @PostConstruct
    public void init() {
        try {
            listarDocumento();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarDocumento() throws Exception {
        try {
            listaDocumento = documentoDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
