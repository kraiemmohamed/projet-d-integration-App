package com.example.demo3.Bas.Joystick;

import com.example.demo3.Affichable;
import com.example.demo3._Constantes.Constantes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Curseur implements Affichable{
    final Point2D origine;
    Point2D position;
    double distanceCentre = 0;
    double rayon;
    double distanceLimite;


    public Curseur(Point2D origine, double rayon, double rayonJoystick){
        this.origine = origine;
        position = new Point2D(origine.getX(),origine.getY());
        this.rayon = rayon;
        distanceLimite = rayonJoystick-rayon;
    }

    public void deplacerCurseur(double x, double y){
        position = new Point2D(x,y);
        setDistanceCentre();
        clamp();

    }

    public void resetCurseur(){
        position = new Point2D(origine.getX(), origine.getY());
        setDistanceCentre();
    }

    public Zone detecterZone(){
        if (distanceCentre < distanceLimite*0.1) return Zone.S;
        else {
            Point2D deplacement = position.subtract(origine);
            double dx = deplacement.getX();
            double dy = deplacement. getY();

            double angle = Math.toDegrees(Math.atan2(dy, dx));
            if (angle < 0) angle += 360;

            if (angle >= 247.5 && angle <= 292.5) return Zone.A;     // avant
            if (angle >= 337.5 || angle <= 22.5) return Zone.B;      // droite
            if (angle >= 67.5 && angle <= 112.5) return Zone.C;      // derrière
            if (angle >= 157.5 && angle <= 202.5) return Zone.D;     // gauche
            if (angle > 292.5 && angle < 337.5) return Zone.E;     // avant-gauche
            if (angle > 22.5 && angle < 67.5) return Zone.F;       // derrière-gauche
            if (angle > 112.5 && angle < 157.5) return Zone.G;     // derrière-droite
            return Zone.H;                                          // avant-droite
        }
    }

    public void setDistanceCentre(){
        distanceCentre = origine.distance(position);
    }

    /**
     * Empêche le curseur à dépacer la limite du joystick
     */
    public void clamp(){
        if (distanceCentre > distanceLimite){
            Point2D deplacement = position.subtract(origine);
            deplacement = deplacement.normalize().multiply(distanceLimite);
            position = origine.add(deplacement);
            setDistanceCentre();
        }
    }

    @Override
    public void afficher(GraphicsContext gc){
        gc.setFill(Constantes.CURSEUR_COULEUR);
        gc.fillOval(position.getX() - rayon, position.getY() - rayon, 2*rayon,2*rayon);

        gc.setStroke(Color.BLACK);
        gc.strokeOval(position.getX() - rayon, position.getY() - rayon, 2*rayon,2*rayon);
    }
}
