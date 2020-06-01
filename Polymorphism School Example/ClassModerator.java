

public class ClassModerator extends Teacher implements Advisor
{

    public ClassModerator(String givenName)
    {
        super(givenName);
    }

    public String giveAdvice(Student student) {
        return "Hey " + student.getName() + ", work together nicely.";
    }

}
