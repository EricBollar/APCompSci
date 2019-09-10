
import acm.program.*;

public class StudentExampleE extends ConsoleProgram
{

    public void run()
    {
        Student senior;
        senior = new Student("Devin");
        
        takeSeveralQuizzes(senior);
        
        println(senior.getName() + " has a quiz average of " + senior.quizAverage());
    }
    
    public void takeSeveralQuizzes(Student someone)
    {
        int numQuizzes = readInt("How many quizzes? ");
        int score;
        for (int i=0; i<numQuizzes; i++)
        {
            score = readInt("Enter quiz score: ");
            someone.takeQuiz(score);
        }
    }


}
