package model;

import java.util.Date;

public class FlightEntity {
    int flightId;
    int flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    Date departureDate;

    //WSZYSTKO PRYWATNE ZMIENIC
    //TESTY

    public int getFlightId() {
        return flightId;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirportIATACode() {
        return departureAirportIATACode;
    }

    public String getArrivalAirportIATACode() {
        return arrivalAirportIATACode;
    }

    public Date getDepartureDate() {
        return departureDate;
    }
}
