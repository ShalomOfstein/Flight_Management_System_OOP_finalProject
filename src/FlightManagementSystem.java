import java.util.ArrayList;

/**
* This is the main class for the system.
* This class will be able to:
* -> create a new Flight
* -> create a new Airline
* -> create a new Passenger
* -> add a Passenger to a Flight
* -> search for a Flight by different criteria
 */

public class FlightManagementSystem {
    private final ArrayList<Airline> airlines;
    private final ArrayList<Flight> flights;
    private final ArrayList<Passenger> passengers;
    private final ArrayList<AirlineWorker> airlineWorkers;

    /**
     * This is the constructor for the FlightManagementSystem class.
     * It will create a new FlightManagementSystem object with the given parameters.
     * It will initialize the airlines, flights, and passengers ArrayLists.
     */

    public FlightManagementSystem() {
        airlines = new ArrayList<Airline>();
        flights = new ArrayList<Flight>();
        passengers = new ArrayList<Passenger>();
        airlineWorkers = new ArrayList<AirlineWorker>();
    }

    /**
     * This method will create a new Airline object with the given parameters.
     * @param airlineName the name of the airline
     * @return Airline
     */

    public Airline createAirline(String airlineName) {
        if (airlineName == null || airlineName.isEmpty()) {
            return null;
        }
        for (Airline airline : airlines) {
            if (airline.getName().equals(airlineName)) {
                return null;
            }
        }
        Airline airline = new Airline(airlineName);
        airlines.add(airline);
        return airline;
    }

    /**
     * This method will create a new Flight object with the given parameters.
     * @param flightNumber the flight number
     * @param airline the airline of the flight
     * @param departureTime the departure time
     * @param arrivalTime the arrival time
     * @param ticketPrice the ticket price
     * @return Flight
     */
    public Flight createFlight(int flightNumber, Airline airline, int departureTime, int arrivalTime, int ticketPrice) {
        if (flightNumber <= 0 || airline == null || departureTime < 0 || arrivalTime < 0 || ticketPrice < 0) {
            System.out.println("Invalid input");
            return null;
        }
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                System.out.println("Flight already exists");
                return null;
            }
        }
        Flight flight = new Flight(flightNumber, airline, departureTime, arrivalTime, ticketPrice);
        flights.add(flight);
        airline.addFlight(flight);
        return flight;
    }


    /**
     * This method will create a new Passenger object with the given parameters.
     * @param passengerName the name of the passenger
     * @return Passenger
     */

    public Passenger createPassenger(String passengerName,int id) {
        if (passengerName == null || passengerName.isEmpty()||id<=0) {
            return null;
        }
        for (Passenger passenger : passengers) {
            if (passenger.getID() == id) {
                System.out.println("Passenger already exists");
                return null;
            }
        }
        Passenger passenger = new Passenger(passengerName, id);
        passengers.add(passenger);
        return passenger;
    }

    /**
     * This method will add a Passenger to a Flight.
     * @param passenger the passenger to be added
     * @param flight the flight to be added to
     */
    public void addPassengerToFlight(Passenger passenger, Flight flight) {
        if (passenger == null || flight == null) {
            System.out.println("Invalid input");
            return;
        }
        passenger.BookFlight(flight);
    }

    public void removePassengerFromFlight(Passenger passenger, Flight flight) {
        if (passenger == null || flight == null) {
            System.out.println("Invalid input");
            return;
        }
        flight.removePassenger(passenger);
    }

    public AirlineWorker createAirlineWorker(String name, int id, Airline airline) {
        if(name == null || name.isEmpty() || id <= 0 || airline == null) {
            return null;
        }
        for(AirlineWorker airlineWorker : airlineWorkers) {
            if(airlineWorker.getID()==id) {
                System.out.println("Airline Worker already exists");
                return null;
            }
        }
        AirlineWorker airlineWorker = new AirlineWorker(name, id, airline);
        airlineWorkers.add(airlineWorker);
        return airlineWorker;
    }




}
