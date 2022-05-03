package model;

public class CargoEntity {
    int flightId;
    Baggage[] baggage = new Baggage[6];
    Cargo[] cargo = new Cargo[10];


    public int getFlightId() {
        return flightId;
    }

    public Baggage[] getBaggage() {
        return baggage;
    }

    public Cargo[] getCargo() {
        return cargo;
    }
}
