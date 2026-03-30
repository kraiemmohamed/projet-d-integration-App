package com.example.demo3.Haut;

import com.example.demo3.Affichable;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;

public class CameraRobot implements Affichable {
    Image image = new Image("No_Images.png");
    double temperature = 0;
    double humidite = 0;

    public CameraRobot(){}

    public void setParameters(Image img, double temperature,double humidite){
        this.image = img;
        this.temperature = temperature;
        this.humidite = humidite;
    }


    @Override
    public void afficher(GraphicsContext gc){
        try {
            afficherImage(gc);
        } catch (Exception e) {
            afficherErreur(gc);
        }

        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(humidite + "%", Constantes.POSITION_HUMIDITE.getX(),Constantes.POSITION_HUMIDITE.getY());

        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(temperature + "ºC", Constantes.POSITION_TEMPERATURE.getX(),Constantes.POSITION_TEMPERATURE.getY());
    }

    private void afficherImage(GraphicsContext gc) {
        // Centralise l'image au milieu de la partie haute de l'écran
        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();
        double imageX = (Constantes.SCREEN_WIDTH/2) - (imageWidth/2);
        double imageY = (Constantes.HALF_HEIGHT/2) - (imageHeight/2);
        gc.drawImage(image, imageX, imageY, imageWidth, imageHeight);
    }

    private void afficherErreur(GraphicsContext gc) {
        // Centralise l'image au milieu de la partie haute de l'écran
        Image replacementImage = new Image("No_Images.png");
        double imageWidth = replacementImage.getWidth();
        double imageHeight = replacementImage.getHeight();
        double imageX = (Constantes.SCREEN_WIDTH/2) - (imageWidth/2);
        double imageY = (Constantes.HALF_HEIGHT/2) - (imageHeight/2);
        gc.drawImage(replacementImage, imageX, imageY, imageWidth, imageHeight);
    }
}
