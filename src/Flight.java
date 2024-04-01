import java.util.ArrayList;

public class Flight implements AirlineInterface{
    private int FlightNumber;
    private Airline Airline;
    private int DepartureTime;
    private int ArrivalTime;
    private int FlightLength;
    private final ArrayList<Passenger> passengers;
    private int TicketPrice;

    /**
     * This is the constructor for the Flight class.
     * It will create a new Flight object with the given parameters.
     * @param Airline
     * @param FlightNumber
     * @param DepartureTime
     * @param ArrivalTime
     * @param TicketPrice
     */
    public Flight(int FlightNumber, Airline Airline, int DepartureTime, int ArrivalTime, int TicketPrice) {
        this.Airline = Airline;
        this.FlightNumber = FlightNumber;
        this.DepartureTime = DepartureTime;
        this.ArrivalTime = ArrivalTime;
        this.FlightLength = ArrivalTime - DepartureTime;
        this.TicketPrice = TicketPrice;
        this.passengers = new ArrayList<Passenger>();
    }

    /**
     * This method will add a passenger to the flight.
     * @param Passenger
     */
    public void addPassenger(Passenger Passenger) {
        if(!passengers.contains(Passenger)) {
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
     * This method will return the flight number.
     * @return int
     */
    public int getFlightNumber() {
        return FlightNumber;
    }



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
