package de.arbeitsagentur.allegro.azubi.wetterstation.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Kann von einer Bean Klasse implementiert werden, um auf Änderungen auf diesem Bean zu reagieren
 * @author RichterD042
 *
 */
public abstract class Bindable {
	 protected PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	 public void addPropertyChangeListener(PropertyChangeListener x) {
	  changeSupport.addPropertyChangeListener(x);
	 }

	 public void removePropertyChangeListener(PropertyChangeListener x) {
	  changeSupport.removePropertyChangeListener(x);
	 }
}