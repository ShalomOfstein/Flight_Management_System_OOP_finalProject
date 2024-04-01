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
    ArrayList<Airline> airlines;
    ArrayList<Flight> flights;
    ArrayList<Passenger> passengers;

    /**
     * This is the constructor for the FlightManagementSystem class.
     * It will create a new FlightManagementSystem object with the given parameters.
     * It will initialize the airlines, flights, and passengers ArrayLists.
     */

    public FlightManagementSystem() {
        airlines = new ArrayList<Airline>();
        flights = new ArrayList<Flight>();
        passengers = new ArrayList<Passenger>();
    }

    /**
     * This method will create a new Airline object with the given parameters.
     * @param airlineName
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
     * @param flightNumber
     * @param airline
     * @param departureTime
     * @param arrivalTime
     * @param ticketPrice
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
     * @param passengerName
     * @return Passenger
     */

    public Passenger createPassenger(String passengerName,int id) {
        if (passengerName == null || passengerName.isEmpty()||id<=0) {
            return null;
        }
        for (Passenger passenger : passengers) {
            if (passenger.getName().equals(passengerName)) {
                return null;
            }
        }
        Passenger passenger = new Passenger(passengerName, id);
        passengers.add(passenger);
        return passenger;
    }

    /**
     * This method will add a Passenger to a Flight.
     * @param passenger
     * @param flight
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




}
