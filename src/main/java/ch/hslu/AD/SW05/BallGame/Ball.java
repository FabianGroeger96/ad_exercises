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

    private static final int FALL_INTERVAL = 5;
    private static final int WHITEN_INTERVAL = 10;
    private static final float FALL_SPEED_INC = 0.05f;

    private int x; // x - coordinate of the ball
    private int y; // y - coordinate of the ball
    private int radius; // radius of the ball
    private float fallSpeed; // speed of the ball falling
    private Color color; // color of the ball
    private DrawingArea drawingArea; // on which drawing area the ball is on
    private Ellipse2D circle; // the actual circle of the ball

    private boolean atTheBottom = false;
    private boolean whited = false;
    private boolean interrupted = false;

    public Ball(final int x, final int y, final int radius, final Color color, final DrawingArea drawingArea){
        this.circle = new Ellipse2D.Float(x, y, radius, radius);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.fallSpeed = 1.0f;
        this.color = color;
        this.drawingArea = drawingArea;
    }

    @Override
    public void run(){
        while (!atTheBottom && !interrupted) {
            drawingArea.repaint();

            if (y >= drawingArea.getHeight() - radius) {
                atTheBottom = true;
            } else {
                y += fallSpeed;
                fallSpeed += FALL_SPEED_INC;
            }

            circle = new Ellipse2D.Float(x, y, radius, radius);

            try {
                Thread.sleep(FALL_INTERVAL);
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }

        while (!interrupted && !whited) {
            drawingArea.repaint();
            color = whitenColor(color, 1);

            if (color.equals(Color.WHITE)) {
                whited = true;
            }

            try {
                Thread.sleep(WHITEN_INTERVAL);
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
    }

    public Color getColor() {
        return this.color;
    }

    public Ellipse2D getCircle() {
        return this.circle;
    }

    public boolean isDone() {
        return atTheBottom && whited & !interrupted;
    }

    private Color whitenColor(Color color, int step) {
        int red = increase(color.getRed(), 255, step);
        int green = increase(color.getGreen(), 255, step);
        int blue = increase(color.getBlue(), 255, step);
        return new Color(red, green, blue);
    }

    private int increase(int number, int max, int step) {
        if (number < max) {
            return number + step;
        } else {
            return number;
        }
    }
}
