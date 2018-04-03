package ch.hslu.AD.SW05.BallGame;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Ballspiele
 *
 * @author Fabian Gröger
 * @version 29.03.2018
 */
public class Ball extends Thread implements Runnable {

    private int x; // x - coordinate of the ball
    private int y; // y - coordinate of the ball
    private int radius; // radius of the ball
    private float fallSpeed; // speed of the ball falling
    private Color color; // color of the ball
    private DrawingArea drawingArea; // on which drawing area the ball is on
    private Ellipse2D circle; // the actual circle of the ball

    public Ball(final int x, final int y, final int radius, final float fallSpeed, final Color color, final DrawingArea drawingArea){
        this.circle = new Ellipse2D.Float(x, y, radius, radius);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.fallSpeed = fallSpeed;
        this.color = color;
        this.drawingArea = drawingArea;
    }

    @Override
    public void run(){

    }

    public Color getColor() {
        return this.color;
    }

    public Ellipse2D getCircle() {
        return this.circle;
    }
}
