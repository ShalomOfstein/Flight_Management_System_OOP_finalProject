import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

public class Flight implements AirlineInterface,Subject{
    private final int FlightNumber;
    private final Airline Airline;
    private LocalDateTime DepartureTime;
    private LocalDateTime ArrivalTime;
    private double FlightLength; // Will be calculated based on DepartureTime and ArrivalTime
    private final ArrayList<Passenger> passengers;
    private final ArrayList<Observer> observers;
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
    public Flight(int FlightNumber, Airline Airline, LocalDateTime DepartureTime, LocalDateTime ArrivalTime, int TicketPrice) {
        this.Airline = Airline;
        this.FlightNumber = FlightNumber;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        this.FlightLength = (double) DepartureTime.until(ArrivalTime, ChronoUnit.HOURS);

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
            String message = "The ticket price for flight number:" + FlightNumber +" has been changed from " +
                    oldPrice + " to " + newPrice;
            notifyObservers(message);
        }
    }

    public void changeFlightTimes(LocalDateTime newDepartureTime, LocalDateTime newArrivalTime) {
        LocalDateTime oldDepartureTime = DepartureTime;
        LocalDateTime oldArrivalTime = ArrivalTime;
        DepartureTime = newDepartureTime;
        ArrivalTime = newArrivalTime;
        FlightLength = (double) DepartureTime.until(ArrivalTime, ChronoUnit.HOURS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String message = "The flight times for flight number: " + FlightNumber + " have been changed:\n" +
                "    Departure time has been changed from: " + oldDepartureTime.format(formatter) + ", to: " + newDepartureTime.format(formatter) + "\n" +
                "    Arrival time has been changed from: " + oldArrivalTime.format(formatter) + ", to: " + newArrivalTime.format(formatter) + "\n" +
                "    Flight length is now: " + (FlightLength) + " hours.";

        notifyObservers(message);
    }

    public void cancelFlight() {
        String message = "Flight number: " + FlightNumber + " has been canceled.";
        notifyObservers(message);
        Iterator<Passenger> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            passenger.removeFromFlight(this);
            iterator.remove();
        }
        Airline.cancelFlight(this);
        isCanceled = true;
    }

    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)){
            if(observer instanceof Passenger pass) { // cannot remove the observer if it is a passenger and is in the flight
                if(passengers.contains(pass)) {
                    return;
                }
            }
            observers.remove(observer);
        }
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            Notification notification = new Notification(observer, Airline, message);
            observer.update(notification);
        }
    }


    /**
     * This method will return the flight number.
     * @return int
     */
    public int getFlightNumber() {
        return FlightNumber;
    }
    public int getTicketPrice() {
        return TicketPrice;
    }
    public double getFlightLength() {
        return FlightLength;
    }
    public LocalDateTime getDepartureTime() {
        return DepartureTime;
    }

    public ArrayList<Observer> getObservers() {
        return observers;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void displayFlightInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String message = "Flight number: " + FlightNumber + "; Departure time: " + DepartureTime.format(formatter) + "; Arrival time: " +
                ArrivalTime.format(formatter) + "; Ticket price: " + TicketPrice + "; Flight length: " + FlightLength;
        System.out.println(message);
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "---------------------------------------------------------"+
                "\n-> Flight number: " + FlightNumber +
                "\nDeparture time: " + DepartureTime.format(formatter) +
                "\nArrival time: " + ArrivalTime.format(formatter) +
                "\nTicket price: " + TicketPrice +
                "\nFlight length: " + FlightLength;
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


}
