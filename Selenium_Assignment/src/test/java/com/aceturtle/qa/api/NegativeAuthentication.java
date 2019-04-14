package com.aceturtle.qa.api;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.Test;

import java.util.Base64;

public class NegativeAuthentication {

    @Test
    public void execute() {
        try {

            String apiUri = "https://api.github.com/user";
            String username = "dev1011";
            String password = "WrongPassword";

            HttpGet httpGet = new HttpGet(apiUri);

            String encodedString = Base64.getEncoder().encodeToString((username + ":" + password).getBytes()).toString();//.replace("\n", "");

            httpGet.setHeader("Authorization", "Basic " + encodedString);
            httpGet.setHeader("Accept", "application/vnd.github.v3+json");
            int responseCode = 0;
            int requestCount = 0;

            // tried for upto 2000 bad credential requests but did not get 403, so restricting upto 1000.
            while (requestCount < 1000) {

                HttpClient client = HttpClients.createDefault();
                HttpResponse httpResponse = client.execute(httpGet);

                String con = httpResponse.getEntity().getContent().toString();
                responseCode = httpResponse.getStatusLine().getStatusCode();
                String responseString = EntityUtils.toString(httpResponse.getEntity());
                System.out.println("------------------");
                System.out.println("Request Number : " + requestCount);
                System.out.println("Response Code :" + responseCode);

                System.out.println("Response Below:\n" + new JSONObject(responseString).toString(3));

                //get all headers
                System.out.println("Response Headers :");
                Header[] headers = httpResponse.getAllHeaders();
                for (Header header : headers) {
                    System.out.println("Key : " + header.getName()
                            + " ,Value : " + header.getValue());
                }
                if (responseCode == 401) {
                    System.out.println("Got 401 response, sending bad credentials again..!!");
                } else if (responseCode == 403) {
                    break;
                }
                httpResponse = null;
                requestCount++;
            }
        } catch (Exception cpe) {
            cpe.printStackTrace();
        }
    }
}
