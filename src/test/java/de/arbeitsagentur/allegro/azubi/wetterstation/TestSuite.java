package de.arbeitsagentur.allegro.azubi.wetterstation;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import de.arbeitsagentur.allegro.azubi.wetterstation.model.WetterStationTest;
import util.UtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	UtilTest.class,
	WetterStationTest.class
})

public class TestSuite {}
