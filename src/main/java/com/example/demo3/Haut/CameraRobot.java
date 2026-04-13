package com.example.demo3.Haut;

import com.example.demo3.Affichable;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class CameraRobot implements Affichable {
    volatile Image image = new Image("No_Images.png");
    volatile double temperature = 0;
    volatile double humidite = 0;

    Image replacementImage = new Image("No_Images.png");

    public CameraRobot(){}

    public void setParameters(Image img, double temperature,double humidite){
        this.image = img;
        this.temperature = temperature;
        this.humidite = humidite;
    }


    @Override
    public void afficher(GraphicsContext gc){
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0, Constantes.SCREEN_WIDTH, Constantes.HALF_HEIGHT);

        Image newImage = image;
        if (image == null || image.getHeight() == 0 || image.getWidth() == 0) newImage = replacementImage;
        afficherImage(gc,newImage);


        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(humidite + "%", Constantes.POSITION_HUMIDITE.getX(),Constantes.POSITION_HUMIDITE.getY());

        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(temperature + "ºC", Constantes.POSITION_TEMPERATURE.getX(),Constantes.POSITION_TEMPERATURE.getY());
    }

    private void afficherImage(GraphicsContext gc, Image img) {
        double imageScale = Math.min(Constantes.HALF_HEIGHT/img.getHeight(), Constantes.SCREEN_WIDTH/img.getWidth());


        // Centralise l'image au milieu de la partie haute de l'écran
        double imageWidth = img.getWidth()*imageScale;
        double imageHeight = img.getHeight()*imageScale;
        double imageX = (Constantes.SCREEN_WIDTH/2) - (imageWidth/2);
        double imageY = (Constantes.HALF_HEIGHT/2) - (imageHeight/2);



        gc.drawImage(img, imageX, imageY, imageWidth, imageHeight);
    }

    private void afficherErreur(GraphicsContext gc) {
        // Centralise l'image au milieu de la partie haute de l'écran
        double imageWidth = replacementImage.getWidth();
        double imageHeight = replacementImage.getHeight();
        double imageX = (Constantes.SCREEN_WIDTH/2) - (imageWidth/2);
        double imageY = (Constantes.HALF_HEIGHT/2) - (imageHeight/2);

        gc.drawImage(replacementImage, imageX, imageY, imageWidth, imageHeight);
    }
}
