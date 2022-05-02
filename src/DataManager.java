import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DataManager {
    public FlightEntity [] flights;
    public CargoEntity [] cargos;
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
        for(int i=0; i<flights.length;i++) {
            if(!airportsList.contains(flights[i].arrivalAirportIATACode)){
                airportsList.add(flights[i].arrivalAirportIATACode);
            }
            if(!airportsList.contains(flights[i].departureAirportIATACode)){
                airportsList.add(flights[i].departureAirportIATACode);
            }
            if(!datesFormattedList.contains(dateFormat.format(flights[i].departureDate))){
                datesList.add(flights[i].departureDate);
                String date = dateFormat.format(flights[i].departureDate);
                datesFormattedList.add(date);
            }
        }
    }
}
