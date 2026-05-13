package com.example.demo3;

import com.example.demo3.Bas.Boutton;
import com.example.demo3.Bas.BouttonApp;
import com.example.demo3.Bas.Joystick.Joystick;
import com.example.demo3.Bas.Joystick.Zone;
import com.example.demo3.ConnectionRobot.HttpCommunicator;
import com.example.demo3.Haut.CameraRobot;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class InterfaceApplication {
    final String URL;

    volatile boolean connected = false;
    CameraRobot camera;
    BouttonApp bouttonCompartiment;
    BouttonApp bouttonLampe;
    Joystick joystick;

    ArrayList<Boutton> bouttons = new ArrayList<>();
    ArrayList<Affichable> affichables = new ArrayList<>();

    HttpCommunicator communicator;

    public InterfaceApplication(HttpCommunicator communicator){
        camera = new CameraRobot();
        joystick = new Joystick(Constantes.JOYSTICK_CENTER, Constantes.JOYSTICK_RAYON,Constantes.RAYON_CURSEUR);
        bouttonCompartiment = new BouttonApp(Constantes.COMPARTIMENT_COORDONNEES,
                Constantes.COMPARTIMENT_DIMENSIONS,
                Constantes.COMPARTIMENT_COLOR_CLOSED,
                Constantes.COMPARTIMENT_COLOR_OPENED,
                Constantes.COMPARTIMENT_IMAGE_CLOSED,
                Constantes.COMPARTIMENT_IMAGE_CLOSED);
        bouttonLampe = new BouttonApp(Constantes.LAMPE_COORDONNEES,
                Constantes.LAMPE_DIMENSIONS,
                Constantes.LAMPE_COLOR_OPENED,
                Constantes.LAMPE_COLOR_OPENED,
                Constantes.LAMPE_OPEN_IMAGE,
                Constantes.LAMPE_CLOSED_IMAGE);

        this.communicator = communicator;
        URL = communicator.getIP();


        bouttons.add(bouttonCompartiment);
        bouttons.add(bouttonLampe);
        bouttons.add(joystick);

        affichables.add(bouttonCompartiment);
        affichables.add(bouttonLampe);
        affichables.add(joystick);
        affichables.add(camera);

        camera.startStream(URL);
        startCommunicationThread();
    }

    private void startCommunicationThread() {
        Thread communicateur = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try{
                    send();
                    //   receive();
                    Thread.sleep(Constantes.INTERVALLE_SEND);
                    connected = true;
                }
                catch (IOException e) {
                        System.out.println("Network error: " + e.getMessage());
                        connected = false;
                    }
                 catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    connected = false;
                    break;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    connected = false;
                }
            }
        });

        communicateur.setDaemon(true);
        communicateur.start();
    }

    public void handleInputs(TouchEvent event){
        double x = event.getTouchPoint().getSceneX();
        double y = event.getTouchPoint().getSceneY();


        if (event.getEventType() == TouchEvent.TOUCH_PRESSED) {
            for (var i : bouttons){
                i.detecterInput(x,y);
            }
        }
            if (event.getEventType() == TouchEvent.TOUCH_RELEASED) {
                joystick.relache();
            }


        if (event.getEventType() == TouchEvent.TOUCH_MOVED && joystick.getActive()) {
            joystick.deplacerCurseur(x,y);
        }
    }

    public void handleInputs(MouseEvent event){
        double x = event.getSceneX();
        double y = event.getSceneY();


        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            for (var i : bouttons){
                i.detecterInput(x,y);
            }
        }
        if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            joystick.relache();
        }


        if (event.getEventType() == MouseEvent.MOUSE_DRAGGED && joystick.getActive()) {
            joystick.deplacerCurseur(x,y);
        }
    }

    // UPDATE
    public void update(GraphicsContext gc) throws IOException {
        afficherInterface(gc);
//        send();
    }

    // SEND
    public void send() throws IOException, InterruptedException {
        communicator.sendCommand(sendZone().name() + "," + sendVitesse() + "," + sendEtatCompartiment() + "," + sendEtatLampe() + ",");
    }

    public Zone sendZone(){
        return joystick.getZone();
    }
    public int sendVitesse(){
        return joystick.getDistance();
    }
    public boolean sendEtatCompartiment(){
        return bouttonCompartiment.getActive();
    }
    public String sendEtatLampe(){
        return bouttonLampe.getActive()? "O" : "P";
    }

    //AFFICHER
    public void afficherInterface(GraphicsContext gc){
        gc.setFill(Constantes.DOWN_COULEUR);
        gc.fillRect(0,Constantes.HALF_HEIGHT, Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);

        for (var i : affichables) i.afficher(gc);

        gc.setFill(connected?  Color.GREEN : Color.RED);
        gc.fillOval(Constantes.INDICATOR_POSITION.getX() - Constantes.INDICATOR_RAYON,
                Constantes.INDICATOR_POSITION.getY()-Constantes.INDICATOR_RAYON,
                Constantes.INDICATOR_RAYON*2,
                Constantes.INDICATOR_RAYON*2);
    }
}