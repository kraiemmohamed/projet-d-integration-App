package com.example.demo3;

import com.example.demo3._Constantes.Constantes;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    boolean appliStart = false;
    String IP = null;
    @Override
    public void start(Stage stage) throws Exception {
        var menu = new InterfaceApplication();
        var pane = new Pane();
        Scene scene = new Scene(pane, Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);
        var canva = new Canvas(Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);
//
//        var ligne = new HBox();
//        var txt = new Text("Entrez l'adresse IP:");
//        var txtBox = new TextField();
//        var bouttonStrart = new Button("Connecter");
//        ligne.getChildren().addAll(txt, txtBox);
//        bouttonStrart.setOnAction(actionEvent -> {
//            IP = txtBox.getText();
//        });
//

        pane.getChildren().add(canva);

        var contexte = canva.getGraphicsContext2D();


//
//        contexte.setLineWidth(30);
//        contexte.strokeRect(100,100, 300, 600);

        scene.addEventFilter(TouchEvent.ANY, menu::handleInputs);

        scene.addEventFilter(MouseEvent.ANY, menu::handleInputs);


        var timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                menu.update(contexte);
            }
        };
        timer.start();


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private static GraphicsContext getGraphicsContext(Canvas canva) {
        var contexte = canva.getGraphicsContext2D();

        //Cameras
        contexte.setFill(Color.LIGHTGRAY);
        contexte.fillRect(0, 0, Constantes.SCREEN_WIDTH, Constantes.HALF_HEIGHT);


        //contexte.strokeRect(300-30+40, 420-10, 100-40, 40);

        // Compartiment
        contexte.setFill(Color.GRAY);
        contexte.fillRect(Constantes.COMPARTIMENT_COORDONNEES.getX(), Constantes.COMPARTIMENT_COORDONNEES.getY(), Constantes.COMPARTIMENT_DIMENSIONS.getX(), Constantes.COMPARTIMENT_DIMENSIONS.getY());

        // Seperating line
        contexte.strokeLine(0, Constantes.HALF_HEIGHT, Constantes.SCREEN_WIDTH, Constantes.HALF_HEIGHT);

        //Joystick
        contexte.fillOval(Constantes.JOYSTICK_CENTER.getX()- Constantes.RAYON_CURSEUR, Constantes.JOYSTICK_CENTER.getY()-Constantes.RAYON_CURSEUR, 2*Constantes.RAYON_CURSEUR, 2*Constantes.RAYON_CURSEUR);
        contexte.strokeOval(Constantes.JOYSTICK_CENTER.getX()-Constantes.JOYSTICK_RAYON, Constantes.JOYSTICK_CENTER.getY()- Constantes.JOYSTICK_RAYON, 2*Constantes.JOYSTICK_RAYON, 2*Constantes.JOYSTICK_RAYON);
        return contexte;
    }

    public void drawShape(GraphicsContext context, Point2D position, Point2D dimensions){
        context.fillRect(position.getX(),position.getY(),dimensions.getX(),dimensions.getY());
    }

    public void drawShape(GraphicsContext context, double x, double y, double largeur, double hauteur){
        context.fillRect(x, y, largeur, hauteur);
    }
}
