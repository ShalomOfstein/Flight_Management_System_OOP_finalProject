package Flight_Management_System.Composite;

import Flight_Management_System.Observer.AirlineWorker;
import Flight_Management_System.Observer.Notification;
import Flight_Management_System.Observer.Observer;
import Flight_Management_System.Observer.Subject;

import java.util.ArrayList;
/**
 * This class represents an airline.
 * it will implement the Composite design pattern to represent the hierarchy of airlines.
 * the airline class does so by implementing the AirlineInterface to provide the methods for the composite design pattern.
 * it will also implement the Subject interface to provide the methods for the observer design pattern.
 * if an Observer is added to an airline, it will be added as an Observer to all the flights and subAirlines of that airline.
 * so the Subject interface methods are also implemented in a composite way.
 */

public class Airline implements AirlineInterface, Subject {
    private final String name; // the name of the airline
    private final ArrayList<AirlineInterface> flights; // the flights of the airline
    private final ArrayList<AirlineInterface> subAirlines; // the subAirlines of the airline
    private final ArrayList<AirlineWorker> airlineWorkers; // the airline workers of the airline

    private final ArrayList<Observer> observers; // the observers of the airline



    /**
     * This is the constructor for the Airline class.
     * to create a new Airline object with the given parameters.
     */

    public Airline(String newName) {
        this.name = newName;
        this.flights = new ArrayList<>();
        this.subAirlines = new ArrayList<>();
        this.airlineWorkers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    /**
     * This method will add a flight to the airline.
     * @param flight the flight to be added
     */
    public void addFlight(Flight flight) {
        if (!flights.contains(flight)) {
            flights.add(flight);
        }
    }

    /**
     * This method will cancel a flight from the airline.
     * @param flight the flight to be canceled
     */
    public void cancelFlight(Flight flight) {
       flights.remove(flight);
    }


    /**
     * This method will add a subAirline to the airline.
     * @param subAirline the Flight_Management_System.Composite.Airline to be added as a sub-Division of the airline
     */
    public void addSubAirline(Airline subAirline) {
        if (!subAirlines.contains(subAirline)) {
            subAirlines.add(subAirline);
        }

    }

    /**
     * this method will return the name of the airline
     * @return the Airline name
     */
    public String getName() {
        return name;
    }

    /**
     * this method will add a new airline worker to the airline
     * @param airlineWorker the new worker to be added
     */
    public void addAirlineWorker(AirlineWorker airlineWorker) {
        if (!airlineWorkers.contains(airlineWorker)) {
            airlineWorkers.add(airlineWorker);
        }
    }

    /**
     * This method will remove an airline worker from the airline.
     * @param airlineWorker the worker to be removed
     */
    public void removeAirlineWorker(AirlineWorker airlineWorker) {
            airlineWorkers.remove(airlineWorker);
    }


    /**
     * These are the methods for the Flight_Management_System.Composite.Airline Interface.
     * They will be implemented in this class.
     * -> NumberOfFlights
     *      This method will return the number of flights in the airline. using the composite design pattern
     * -> NumberOfPassengers
     *     This method will return the number of passengers in the airline. using the composite design pattern
     * -> ProfitsFromTickets
     *    This method will return the profits from tickets in the airline. using the composite design pattern
     *
     */
    @Override
    public int NumberOfFlights() {
        int numOfSubFlights = 0;
        // loop through the subAirlines and get the number of flights from each one.
        for(AirlineInterface airline : subAirlines) {
            numOfSubFlights += airline.NumberOfFlights();
        }
        // loop through the flights and get the number of flights
        for(AirlineInterface flight : flights) {
            numOfSubFlights += flight.NumberOfFlights();
        }
        return numOfSubFlights;
    }

    @Override
    public int NumberOfPassengers() {
        int numOfSubPassengers=0;
        // loop through the subAirlines and get the number of passengers from each one
        for(AirlineInterface airline : subAirlines) {
            numOfSubPassengers += airline.NumberOfPassengers();
        }
        int numOfPassengers = 0;
        // loop through the flights and get the number of passengers
        for(AirlineInterface flight : flights) {
            numOfPassengers += flight.NumberOfPassengers();
        }
        return numOfPassengers + numOfSubPassengers;
    }

    @Override
    public int ProfitsFromTickets() {
        int profitsFromSubAirlines = 0;
        // loop through the subAirlines and get the profits from tickets from each one.
        for(AirlineInterface airline : subAirlines) {
            profitsFromSubAirlines += airline.ProfitsFromTickets();
        }
        int profitsFromFlights = 0;
        // loop through the flights and get the profits from tickets
        for(AirlineInterface flight : flights) {
            profitsFromFlights += flight.ProfitsFromTickets();
        }
        return profitsFromFlights + profitsFromSubAirlines;
    }

    /**
     * These are the methods for the Subject Interface.
     * They will be implemented in this class.
     * -> registerObserver
     *      This method will register an observer to the airline.
     * -> removeObserver
     *     This method will remove an observer from the airline.
     * -> notifyObservers
     *    This method will notify all the observers of the airline.
     *
     */
    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for(Observer observer : observers) {
            Notification notification = new Notification(observer, this, message);
            observer.update(notification);
        }
    }

}
