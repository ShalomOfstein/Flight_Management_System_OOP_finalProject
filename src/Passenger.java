import java.util.ArrayList;

public class Passenger extends People {

    private ArrayList<Notification> notifications ;
    private ArrayList<Flight> flights;

    /**
     * This is the constructor for the Passenger class.
     * It will create a new Passenger object with the given parameters.
     * @param name
     */

    public Passenger(String name, int id) {
        super(name,id);
        this.notifications = new ArrayList<Notification>();
        this.flights = new ArrayList<Flight>();
    }


    public void BookFlight(Flight flight) {
        if (!flights.contains(flight)) {
            flights.add(flight);
            flight.addPassenger(this);
        }
    }
    public void removeFromFlight(Flight flight) {
        if (flights.contains(flight)) {
            flights.remove(flight);
            flight.removePassenger(this);
        }
    }

    /**
     * This method will return the name of the passenger.
     * @return String
     */
    public String getName() {
        return super.getName();
    }

    /**
     * This method will add a notification to the passenger.
     * it implements the Observer design pattern.
     * in this case the observer is the passenger and the subject is the flight.
     * @param notification
     */
    public void receiveNotification(Notification notification) {
        if (!notifications.contains(notification)) {
            notifications.add(notification);
        }
    }

}
