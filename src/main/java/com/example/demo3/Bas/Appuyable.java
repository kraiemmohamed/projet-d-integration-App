package com.example.demo3.Bas;

import javafx.geometry.Point2D;

public interface Appuyable {
    boolean detecterInput(Point2D position);
    boolean detecterInput(double x, double y);


    void appuye();

    void relache();
}
