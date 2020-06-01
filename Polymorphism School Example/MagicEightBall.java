

public class MagicEightBall implements Advisor
{

    public MagicEightBall()
    {

    }

    public String giveAdvice(Student student) {
        return ("Dear " + student.getName() + ", you should ask again.");
    }

}
