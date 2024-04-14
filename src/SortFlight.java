import java.util.ArrayList;


public class SortFlight {
    private final ArrayList <Flight> flights;
    private SortStrategy searchStrategy;


    public SortFlight(ArrayList<Flight> flights) {
        this.flights = flights;
        searchStrategy= null;
    }
    public SortFlight(ArrayList<Flight> flights, SortStrategy searchStrategy) {
        this.flights = flights;
        this.searchStrategy = searchStrategy;
    }
    // Method to perform search based on the provided strategy
    public void sort() {
        if(searchStrategy == null) {
            System.out.println("Please select a search strategy");
            return;
        }
        searchStrategy.sort(flights);
        displayFlights();
    }
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
