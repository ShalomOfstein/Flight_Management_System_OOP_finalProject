import java.util.ArrayList;
/**
 * This class represents an airline.
 * it will implement the Composite design pattern
 * to represent the hierarchy of airlines.
 */

public class Airline implements AirlineInterface, Subject {
    private final String name;
    private final ArrayList<Flight> flights;
    private final ArrayList<Airline> subAirlines;
    private final ArrayList<AirlineWorker> airlineWorkers;



    /**
     * This is the constructor for the Airline class.
     * to create a new Airline object with the given parameters.
     */

    public Airline(String newName) {
        this.name = newName;
        this.flights = new ArrayList<>();
        this.subAirlines = new ArrayList<>();
        this.airlineWorkers = new ArrayList<>();
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
    public void cancelFlight(Flight flight) {
       flights.remove(flight);
    }


    /**
     * This method will add a subAirline to the airline.
     * @param subAirline the Airline to be added as a sub-Division of the airline
     */
    public void addSubAirline(Airline subAirline) {
        if (!subAirlines.contains(subAirline)) {
            subAirlines.add(subAirline);
        }

    }


    public String getName() {
        return name;
    }

    public void addAirlineWorker(AirlineWorker airlineWorker) {
        if (!airlineWorkers.contains(airlineWorker)) {
            airlineWorkers.add(airlineWorker);
        }
    }
    public void removeAirlineWorker(AirlineWorker airlineWorker) {
            airlineWorkers.remove(airlineWorker);
    }


    /**
     * These are the methods for the Airline Interface.
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
        for(Airline airline : subAirlines) {
            numOfSubFlights += airline.NumberOfFlights();
        }
        // loop through the flights and get the number of flights
        for(Flight flight : flights) {
            numOfSubFlights += flight.NumberOfFlights();
        }
        return numOfSubFlights;
    }

    @Override
    public int NumberOfPassengers() {
        int numOfSubPassengers=0;
        // loop through the subAirlines and get the number of passengers from each one
        for(Airline airline : subAirlines) {
            numOfSubPassengers += airline.NumberOfPassengers();
        }
        int numOfPassengers = 0;
        // loop through the flights and get the number of passengers
        for(Flight flight : flights) {
            numOfPassengers += flight.NumberOfPassengers();
        }
        return numOfPassengers + numOfSubPassengers;
    }

    @Override
    public int ProfitsFromTickets() {
        int profitsFromSubAirlines = 0;
        // loop through the subAirlines and get the profits from tickets from each one.
        for(Airline airline : subAirlines) {
            profitsFromSubAirlines += airline.ProfitsFromTickets();
        }
        int profitsFromFlights = 0;
        // loop through the flights and get the profits from tickets
        for(Flight flight : flights) {
            profitsFromFlights += flight.ProfitsFromTickets();
        }
        return profitsFromFlights + profitsFromSubAirlines;
    }

    @Override
    public void registerObserver(Observer observer) {
        for(Flight flight : flights) {
            flight.registerObserver(observer);
        }
        for(Airline subAirline : subAirlines) {
            subAirline.registerObserver(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        for(Flight flight : flights) {
            flight.removeObserver(observer);
        }
        for(Airline subAirline : subAirlines) {
            subAirline.removeObserver(observer);
        }

    }

    @Override
    public void notifyObservers(String message) {
        for(Flight flight : flights) {
            flight.notifyObservers(message);
        }
        for(Airline subAirline : subAirlines) {
            subAirline.notifyObservers(message);
        }
    }


}
