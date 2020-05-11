package br.com.fiap.trabalho.service.impl;

import br.com.fiap.trabalho.dto.pagseguro.SessionDTO;
import br.com.fiap.trabalho.util.ParameterStringBuilder;
import org.hibernate.Session;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class PagseguroService {

    public String GetSessionId() throws IOException {
        URL url = new URL("https://ws.sandbox.pagseguro.uol.com.br/v2/sessions/?email=diegoclimaites@gmail.com&token=531B260AB5CB4126B1267F1A09817A47");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        //Map<String, String> parameters = new HashMap<>();

        //parameters.put("email", "diegoclimaites@gmail.com");
        //parameters.put("token", "531B260AB5CB4126B1267F1A09817A47");

        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");

        //OutputStream os = con.getOutputStream();
        //os.write(ParameterStringBuilder.getParamsString(parameters).getBytes());
        //os.close();
        //os.flush();

        //DataOutputStream out = new DataOutputStream(con.getOutputStream());
        //out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        //out.close();
        //out.flush();

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        int status = con.getResponseCode();

        if(status == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            try {
                JAXBContext contextoXML = JAXBContext.newInstance(SessionDTO.class);
                Unmarshaller jaxbUnmarshaller  = contextoXML.createUnmarshaller();

                SessionDTO sessao = (SessionDTO) jaxbUnmarshaller.unmarshal(new StringReader(content.toString()));

                return sessao.getId();
            } catch (Exception e) {
                e.printStackTrace();
            }

            in.close();
            con.disconnect();
        }
        else {
            System.out.println("Deu erro");

            Reader streamReader = null;

            if (status > 299) {
                streamReader = new InputStreamReader(con.getErrorStream());
            } else {
                streamReader = new InputStreamReader(con.getInputStream());
            }

            return streamReader.toString();
        }



        return null;
    }
}
