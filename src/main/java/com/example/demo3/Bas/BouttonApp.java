package com.example.demo3.Bas;

import com.example.demo3.Affichable;
import com.example.demo3._Constantes.Constantes;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BouttonApp extends Boutton implements Affichable {
    double largeur;
    double hauteur;

    Color couleurFerme;
    Color couleurOuvert;

    Image imgOuvert;
    Image imgFerme;

    public BouttonApp(Point2D position, Point2D dimensions, Image img){
        this.position = position;
        largeur = dimensions.getX();
        hauteur = dimensions.getY();
        couleurFerme = Color.RED;
        couleurOuvert = Color.GREEN;
    }

    public BouttonApp(Point2D position, Point2D dimensions, Color closed, Color opened, Image ouvert, Image ferme){
        this.position = position;
        largeur = dimensions.getX();
        hauteur = dimensions.getY();
        couleurFerme = closed;
        couleurOuvert = opened;
        imgOuvert = ouvert;
        imgFerme = ferme;
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
        gc.fillRoundRect(position.getX(),
                position.getY(),
                largeur, hauteur,
                largeur* Constantes.ARRONDISSEMENT_BOUTTON_HORIZONTAL,
                hauteur*Constantes.ARRONDISSEMENT_BOUTTON_VERTICAL);

        gc.setStroke(Color.BLACK);
        gc.strokeRoundRect(position.getX(),
                position.getY(),
                largeur, hauteur,
                largeur* Constantes.ARRONDISSEMENT_BOUTTON_HORIZONTAL,
                hauteur*Constantes.ARRONDISSEMENT_BOUTTON_VERTICAL);


        gc.drawImage(getActive()? imgOuvert:imgFerme,
                position.getX(), position.getY(), largeur, hauteur);
    }
}
