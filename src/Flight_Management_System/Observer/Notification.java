package Flight_Management_System.Observer;
import Flight_Management_System.Composite.Airline;

/**
 * this class represents a notification that is sent to an observer
 * it contains the information of the sender, receiver and the message
 */
public class Notification {
    protected final Observer receiver;
    protected final Subject sender;
    protected final String message;

    public Notification(Observer receiver, Subject sender, String message) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
    }


    public void sendNotification(){
        receiver.update(this);
    }
    public void printNotification(){
        Person receiver1 = (Person) this.receiver;
        Airline sender1 = (Airline) this.sender;
        System.out.println("Notification for " + receiver1.getName() + " from " + sender1.getName() + ": " + "\""+message+"\"");
    }
    public String toString(){
        Person receiver1 = (Person) this.receiver;
        Airline sender1 = (Airline) this.sender;
        return "Notification for " + receiver1.getName() + " from " + sender1.getName() + ": " + "\""+message+"\"";
    }

}

