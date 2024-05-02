import Flight_Management_System.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Flight_Management_System.Composite.Airline;
import Flight_Management_System.Composite.Flight;
import Flight_Management_System.Observer.AirlineWorker;
import Flight_Management_System.Observer.Passenger;
import Flight_Management_System.Sort_Strategy.SortByDepartureTime;
import Flight_Management_System.Sort_Strategy.SortByFlightLength;
import Flight_Management_System.Sort_Strategy.SortByPrice;

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

        System.out.println("""
                This is the Flight Management System.
                The system is designed to manage airlines, flights, passengers, and airline workers.
                Creating the system...
                """);
        FlightManagementSystem system = FlightManagementSystem.getInstance();
        System.out.println("The system has been created.\n");
        System.out.println("""
                Do you want to load the system with automatic data? (you will still be able to add you own data after)
                1. Yes
                2. No
                Please enter your choice:""");
        Scanner scanner = new Scanner(System.in);
        int automaticData;
        try{
            automaticData = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Invalid choice. please enter 1 or 2. Exiting the system...");
            return;
        }
        if(automaticData == 1) {
            System.out.println("Loading the system with automatic data...");
            loadAutomaticData(system);
            System.out.println("""
                The system has been loaded with the following automatic data.
                                          Airline1               Airline5
                                      /       |     \\                |
                              Airline2    Airline3   flight5         flight6
                                 /            \\
                                Airline4     flight4
                                   |
                       flight1 , flight2 , flight3
                    
                Passenger Alice is added to flight1
                Passenger Bob is added to flight2
                Passenger Charlie is added to flight3
                Passenger David is added to flight4
                Passenger Eve is added to flight5
                Passenger Frank is added to flight6
                Passenger Grace is added to flight1
                
                Eve, Frank, and Hank are watching flight1.
                Charlie, David, and Grace are watching flight2.
                Alice and Bob are watching flight3.
                Airline Worker Ivan is watching flight1
                Airline Worker John is watching flight2
                Airline Worker Kate is watching flight3
                
                Ticket price for flight1 has been changed from 100 to 150
                Flight times for flight2 have been changed from 2025-06-13 06:00 to 2025-06-13 07:00
                Flight3 has been canceled.
                
                """);
        }
        int mainMenu = 1;
        while (mainMenu != 0) {
            System.out.println("""
                    Main Menu:
                        1. Add an airline, flight, passenger, or airline worker
                        2. Make changes to a flight, passenger, or airline worker
                        3. Search for a flight
                        4. Print a list of sorted flights
                        5. Print info about airlines, flights, passengers, and airline workers
                        0. Exit
                        Please enter your choice:
                        """);
            mainMenu = scanner.nextInt();
            switch (mainMenu) {
                case 1 ->{
                    System.out.println("""
                            Add Menu:
                                1. Add an airline
                                2. Add a flight
                                3. Add a passenger
                                4. Add an airline worker
                                0. Back to main menu
                                Please enter your choice:
                                """);
                    int addMenu = scanner.nextInt();
                    createMenu(addMenu, system);

                }
                case 2 ->{
                    System.out.println("""
                            Change Menu:
                                1. Change the ticket price of a flight
                                2. Change the flight times of a flight
                                3. Cancel a flight
                                4. Remove a passenger from a flight
                                0. Back to main menu
                                Please enter your choice:
                                """);
                    int changeMenu = scanner.nextInt();
                    changeMenu(changeMenu, system);
                }
                case 3 -> {
                    system.searchFlight();
                }
                case 4 -> {
                    System.out.println("""
                            Please select a strategy to sort the flights:
                            1. Sort by Departure Time
                            2. Sort by Flight Length
                            3. Sort by Price
                            """);
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> system.sortFlight(new SortByDepartureTime());
                        case 2 -> system.sortFlight(new SortByFlightLength());
                        case 3 -> system.sortFlight(new SortByPrice());
                        default -> System.out.println("Invalid choice.");
                    }
                }
                case 5 -> {
                    System.out.println("""
                            Printing info menu:
                            1. Print profits from airline\\flight
                            2. Print number of flights in airline
                            3. Print number of passengers in a flight or an airline
                            4. Print all the notifications a person has received
                            5. Print all the notifications everyone has received
                            0. Back to main menu
                            Please enter your choice:""");
                    int infoChoice = scanner.nextInt();
                    printInfo(infoChoice, system);
                }

                case 0 -> System.out.println("Exiting the system...");
                default -> System.out.println("Invalid choice.");
            }


        }



    }

    public static void loadAutomaticData(FlightManagementSystem system) {
        Airline airline1 = system.createAirline("Airline1");
        Airline airline2 = system.createAirline("Airline2");
        Airline airline3 = system.createAirline("Airline3");
        Airline airline4 = system.createAirline("Airline4");
        Airline airline5 = system.createAirline("Airline5");

        airline1.addSubAirline(airline2);
        airline1.addSubAirline(airline3);
        airline2.addSubAirline(airline4);

        LocalDateTime departureTime1 = LocalDateTime.of(2025, 5, 1, 7, 0);
        LocalDateTime arrivalTime1 = LocalDateTime.of(2025, 5, 1, 14, 0);
        Flight flight1 = system.createFlight(1, airline4, departureTime1, arrivalTime1, 100);

        LocalDateTime departureTime2 = LocalDateTime.of(2025, 6, 13, 6, 0);
        LocalDateTime arrivalTime2 = LocalDateTime.of(2025, 6, 13, 7, 0);
        Flight flight2 = system.createFlight(2, airline4, departureTime2, arrivalTime2, 200);

        LocalDateTime departureTime3 = LocalDateTime.of(2025, 5, 26, 14, 0);
        LocalDateTime arrivalTime3 = LocalDateTime.of(2025, 5, 26, 16, 0);
        Flight flight3 = system.createFlight(3, airline4, departureTime3, arrivalTime3, 300);

        LocalDateTime departureTime4 = LocalDateTime.of(2025, 5, 22, 4, 0);
        LocalDateTime arrivalTime4 = LocalDateTime.of(2025, 5, 22, 10, 0);
        Flight flight4 = system.createFlight(4, airline3, departureTime4, arrivalTime4, 400);

        LocalDateTime departureTime5 = LocalDateTime.of(2025, 5, 4, 7, 0);
        LocalDateTime arrivalTime5 = LocalDateTime.of(2025, 5, 4, 10, 0);
        Flight flight5 = system.createFlight(5, airline1, departureTime5, arrivalTime5, 500);

        LocalDateTime departureTime6 = LocalDateTime.of(2025, 5, 7, 7, 30);
        LocalDateTime arrivalTime6 = LocalDateTime.of(2025, 5, 7, 11, 0);
        Flight flight6 = system.createFlight(6, airline5, departureTime6, arrivalTime6, 600);

        Passenger passenger1 = system.createPassenger("Alice", 1);
        Passenger passenger2 = system.createPassenger("Bob", 2);
        Passenger passenger3 = system.createPassenger("Charlie", 3);
        Passenger passenger4 = system.createPassenger("David", 4);
        Passenger passenger5 = system.createPassenger("Eve", 5);
        Passenger passenger6 = system.createPassenger("Frank", 6);
        Passenger passenger7 = system.createPassenger("Grace", 7);
        Passenger passenger8 = system.createPassenger("Hank", 8);

        // when adding a passenger to a flight, the passenger will be added to the list of observers of the flight
        system.addPassengerToFlight(passenger1, flight1);
        system.addPassengerToFlight(passenger2, flight2);
        system.addPassengerToFlight(passenger3, flight3);
        system.addPassengerToFlight(passenger4, flight4);
        system.addPassengerToFlight(passenger5, flight5);
        system.addPassengerToFlight(passenger6, flight6);
        system.addPassengerToFlight(passenger7, flight1);

        passenger5.watchFlight(flight1);
        passenger6.watchFlight(flight1);
        passenger8.watchFlight(flight1);
        passenger3.watchFlight(flight2);
        passenger4.watchFlight(flight2);
        passenger7.watchFlight(flight2);
        passenger1.watchFlight(flight3);
        passenger2.watchFlight(flight3);



        AirlineWorker airlineWorker1 = system.createAirlineWorker("Ivan", 1, airline1);
        AirlineWorker airlineWorker2 = system.createAirlineWorker("John", 2, airline2);
        AirlineWorker airlineWorker3 = system.createAirlineWorker("Kate", 3, airline3);

        airlineWorker1.watchFlight(flight1);
        airlineWorker2.watchFlight(flight2);
        airlineWorker3.watchFlight(flight3);

        flight1.changeTicketPrice(150);
        LocalDateTime newDepartureTime2 = LocalDateTime.of(2025, 6, 13, 7, 0);
        LocalDateTime newArrivalTime2 = LocalDateTime.of(2025, 6, 13, 8, 0);
        flight2.changeFlightTimes(newDepartureTime2, newArrivalTime2);
        flight3.cancelFlight();





    }


    public static void createMenu(int choice, FlightManagementSystem system){
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1 -> {
                System.out.println("Enter the name of the airline:");
                String airlineName = scanner.nextLine();
                system.createAirline(airlineName);
                System.out.println("Airline has been added. do you want to make it a sub airline of an existing airline? (y/n)");
                String subAirline = scanner.nextLine();
                if (subAirline.equals("y")) {
                    System.out.println("Enter the name of the parent airline:");
                    String parentAirline = scanner.nextLine();
                    if (system.getAirline(parentAirline) != null) {
                        system.getAirline(parentAirline).addSubAirline(system.getAirline(airlineName));
                        System.out.println("Airline has been added as a sub airline.");
                    } else {
                        System.out.println("Parent airline not found.");

                    }
                }
            }
            case 2 -> {
                System.out.println("Enter the flight number:");
                int flightNumber;
                try {
                    flightNumber = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid flight number.");
                    return;
                }
                System.out.println("Enter the airline name:");
                scanner.nextLine();
                String airlineName = scanner.nextLine();
                Airline airline = system.getAirline(airlineName);
                if (airline == null) {
                    System.out.println("Airline not found.");
                    return;
                }
                System.out.println("Enter the departure Date and Time in the format yyyy-MM-dd HH:mm:");
                LocalDateTime departureDateTime;
                try {
                    String inputDateTime = scanner.nextLine(); // Read the entire line
                    departureDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception e) {
                    System.out.println("Invalid date and time format.");
                    return;
                }
                System.out.println("Enter the arrival Date and Time in the format yyyy-MM-dd HH:mm:");
                LocalDateTime arrivalDateTime;
                try {
                    String inputDateTime = scanner.nextLine(); // Read the entire line
                    arrivalDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));                } catch (Exception e) {
                    System.out.println("Invalid date and time format.");
                    return;
                }
                System.out.println("Enter the ticket price:");
                int ticketPrice = scanner.nextInt();
                system.createFlight(flightNumber, airline, departureDateTime, arrivalDateTime, ticketPrice);
                System.out.println("Flight has been added.");

            }
            case 3 -> {
                System.out.println("Enter the name of the passenger:");
                String passengerName = scanner.nextLine();
                System.out.println("Enter the ID number of the passenger:");
                int id = scanner.nextInt();
                system.createPassenger(passengerName, id);
                scanner.nextLine();
                System.out.println("Passenger has been added. do you want to add the passenger to a flight? (y/n)");
                String addFlight = scanner.nextLine();
                if (addFlight.equals("y")) {
                    System.out.println("Enter ID of the flight:");
                    int flightID = scanner.nextInt();
                    Flight flight = system.getFlight(flightID);
                    if (flight == null) {
                        System.out.println("Flight not found.");
                        return;
                    }
                    system.addPassengerToFlight(system.getPassenger(id), flight);
                    System.out.println("Passenger has been added to the flight.");
                }
            }
            case 4 -> {
                System.out.println("Enter the name of the airline worker:");
                String workerName = scanner.nextLine();
                System.out.println("Enter the ID number of the airline worker:");
                int id = scanner.nextInt();
                System.out.println("Enter the airline name:");
                String airlineName = scanner.nextLine();
                Airline airline = system.getAirline(airlineName);
                if(airline == null){
                    System.out.println("Airline not found.");
                    return;
                }
                system.createAirlineWorker(workerName, id, airline);
                System.out.println("Airline worker has been added. do you want to watch a flight? (y/n)");
                String watchFlight = scanner.nextLine();
                if (watchFlight.equals("y")) {
                    System.out.println("Enter ID of the flight:");
                    int flightID = scanner.nextInt();
                    Flight flight = system.getFlight(flightID);
                    if (flight == null) {
                        System.out.println("Flight not found.");
                        return;
                    }
                    system.getAirlineWorker(id).watchFlight(flight);
                    System.out.println("Airline worker is now watching the flight.");
                }
            }
            case 0 -> System.out.println("Returning to the main menu...");
            default -> System.out.println("Invalid choice.");
        }
    }


    public static void changeMenu(int choice, FlightManagementSystem system){
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case 1 -> {
                System.out.println("Enter the ID of the flight:");
                int flightID = scanner.nextInt();
                Flight flight = system.getFlight(flightID);
                if (flight == null) {
                    System.out.println("Flight not found.");
                    return;
                }
                System.out.println("Enter the new ticket price:");
                int newPrice = scanner.nextInt();
                flight.changeTicketPrice(newPrice);
                System.out.println("Ticket price has been changed.");
            }
            case 2 -> {
                System.out.println("Enter the ID of the flight:");
                int flightID = scanner.nextInt();
                Flight flight = system.getFlight(flightID);
                if (flight == null) {
                    System.out.println("Flight not found.");
                    return;
                }
                System.out.println("Enter the new departure Date and Time in the format yyyy-MM-dd HH:mm:");
                LocalDateTime newDepartureDateTime ;
                try {
                    String inputDateTime = scanner.nextLine(); // Read the entire line
                    newDepartureDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception e) {
                    System.out.println("Invalid date and time format.");
                    return;
                }
                System.out.println("Enter the new arrival Date and Time in the format yyyy-MM-dd HH:mm:");
                LocalDateTime newArrivalDateTime;
                try {
                    String inputDateTime = scanner.nextLine(); // Read the entire line
                    newArrivalDateTime = LocalDateTime.parse(inputDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                } catch (Exception e) {
                    System.out.println("Invalid date and time format.");
                    return;
                }
                flight.changeFlightTimes(newDepartureDateTime, newArrivalDateTime);
                System.out.println("Flight times have been changed.");
            }
            case 3 -> {
                System.out.println("Enter the ID of the flight:");
                int flightID = scanner.nextInt();
                Flight flight = system.getFlight(flightID);
                if (flight == null) {
                    System.out.println("Flight not found.");
                    return;
                }
                flight.cancelFlight();
                System.out.println("Flight has been canceled.");
            }
            case 4 -> {
                System.out.println("Enter the ID of the flight:");
                int flightID = scanner.nextInt();
                Flight flight = system.getFlight(flightID);
                if (flight == null) {
                    System.out.println("Flight not found.");
                    return;
                }
                System.out.println("Enter the ID of the passenger:");
                int passengerID = scanner.nextInt();
                Passenger passenger = system.getPassenger(passengerID);
                if (passenger == null) {
                    System.out.println("Passenger not found.");
                    return;
                }
                system.removePassengerFromFlight(passenger, flight);
                System.out.println("Passenger "+passenger.getName() +" has been removed from flight "+ flight.getFlightNumber()+".");
            }
        }

    }


    public static void printInfo(int choice, FlightManagementSystem system){
        Scanner scanner = new Scanner(System.in);
        switch (choice){
            case 1 -> {
                System.out.println("Enter the name of the airline or ID of a flight:");
                String name = scanner.nextLine();
                Airline airline = system.getAirline(name);
                if (airline != null) {
                    System.out.println("Profits from tickets in " + name + ": " + airline.ProfitsFromTickets());
                } else {
                    Flight flight = system.getFlight(Integer.parseInt(name));
                    if (flight != null) {
                        System.out.println("Profits from tickets in " + name + ": " + flight.ProfitsFromTickets());
                    } else {
                        System.out.println("Airline or flight not found.");
                    }
                }
            }
            case 2 -> {
                System.out.println("Enter the name of the airline:");
                String name = scanner.nextLine();
                Airline airline = system.getAirline(name);
                if (airline != null) {
                    System.out.println("Number of flights in " + name + ": " + airline.NumberOfFlights());
                } else {
                    System.out.println("Airline not found.");
                }
            }
            case 3 -> {
                System.out.println("Enter the name of the airline or the ID of a flight:");
                String name = scanner.nextLine();
                Airline airline = system.getAirline(name);
                if (airline != null) {
                    System.out.println("Number of passengers in " + name + ": " + airline.NumberOfPassengers());
                } else {
                    Flight flight = system.getFlight(Integer.parseInt(name));
                    if (flight != null) {
                        System.out.println("Number of passengers in " + name + ": " + flight.NumberOfPassengers());
                    } else {
                        System.out.println("Airline or flight not found.");
                    }
                }
            }
            case 4 -> {
                System.out.println("Enter the name or ID of a person:");
                String name = scanner.nextLine();
                Passenger passenger = system.getPassenger(name);
                if (passenger != null) {
                    passenger.printNotifications();
                } else {
                    AirlineWorker airlineWorker = system.getAirlineWorker(name);
                    if (airlineWorker != null) {
                        airlineWorker.printNotifications();
                    } else {
                        int ID = Integer.parseInt(name);
                        passenger = system.getPassenger(ID);
                        if (passenger != null) {
                            passenger.printNotifications();
                        } else {
                            airlineWorker = system.getAirlineWorker(ID);
                            if (airlineWorker != null) {
                                airlineWorker.printNotifications();
                            } else {
                                System.out.println("Person not found.");
                            }
                        }
                    }

                }
            }
            case 5 -> {
                system.printNotifications();
            }
            case 0 -> System.out.println("Returning to the main menu...");
            default -> System.out.println("Invalid choice.");
        }
    }
}