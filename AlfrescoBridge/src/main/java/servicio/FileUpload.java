package servicio;

import java.io.File;
import java.io.IOException;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class FileUpload {

    public static void uploadDocument(
            String authTicket,
            File fileobj,
            String filename,
            String filetype,
            String description,
            String destination) {
        try {
            String urlString = "http://35.236.223.58:8080/alfresco/service/api/upload?alf_ticket=" + authTicket;

            HttpClient client = new HttpClient();
            PostMethod mPost = new PostMethod(urlString);

            Part[] parts = {
                new FilePart("filedata", filename, fileobj, filetype, null),
                new StringPart("filename", filename),
                new StringPart("description", description),
                // new StringPart("destination", destination)   
                new StringPart("description", description),
                new StringPart("siteid", "Documentos"),
                new StringPart("containerid", "Documentos"),
                new StringPart("uploaddirectory", "/share/page/context/mine/myfiles")

            };
            mPost.setRequestEntity(
                    new MultipartRequestEntity(parts, mPost.getParams())
            );
            int statusCode1 = client.executeMethod(mPost);
            //AQUI IMPRIMO LA CONEXIÓN POR MEDIO DEL HTTP CLIENT :'v           
            System.out.println("statusLine>>>" + statusCode1 + "......" + mPost.getStatusLine() + mPost.getResponseBodyAsString());
            mPost.releaseConnection();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String args[]) throws IOException {

        String alfrescoTiccketURL = "http://35.236.223.58:8080/alfresco" + "/service/api/login?u=" + "admin" + "&pw=" + "bc.8jQvn";

        String ticketURLResponse = invokeWebScriptgetRequest(alfrescoTiccketURL);

//ticketURLResponse =
        int startindex = ticketURLResponse.indexOf("<ticket>") + 8;
        int endindex = ticketURLResponse.indexOf("</ticket>");

        ticketURLResponse = ticketURLResponse.substring(startindex, endindex);

        File f = new File("D:/ILUSTRATOR/IONIC/modern-minimalist-mobile-financing-template/CARLOS_DATOS.pdf");

//FileInputStream is=new FileInputStream(f);
        uploadDocument(ticketURLResponse, f, "CARLOS_DATOS.pdf", "application/pdf", "description", null);

//uploadDocument("TICKET_3ef085c4e24f4e2c53a3fa72b3111e55ee6f0543", f,"47.bmp","image file","application/jpg","workspace://SpacesStore/65a06f8c-0b35-4dae-9835-e38414a99bc1");
    }

    public static String invokeWebScriptgetRequest(String url) {

// Create an instance of HttpClient.
        HttpClient client = new HttpClient();

// Create a method instance.
        GetMethod method = new GetMethod(url);
                
        // Provide custom retry handler is necessary
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));
        
        String response = null;
        try {
// Execute the method.
            int statusCode = client.executeMethod(method);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + method.getStatusLine());
            }

// Read the response body.
            byte[] responseBody = method.getResponseBody();

// Deal with the response.
// Use caution: ensure correct character encoding and is not binary data
            response = new String(responseBody);
            System.out.println(response);

        } catch (HttpException e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Fatal transport error: " + e.getMessage());
            e.printStackTrace();
        } finally {
// Release the connection.
            method.releaseConnection();
        }
        return response;

    }

}
