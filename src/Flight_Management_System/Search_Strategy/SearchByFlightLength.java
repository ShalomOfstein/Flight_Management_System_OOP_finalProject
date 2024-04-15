package Flight_Management_System.Search_Strategy;
import Flight_Management_System.Composite.Flight;
import java.util.ArrayList;

/**
 * Search strategy for searching flights based on flight length
 */
public class SearchByFlightLength implements SearchStrategy{
    private final double length; // length of the flight

    /**
     * Constructor
     * @param length length of the flight
     */
    public SearchByFlightLength(double length) {
        this.length = length;
    }

    /**
     * Search for flights based on flight length
     * @param flights list of flights to search
     * @return list of flights that have the same length
     */
    @Override
    public ArrayList<Flight> search(ArrayList<Flight> flights) {
        ArrayList<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getFlightLength() == length) {
                result.add(flight);
            }
        }
        return result;
    }
}
