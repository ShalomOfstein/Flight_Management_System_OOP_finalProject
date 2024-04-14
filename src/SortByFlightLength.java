import java.util.ArrayList;
import java.util.Comparator;

public class SortByFlightLength implements SortStrategy {
    @Override
    public void sort(ArrayList<Flight> flights) {
        // search by flight length
        flights.sort(Comparator.comparingDouble(Flight::getFlightLength));
    }
}
