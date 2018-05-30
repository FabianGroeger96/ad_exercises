package ch.hslu.AD.SW14.AdjacencyMatrix;

import nl.jqno.equalsverifier.internal.prefabvalues.Tuple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Übung: Graphen (A5)
 * Aufgabe: Adjazenzmatrix
 *
 * @author Fabian Gröger
 * @version 30.05.2018
 */
public class RailwayNet1Test {

    private RailwayNetInterface net;
    private int stationCount;
    private int connectionCount;

    @Before
    public void init() {
        net = RailwayNetTestUtils.createAdjacencyMatrixRailwayNet();
        stationCount = RailwayNetTestUtils.getStationCount();
        connectionCount = RailwayNetTestUtils.getConnectionCount();
    }

    @Test
    public void testStationCount() {
        assertEquals(stationCount, net.getStationCount());
    }

    @Test
    public void testConnectionCount() {
        assertEquals(connectionCount, net.getConnectionCount());
    }

    @Test
    public void testHasConnectionBetweenConnected() {
        List<Tuple<String>> connected = new ArrayList<>();
        connected.add(new Tuple<String>("Olten", "Zürich"));
        connected.add(new Tuple<String>("Pfäffikon", "Arth-Goldau"));
        connected.add(new Tuple<String>("Rotkreuz", "Wohlen"));
        connected.add(new Tuple<String>("Luzern", "Zofingen"));
        for (Tuple<String> t : connected) {
            assertTrue(net.hasConnectionBetween(t.getRed(), t.getBlack()));
        }
    }

    @Test
    public void testHasConnectionBetweenUnconnected() {
        List<Tuple<String>> unconnected = new ArrayList<>();
        unconnected.add(new Tuple<String>("Olten", "Pfäffikon"));
        unconnected.add(new Tuple<String>("Zofingen", "Zürich"));
        unconnected.add(new Tuple<String>("Luzern", "Brugg"));
        unconnected.add(new Tuple<String>("Dietikon", "Arth-Goldau"));
        for (Tuple<String> t : unconnected) {
            assertFalse(net.hasConnectionBetween(t.getRed(), t.getBlack()));
        }
    }

    @Test
    public void testGetDirectlyConnectedStations() {
        String[] rotkreuzConnections = new String[]{"Luzern", "Wohlen", "Zug", "Arth-Goldau"};
        Collection<String> connected = net.getDirectlyConnectedStations("Rotkreuz");
        assertEquals(4, connected.size());
        for (String to : rotkreuzConnections) {
            assertTrue(connected.contains(to));
        }
    }

    @Test
    public void testGetDurationOltenZuerich() {
        assertEquals(36, net.getDuration("Olten", "Zürich"));
    }

    @Test
    public void testGetDurationBruggAarau() {
        assertEquals(13, net.getDuration("Brugg", "Aarau"));
    }

    @Test
    public void testGetDurationRotkreuzArthGoldau() {
        assertEquals(15, net.getDuration("Rotkreuz", "Arth-Goldau"));
    }
}