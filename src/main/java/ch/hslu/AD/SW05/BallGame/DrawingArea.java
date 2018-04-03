package ch.hslu.AD.SW05.BallGame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Ballspiele
 *
 * @author Fabian Gröger
 * @version 29.03.2018
 */
public class DrawingArea extends JPanel {
    private static final Logger LOGGER = LogManager.getLogger(DrawingArea.class);

    private final static int WIDTH = 650;
    private final static int HEIGHT = 500;

    private List<Ball> balls = new ArrayList<>();

    private Dimension areaDimension = new Dimension(WIDTH, HEIGHT);

    private Random rnd = new Random(System.currentTimeMillis());

    public DrawingArea() {
        setPreferredSize(areaDimension);
        setBackground(Color.WHITE);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createBall(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                createBall(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }
        });
    }

    public void createBall(int x, int y) {
        int radius = getRandomNumber(20, 50);
        Color color = getRandomColor();
        Ball ball = new Ball(x, y, radius, color, this);
        balls.add(ball);
        ball.start();
        LOGGER.info("added ball, now " + balls.size() + " balls");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()) {
            Ball ball = ballIterator.next();
            if (!ball.isDone()) {
                g2d.setColor(ball.getColor());
                g2d.fill(ball.getCircle());
            } else {
                ballIterator.remove();
                LOGGER.info("removed ball, now " + balls.size() + " balls");
            }
        }
    }

    public int getHeight() {
        return (int) areaDimension.getHeight();
    }

    public int getRandomNumber(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    private Color getRandomColor() {
        int r = getRandomNumber(0, 255);
        int g = getRandomNumber(0, 255);
        int b = getRandomNumber(0, 255);
        return new Color(r, g, b);
    }
}
