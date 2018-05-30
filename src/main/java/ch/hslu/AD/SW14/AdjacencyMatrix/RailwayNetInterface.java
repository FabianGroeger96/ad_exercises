package ch.hslu.AD.SW14.AdjacencyMatrix;

import java.util.Collection;

/**
 * Übung: Graphen (A5)
 * Aufgabe: Adjazenzmatrix
 *
 * @author Fabian Gröger
 * @version 30.05.2018
 */
public interface RailwayNetInterface {

    void addConnection(String from, String to, int duration);

    int getStationCount();

    int getConnectionCount();

    boolean hasConnectionBetween(String a, String b);

    Collection<String> getDirectlyConnectedStations(String station);

    int getDuration(String from, String to);

}
