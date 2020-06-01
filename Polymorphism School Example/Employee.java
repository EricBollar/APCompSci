

public abstract class Employee extends Person
{
    private int salary;
    
    public Employee(String givenName, int startingSalary)
    {
        super(givenName);
        salary = startingSalary;
    }

    public int getSalary() {
        return salary;
    }
}
