package com.example.demo3.Bas.Joystick;

import com.example.demo3.Affichable;
import com.example.demo3.Bas.Boutton;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

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
    }

    @Override
    public void afficher(GraphicsContext gc) {

    }
}
