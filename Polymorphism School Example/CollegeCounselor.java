

public class CollegeCounselor extends Employee implements Advisor
{

    public CollegeCounselor(String givenName)
    {
        super(givenName, 40000);
    }
    
    public void doWork(double hours) {
        System.out.println(getName() + " just wrote " + (4/3.0 * hours) + " letters of recommendation.");
    }

    public String giveAdvice(Student student) {
        return "Dear " + student.getName() + ", you should do well on the SAT.";
    }
}
