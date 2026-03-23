package com.example.demo3;

import com.example.demo3.Bas.Boutton;
import com.example.demo3.Bas.BouttonCompartiment;
import com.example.demo3.Bas.Joystick.Curseur;
import com.example.demo3.Bas.Joystick.Joystick;
import com.example.demo3.Haut.CameraRobot;
import javafx.scene.image.Image;
import javafx.scene.input.TouchEvent;

import java.util.ArrayList;

public class Interface {
    CameraRobot camera;
    BouttonCompartiment bouttonCompartiment;
    Joystick joystick;
    Curseur curseur;

    ArrayList<Boutton> bouttons = new ArrayList<>();

    public Interface(){
        camera = new CameraRobot();
        joystick = new Joystick(Constantes.JOYSTICK_CENTER, Constantes.JOYSTICK_RAYON,Constantes.RAYON_CURSEUR);
        bouttonCompartiment = new BouttonCompartiment(Constantes.COMPARTIMENT_COORDONNEES, Constantes.COMPARTIMENT_DIMENSIONS);


        bouttons.add(joystick);
        bouttons.add(bouttonCompartiment);
    }

    public void handleInputs(TouchEvent event){
        double x = event.getTouchPoint().getSceneX();
        double y = event.getTouchPoint().getSceneY();

        for (var i : bouttons){
            if (event.getEventType() == TouchEvent.TOUCH_PRESSED) {
                i.detecterInput(x,y);
            }
            if (event.getEventType() == TouchEvent.TOUCH_RELEASED) {
                i.relache();
            }
        }


        if (event.getEventType() == TouchEvent.TOUCH_MOVED && joystick.getActive()) {
            joystick.deplacerCurseur(x,y);
        }
    }




    public void update(){}

    public void send(){}

    public void receive(Image image, int temp, int hum){
        camera.setParameters(image,temp,hum);
    }
}
