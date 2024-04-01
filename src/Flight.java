import java.util.ArrayList;
import java.util.Iterator;

public class Flight implements AirlineInterface{
    private final int FlightNumber;
    private final Airline Airline;
    private int DepartureTime;
    private int ArrivalTime;
    private int FlightLength;
    private final ArrayList<Passenger> passengers;
    private final ArrayList<Person> observers;
    private int TicketPrice;
    public boolean isCanceled;

    /**
     * This is the constructor for the Flight class.
     * It will create a new Flight object with the given parameters.
     * @param Airline the airline of the flight
     * @param FlightNumber the flight number
     * @param DepartureTime the departure time
     * @param ArrivalTime the arrival time
     * @param TicketPrice the ticket price
     */
    public Flight(int FlightNumber, Airline Airline, int DepartureTime, int ArrivalTime, int TicketPrice) {
        this.Airline = Airline;
        this.FlightNumber = FlightNumber;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        this.FlightLength = ArrivalTime - DepartureTime;
        this.TicketPrice = TicketPrice;
        this.passengers = new ArrayList<>();
        this.observers = new ArrayList<>();
        isCanceled = false;
    }

    /**
     * This method will add a passenger to the flight.
     * @param Passenger the passenger to be added
     */
    public void addPassenger(Passenger Passenger) {
        if(!passengers.contains(Passenger)) {
            observers.add(Passenger);
            passengers.add(Passenger);
            Passenger.BookFlight(this);
        }
    }

    public void removePassenger(Passenger Passenger) {
        if(passengers.contains(Passenger)) {
            passengers.remove(Passenger);
            Passenger.removeFromFlight(this);
        }
    }

    /**
     * this method is for changing the ticket price.
     */
    public void changeTicketPrice(int newPrice) {
        int oldPrice = TicketPrice;
        TicketPrice = newPrice;
        if(newPrice!=oldPrice) {
            String message = "The ticket price for flight number:" + FlightNumber +" has been changed from " + oldPrice + " to " +
                    newPrice;
            for(Person observer : observers) {
                Notification note = new Notification( observer, Airline, message);
                note.sendNotification();
            }
        }
    }

    public void changeFlightTimes(int newDepartureTime, int newArrivalTime) {
        int oldDepartureTime = DepartureTime;
        int oldArrivalTime = ArrivalTime;
        DepartureTime = newDepartureTime;
        ArrivalTime = newArrivalTime;
        FlightLength = ArrivalTime - DepartureTime;
        String message = "The flight times for flight number: " + FlightNumber + " have been changed:\n" +
                "    Departure time has been changed from: " + oldDepartureTime + ", to: " + newDepartureTime + "\n" +
                "    Arrival time has been changed from: " + oldArrivalTime + ", to: " + newArrivalTime+ "\n"+
                "    Flight length is now: " + (oldArrivalTime - oldDepartureTime);

        for (Person observer : observers) {
            Notification note = new Notification(observer, Airline, message);
            note.sendNotification();
        }

    }

    public void cancelFlight() {
        for(Person observer : observers) {
            String message = "Flight number: " + FlightNumber + " has been canceled.";
            Notification note = new Notification(observer, Airline, message);
            note.sendNotification();
        }
        Iterator<Passenger> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            passenger.removeFromFlight(this);
            iterator.remove();
        }
        Airline.cancelFlight(this);
        isCanceled = true;
    }

    public void addObserver(Person observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    public void removeObserver(Person observer) {
        if (observers.contains(observer)){
            if(observer instanceof Passenger pass) { // cannot remove the observer if it is a passenger and is in the flight
                if(passengers.contains(pass)) {
                    return;
                }
            }
            observers.remove(observer);
        }
    }


    /**
     * This method will return the flight number.
     * @return int
     */
    public int getFlightNumber() {
        return FlightNumber;
    }

    public ArrayList<Person> getObservers() {
        return observers;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    /**
     * these are the implemented methods from the AirlineInterface
     * They will be implemented in this class.
     * this is how the composite design pattern is implemented.
     */

    @Override
    public int NumberOfFlights() {
        return 1;
    }

    @Override
    public int NumberOfPassengers() {
        return passengers.size();
    }

    @Override
    public int ProfitsFromTickets() {
        return passengers.size() * TicketPrice;
    }

//    @Override
//    public String getAirlineName() {
//        return Airline.getAirlineName();
//    }
}
