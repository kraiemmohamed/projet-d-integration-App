package com.example.demo3.Bas;

import com.example.demo3.Affichable;
import com.example.demo3.Constantes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class BouttonCompartiment extends Boutton implements Affichable {
    final Rectangle boutton;

    Point2D position = Constantes.COMPARTIMENT_COORDONNEES;
    double largeur = Constantes.COMPARTIMENT_DIMENSIONS.getX();
    double hauteur = Constantes.COMPARTIMENT_DIMENSIONS.getY();

    public BouttonCompartiment(Point2D position, Point2D dimensions){
        this.position = position;
        largeur = dimensions.getX();
        hauteur = dimensions.getY();

        this.boutton = new Rectangle(position.getX(),position.getY(), dimensions.getX(),dimensions.getY());
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
        if (x > position.getX() &&
                x < position.getX() + largeur &&
                y > position.getY() &&
                y < position.getY() + hauteur){
            active = !active;
            return true;
        }
        else return false;
    }


    @Override
    public void afficher(GraphicsContext gc) {

    }
}
