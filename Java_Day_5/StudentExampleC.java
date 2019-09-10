
import acm.program.*;

public class StudentExampleC extends ConsoleProgram
{
       
    public void run()
    {
        Student senior;
        senior = new Student("Luke");
        Student junior;
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
        
        determineHonorRoll(senior);
        determineHonorRoll(junior);
    }
    
    public void determineHonorRoll(Student someone)
    {
        if (someone.quizAverage() > 90)
        {
            println(someone.getName() + " is on the honor roll.");
        }
        else
        {
            println(someone.getName() + " is NOT on the honor roll.  Study harder.");
        }
    }
}
