package de.arbeitsagentur.allegro.azubi.wetterstation.model;
import static org.junit.Assert.assertEquals;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;


public class WetterStationTest {

	/**
	 * Testet die Berechnung der korrekten Durschnittstemperatur
	 */
	@Test
	public void getDurchschnittTest(){
		SortedMap<Integer, Integer> map;
		WetterStation w;

		map = new TreeMap<Integer, Integer>();
		map.put(1, 2);
		w = new WetterStation(map);
		assertEquals("Durschnitt von {2} = 2", 2, w.getDurchschnitt(), 0);

		map = new TreeMap<Integer, Integer>();
		map.put(1, 2);
		map.put(2, 4);
		w = new WetterStation(map);
		assertEquals("Durschnitt von {2, 4} = 3", 3, w.getDurchschnitt(), 0);

		map = new TreeMap<Integer, Integer>();
		map.put(1, -1);
		map.put(2, 1);
		w = new WetterStation(map);
		assertEquals("Durschnitt von {-1, 1} = 0", 0, w.getDurchschnitt(), 0);

		map = new TreeMap<Integer, Integer>();
		map.put(1, -1);
		map.put(2, -2);
		map.put(3, 5);
		w = new WetterStation(map);
		assertEquals("Durschnitt von {-1, -2, 5} = 0,6", 0.6, w.getDurchschnitt(), 0.1);

		map = new TreeMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(4, 4);
		w = new WetterStation(map);
		assertEquals("Durschnitt von {1, 2, 3, 4} = 2,5", 2.5, w.getDurchschnitt(), 0.1);

		map = new TreeMap<Integer, Integer>();
		w = new WetterStation(map);
		assertEquals("Durschnitt einer leeren Menge ist 0", 0, w.getDurchschnitt(), 0.1);
	}

	@Test
	public void testMax(){
		SortedMap<Integer, Integer> map;
		WetterStation w;

		map = new TreeMap<Integer, Integer>();
		map.put(1, 2);
		w = new WetterStation(map);
		assertEquals("Max von {2} ist 2", 2, w.getMax());

		map = new TreeMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(4, 4);

		w = new WetterStation(map);
		assertEquals("Max von {1, 2, 3, 4} ist 4", 4, w.getMax());

		map = new TreeMap<Integer, Integer>();
		map.put(1, 4);
		map.put(2, 3);
		map.put(3, 2);
		map.put(4, 1);

		w = new WetterStation(map);
		assertEquals("Max von {4, 3, 2, 1} ist 4", 4, w.getMax());

		map = new TreeMap<Integer, Integer>();
		map.put(1, -4);
		map.put(2, -3);
		map.put(3, -2);
		map.put(4, -1);

		w = new WetterStation(map);
		assertEquals("Max von {-4, -3, -2, -1} ist -1", -1, w.getMax());

		map = new TreeMap<Integer, Integer>();

		w = new WetterStation(map);
		assertEquals("Max einer leeren Menge ist 0", 0, w.getMax());
	}

	@Test
	public void testMin(){
		SortedMap<Integer, Integer> map;
		WetterStation w;

		map = new TreeMap<Integer, Integer>();
		map.put(1, 2);
		w = new WetterStation(map);
		assertEquals("Min von {2} ist 2", 2, w.getMin());

		map = new TreeMap<Integer, Integer>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(4, 4);

		w = new WetterStation(map);
		assertEquals("Min von {1, 2, 3, 4} ist 1", 1, w.getMin());

		map = new TreeMap<Integer, Integer>();
		map.put(1, 4);
		map.put(2, 3);
		map.put(3, 2);
		map.put(4, 1);

		w = new WetterStation(map);
		assertEquals("Min von {4, 3, 2, 1} ist 1", 1, w.getMin());

		map = new TreeMap<Integer, Integer>();
		map.put(1, -4);
		map.put(2, -3);
		map.put(3, -2);
		map.put(4, -1);

		w = new WetterStation(map);
		assertEquals("Min von {-4, -3, -2, -1} ist -4", -4, w.getMin());


		map = new TreeMap<Integer, Integer>();

		w = new WetterStation(map);
		assertEquals("Min einer leeren Menge ist 0", 0, w.getMin());
	}

	@Test
	public void testGetMaxDiff(){
		SortedMap<Integer, Integer> map;
		WetterStation w;

		map = new TreeMap<Integer, Integer>();
		map.put(1, 2);
		w = new WetterStation(map);
		assertEquals("Max Diff von {2} ist an Tag -1", -1, w.getMaxDiff());

		map = new TreeMap<Integer, Integer>();
		w = new WetterStation(map);
		assertEquals("Max Diff von leere Menge ist an Tag null", -1, w.getMaxDiff());

		map = new TreeMap<Integer, Integer>();
		map.put(4, 3);
		map.put(5, 10);
		map.put(6, 4);
		map.put(7, 9);
		w = new WetterStation(map);
		assertEquals("Max Diff von {3, 10, 4, 9} ist an Tag 4", (int)new Integer(4), w.getMaxDiff());


		map = new TreeMap<Integer, Integer>();
		map.put(4, -3);
		map.put(5, -10);
		map.put(6, -4);
		map.put(7, -20);
		w = new WetterStation(map);
		assertEquals("Max Diff von {3, 10, 4, 9} ist an Tag 6", (int)new Integer(6), w.getMaxDiff());
	}
}
