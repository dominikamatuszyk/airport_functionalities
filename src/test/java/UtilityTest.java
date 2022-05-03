import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class UtilityTest {
    static DataManager dm = new DataManager();
    Utility util = new Utility(dm);

    @BeforeClass
    public static void initializeData() throws IOException {
        dm.convertCargosJsonToArray();
        dm.convertFlightsJsonToArray();
        dm.setLists();
    }

    @Test
    public void shouldSayThatDatesAreEqual(){
        Date testDate = new Date(1540264000000L);
        Assert.assertTrue(util.isDateEqual(testDate, testDate));
    }

    @Test
    public void shouldGet3221BaggagePieces() {
        Assert.assertEquals(3221, util.getBaggagePieces(0, 0));
    }

    @Test
    public void shouldReturnNoFlights()  {
        Assert.assertEquals("There are no flights from or to the LAX airport.", util.getAirportInfo("LAX", dm.flights[0].getDepartureDate()));
    }

    @Test
    public void shouldGetAirportInfoWithArrival() {
        Assert.assertEquals("a. Number of flights departing from this airport: 0" +
                "\nb. Number of flights arriving to this airport: 1" +
                "\nc. Total number (pieces) of baggage arriving to this airport: 3221" +
                "\nd. Total number (pieces) of baggage departing from this airport: 0", util.getAirportInfo("PPX", dm.flights[0].getDepartureDate()));
    }

    @Test
    public void shouldNotGet1FlightId() {
        Assert.assertNotEquals(2, util.getFlightId(1806, new Date(2020, Calendar.SEPTEMBER,16), 0));
    }

    @Test
    public void shouldGetFlightInfo()  {
        Assert.assertEquals("a. model.Cargo Weight for requested Flight: 1824"
                + "\nb. model.Baggage Weight for requested Flight: 2183"
                + "\nc. Total Weight for requested Flight: 4007", util.getFlightInfo(1806, dm.flights[1].getDepartureDate()));
    }

    @Test
    public void shouldGetAirportInfoWithDeparture() {
        Assert.assertEquals("a. Number of flights departing from this airport: 1" +
                "\nb. Number of flights arriving to this airport: 0" +
                "\nc. Total number (pieces) of baggage arriving to this airport: 0" +
                "\nd. Total number (pieces) of baggage departing from this airport: 3221", util.getAirportInfo("ANC", dm.flights[0].getDepartureDate()) );
    }

}
