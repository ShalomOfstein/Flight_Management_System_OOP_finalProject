package Flight_Management_System.Composite;

import Flight_Management_System.Observer.Notification;
import Flight_Management_System.Observer.Observer;
import Flight_Management_System.Observer.Passenger;
import Flight_Management_System.Observer.Subject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class represents a Flight.
 * It implements the AirlineInterface and the Subject interface.
 * It will be used to create a flight object with the given parameters.
 *
 */
public class Flight implements AirlineInterface, Subject {
    private final int FlightNumber; // the flight number
    private final Airline Airline; // the airline of the flight
    private LocalDateTime DepartureTime; // the departure time
    private LocalDateTime ArrivalTime; // the arrival time
    private double FlightLength; // Will be calculated based on DepartureTime and ArrivalTime
    private final ArrayList<Passenger> passengers; // the passengers of the flight
    private final ArrayList<Observer> observers; // the observers of the flight
    private int TicketPrice; // the ticket price
    public boolean isCanceled; // if the flight is canceled

    /**
     * This is the constructor for the Flight_Management_System.Composite.Flight class.
     * It will create a new Flight_Management_System.Composite.Flight object with the given parameters.
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
        if(!passengers.contains(Passenger)) { // if the passenger is not already in the flight
            registerObserver(Passenger); // add the passenger as an observer (all passengers are observers by default)
            passengers.add(Passenger); // add the passenger to the flight
            Passenger.BookFlight(this); // add the flight to the passenger
        }
    }

    /**
     * This method will remove a passenger from the flight.
     * @param Passenger the passenger to be removed
     */
    public void removePassenger(Passenger Passenger) {
        if(passengers.contains(Passenger)) { // if the passenger is in the flight
            passengers.remove(Passenger); // remove the passenger from the flight
            Passenger.removeFromFlight(this); // remove the flight from the passenger
        }
    }

    /**
     * this method is for changing the ticket price.
     */
    public void changeTicketPrice(int newPrice) {
        int oldPrice = TicketPrice; // save the old ticket price
        TicketPrice = newPrice; // change the ticket price
        if(newPrice!=oldPrice) {
            // notify the observers if the ticket price has changed
            String message = "The ticket price for flight number:" + FlightNumber +" has been changed from " +
                    oldPrice + " to " + newPrice;
            notifyObservers(message);
        }
    }


    /**
     * This method will change the flight times.
     * @param newDepartureTime the new departure time
     * @param newArrivalTime the new arrival time
     */
    public void changeFlightTimes(LocalDateTime newDepartureTime, LocalDateTime newArrivalTime) {
        LocalDateTime oldDepartureTime = DepartureTime;
        LocalDateTime oldArrivalTime = ArrivalTime;
        DepartureTime = newDepartureTime;
        ArrivalTime = newArrivalTime;
        FlightLength = (double) DepartureTime.until(ArrivalTime, ChronoUnit.HOURS);
        if( !oldDepartureTime.equals(newDepartureTime) || !oldArrivalTime.equals(newArrivalTime) ) {
            // notify the observers if the flight times have changed
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String message = "The flight times for flight number: " + FlightNumber + " have been changed:\n" +
                    "    Departure time has been changed from: " + oldDepartureTime.format(formatter) + ", to: " + newDepartureTime.format(formatter) + "\n" +
                    "    Arrival time has been changed from: " + oldArrivalTime.format(formatter) + ", to: " + newArrivalTime.format(formatter) + "\n" +
                    "    Flight length is now: " + (FlightLength) + " hours.";

            notifyObservers(message);
        }
    }

    /**
     * This method will cancel the flight.
     * and notify the observers.
     */
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

    /**
     * These are the implemented methods from the Subject interface.
     * They will be implemented in this class.
     * -> registerObserver: This method will register an observer to observe the flight.
     * -> removeObserver: This method will remove an observer from observing the flight.
     * -> notifyObservers: This method will notify all the observers of the flight a message.
     */

    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)){
            if(observer instanceof Passenger pass) {
                // cannot remove the observer if it is a passenger and is in the flight
                // All passengers are observers by default and must stay as observers unless they un-book the flight
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
     * These are the getter methods for the Flight class
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
//
//    public ArrayList<Observer> getObservers() {
//        return observers;
//    }
//
//    public ArrayList<Passenger> getPassengers() {
//        return passengers;
//    }
//
//    public void displayFlightInfo() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        String message = "Flight number: " + FlightNumber + "; Departure time: " + DepartureTime.format(formatter) + "; Arrival time: " +
//                ArrivalTime.format(formatter) + "; Ticket price: " + TicketPrice + "; Flight_Management_System.Composite.Flight length: " + FlightLength;
//        System.out.println(message);
//    }

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
