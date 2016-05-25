package com.example.vrebo.practica16;

import android.graphics.Color;

/**
 * Created by VREBO on 25/05/2016.
 */
public class Balloon {

    private int cX;
    private int cY;
    private int color;
    private int radius;

    public Balloon() {
        this.cX = 108;
        this.cY = 107;
        color = Color.GRAY;
        radius = 100;
    }

    public int getcX() {
        return cX;
    }

    public void setcX(int cX) {
        this.cX = cX;
    }

    public int getcY() {
        return cY;
    }

    public void setcY(int cY) {
        this.cY = cY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
