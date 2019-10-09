package modelo.oracle;

import java.util.Date;
import lombok.Data;

@Data
public class DocumentoM {

    private int IDDOC;
    private int CODDOC;
    private String NUMLIBDOC;
    private String NUMFOLDOC;
    private Date FECDOC = new Date();
    private String ASUDOC;
    private String OBSDOC;
    private String ESTDOC;
    private String KEYDOC;
    private String DENDOC;
    private String UUID;
}
