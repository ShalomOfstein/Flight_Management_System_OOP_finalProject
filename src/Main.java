import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("creating the system...");
        FlightManagementSystem system = new FlightManagementSystem();

        System.out.println("Create a new airline:\nEnter the name of the airline:");
        String airlineName = scanner.nextLine();
        Airline airline = system.createAirline(airlineName);

        System.out.println("Create a new flight:\nEnter the flight number:");
        int flightNumber = scanner.nextInt();
        System.out.println("Enter the departure time:");
        int departureTime = scanner.nextInt();
        System.out.println("Enter the arrival time:");
        int arrivalTime = scanner.nextInt();
        System.out.println("Enter the ticket price:");
        int ticketPrice = scanner.nextInt();
        Flight flight = system.createFlight(flightNumber, airline, departureTime, arrivalTime, ticketPrice);

        System.out.println("Create a new passenger:\nEnter the name of the passenger:");
        String passengerName = scanner.nextLine();
        System.out.println("Enter the ID number of the passenger:");
        int id = scanner.nextInt();
        Passenger passenger = system.createPassenger(passengerName, id);

        System.out.println("Add the passenger to the flight:");
        system.addPassengerToFlight(passenger, flight);

        System.out.println("Search for a flight:\nEnter the flight number:");
        int searchFlightNumber = scanner.nextInt();

        Flight searchFlight = system.searchFlight(searchFlightNumber);

        System.out.println("");


    }
}