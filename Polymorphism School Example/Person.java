
public abstract class Person
{
    private String name;
    
    public Person (String givenName) {
        name = givenName;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract void doWork(double hours);
}
