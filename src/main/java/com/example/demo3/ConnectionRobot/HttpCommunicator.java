package com.example.demo3.ConnectionRobot;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.PrintWriter;
import java.net.Socket;


public class HttpCommunicator {
    private String SERVER_URL; // CHANGE THIS
    private final HttpClient client = HttpClient.newHttpClient();

    public HttpCommunicator(String URL){
        SERVER_URL = URL;
    }

    public void setURL(String URL){
        SERVER_URL = URL;
    }

    public void sendCommand(String command) {


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