import java.util.ArrayList;
import java.util.Comparator;

public class SortByPrice implements SortStrategy {
    @Override
    public void sort(ArrayList<Flight> flights) {
        // search by price
        flights.sort(Comparator.comparingInt(Flight::getTicketPrice));
    }
}
