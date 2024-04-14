public class Notification {
    protected final Observer receiver;
    protected final Airline sender;
    protected final String message;

    public Notification(Observer receiver, Airline sender, String message) {
        this.receiver = receiver;
        this.sender = sender;
        this.message = message;
    }


    public void sendNotification(){
        receiver.update(this);
    }
    public void printNotification(){
        Person receiver1 = (Person) this.receiver;
        System.out.println("Notification for " + receiver1.getName() + " from " + sender.getName() + ": " + "\""+message+"\"");
    }
}

