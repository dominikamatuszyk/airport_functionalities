import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utility {
    DataManager dm;

    public Utility(DataManager dm) {
        this.dm = dm;
    }

    String getFlightInfo(int flightNum, Date date){
        int cargoWeight = 0;
        int baggageWeight = 0;
        int totalWeight;
        int flightId = -1;
        flightId = getFlightId(flightNum, date, flightId);
        for(int i = 0; i<dm.cargos.length; i++){
            if(dm.cargos[i].getFlightId() == flightId){
                for(int j = 0; j<dm.cargos[i].getBaggage().length; j++){
                    baggageWeight += dm.cargos[i].getBaggage()[j].getWeight();
                }
                for(int j = 0; j<dm.cargos[i].getCargo().length; j++){
                    cargoWeight += dm.cargos[i].getCargo()[j].getWeight();
                }
            }
        }
        totalWeight = cargoWeight + baggageWeight;
        return ("a. model.Cargo Weight for requested Flight: " + cargoWeight +
        "\nb. model.Baggage Weight for requested Flight: " + baggageWeight +
        "\nc. Total Weight for requested Flight: " + totalWeight);
    }

     int getFlightId(int flightNum, Date date, int flightId) {
        for(int i=0; i<dm.flights.length; i++){
            if(dm.flights[i].getFlightNumber() == flightNum && isDateEqual(dm.flights[i].getDepartureDate(), date)){
                flightId = dm.flights[i].getFlightId();
                break;
            }
        }
        return flightId;
    }


    String getAirportInfo(String code, Date date) {
        int flightsCounterDepart = 0;
        int flightsCounterArrival = 0;
        int baggageArrival = 0;
        int baggageDepart = 0;
        for (int i = 0; i < dm.flights.length; i++) {
            if (isDateEqual(dm.flights[i].getDepartureDate(), date)) {
                if (dm.flights[i].getDepartureAirportIATACode().equals(code)) {
                    flightsCounterDepart++;
                    baggageDepart = getBaggagePieces(baggageDepart, i);
                }
                else if (dm.flights[i].getArrivalAirportIATACode().equals(code)) {
                    flightsCounterArrival++;
                    baggageArrival = getBaggagePieces(baggageArrival, i);
                }
            }
        }
        if (flightsCounterArrival == 0 && flightsCounterDepart == 0) {
            return "There are no flights from or to the " + code + " airport.";
        }
        return ("a. Number of flights departing from this airport: " + flightsCounterDepart
                + "\nb. Number of flights arriving to this airport: " + flightsCounterArrival
                + "\nc. Total number (pieces) of baggage arriving to this airport: " + baggageArrival
                + "\nd. Total number (pieces) of baggage departing from this airport: " + baggageDepart);

    }

     int getBaggagePieces(int baggageDepart, int i) {
         if (dm.cargos[i].getFlightId() == dm.flights[i].getFlightId()) {
            for (int j = 0; j < dm.cargos[i].getBaggage().length; j++) {
                baggageDepart += dm.cargos[i].getBaggage()[j].getPieces();
            }
        }
        return baggageDepart;
    }

    boolean isDateEqual(Date date1, Date date2) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant1 = date1.toInstant();
        LocalDate localDate1 = instant1.atZone(defaultZoneId).toLocalDate();
        Instant instant2 = date2.toInstant();
        LocalDate localDate2 = instant2.atZone(defaultZoneId).toLocalDate();
        return (localDate1.equals(localDate2));
    }

    //MAVEN --> LOMBOK

}
