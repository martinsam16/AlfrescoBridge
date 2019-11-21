package modelo.oracle;

import lombok.Data;
import modelo.alfresco.AlfContentM;

@Data
public class DocumentoM {

    private int IDDOC;
    private String ASUDOC;
    private AlfContentM alfContent = new AlfContentM();
    private String UUID;
    private String EXTDOC;
}
