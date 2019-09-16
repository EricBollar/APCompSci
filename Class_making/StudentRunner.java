import acm.program.*;

public class StudentRunner extends ConsoleProgram
{

    public void run()
    {
        Student stud = new Student("John");
        stud.takeQuiz(80);
        stud.takeQuiz(62);
        stud.takeQuiz(98);
        double avg = stud.quizAverage(); 
        String who = stud.getName();
        println(who + "'s average is " + avg); 

        
    }


}
