package com.example.demo3.Bas.Joystick;

import com.example.demo3.Affichable;
import com.example.demo3.Bas.Boutton;
import com.example.demo3._Constantes.Constantes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Joystick extends Boutton implements Affichable {

    //    final Circle contour;
    final Point2D position;
    double rayon;
    Zone zone = Zone.STATIONARY;

    Curseur curseur;


    public Joystick(Point2D position, double rayonJoystick, double rayonCurseur){
        this.position = position;
        this.rayon = rayonJoystick;

//        this.contour = new Circle(position.getX(),position.getY(),rayon, Constantes.JOYSTICK_COULEUR);
        curseur = new Curseur(position, rayonCurseur, rayonJoystick);
    }


    public Point2D getPosition() {
        return position;
    }

    public double getRayon(){
        return rayon;
    }

    public boolean getActive(){
        return active;
    }

    public Zone getZone() {
        return zone;
    }

    public int getDistance(){
        double distancePercent = curseur.distanceCentre/curseur.distanceLimite;

        if (distancePercent<0.2) return 0;
        else if (distancePercent<0.4) return 20;
        else if (distancePercent<0.6) return 40;
        else if (distancePercent<0.8) return 60;
        else if (distancePercent<1) return 80;
        else return 100;
    }


    @Override
    public boolean detecterInput(Point2D position) {
        return detecterInput(position.getX(), position.getY());
    }

    @Override
    public boolean detecterInput(double x, double y) {
        if(position.distance(x,y) < rayon){
            active = true;
            return true;
        }
        else return false;
    }

    public void deplacerCurseur(double x, double y){
        curseur.deplacerCurseur(x,y);
        zone = curseur.detecterZone();
    }
    @Override
    public void relache(){
        curseur.resetCurseur();
        super.relache();
        zone = Zone.STATIONARY;
    }

    @Override
    public void afficher(GraphicsContext gc) {
        gc.setFill(active? Constantes.JOYSTICK_COULEUR : Color.WHITE);
        gc.fillOval(position.getX() - rayon, position.getY() - rayon, 2*rayon,2*rayon);

        gc.setStroke(Color.BLACK);
        gc.strokeOval(position.getX() - rayon, position.getY() - rayon, 2*rayon,2*rayon);

        curseur.afficher(gc);
    }
}
