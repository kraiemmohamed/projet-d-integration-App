package com.example.demo3.Bas;

import com.example.demo3.Affichable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BouttonApp extends Boutton implements Affichable {
    final Rectangle boutton;

    double largeur;
    double hauteur;

    Color couleurFerme;
    Color couleurOuvert;

    public BouttonApp(Point2D position, Point2D dimensions){
        this.position = position;
        largeur = dimensions.getX();
        hauteur = dimensions.getY();
        couleurFerme = Color.RED;
        couleurOuvert = Color.GREEN;

        this.boutton = new Rectangle(position.getX(),position.getY(), dimensions.getX(),dimensions.getY());
    }

    public BouttonApp(Point2D position, Point2D dimensions, Color closed, Color opened){
        this.position = position;
        largeur = dimensions.getX();
        hauteur = dimensions.getY();
        couleurFerme = closed;
        couleurOuvert = opened;

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
        gc.setFill(getActive()? couleurOuvert : couleurFerme);
        gc.fillRect(position.getX(), position.getY(), largeur, hauteur);

        gc.setStroke(Color.BLACK);
        gc.strokeRect(position.getX(), position.getY(), largeur, hauteur);
    }
}
