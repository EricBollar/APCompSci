
import acm.program.*;

public class EmployeeRunner extends ConsoleProgram
{

    public void run()
    {
        Employee person = new Employee("Bruce Wayne", 10900.00);
        person.raiseSalary(15);
        println(person.getName() + " earns " + person.getSalary() + ".");
    }


}
