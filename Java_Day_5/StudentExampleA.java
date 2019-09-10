
import acm.program.*;

public class StudentExampleA extends ConsoleProgram
{
    Student senior;
        
    public void run()
    {

        senior = new Student("Luke");
        
        senior.takeQuiz(65);
        senior.takeQuiz(80);
        senior.takeQuiz(98);
        
        println(senior.getName() + " has a quiz average of " + senior.quizAverage());
        
        determineHonorRoll();
    }
    
    public void determineHonorRoll()
    {
        if (senior.quizAverage() > 90)
        {
            println(senior.getName() + " is on the honor roll.");
        }
        else
        {
            println(senior.getName() + " is NOT on the honor roll.  Study harder.");
        }
    }


}
