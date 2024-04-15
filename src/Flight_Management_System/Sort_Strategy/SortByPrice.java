package Flight_Management_System.Sort_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * this is the strategy for sorting flights by price
 */
public class SortByPrice implements SortStrategy {
    @Override
    public void sort(ArrayList<Flight> flights) {
        // search by price
        flights.sort(Comparator.comparingInt(Flight::getTicketPrice));
    }
}
