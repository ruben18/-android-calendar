package semestre.a1.a2016.estg.ei.ruben.calendar.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ruben on 10/07/2017.
 */

public class WebServiceManager {
    public static WebService API=new WebService();

    public String send(String method, String url, String header, String content){
        HttpURLConnection connection=null;

        try{
            URL completeUrl=new URL("http://192.168.1.202/api/"+url);
            connection=(HttpURLConnection) completeUrl.openConnection();
            connection.setRequestMethod(method);

            if(header!=null){
                connection.setRequestProperty("token", header);
            }

            if(content!=null){
                connection.setDoOutput(true);
                PrintWriter pw=new PrintWriter(connection.getOutputStream());
                pw.print(content);
                pw.close();
            }

            if(connection.getResponseCode()==200){
                String all=readStream(connection.getInputStream());

                return all;
            }else{
                int code=connection.getResponseCode();

                String all=readStream(connection.getErrorStream());
                throw new SendException(code, all);
            }

        } catch (IOException e) {
            throw new SendException(-1, e.getMessage());
        }
    }

    private String readStream(InputStream in) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        String s="";
        String message="";

        while((s=br.readLine())!=null){
            message=message+s+"\n";
        }
        br.close();
        return message;

    }

}
