package de.arbeitsagentur.allegro.azubi.wetterstation;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import de.arbeitsagentur.allegro.azubi.wetterstation.controller.Controller;
import de.arbeitsagentur.allegro.azubi.wetterstation.model.WetterStation;
import de.arbeitsagentur.allegro.azubi.wetterstation.view.WetterStationPanel;

/**
 * Hauptklasse der WetterStation Anwendung, initialisiert Model, View und Controller
 * @author RichterD042
 *
 */
public class WetterStationMain{
	private JFrame frame;
	private WetterStation model;
	private WetterStationPanel view;

	/**
	 * Standard Konstruktor
	 */
	public WetterStationMain() {
		model = new WetterStation();
		view = new WetterStationPanel();
		new Controller(model, view);

		frame = new JFrame("Wetter Station");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(view);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Main Methode
	 * @param args Kommandozeilen Parameter
	 */
	public static void main(String... args) {
		/*
		 * Look and Feel setzen
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new WetterStationMain();
	}

}
