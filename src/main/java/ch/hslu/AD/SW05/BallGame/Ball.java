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

    /**
     * Creates a ball
     *
     * @param x           the x - coordinate of the ball
     * @param y           the y - coordinate of the ball
     * @param radius      the radius of the ball
     * @param color       the color of the ball
     * @param drawingArea the drawing area the ball is displayed
     */
    public Ball(final int x, final int y, final int radius, final Color color, final DrawingArea drawingArea) {
        this.circle = new Ellipse2D.Float(x, y, radius, radius);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.fallSpeed = 1.0f;
        this.color = color;
        this.drawingArea = drawingArea;
    }

    /**
     * Overrides the run method
     */
    @Override
    public void run() {
        while (!atTheBottom && !interrupted) { // if the ball is not at the bottom and is not interrupted
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

        while (!interrupted && !whited) { // if the ball is not whited and is not interrupted
            drawingArea.repaint();
            color = whitenColor(color, 1);

            if (color.equals(Color.WHITE)) { // if the ball is white, set flag whited = true
                whited = true;
            }

            try {
                Thread.sleep(WHITEN_INTERVAL);
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
    }

    /**
     * Returns the color of the ball
     *
     * @return color of the ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Returns the circle of the ball
     *
     * @return circle of the ball
     */
    public Ellipse2D getCircle() {
        return this.circle;
    }

    /**
     * Returns if the ball is done
     * The ball is done if it's at the bottom and whited and not interrupted
     *
     * @return ball is done
     */
    public boolean isDone() {
        return atTheBottom && whited & !interrupted;
    }

    /**
     * Whiten the color of the ball
     *
     * @param color the color that it should be in the end
     * @param step  the step how much it should whiten
     * @return the new color of the ball
     */
    private Color whitenColor(Color color, int step) {
        int red = increase(color.getRed(), 255, step);
        int green = increase(color.getGreen(), 255, step);
        int blue = increase(color.getBlue(), 255, step);
        return new Color(red, green, blue);
    }

    /**
     * Helper method for whitenColor
     * Increase the passed number with the step and checking if it's not the maximum
     *
     * @param number the number to increase
     * @param max    the maximum of the passed number
     * @param step   the amount to increase the passed number
     * @return the calculated number
     */
    private int increase(int number, int max, int step) {
        if (number < max) {
            return number + step;
        } else {
            return number;
        }
    }
}
