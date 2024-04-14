import java.util.ArrayList;
import java.util.Comparator;

public class SortByDepartureTime implements SortStrategy  {
    @Override
    public void sort(ArrayList<Flight> flights) {
        // search by departure time
        flights.sort(Comparator.comparing(Flight::getDepartureTime));
    }
}
