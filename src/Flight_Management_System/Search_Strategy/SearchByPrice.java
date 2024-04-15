package Flight_Management_System.Search_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;

/**
 * Search for flights based on a specific price
 */

public class SearchByPrice implements SearchStrategy{
    private final double price; // price to search for

    /**
     * Constructor
     * @param price price to search for
     */
    public SearchByPrice(double price) {
        this.price = price;
    }

    /**
     * Search for flights based on price
     * @param flights list of flights to search
     * @return list of flights that have the same price
     */
    @Override
    public ArrayList<Flight> search(ArrayList<Flight> flights) {
        ArrayList<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getTicketPrice() == price) {
                result.add(flight);
            }
        }
        return result;
    }
}
