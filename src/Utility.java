import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Utility {
    DataManager dm;

    public Utility(DataManager dm) {
        this.dm = dm;
    }

    String getFlightInfo(int flightNum, Date date){
        int cargoWeight = 0, baggageWeight = 0, totalWeight = 0, flightId = 0;
        for(int i=0; i<dm.flights.length; i++){
            if(dm.flights[i].flightNumber == flightNum && compareDates(dm.flights[i].departureDate,date)){
                flightId = dm.flights[i].flightId;
                break;
            }
        }
        for(int i = 0; i<dm.cargos.length; i++){
            if(dm.cargos[i].flightId == flightId){
                for(int j = 0; j<dm.cargos[i].baggage.length; j++){
                    baggageWeight += dm.cargos[i].baggage[j].weight;
                }
                for(int j = 0; j<dm.cargos[i].cargo.length; j++){
                    cargoWeight += dm.cargos[i].cargo[j].weight;
                }
            }
        }
        totalWeight = cargoWeight + baggageWeight;

       return ("a. Cargo Weight for requested Flight: " + cargoWeight +
        "\nb. Baggage Weight for requested Flight: " + baggageWeight +
        "\nc. Total Weight for requested Flight: " + totalWeight);

    }

    String getAirportInfo(String code, int dateIndex) {
        int flightsCounterDepart = 0, flightsCounterArrival = 0;
        int baggageArrival = 0, baggageDepart = 0;
        for (int i = 0; i < dm.flights.length; i++) {
            if (compareDates(dm.flights[i].departureDate, dm.datesList.get(dateIndex))) {
                if (dm.flights[i].departureAirportIATACode.equals(code)) {
                    flightsCounterDepart++;
                    if (dm.cargos[i].flightId == dm.flights[i].flightId) {
                        for (int j = 0; j < dm.cargos[i].baggage.length; j++) {
                            baggageDepart += dm.cargos[i].baggage[j].pieces;
                        }
                    }
                }
                else if (dm.flights[i].arrivalAirportIATACode.equals(code)) {
                    flightsCounterArrival++;
                    if (dm.cargos[i].flightId == dm.flights[i].flightId) {
                        for (int j = 0; j < dm.cargos[i].baggage.length; j++) {
                            baggageArrival += dm.cargos[i].baggage[j].pieces;
                        }
                    }
                }
            }
        }

        if (flightsCounterArrival==0 &&  flightsCounterDepart == 0) {
            System.out.println("NO FLIGHTS");
            return "There are no flights from or to the " + code + " airport.";
        }

        return ("a. Number of flights departing from this airport: " + flightsCounterDepart
                + "\nb. Number of flights arriving to this airport: " + flightsCounterArrival
                + "\nc. Total number (pieces) of baggage arriving to this airport: " + baggageArrival
                + "\nd. Total number (pieces) of baggage departing from this airport: " + baggageDepart);

    }

    boolean compareDates(Date date1, Date date2) {
        int month1 = date1.getMonth();
        int day1 = date1.getDay();
        int year1 = date1.getYear();
        Date newDate1 = new Date(month1, day1, year1);

        int month2 = date2.getMonth();
        int day2 = date2.getDay();
        int year2 = date2.getYear();
        Date newDate2 = new Date(month2, day2, year2);

        return (newDate1.equals(newDate2));
    }

}
