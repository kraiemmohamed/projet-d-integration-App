package com.example.demo3.Bas;

import com.example.demo3.Affichable;
import javafx.geometry.Point2D;

public abstract class Boutton implements Appuyable, Affichable{
    protected boolean active = false;
    protected Point2D position;

    @Override
    public abstract boolean detecterInput(Point2D position);
    @Override
    public abstract boolean detecterInput(double x, double y);

    @Override
    public void appuye(){
        active = true;
    }

    @Override
    public void relache(){
        active = false;
    }
}