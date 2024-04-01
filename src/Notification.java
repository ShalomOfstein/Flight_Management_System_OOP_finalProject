public class Notification {
    protected final Person receiver;
    protected final Airline sender;
    protected final String message;

    public Notification(Person receiver, Airline sender, String message) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
    }

    public void sendNotification(){
        receiver.receiveNotification(this);
    }
    public void printNotification(){
        System.out.println("Notification for " + receiver.getName() + " from " + sender.getName() + ": " + "\""+message+"\"");
    }
}

