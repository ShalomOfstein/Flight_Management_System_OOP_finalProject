import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /**
         *                        airline1               airline5
         *                    /     |        \                 |
         *            airline2    airline3   flight5         flight6
         *                  /           \
         *             airline4        flight4
         *                |
         *   flight1 , flight2 , flight3
         *
         *
         */

        FlightManagementSystem system = new FlightManagementSystem();
        Airline airline1 = system.createAirline("Airline1");
        Airline airline2 = system.createAirline("Airline2");
        Airline airline3 = system.createAirline("Airline3");
        Airline airline4 = system.createAirline("Airline4");
        Airline airline5 = system.createAirline("Airline5");

        airline1.addSubAirline(airline2);
        airline1.addSubAirline(airline3);
        airline2.addSubAirline(airline4);

        Flight flight1 = system.createFlight(1, airline4, 1, 2, 100);
        Flight flight2 = system.createFlight(2, airline4, 2, 3, 200);
        Flight flight3 = system.createFlight(3, airline4, 3, 4, 300);
        Flight flight4 = system.createFlight(4, airline3, 4, 5, 400);
        Flight flight5 = system.createFlight(5, airline1, 5, 6, 500);
        Flight flight6 = system.createFlight(6, airline5, 6, 7, 600);

        Passenger passenger1 = system.createPassenger("Alice", 1);
        Passenger passenger2 = system.createPassenger("Bob", 2);
        Passenger passenger3 = system.createPassenger("Charlie", 3);
        Passenger passenger4 = system.createPassenger("David", 4);
        Passenger passenger5 = system.createPassenger("Eve", 5);
        Passenger passenger6 = system.createPassenger("Frank", 6);
        Passenger passenger7 = system.createPassenger("Grace", 7);
        Passenger passenger8 = system.createPassenger("Hank", 8);

        system.addPassengerToFlight(passenger1, flight1);
        system.addPassengerToFlight(passenger2, flight2);
        system.addPassengerToFlight(passenger3, flight3);
        system.addPassengerToFlight(passenger4, flight4);
        system.addPassengerToFlight(passenger5, flight5);
        system.addPassengerToFlight(passenger6, flight6);
        system.addPassengerToFlight(passenger7, flight1);

        passenger8.watchFlight(flight1);

        AirlineWorker airlineWorker1 = system.createAirlineWorker("Ivan", 1, airline1);
        AirlineWorker airlineWorker2 = system.createAirlineWorker("John", 2, airline2);
        AirlineWorker airlineWorker3 = system.createAirlineWorker("Kate", 3, airline3);

        airlineWorker1.watchFlight(flight1);
        airlineWorker2.watchFlight(flight2);
        airlineWorker3.watchFlight(flight3);



        int numberOfFlights1 = airline1.NumberOfFlights();
        int numberOfFlights2 = airline2.NumberOfFlights();
        int numberOfFlights3 = airline3.NumberOfFlights();
        int numberOfFlights4 = airline4.NumberOfFlights();
        int numberOfFlights5 = airline5.NumberOfFlights();

        System.out.println("Number of flights in airline1: " + numberOfFlights1);
        System.out.println("Number of flights in airline2: " + numberOfFlights2);
        System.out.println("Number of flights in airline3: " + numberOfFlights3);
        System.out.println("Number of flights in airline4: " + numberOfFlights4);
        System.out.println("Number of flights in airline5: " + numberOfFlights5);
        System.out.println();

        int numberOfPassengers1 = airline1.NumberOfPassengers();
        int numberOfPassengers2 = airline2.NumberOfPassengers();
        int numberOfPassengers3 = airline3.NumberOfPassengers();
        int numberOfPassengers4 = airline4.NumberOfPassengers();
        int numberOfPassengers5 = airline5.NumberOfPassengers();

        System.out.println("Number of passengers in airline1: " + numberOfPassengers1);
        System.out.println("Number of passengers in airline2: " + numberOfPassengers2);
        System.out.println("Number of passengers in airline3: " + numberOfPassengers3);
        System.out.println("Number of passengers in airline4: " + numberOfPassengers4);
        System.out.println("Number of passengers in airline5: " + numberOfPassengers5);
        System.out.println();
        int profits1 = airline1.ProfitsFromTickets();
        int profits2 = airline2.ProfitsFromTickets();
        int profits3 = airline3.ProfitsFromTickets();
        int profits4 = airline4.ProfitsFromTickets();
        int profits5 = airline5.ProfitsFromTickets();

        System.out.println("Profits from tickets in airline1: " + profits1);
        System.out.println("Profits from tickets in airline2: " + profits2);
        System.out.println("Profits from tickets in airline3: " + profits3);
        System.out.println("Profits from tickets in airline4: " + profits4);
        System.out.println("Profits from tickets in airline5: " + profits5);
        System.out.println();

        flight1.changeFlightTimes(8,9);
        flight2.cancelFlight();
        flight3.changeTicketPrice(350);
        flight4.changeTicketPrice(450);
        flight5.changeFlightTimes(10,11);
        flight6.changeFlightTimes(3,5);
        flight3.changeFlightTimes(6,7);
        flight5.changeTicketPrice(550);
        flight1.changeTicketPrice(150);
        flight1.cancelFlight();


        airlineWorker1.printNotifications();
        airlineWorker2.printNotifications();
        airlineWorker3.printNotifications();

        passenger1.printNotifications();
        passenger2.printNotifications();
        passenger3.printNotifications();
        passenger4.printNotifications();
        passenger5.printNotifications();
        passenger6.printNotifications();
        passenger7.printNotifications();
        passenger8.printNotifications();








//        Scanner scanner = new Scanner(System.in);
//        System.out.println("creating the system...");
//        FlightManagementSystem system = new FlightManagementSystem();
//
//        System.out.println("Create a new airline:\nEnter the name of the airline:");
//        String airlineName = scanner.nextLine();
//        Airline airline = system.createAirline(airlineName);
//
//        System.out.println("Create a new flight:\nEnter the flight number:");
//        int flightNumber = scanner.nextInt();
//        System.out.println("Enter the departure time:");
//        int departureTime = scanner.nextInt();
//        System.out.println("Enter the arrival time:");
//        int arrivalTime = scanner.nextInt();
//        System.out.println("Enter the ticket price:");
//        int ticketPrice = scanner.nextInt();
//        Flight flight = system.createFlight(flightNumber, airline, departureTime, arrivalTime, ticketPrice);
//
//        System.out.println("Create a new passenger:\nEnter the name of the passenger:");
//        String passengerName = scanner.nextLine();
//        System.out.println("Enter the ID number of the passenger:");
//        int id = scanner.nextInt();
//        Passenger passenger = system.createPassenger(passengerName, id);
//
//        System.out.println("Add the passenger to the flight:");
//        system.addPassengerToFlight(passenger, flight);
//
//        System.out.println("Search for a flight:\nEnter the flight number:");
//        int searchFlightNumber = scanner.nextInt();
//
////        Flight searchFlight = system.searchFlight(searchFlightNumber);
//
//        System.out.println("");


    }
}