package com.example.demo3.Haut;

import com.example.demo3.Affichable;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CameraRobot implements Affichable {
    volatile Image image = new Image("No_Images.png");

    Image replacementImage = new Image("No_Images.png");

    public CameraRobot(){}

    public void setParameters(Image img){
        this.image = img;
    }


    @Override
    public void afficher(GraphicsContext gc){
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0, Constantes.SCREEN_WIDTH, Constantes.HALF_HEIGHT);

        Image newImage = image;
        if (newImage == null || newImage.getHeight() == 0 || newImage.getWidth() == 0) newImage = replacementImage;
        afficherImage(gc,newImage);
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
}
