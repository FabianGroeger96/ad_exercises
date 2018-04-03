package ch.hslu.AD.SW05.BallGame;

import javax.swing.*;
import java.awt.*;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Ballspiele
 *
 * @author Fabian Gröger
 * @version 29.03.2018
 */
public class FallingBalls extends JFrame {
    private DrawingArea area = new DrawingArea();

    public static void main(String args[]) {
        new FallingBalls();
    }

    public FallingBalls() {
        super("Beschleunigt fallende Bälle unterschiedlicher Farben und Grössen");
        getRootPane().setLayout(new GridBagLayout());
        getRootPane().add(area, new GridBagConstraints());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
