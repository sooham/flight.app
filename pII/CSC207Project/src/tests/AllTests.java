package tests;

import org.junit.runner.RunWith;

import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FlightTest.class, ItineraryTest.class, FlightManagerTest.class})
public class AllTests {

}
