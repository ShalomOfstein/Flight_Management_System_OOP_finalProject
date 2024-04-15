package Flight_Management_System.Sort_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;

/**
 * This class is the context class for the sort strategies
 * It contains the list of flights and the sort strategy
 * It also contains the method to perform the sorting
 * and display the flights after sorting
 */
public class SortFlight {
    private final ArrayList <Flight> flights; // List of flights
    private SortStrategy searchStrategy; // Sort strategy to be used

    /**
     * Constructor to initialize the flights and the sort strategy
     * @param flights list of flights to sort
     */
    public SortFlight(ArrayList<Flight> flights) {
        this.flights = flights;
        searchStrategy= null;
    }
    public SortFlight(ArrayList<Flight> flights, SortStrategy searchStrategy) {
        this.flights = flights;
        this.searchStrategy = searchStrategy;
    }

    // Method to perform search based on the set strategy
    public void sort() {
        if(searchStrategy == null) {
            System.out.println("Please select a search strategy");
            return;
        }
        searchStrategy.sort(flights);
        displayFlights();
    }
    // Method to perform search based on a provided strategy
    public void sort(SortStrategy strategy) {
        searchStrategy = strategy;
        strategy.sort(flights);
        displayFlights();
    }
    // Method to display the flights
    public void displayFlights() {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
        System.out.println();
    }


    




}
