import java.util.ArrayList;

public class CargoEntity {
    int flightId;
    Baggage[] baggage = new Baggage[6];
    Cargo[] cargo = new Cargo[10];


    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Baggage[] getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage[] baggage) {
        this.baggage = baggage;
    }

    public Cargo[] getCargo() {
        return cargo;
    }

    public void setCargo(Cargo[] cargo) {
        this.cargo = cargo;
    }
}
