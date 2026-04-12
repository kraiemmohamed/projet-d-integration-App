package com.example.demo3._Constantes;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public final class Constantes {
    public Constantes() {}
    public static final double SCREEN_WIDTH = 400;
    public static final double SCREEN_HEIGHT = 800;

    public static final double HALF_HEIGHT = SCREEN_HEIGHT /2;

    //Joystick
    public static final Point2D JOYSTICK_CENTER = new Point2D(SCREEN_WIDTH /2, HALF_HEIGHT *1.65);
    public static final double JOYSTICK_RAYON = SCREEN_WIDTH /3;
    public static final Color JOYSTICK_COULEUR = Color.gray(0.8);
    //Curseur
    public static final double RAYON_CURSEUR = JOYSTICK_RAYON /2;
    public static final Color CURSEUR_COULEUR = Color.gray(0.2);
    //Zones du joystick
    public static final double EPAISSEUR_ZONES_JOYSTICK = RAYON_CURSEUR;
    public static final double longueurZonesJoystick = JOYSTICK_RAYON;

    public static final double DIMENSIONS_ZONES_GIAGONALES = JOYSTICK_RAYON - EPAISSEUR_ZONES_JOYSTICK /2;

    // not gonna be used
//    public static final Point2D zoneA = new Point2D(JOYSTICK_CENTER.getX()- EPAISSEUR_ZONES_JOYSTICK /2, JOYSTICK_CENTER.getY()-longueurZonesJoystick);
//    public static final Point2D zoneADimensions = new Point2D(EPAISSEUR_ZONES_JOYSTICK, longueurZonesJoystick);
//    public static final Point2D zoneB = new Point2D(JOYSTICK_CENTER.getX(), JOYSTICK_CENTER.getY()- EPAISSEUR_ZONES_JOYSTICK /2);
//    public static final Point2D zoneBDimensions = new Point2D(longueurZonesJoystick, EPAISSEUR_ZONES_JOYSTICK);
//    public static final Point2D zoneC = new Point2D(JOYSTICK_CENTER.getX()- EPAISSEUR_ZONES_JOYSTICK /2, JOYSTICK_CENTER.getY());
//    public static final Point2D zoneCDimensions = new Point2D(EPAISSEUR_ZONES_JOYSTICK, longueurZonesJoystick);
//    public static final Point2D zoneD = new Point2D(JOYSTICK_CENTER.getX()- EPAISSEUR_ZONES_JOYSTICK, JOYSTICK_CENTER.getY()- EPAISSEUR_ZONES_JOYSTICK /2);
//    public static final Point2D zoneDDimensions = new Point2D(longueurZonesJoystick, EPAISSEUR_ZONES_JOYSTICK);
//    public static final Point2D ZoneE = new Point2D(JOYSTICK_CENTER.getX()+EPAISSEUR_ZONES_JOYSTICK/2, JOYSTICK_CENTER.getY()-JOYSTICK_RAYON);
//    public static final Point2D ZoneF = new Point2D(JOYSTICK_CENTER.getX()+EPAISSEUR_ZONES_JOYSTICK/2, JOYSTICK_CENTER.getY()+EPAISSEUR_ZONES_JOYSTICK/2);
//    public static final Point2D ZoneG = new Point2D(JOYSTICK_CENTER.getX()-JOYSTICK_RAYON, JOYSTICK_CENTER.getY()+EPAISSEUR_ZONES_JOYSTICK/2);
//    public static final Point2D ZoneH = new Point2D(JOYSTICK_CENTER.getX()-JOYSTICK_RAYON, JOYSTICK_CENTER.getY()-JOYSTICK_RAYON);




    //Boutton Compartiment
    public static final Point2D COMPARTIMENT_DIMENSIONS = new Point2D(SCREEN_WIDTH /5, SCREEN_HEIGHT /10);
    public static final Point2D COMPARTIMENT_COORDONNEES = new Point2D(SCREEN_WIDTH *9/10 - COMPARTIMENT_DIMENSIONS.getX(), HALF_HEIGHT *11/10);

    //Info camera (haut)
    public static final Point2D POSITION_TEMPERATURE = new Point2D(SCREEN_WIDTH /20, HALF_HEIGHT /20);

    public static final Point2D POSITION_HUMIDITE = new Point2D(SCREEN_WIDTH *19/20, HALF_HEIGHT /20);

    public static final double IMAGE_WIDTH = SCREEN_WIDTH;
    public static final double IMAGE_HEIGHT = HALF_HEIGHT;
    public static final double IMAGE_X = (SCREEN_WIDTH/2)- IMAGE_WIDTH/2;
    public static final double IMAGE_Y= (HALF_HEIGHT/2)- IMAGE_HEIGHT/2;

    // Misc
    public static final double INTERVALLE_SEND = 100;
}
