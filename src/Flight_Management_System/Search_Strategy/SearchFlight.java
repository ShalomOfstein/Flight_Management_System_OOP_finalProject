package Flight_Management_System.Search_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;

/**
 * This class is the context class for the search strategies
 * It contains the list of flights and the search strategy
 * It also contains the method to perform the search
 * and display the found flights
 *
 */
public class SearchFlight {
    private final ArrayList<Flight> flights;
    private ArrayList<Flight> found;
    private SearchStrategy searchStrategy;

    public SearchFlight(ArrayList<Flight> flights) {
        this.flights = flights;
        searchStrategy = null;
        this.found = new ArrayList<>();
    }

    /**
     * Constructor to initialize the flights and the search strategy
     * @param flights the current list of flights
     * @param searchStrategy the search strategy to be used
     */
    public SearchFlight(ArrayList<Flight> flights, SearchStrategy searchStrategy) {
        this.flights = flights;
        this.searchStrategy = searchStrategy;
        this.found = new ArrayList<>();
    }


    // Method to perform search based on the set strategy
    public void search() {
        if(searchStrategy == null) {
            System.out.println("Please select a search strategy");
            return;
        }
        this.found = searchStrategy.search(flights);
        displayFound();
    }
    // Method to perform search based on a provided strategy
    public void search(SearchStrategy strategy) {
        this.searchStrategy = strategy;
        this.found = searchStrategy.search(flights);
        displayFound();
    }

    // Method to display the flights
    public void displayFound() {
        if (found.isEmpty()) {
            System.out.println("No Flights match your search");
            return;
        } else {
            System.out.println("These Flights match your search:");
            for (Flight flight : found) {
                System.out.println(flight);
            }
            System.out.println();
        }
    }

}
