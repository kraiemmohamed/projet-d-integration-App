package com.example.demo3._Constantes;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import javax.print.StreamPrintServiceFactory;

public final class Constantes {
    public Constantes() {}
    public static final double SCREEN_WIDTH = 480;
    public static final double SCREEN_HEIGHT = 720;

    public static final double HALF_HEIGHT = SCREEN_HEIGHT /2;

    //Joystick
    public static final Point2D JOYSTICK_CENTER = new Point2D(SCREEN_WIDTH /2, HALF_HEIGHT+(SCREEN_HEIGHT-HALF_HEIGHT)/2);
    public static final double JOYSTICK_RAYON = Math.min(SCREEN_WIDTH /3, HALF_HEIGHT/3);
    public static final Color JOYSTICK_COULEUR_APPUYE = Color.ORANGE;
    public static final Color JOYSTICK_COULEUR_LACHE = Color.gray(0.6);
    //Curseur
    public static final double RAYON_CURSEUR = JOYSTICK_RAYON /3;
    public static final Color CURSEUR_COULEUR = Color.gray(0.2);


    //Boutton Compartiment
    public static final Point2D COMPARTIMENT_DIMENSIONS = new Point2D(SCREEN_WIDTH /4, HALF_HEIGHT /4);
    public static final Point2D COMPARTIMENT_COORDONNEES = new Point2D(SCREEN_WIDTH - COMPARTIMENT_DIMENSIONS.getX(), HALF_HEIGHT);
    public static final Color COMPARTIMENT_COLOR_CLOSED = Color.ORANGE;
    public static final Color COMPARTIMENT_COLOR_OPENED = Color.GREEN;
    public static final double ARRONDISSEMENT_BOUTTON_HORIZONTAL = 0.33;
    public static final double ARRONDISSEMENT_BOUTTON_VERTICAL = 0.33;
    public static final Image COMPARTIMENT_IMAGE_CLOSED = new Image("Locker_closed(1).png");
    public static final Image COMPARTIMENT_IMAGE_OPEN = new Image("Locker_open.png");

    //Boutton Lampe
    public static final Point2D LAMPE_DIMENSIONS = new Point2D(SCREEN_WIDTH/4, HALF_HEIGHT/4);
    public static final Point2D LAMPE_COORDONNEES = new Point2D(0, HALF_HEIGHT);
    public static final Color LAMPE_COLOR_CLOSED = Color.gray(0.3);
    public static final Color LAMPE_COLOR_OPENED = Color.gray(0.6);
    public static final Image LAMPE_OPEN_IMAGE = new Image("Lampe_Ouverte.png");
    public static final Image LAMPE_CLOSED_IMAGE = new Image("Lampe_Closed.png");

    // Connection indicator
    public static final double INDICATOR_RAYON = Math.min(SCREEN_WIDTH/20,HALF_HEIGHT/20);
    public static final Point2D INDICATOR_POSITION = new Point2D(SCREEN_WIDTH*0.99 - INDICATOR_RAYON, SCREEN_HEIGHT*0.99-INDICATOR_RAYON);


    // Misc
    public static final int INTERVALLE_SEND = 100; //en ms, donc 10 fois/sec
    public static final Color DOWN_COULEUR = Color.gray(0.9);
    public static final Color UP_COULEUR = Color.ORANGE;
}
