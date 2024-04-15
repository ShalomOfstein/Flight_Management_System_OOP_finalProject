package Flight_Management_System.Sort_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * this is the strategy for sorting flights by departure time
 */
public class SortByDepartureTime implements SortStrategy  {
    @Override
    public void sort(ArrayList<Flight> flights) {
        // search by departure time
        flights.sort(Comparator.comparing(Flight::getDepartureTime));
    }
}
