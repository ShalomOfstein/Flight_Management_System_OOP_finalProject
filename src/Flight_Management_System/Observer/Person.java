package Flight_Management_System.Observer;

import Flight_Management_System.Composite.Flight;

import java.util.ArrayList;

public abstract class Person implements Observer{
    protected final String Name;
    protected final int ID;
    protected static int NumberOfPeople = 0;

    private final ArrayList<Notification> notifications;

    public Person(String name, int id){
        this.Name = name;
        this.ID = id ;
        NumberOfPeople++;
        notifications = new ArrayList<>();
    }


    /**
     * This method will return the name of the person.
     * @return String
     */
    public String getName(){
        return Name;
    }

    /**
     * This method will return the ID of the person.
     * @return int
     */
    public int getID(){
        return ID;
    }

    /**
     * This method will add a notification to the person.
     * it implements the Flight_Management_System.Observer.Observer design pattern.
     * in this case the observer is the person and the subject is the flight.
     * @param notification the notification to be added
     */

    @Override
    public void update(Notification notification) {

        notifications.add(notification);
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void printNotifications() {
        System.out.println(notifications.size()+" Notifications for " + Name + ":");
        for (Notification notification : notifications) {
            notification.printNotification();
        }
        System.out.println();
    }

    public void watchFlight(Flight flight) {
        flight.registerObserver(this);
    }

    public void unwatchFlight(Flight flight) {
        flight.removeObserver(this);
    }

}
