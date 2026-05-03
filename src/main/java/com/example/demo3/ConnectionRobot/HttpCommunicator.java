package com.example.demo3.ConnectionRobot;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpCommunicator {
    private String IP;
    private String commandURL;
    private final HttpClient client = HttpClient.newHttpClient();

    public HttpCommunicator(){
    }

    public void setURL(String URL) throws IOException, InterruptedException {
        IP = URL;

        if (!IP.startsWith("http://")) IP = "http://" + IP;
        commandURL = IP + ":5000/command";
        tryCommand();
    }

    public String getIP(){
        return IP;
    }

    public void tryCommand() throws IOException, ConnectException, InterruptedException, IllegalArgumentException{
        sendCommand("S,0,false,P,");
    }


    public void sendCommand(String command) throws IOException, ConnectException, InterruptedException {
        if (commandURL == null) return;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(commandURL))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString(command))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());
    }
}