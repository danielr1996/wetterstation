package de.arbeitsagentur.allegro.azubi.wetterstation;

import de.arbeitsagentur.allegro.azubi.wetterstation.model.WetterStation;

public class WetterStationConsole {
	WetterStation wetterstation = new WetterStation();

	public WetterStationConsole(){
		wetterstation.addTemps(1, 12);
		wetterstation.addTemps(2, 22);
		wetterstation.addTemps(3, 32);
		wetterstation.addTemps(4, 12);
		wetterstation.addTemps(5, 28);
		wetterstation.addTemps(6, 28);
		wetterstation.addTemps(7, 28);
		wetterstation.addTemps(8, 28);
		wetterstation.addTemps(9, 28);
		wetterstation.addTemps(10, 28);
		wetterstation.addTemps(1001, 28);
		wetterstation.print();
	}

	public static void main(String[] args) {
		new WetterStationConsole();
	}
}
