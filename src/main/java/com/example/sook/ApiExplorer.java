package com.example.sook;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        //4.2.2 monthFdmtLst(식재료 목록)
        StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFd/monthFdmtLst"); //URL
        urlBuilder.append("?" + URLEncoder.encode("apiKey","UTF-8")  + "20200609CU4ACGJZDFQB3TQNBAV4Q"); //apikey
        urlBuilder.append("&" + URLEncoder.encode("thisYear","UTF-8") + "=" + URLEncoder.encode("2019", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("thisMonth","UTF-8") + "=" + URLEncoder.encode("06", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}
