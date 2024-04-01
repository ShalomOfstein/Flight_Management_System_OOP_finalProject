

public class AirlineWorker extends Person{
    private static int NumberOfAirlineWorkers = 0;
    private final Airline airline;


    public AirlineWorker(String name, int id, Airline airline){
        super(name, id);
        this.airline = airline;
        NumberOfAirlineWorkers++;
        airline.addAirlineWorker(this);
    }

    public Airline getAirline(){
        return airline;
    }

}
