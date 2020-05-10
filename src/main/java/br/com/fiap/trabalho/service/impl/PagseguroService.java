package br.com.fiap.trabalho.service.impl;

import br.com.fiap.trabalho.util.ParameterStringBuilder;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class PagseguroService {

    public String GetSessionId() throws IOException {
        URL url = new URL("https://ws.sandbox.pagseguro.uol.com.br/v2/sessions");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        Map<String, String> parameters = new HashMap<>();

        parameters.put("email", "diegoclimaites@gmail.com");
        parameters.put("token", "531B260AB5CB4126B1267F1A09817A47");

        con.setRequestProperty("Content-Type", "application/json");
        String contentType = con.getHeaderField("Content-Type");

        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);



        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        con.setDoOutput(false);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        int status = con.getResponseCode();

        in.close();
        con.disconnect();

        Reader streamReader = null;

        if (status > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }

        return content.toString();
    }
}
