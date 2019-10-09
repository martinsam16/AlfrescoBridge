package modelo.alfresco;

import lombok.Data;

@Data
public class AlfContentM {

    private AlfNode alfNode = new AlfNode();
    private AlfContentUrl alfContentUrl = new AlfContentUrl();
    private AlfNodeProperties alfNodeProperties = new AlfNodeProperties();

}
