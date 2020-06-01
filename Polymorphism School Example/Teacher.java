

public class Teacher extends Employee
{

    public Teacher(String givenName)
    {
        super(givenName, 30000);
    }

    public void doWork(double hours) {
        System.out.println(getName() + " just graded " + (3*hours) + " papers.");
    }
}
