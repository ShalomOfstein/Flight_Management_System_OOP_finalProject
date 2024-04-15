package Flight_Management_System.Composite;

/**
 * Interface for the composite design pattern
 * This interface will be implemented in the Airline class and the Flight class
 * -> NumberOfFlights : This method will return the number of flights in the airline.
 * -> NumberOfPassengers : This method will return the number of passengers in the airline.
 * -> ProfitsFromTickets : This method will return the profits from tickets in the airline.
 */
public interface AirlineInterface {

    int NumberOfFlights();
    int NumberOfPassengers();
    int ProfitsFromTickets();
//    String getAirlineName();

}
