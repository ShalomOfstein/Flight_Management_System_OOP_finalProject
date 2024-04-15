package Flight_Management_System.Search_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;

/**
 * Interface for search strategies
 */
public interface SearchStrategy {

    /**
     * Search for flights based on a specific criteria
     * @param flights list of flights to search
     * @return list of flights that match the criteria
     */
    ArrayList<Flight> search(ArrayList<Flight> flights);
}
