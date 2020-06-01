
import acm.program.*;
import java.util.ArrayList;

public class Quizzer extends ConsoleProgram
{

    public void run()
    {
        ArrayList<Question> questions = new ArrayList<Question>();
        for (int i = 0; i < 10; i++) {
            Question q;
            double r = Math.random();
            if (r < 1.0/4) {
                q = new ArithmeticQuestion();
            } else if (r < 2.0/4) {
                q = new FavoriteQuestion();
            } else if (r < 3.0/4) {
                q = new PresidentQuestion();
            } else {
                q = new CapitalQuestion();
            }
            questions.add(q);
        } 

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            println(questions.get(i).getQuestion());
            String answer = readLine("Enter your answer: ");
            if (questions.get(i).isCorrect(answer)) {
                score++;
                println("Correct!");
            } else {
                println("Incorrect! The correct answer was " + questions.get(i).getAnswer());
            }
        }
        println("Your grade was " + score + "/" + questions.size() + ".");
    }

}
