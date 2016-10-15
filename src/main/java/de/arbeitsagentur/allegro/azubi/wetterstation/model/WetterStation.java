package de.arbeitsagentur.allegro.azubi.wetterstation.model;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import util.Direction;
import util.Util;


/**
 * Model Klasse
 *
 * @author RichterD042
 *
 */
public class WetterStation extends Bindable {
	private SortedMap<Integer, Integer> temps;
	private double durchschnitt;
	private int max, min, maxdiff;
	public static final String PROPERTY_DURCHSCHNITT = "Durchschnitt";
	public static final String PROPERTY_MAX = "Max";
	public static final String PROPERTY_MIN = "Min";
	public static final String PROPERTY_MAXDIFF = "Max Diff";
	public static final String PROPERTY_TEMPS = "Temps";

	public double getDurchschnitt() {
		return durchschnitt;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public int getMaxDiff() {
		return maxdiff;
	}

	/**
	 * Gibt die Temperaturen Map zurück
	 *
	 * @return
	 */
	public SortedMap<Integer, Integer> getTemps() {
		return temps;
	}

	/**
	 * Erstellet ein neues WetterStation Object mit der angebenen Map
	 *
	 * @param temps
	 *            Map enthält die Temperaturwerte
	 */
	public WetterStation(SortedMap<Integer, Integer> temps) {
		this.temps = temps;
		this.durchschnitt = durchschnitt();
		this.max = maxTemp();
		this.min = minTemp();
		this.maxdiff = maxDiff();

		changeSupport.firePropertyChange(PROPERTY_TEMPS, 1, this.temps);
	}

	/**
	 * Standardkonstruktur erstellt leere Map
	 */
	public WetterStation() {
		this.temps = new TreeMap<Integer, Integer>();
		changeSupport.firePropertyChange(PROPERTY_TEMPS, 1, this.temps);
	}

	/**
	 * Gibt den Durchschnitt der Temperaturen zurück
	 *
	 * @return
	 */
	private double durchschnitt() {
		if (temps.size() <= 0) {
			return 0;
		}
		float ergebnis = 0;

		for (Integer value : temps.values()) {
			ergebnis += value;
		}

		ergebnis /= temps.size();

		return ergebnis;
	}

	/**
	 * Gibt die höchste Temperatur zurück
	 *
	 * @return
	 */
	private int maxTemp() {
		if (temps.size() <= 0) {
			return 0;
		}
		Integer max = null;

		for (Integer temp : temps.values()) {
			if (max == null || temp > max) {
				max = temp;
			}
		}
		return max;
	}

	/**
	 * Gibt die kleinste Temperatur zurück
	 *
	 * @return
	 */
	private int minTemp() {
		if (temps.size() <= 0) {
			return 0;
		}
		Integer min = null;

		for (Integer temp : temps.values()) {
			if (min == null || temp < min) {
				min = temp;
			}
		}
		return min;
	}

	/**
	 * Gibt den Tag mit der höchsten Temperaturdifferenz zurück
	 *
	 * @return
	 */
	private int maxDiff() {
		if (temps.size() <= 1) {
			return -1;
		}

		Integer[] keys = temps.keySet().toArray(new Integer[0]);
		// Integer[] values = temps.values().toArray(new Integer[0]);
		Integer diff = null;
		Integer start = null;
		// int ende = 0;
		for (int i = 0; i < keys.length - 1; i++) {
			int today = temps.get(keys[i]);
			int tomorrow = temps.get(keys[i + 1]);
			if (diff == null || Util.abs(today - tomorrow) > diff) {
				diff = Util.abs(today - tomorrow);
				start = keys[i];
				// ende = keys[i+1];
			}

		}

		// System.out.println(diff+", "+start+", "+ende);
		return start;
	}

	/**
	 * Gibt die Temperaturen auf der Konsole aus
	 */
	public void print() {
		for (Entry<Integer, Integer> entry : temps.entrySet()) {
			int padLength = Util.maxKey(temps);
			String s = Util.pad(padLength, ' ', entry.getKey().toString(),
					Direction.LEFT);
			System.out.print(s + ": ");
			for (int i = 0; i < entry.getValue(); i++) {
				System.out.print("* ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Setzt die Temperaturen Map und benachrichtigt über die Änderung
	 *
	 * @param temps
	 */
	public void addTemps(int key, int value) {
		SortedMap<Integer, Integer> old = new TreeMap<Integer, Integer>(temps);
		temps.put(key, value);

		changeSupport.firePropertyChange(PROPERTY_DURCHSCHNITT, durchschnitt,
				durchschnitt = durchschnitt());
		changeSupport.firePropertyChange(PROPERTY_MAX, max, max=maxTemp());
		changeSupport.firePropertyChange(PROPERTY_MIN, min, min = minTemp());
		changeSupport.firePropertyChange(PROPERTY_MAXDIFF, maxdiff, maxdiff=maxDiff());


		changeSupport.firePropertyChange(PROPERTY_TEMPS, old,temps);
	}
}
