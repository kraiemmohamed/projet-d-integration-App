package com.example.demo3.Haut;

import com.example.demo3.Affichable;
import com.example.demo3._Constantes.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class CameraRobot implements Affichable{
    volatile Image image = new Image("No_Images.png");

    Image replacementImage = new Image("No_Images.png");

    public CameraRobot(){}

    public void startStream(String url) { // Frame by frame
        Thread afficheurCamera = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                long tempsInitial = System.currentTimeMillis();
                try {
                    Image img = new Image(url + ":8080/frame", false);

                    if (!img.isError() && img.getWidth() !=0) image = img;

                    long deltaTemps = System.currentTimeMillis() - tempsInitial;
                    long delay = Math.max(0, 50 - deltaTemps);

                    Thread.sleep(delay);

                } catch (Exception e) {
                    image = replacementImage;
                }
            }
        });

        afficheurCamera.setDaemon(true);
        afficheurCamera.start();
    }

    public void afficher(GraphicsContext gc){
        gc.setFill(Constantes.UP_COULEUR);
        gc.fillRect(0,0, Constantes.SCREEN_WIDTH, Constantes.HALF_HEIGHT);
        Image img = image;
        if (img == null || img.getHeight() == 0 || img.getWidth() == 0) img = replacementImage;
        try {
            afficherImage(gc,img);
        } catch (Exception ignored){
        }
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
