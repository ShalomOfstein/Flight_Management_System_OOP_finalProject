package Flight_Management_System;

import Flight_Management_System.Composite.Airline;
import Flight_Management_System.Composite.Flight;
import Flight_Management_System.Observer.AirlineWorker;
import Flight_Management_System.Observer.Passenger;
import Flight_Management_System.Search_Strategy.*;
import Flight_Management_System.Sort_Strategy.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
* This is the main class for the system.
* This class will be able to:
* -> create a new Flight_Management_System.Composite.Flight
* -> create a new Flight_Management_System.Composite.Airline
* -> create a new Flight_Management_System.Observer.Passenger
* -> add a Flight_Management_System.Observer.Passenger to a Flight_Management_System.Composite.Flight
* -> search for a Flight_Management_System.Composite.Flight by different criteria
 */

public class FlightManagementSystem {
    private final ArrayList<Airline> airlines;
    private final ArrayList<Flight> flights;
    private final ArrayList<Passenger> passengers;
    private final ArrayList<AirlineWorker> airlineWorkers;

    /**
     * This is the constructor for the Flight_Management_System.FlightManagementSystem class.
     * It will create a new Flight_Management_System.FlightManagementSystem object with the given parameters.
     * It will initialize the airlines, flights, and passengers ArrayLists.
     */

    public FlightManagementSystem() {
        airlines = new ArrayList<Airline>();
        flights = new ArrayList<Flight>();
        passengers = new ArrayList<Passenger>();
        airlineWorkers = new ArrayList<AirlineWorker>();
    }

    /**
     * This method will create a new Flight_Management_System.Composite.Airline object with the given parameters.
     * @param airlineName the name of the airline
     * @return Flight_Management_System.Composite.Airline
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
     * This method will create a new Flight_Management_System.Composite.Flight object with the given parameters.
     * @param flightNumber the flight number
     * @param airline the airline of the flight
     * @param departureTime the departure time
     * @param arrivalTime the arrival time
     * @param ticketPrice the ticket price
     * @return Flight_Management_System.Composite.Flight
     */
    public Flight createFlight(int flightNumber, Airline airline, LocalDateTime departureTime, LocalDateTime arrivalTime, int ticketPrice) {
        if (flightNumber <= 0 || airline == null || departureTime.isAfter(arrivalTime) || ticketPrice < 0) {
            System.out.println("Invalid input");
            return null;
        }
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                System.out.println("Flight_Management_System.Composite.Flight already exists");
                return null;
            }
        }
        Flight flight = new Flight(flightNumber, airline, departureTime, arrivalTime, ticketPrice);
        flights.add(flight);
        airline.addFlight(flight);
        return flight;
    }


    /**
     * This method will create a new Flight_Management_System.Observer.Passenger object with the given parameters.
     * @param passengerName the name of the passenger
     * @return Flight_Management_System.Observer.Passenger
     */

    public Passenger createPassenger(String passengerName,int id) {
        if (passengerName == null || passengerName.isEmpty()||id<=0) {
            return null;
        }
        for (Passenger passenger : passengers) {
            if (passenger.getID() == id) {
                System.out.println("Flight_Management_System.Observer.Passenger already exists");
                return null;
            }
        }
        Passenger passenger = new Passenger(passengerName, id);
        passengers.add(passenger);
        return passenger;
    }

    /**
     * This method will add a Flight_Management_System.Observer.Passenger to a Flight_Management_System.Composite.Flight.
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
                System.out.println("Flight_Management_System.Composite.Airline Worker already exists");
                return null;
            }
        }
        AirlineWorker airlineWorker = new AirlineWorker(name, id, airline);
        airlineWorkers.add(airlineWorker);
        return airlineWorker;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void sortFlight(SortStrategy strategy) {
        SortFlight sortFlight = new SortFlight(flights, strategy);
        sortFlight.sort();
    }

    public void sortFlight(){
        System.out.println("Please select a strategy to sort the flights:\n"+
                "1. Sort by Departure Time\n"+
                "2. Sort by Flight Length\n"+
                "3. Sort by Price\n"+
                "Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                sortFlight(new SortByDepartureTime());
                break;
            case 2:
                sortFlight(new SortByFlightLength());
                break;
            case 3:
                sortFlight(new SortByPrice());
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public void searchFlight(SearchStrategy strategy) {
        SearchFlight searchFlight = new SearchFlight(flights, strategy);
        searchFlight.search();
    }

    public void searchFlight(){
        System.out.println("Please select a strategy to search the flights:\n"+
                "1. Search for a specific Departure Date\n"+
                "2. Search for a specific Flight Length\n"+
                "3. Search for a specific Price\n"+
                "Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Enter the Departure Date (YYYY-MM-DD): ");
                LocalDate departureDate = null;
                String userInput = scanner.next();
                try {
                    departureDate = LocalDate.parse(userInput);
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter date in YYYY-MM-DD format.");
                }
                searchFlight(new SearchByDepartureDate(departureDate));
                break;
            case 2:
                System.out.println("Enter the Flight Length: ");
                double flightLength = scanner.nextDouble();
                if(flightLength <= 0) {
                    System.out.println("Invalid input");
                    return;
                }
                searchFlight(new SearchByFlightLength(flightLength));
                break;
            case 3:
                System.out.println("Enter the Price: ");
                int price = scanner.nextInt();
                if(price < 0) {
                    System.out.println("Invalid input");
                    return;
                }
                searchFlight(new SearchByPrice(price));
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public Flight getFlight(int flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                return flight;
            }
        }
        return null;
    }
    public Airline getAirline(String airlineName) {
        for(Airline airline : airlines) {
            if(airline.getName().equals(airlineName)) {
                return airline;
            }
        }
        return null;
    }
    public Passenger getPassenger(int id) {
        for(Passenger passenger : passengers) {
            if(passenger.getID() == id) {
                return passenger;
            }
        }
        return null;
    }
    public AirlineWorker getAirlineWorker(int id) {
        for(AirlineWorker airlineWorker : airlineWorkers) {
            if(airlineWorker.getID() == id) {
                return airlineWorker;
            }
        }
        return null;
    }
    public Passenger getPassenger(String name) {
        for(Passenger passenger : passengers) {
            if(passenger.getName().equals(name)) {
                return passenger;
            }
        }
        return null;
    }
    public AirlineWorker getAirlineWorker(String name) {
        for(AirlineWorker airlineWorker : airlineWorkers) {
            if(airlineWorker.getName().equals(name)) {
                return airlineWorker;
            }
        }
        return null;
    }

    public void printPassengers(){
        for(Passenger passenger : passengers) {
            System.out.println(passenger);
        }
    }
    public void printAirlineWorkers(){
        for(AirlineWorker airlineWorker : airlineWorkers) {
            System.out.println(airlineWorker);
        }
    }
    public void printNotifications(){
        for(AirlineWorker airlineWorker : airlineWorkers) {
            airlineWorker.printNotifications();
        }
        for(Passenger passenger : passengers) {
            passenger.printNotifications();
        }
    }








}
