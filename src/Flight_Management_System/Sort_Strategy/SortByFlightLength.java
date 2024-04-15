package Flight_Management_System.Sort_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * this is the strategy for sorting flights by flight length
 */
public class SortByFlightLength implements SortStrategy {
    @Override
    public void sort(ArrayList<Flight> flights) {
        // search by flight length
        flights.sort(Comparator.comparingDouble(Flight::getFlightLength));
    }
}
