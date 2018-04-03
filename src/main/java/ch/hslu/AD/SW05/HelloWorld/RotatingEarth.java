package ch.hslu.AD.SW05.HelloWorld;

import java.awt.*;
import java.util.List;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Hello World
 *
 * @author Fabian Gröger
 * @version 03.04.2018
 */
public class RotatingEarth extends Thread implements Runnable {
    private final static int INTERVAL = 50;

    private final List<Image> images;
    private final int startImage;
    private final DrawingArea area;

    private boolean interrupted = false;
    private int currentImageIndex = 0;
    private boolean directionForward;

    public RotatingEarth(List<Image> images, boolean directionForward, int currentImage, DrawingArea area) {
        this.images = images;
        this.directionForward = directionForward;
        this.startImage = currentImage;
        this.area = area;
    }

    public void changeDirection() {
        directionForward = !directionForward;
    }

    public void run() {
        currentImageIndex = startImage;
        while (!interrupted) {
            Image image = images.get(currentImageIndex);
            area.paintImage(image);
            try {
                Thread.sleep(INTERVAL);
            } catch (InterruptedException e) {
                interrupted = true;
            }
            if (directionForward)
                if (currentImageIndex == images.size() - 1) {
                    currentImageIndex = 0;
                } else {
                    currentImageIndex++;
                }
            else if (!directionForward) {
                if (currentImageIndex == 0) {
                    currentImageIndex = images.size() - 1;
                } else {
                    currentImageIndex--;
                }
            }
        }
    }
}
