package com.example.demo3;

import com.example.demo3.Bas.Boutton;
import com.example.demo3.Bas.BouttonCompartiment;
import com.example.demo3.Bas.Joystick.Joystick;
import com.example.demo3.Bas.Joystick.Zone;
import com.example.demo3.Haut.CameraRobot;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;

import java.util.ArrayList;

public class InterfaceApplication {
    CameraRobot camera;
    BouttonCompartiment bouttonCompartiment;
    Joystick joystick;
//    Curseur curseur;

    ArrayList<Boutton> bouttons = new ArrayList<>();
    ArrayList<Affichable> affichables = new ArrayList<>();

    long tempDepuisEnvoi = 0;

    public InterfaceApplication(){
        camera = new CameraRobot();
        joystick = new Joystick(Constantes.JOYSTICK_CENTER, Constantes.JOYSTICK_RAYON,Constantes.RAYON_CURSEUR);
        bouttonCompartiment = new BouttonCompartiment(Constantes.COMPARTIMENT_COORDONNEES, Constantes.COMPARTIMENT_DIMENSIONS);


        bouttons.add(joystick);
        bouttons.add(bouttonCompartiment);

        affichables.add(joystick);
        affichables.add(bouttonCompartiment);
        affichables.add(camera);
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

    public void update(GraphicsContext gc){
        long now = System.currentTimeMillis();

        if (now - tempDepuisEnvoi >=Constantes.INTERVALLE_SEND){
            send();
            tempDepuisEnvoi = now;
        }
        //receive();
        afficherInterface(gc);
    }

    // SEND
    public void send(){
        System.out.println("Ouverture du boutton compartiment: " + sendEtatCompartiment());
        System.out.println("Déplacement du robot: " + sendVitesse() +
                "% dans la zone " + sendZone());
        System.out.println();
    }
    public boolean sendEtatCompartiment(){
        return bouttonCompartiment.getActive();
    }
    public int sendVitesse(){
        return joystick.getDistance();
    }
    public Zone sendZone(){
        return joystick.getZone();
    }

    //AFFICHER
    public void afficherInterface(GraphicsContext gc){
        for (var i : affichables) i.afficher(gc);
    }

    //RECEIVE
    public void receive(Image image, int temp, int hum){
        camera.setParameters(image,temp,hum);
    }
}
