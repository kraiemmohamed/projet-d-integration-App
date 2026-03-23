package com.example.demo3.Haut;

import com.example.demo3.Affichable;
import com.example.demo3.Constantes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.TextAlignment;

public class CameraRobot implements Affichable {
    Image image = new Image("No_Images.png");
    int temperature;
    int humidite;

    public CameraRobot(){}

    public void setParameters(Image img, int temperature,int humidite){
        this.image = img;
        this.temperature = temperature;
        this.humidite = humidite;
    }


    @Override
    public void afficher(GraphicsContext gc){
        // Centralise l'image au milieu de la partie haute de l'écran
        gc.drawImage(image,Constantes.IMAGE_X,Constantes.IMAGE_Y,Constantes.IMAGE_WIDTH,Constantes.IMAGE_HEIGHT);

        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(humidite + "%", Constantes.POSITION_HUMIDITE.getX(),Constantes.POSITION_HUMIDITE.getY());

        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(temperature + "ºC", Constantes.POSITION_TEMPERATURE.getX(),Constantes.POSITION_TEMPERATURE.getY());
    }
}
