package com.example.demo3.Bas.Joystick;

import com.example.demo3.Affichable;
import com.example.demo3.Constantes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;

public class Curseur implements Affichable{
    Circle curseur;

    final Point2D origine;
    Point2D position;
    double distanceCentre = 0;
    double rayon;
    double distanceLimite;



    public Curseur(Point2D origine, double rayon, double rayonJoystick){
        this.origine = origine;
        position = new Point2D(origine.getX(),origine.getY());
        this.rayon = rayon;
        distanceLimite = rayonJoystick;

        curseur = new Circle(position.getX(), position.getY(), rayon, Constantes.JOYSTICK_COULEUR);
    }

    public void deplacerCurseur(double x, double y){
        position = new Point2D(x,y);
        setDistanceCentre();
        clamp();
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
            deplacement = deplacement.normalize().multiply(Constantes.JOYSTICK_RAYON);
            position = origine.add(deplacement);

            setDistanceCentre();
        }
    }

    @Override
    public void afficher(GraphicsContext gc){
        
    }
}
