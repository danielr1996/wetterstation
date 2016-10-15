package de.arbeitsagentur.allegro.azubi.wetterstation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import de.arbeitsagentur.allegro.azubi.wetterstation.model.WetterStation;
import de.arbeitsagentur.allegro.azubi.wetterstation.view.WetterStationPanel;
import util.Util;

/**
 * Controller Klasse, reagiert auf Benutzereingaben und setzt Viewkomponenten
 * @author RichterD042
 *
 */
public class Controller{
	private WetterStationPanel view;
	private WetterStation model;

	public Controller(WetterStation model, WetterStationPanel view){
		//Initialisieren der Variablen
		this.model = model;
		this.view = view;
		model.addPropertyChangeListener(new ChangeController());
		view.addButtonListener(new AddController());
	}

	/**
	 * Button Listener
	 * @author RichterD042
	 *
	 */
	class AddController implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			try{
				int tag = Integer.valueOf(view.getTfTag());
				int wert = Integer.valueOf(view.getTfWert());
				model.addTemps(tag, wert);
		    	view.resetEingabeFelder();
			}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(new JFrame(),
					    "Bitte geben Sie eine Zahl ein.",
					    "Fehler",
					    JOptionPane.ERROR_MESSAGE);
			}

	    	view.setTfTag(String.valueOf(model.getTemps().lastKey()+1));
		}
	}

	/**
	 * PropertyChange Listener
	 * @author RichterD042
	 *
	 */
	class ChangeController implements PropertyChangeListener{
		@SuppressWarnings("unchecked")
		public void propertyChange(PropertyChangeEvent evt) {

			if(evt.getPropertyName().equals(WetterStation.PROPERTY_TEMPS)){
				view.setTableData(new DefaultTableModel(Util.toTableArray(model.getTemps()), new String[]{"Tag", "Wert"}));
				for(Entry<Integer, Integer> entry : Util.getDifferenzMenge(model.getTemps(), (SortedMap<Integer, Integer>)evt.getOldValue()).entrySet()){
					view.addChartData(entry.getValue(), "Tages Temperaturen", entry.getKey().toString());
				}

				for(Entry<Integer, Integer> entry : model.getTemps().entrySet()){
					view.addChartData(model.getDurchschnitt(), "Durchschnittstemperatur", entry.getKey().toString());
					view.addChartData(model.getMax(), "Maximaltemperatur", entry.getKey().toString());
					view.addChartData(model.getMin(), "Minimaltemperatur", entry.getKey().toString());
				}
			}
			if(evt.getPropertyName().equals(WetterStation.PROPERTY_MAX)){
				String maximalTemperatur = Integer.toString(model.getMax());
				view.setTfMax(maximalTemperatur +" °C");
			}
			if(evt.getPropertyName().equals(WetterStation.PROPERTY_MIN)){
				String minimalTemperatur = Integer.toString(model.getMin());
				view.setTfMin(minimalTemperatur+" °C");
			}
			if(evt.getPropertyName().equals(WetterStation.PROPERTY_MAXDIFF)){
				Integer maxDiff = model.getMaxDiff();
				String maximaleDifferenz = maxDiff == null ? "" : "Tag " +maxDiff.toString();
				view.setTfMaxDiff(maximaleDifferenz);
			}
			if(evt.getPropertyName().equals(WetterStation.PROPERTY_DURCHSCHNITT)){
				String durchschnitt = new DecimalFormat("#.##").format(model.getDurchschnitt());
				view.setTfAvg(durchschnitt +" °C");
			}
		}
	}
}