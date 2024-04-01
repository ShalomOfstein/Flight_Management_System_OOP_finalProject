import java.util.ArrayList;

public class Passenger extends Person {


    private final ArrayList<Flight> flights;

    /**
     * This is the constructor for the Passenger class.
     * It will create a new Passenger object with the given parameters.
     * @param name the name of the passenger
     * @param id the id of the passenger
     */

    public Passenger(String name, int id) {
        super(name,id);
        this.flights = new ArrayList<>();
    }


    public void BookFlight(Flight flight) {
        if (!flights.contains(flight)) {
            flights.add(flight);
            flight.addPassenger(this);
        }
    }
    public void removeFromFlight(Flight flight) {
            flights.remove(flight);

    }

    /**
     * This method will return the name of the passenger.
     * @return String
     */
    public String getName() {
        return super.getName();
    }




}
