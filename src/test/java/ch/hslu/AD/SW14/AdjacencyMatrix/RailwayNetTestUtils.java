package ch.hslu.AD.SW14.AdjacencyMatrix;

/**
 * Übung: Graphen (A5)
 * Aufgabe: Adjazenzmatrix
 *
 * @author Fabian Gröger
 * @version 30.05.2018
 */
public class RailwayNetTestUtils {

    private static final String[] stations = {"Olten", "Zofingen", "Aarau", "Brugg", "Lenzburg", "Wohlen", "Dietikon",
            "Luzern", "Rotkreuz", "Zürich", "Zug", "Arth-Goldau", "Pfäffikon"};

    public static RailwayNetInterface createAdjacencyMatrixRailwayNet() {
        RailwayNetInterface net = new RailwayNet1(stations);
        populateConnections(net);
        return net;
    }

    public static RailwayNetInterface createAdjacencyListRailwayNet() {
        RailwayNetInterface net = new RailwayNet1(stations);
        populateConnections(net);
        return net;
    }

    private static void populateConnections(RailwayNetInterface net) {
        net.addConnection("Olten", "Zürich", 36);
        net.addConnection("Olten", "Aarau", 13);
        net.addConnection("Olten", "Zofingen", 7);
        net.addConnection("Zofingen", "Lenzburg", 34);
        net.addConnection("Zofingen", "Luzern", 35);
        net.addConnection("Aarau", "Brugg", 13);
        net.addConnection("Aarau", "Lenzburg", 8);
        net.addConnection("Brugg", "Lenzburg", 16);
        net.addConnection("Brugg", "Dietikon", 16);
        net.addConnection("Lenzburg", "Luzern", 80);
        net.addConnection("Lenzburg", "Wohlen", 9);
        net.addConnection("Lenzburg", "Zürich", 19);
        net.addConnection("Lenzburg", "Dietikon", 19);
        net.addConnection("Wohlen", "Dietikon", 30);
        net.addConnection("Wohlen", "Rotkreuz", 23);
        net.addConnection("Dietikon", "Zürich", 12);
        net.addConnection("Luzern", "Rotkreuz", 16);
        net.addConnection("Luzern", "Arth-Goldau", 30);
        net.addConnection("Rotkreuz", "Zug", 12);
        net.addConnection("Rotkreuz", "Arth-Goldau", 15);
        net.addConnection("Zürich", "Pfäffikon", 30);
        net.addConnection("Zürich", "Zug", 25);
        net.addConnection("Zug", "Arth-Goldau", 20);
        net.addConnection("Arth-Goldau", "Pfäffikon", 39);
    }

    public static int getStationCount() {
        return stations.length;
    }

    public static int getConnectionCount() {
        return 24; // counted manually
    }
}
