package Flight_Management_System.Sort_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;

/**
 * Interface for sort strategies
 */
public interface SortStrategy {
    /**
     * Sort flights based on a specific criteria
     * @param flights list of flights to sort
     */
    void sort(ArrayList<Flight> flights);
}
