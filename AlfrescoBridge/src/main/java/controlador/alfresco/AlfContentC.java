package controlador;

import dao.alfresco.AlfContent;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import modelo.alfresco.AlfContentM;

@Named(value = "alfContentC")
@SessionScoped
public class AlfContentC implements Serializable {

    @Getter
    @Setter
    List<AlfContentM> listaContentAlf, listaContentAlfFiltrado;

    @Getter
    @Setter
    AlfContentM alfContentM;

    AlfContent alfContentDao;

    public AlfContentC() {
        alfContentDao = new AlfContent();
        alfContentM = new AlfContentM();
        listaContentAlf = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try {
            listarContenido();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarContenido() throws Exception {
        try {
//            http://35.245.9.33/share/proxy/alfresco/api/node/content/versionStore/version2Store/{uuid}/{alfNodeProperties.string_value}?a=true
//            admin cMDB1o+W
//            el nombre de archivo con el que se sube a Alfresco tendría que ser el id de oracle
//            el uuid tendría que estar oracle
            listaContentAlf = alfContentDao.listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
