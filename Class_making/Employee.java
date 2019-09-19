

public class Employee
{

    double salary;
    String name;
    
    public Employee(String name, double salary)
    {
        this.name = name;
        this.salary = salary;
    }
    
    public void raiseSalary(double amount) {
        salary += amount;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }


}
