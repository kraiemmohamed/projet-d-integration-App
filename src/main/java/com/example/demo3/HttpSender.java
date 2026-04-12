package com.example.demo3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSender {
    private static final String SERVER_URL = "http://192.168.1.141:5000/command"; // CHANGE THIS
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void sendCommand(String command) {

        try {

          //  String json = "{ " + command +": " + " + command + " + " }";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SERVER_URL))
                    .header("Content-Type", "text/plain")
                    .POST(HttpRequest.BodyPublishers.ofString(command))
                    .build();

            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            System.out.println("Response: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}