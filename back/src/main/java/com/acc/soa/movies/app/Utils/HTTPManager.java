package com.acc.soa.movies.app.Utils;

import org.springframework.ws.client.WebServiceFaultException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPManager implements Constants {

    public static String HTTPRequest(String[] params) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url;
            if (params.length == 1) {
                url = new URL(Constants.TMDB_API + params[0] + Constants.TMDB_KEY);
            } else {
                url = new URL(Constants.TMDB_API + params[0] + Constants.TMDB_KEY +
                        params[1]);
            }

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }


        } catch (Exception e) {
            throw new WebServiceFaultException("URL is not valid!");
        }

        return sb.toString();
    }

}
