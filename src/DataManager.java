import com.fasterxml.jackson.databind.ObjectMapper;
import model.CargoEntity;
import model.FlightEntity;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

public class DataManager {
    public FlightEntity[] flights;
    public CargoEntity[] cargos;
    public ArrayList<String> airportsList;
    public ArrayList<Date> datesList;
    public ArrayList<String> datesFormattedList;


    void convertFlightsJsonToArray() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File flightsFile = new File ("./Flights.json");
        flights = mapper.readValue(flightsFile, FlightEntity[].class);
    }

    void convertCargosJsonToArray() throws IOException {
         ObjectMapper mapper = new ObjectMapper();
         File file = new File ("./Cargo.json");
         cargos = mapper.readValue(file, CargoEntity[].class);
    }

    void setLists(){
        airportsList = new ArrayList<>();
        datesList = new ArrayList<>();
        datesFormattedList = new ArrayList<>();
        Locale loc = new Locale("en", "US");
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
        for (FlightEntity flight : flights) {
            if (!airportsList.contains(flight.getArrivalAirportIATACode())) {
                airportsList.add(flight.getArrivalAirportIATACode());
            }
            if (!airportsList.contains(flight.getDepartureAirportIATACode())) {
                airportsList.add(flight.getDepartureAirportIATACode());
            }
            if (!datesFormattedList.contains(dateFormat.format(flight.getDepartureDate()))) {
                datesList.add(flight.getDepartureDate());
                String date = dateFormat.format(flight.getDepartureDate());
                datesFormattedList.add(date);
            }
        }
    }
}
