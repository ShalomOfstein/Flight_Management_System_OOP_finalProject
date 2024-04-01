public abstract class People {
    private String Name;
    private int ID;
    private static int NumberOfPeople = 0;

    protected People(String name, int id){
        this.Name = name;
        this.ID = id ;
        NumberOfPeople++;
    }

    protected String getName(){
        return Name;
    }


}
