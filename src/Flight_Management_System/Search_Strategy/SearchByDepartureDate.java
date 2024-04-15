package Flight_Management_System.Search_Strategy;
import Flight_Management_System.Composite.Flight;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Search for flights based on a specific departure date
 */
public class SearchByDepartureDate implements SearchStrategy{
    LocalDate departureDate; // departure date to search for

    /**
     * Constructor
     * @param departureDate departure date to search for
     */
    public SearchByDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Search for flights based on departure date
     * @param flights list of flights to search
     * @return list of flights that have the same departure date
     */
    @Override
    public ArrayList<Flight> search(ArrayList<Flight> flights) {
        ArrayList<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            // Extract LocalDate from LocalDateTime and compare
            LocalDate flightDepartureDate = flight.getDepartureTime().toLocalDate();
            if (flightDepartureDate.equals(departureDate)) {
                result.add(flight);
            }
        }
        return result;
    }
}
