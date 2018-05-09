package ch.hslu.AD.SW11.FindTheFile;

import java.io.File;

/**
 * Übung: Parallelisierungsframeworks (N4)
 * Aufgabe: Finde das File
 *
 * @author Fabian Gröger
 * @version 09.05.2018
 */
public class FindFile {

    public static void main(String[] args) {
        final String directory = "/home/fabiangroeger/downloads/";

        String search = ".*.java$";
        File rootDir = new File(directory);

        FindFileTask root = new FindFileTask(rootDir, search);
        root.invoke().ifPresent(System.out::println);

        // Alternativer Aufruf
        //root.invoke();
        //root.join().ifPresent(System.out::println);

        System.out.println("done");
    }
}
