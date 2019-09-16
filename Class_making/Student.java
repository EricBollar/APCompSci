
public class Student
{
    private String name;
    private int numQuizzes;
    private int totalPoints;
       
    public Student(String studentName)
    {
        numQuizzes = 0;
        totalPoints = 0;
        name = studentName;
    }
    
    public void takeQuiz(int score)
    {
        numQuizzes = numQuizzes + 1;
        totalPoints = totalPoints + score;
    }
 
    public String getName()
    {
          return name;
    }
    
    public double quizAverage()
    {
        return 1.0*totalPoints/numQuizzes;
    }
}
