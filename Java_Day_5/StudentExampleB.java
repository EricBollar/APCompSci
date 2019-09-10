
import acm.program.*;

public class StudentExampleB extends ConsoleProgram
{
     Student senior;
     Student junior;
    
    public void run()
    {
        senior = new Student("Luke");
        junior = new Student("Gus");
        
        senior.takeQuiz(65);
        senior.takeQuiz(80);
        senior.takeQuiz(98);
        
        junior.takeQuiz(75);
        junior.takeQuiz(96);
        junior.takeQuiz(92);
        junior.takeQuiz(88);
        
        println(senior.getName() + " has a quiz average of " + senior.quizAverage());
        println(junior.getName() + " has a quiz average of " + junior.quizAverage());
        
        determineSeniorHonorRoll();
        determineJuniorHonorRoll();
    }
    
    public void determineSeniorHonorRoll()
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
    
    public void determineJuniorHonorRoll()
    {
        if (junior.quizAverage() > 90)
        {
            println(junior.getName() + " is on the honor roll.");
        }
        else
        {
            println(junior.getName() + " is NOT on the honor roll.  Study harder.");
        }
    }


}
