package ch.hslu.AD.SW05.HelloWorld;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Übung: Threads & Synchronisation (N1)
 * Aufgabe: Hello World
 *
 * @author Fabian Gröger
 * @version 03.04.2018
 */
public class App extends JFrame {
    private DrawingArea area;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super("EX05 Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            List<Image> images = loadImages();
            area = new DrawingArea(images, this);
            getRootPane().setLayout(new GridBagLayout());
            getRootPane().add(area, new GridBagConstraints());
            pack();
            setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private List<Image> loadImages() throws IOException {
        List<Image> images = new ArrayList<>();
        for (int n = 1; n <= 18; n++) {
            final String path = String.format("worlddata/IMG%04d.GIF", n);
            URL url = getClass().getClassLoader().getResource(path);
            Image image = ImageIO.read(url);
            images.add(image);
        }
        return images;
    }
}
