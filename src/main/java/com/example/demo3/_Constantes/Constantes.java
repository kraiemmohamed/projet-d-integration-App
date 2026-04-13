package com.example.demo3._Constantes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public final class Constantes {
    public Constantes() {}
    public static final double SCREEN_WIDTH = 400;
    public static final double SCREEN_HEIGHT = 800;

    public static final double HALF_HEIGHT = SCREEN_HEIGHT /2;

    //Joystick
    public static final Point2D JOYSTICK_CENTER = new Point2D(SCREEN_WIDTH /2, HALF_HEIGHT *1.5);
    public static final double JOYSTICK_RAYON = Math.min(SCREEN_WIDTH /3, HALF_HEIGHT/3);
    public static final Color JOYSTICK_COULEUR = Color.gray(0.8);
    //Curseur
    public static final double RAYON_CURSEUR = JOYSTICK_RAYON /2;
    public static final Color CURSEUR_COULEUR = Color.gray(0.2);


    //Boutton Compartiment
    public static final Point2D COMPARTIMENT_DIMENSIONS = new Point2D(SCREEN_WIDTH /5, SCREEN_HEIGHT /10);
    public static final Point2D COMPARTIMENT_COORDONNEES = new Point2D(SCREEN_WIDTH - COMPARTIMENT_DIMENSIONS.getX(), HALF_HEIGHT);

    //Info camera (haut)
    public static final Point2D POSITION_TEMPERATURE = new Point2D(SCREEN_WIDTH /20, HALF_HEIGHT /20);

    public static final Point2D POSITION_HUMIDITE = new Point2D(SCREEN_WIDTH *19/20, HALF_HEIGHT /20);

    public static final double IMAGE_WIDTH = SCREEN_WIDTH;
    public static final double IMAGE_HEIGHT = HALF_HEIGHT;
    public static final double IMAGE_X = (SCREEN_WIDTH/2)- IMAGE_WIDTH/2;
    public static final double IMAGE_Y = (HALF_HEIGHT/2)- IMAGE_HEIGHT/2;

    // Misc
    public static final int INTERVALLE_SEND = 100;
}
