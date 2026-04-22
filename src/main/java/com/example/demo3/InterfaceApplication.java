package com.example.demo3;

import com.example.demo3.Bas.Boutton;
import com.example.demo3.Bas.BouttonApp;
import com.example.demo3.Bas.Joystick.Joystick;
import com.example.demo3.Bas.Joystick.Zone;
import com.example.demo3.ConnectionRobot.HttpCommunicator;
import com.example.demo3.Haut.CameraRobot;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class InterfaceApplication {
    CameraRobot camera;
    BouttonApp bouttonCompartiment;
    BouttonApp bouttonLampe;
    Joystick joystick;

    ArrayList<Boutton> bouttons = new ArrayList<>();
    ArrayList<Affichable> affichables = new ArrayList<>();

    HttpCommunicator communicator;

    public InterfaceApplication() throws Exception {
        camera = new CameraRobot();
        joystick = new Joystick(Constantes.JOYSTICK_CENTER, Constantes.JOYSTICK_RAYON,Constantes.RAYON_CURSEUR);
        bouttonCompartiment = new BouttonApp(Constantes.COMPARTIMENT_COORDONNEES,
                Constantes.COMPARTIMENT_DIMENSIONS,
                Constantes.COMPARTIMENT_COLOR_CLOSED,
                Constantes.COMPARTIMENT_COLOR_OPENED);
        bouttonLampe = new BouttonApp(Constantes.LAMPE_COORDONNEES,
                Constantes.LAMPE_DIMENSIONS,
                Constantes.LAMPE_COLOR_CLOSED,
                Constantes.LAMPE_COLOR_OPENED);

        communicator = new HttpCommunicator("http://192.168.1.118:5000/command");


        bouttons.add(bouttonCompartiment);
        bouttons.add(bouttonLampe);
        bouttons.add(joystick);

        affichables.add(bouttonCompartiment);
        affichables.add(camera);
        affichables.add(bouttonLampe);
        affichables.add(joystick);

        startCommunicationThread();
    }

    public void setCommunicatorURL(String URL){
        communicator.setURL(URL);
    }

    private void startCommunicationThread() {
        Thread communicateur = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    send();
                 //   receive();

                    Thread.sleep(Constantes.INTERVALLE_SEND);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        communicateur.setDaemon(true); // stops when app closes
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
    public void update(GraphicsContext gc){
        afficherInterface(gc);
    }

    // SEND
    public void send(){
        communicator.sendCommand(sendZone().name() + "," + sendVitesse() + "," + sendEtatCompartiment() + "," + sendEtatLampe() + ",");
        System.out.println(sendZone().name() + "," + sendVitesse() + "," + sendEtatCompartiment() + "," + sendEtatLampe());
        System.out.println();
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
        gc.setFill(Color.WHITE);
        gc.fillRect(0,Constantes.HALF_HEIGHT, Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);

        for (var i : affichables) i.afficher(gc);
    }

    //RECEIVE
    public void receive(Image image){
        camera.setParameters(image);
    }
}