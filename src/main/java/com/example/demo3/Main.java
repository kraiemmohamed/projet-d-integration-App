package com.example.demo3;

import com.example.demo3.ConnectionRobot.HttpCommunicator;
import com.example.demo3._Constantes.Constantes;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ConnectException;

public class Main extends Application {
    String IP = null;
    HttpCommunicator communicator = new HttpCommunicator();
    @Override
    public void start(Stage stage) {
        var root = new VBox(5);
        var ligne = new HBox();
        root.setAlignment(Pos.CENTER);
        var scene = new Scene(root, 400,200);

        var txt = new Text("Entrez l'adresse IP:");
        var txtBox = new TextField();
        txtBox.setPromptText("http://(entrez l'IP)");

        var bouttonStart = new Button("Connecter");
        ligne.getChildren().addAll(txt, txtBox,bouttonStart);


        var errorText = new Text();


        root.getChildren().addAll(ligne,errorText);


        bouttonStart.setOnAction(actionEvent -> {
            IP = txtBox.getText();

            if (IP == null) return;

            try {
                communicator.setURL(IP);
                launchApp(stage);
            }catch (ConnectException | IllegalArgumentException e){
                errorText.setFill(Color.RED);
                errorText.setText("Erreur de connection avec le robot (URL probablement invalide)");
                System.out.println("Erreur de connection avec le robot (URL probablement invalide)");
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        stage.setTitle("URl enter");
        stage.setScene(scene);
        stage.show();
    }

    private void launchApp(Stage stage) {
        var menu = new InterfaceApplication(communicator);
        var pane = new Pane();
        Scene scene = new Scene(pane, Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);
        var canva = new Canvas(Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);

        pane.getChildren().add(canva);

        var contexte = canva.getGraphicsContext2D();

        scene.addEventFilter(TouchEvent.ANY, menu::handleInputs);

        scene.addEventFilter(MouseEvent.ANY, menu::handleInputs);


        var timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                try {
                    menu.update(contexte);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        timer.start();

        stage.setScene(scene);
    }
}
