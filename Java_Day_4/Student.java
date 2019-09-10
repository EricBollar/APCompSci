
public class Student
{
    private String myName;
    private int myNumQuizzes;
    private int myTotalPoints;
       
    public Student(String name)
    {
        myNumQuizzes = 0;
        myTotalPoints = 0;
        myName = name;
    }
    
    public void takeQuiz(int score)
    {
        myNumQuizzes = myNumQuizzes + 1;
        myTotalPoints = myTotalPoints + score;
    }
 
    public String getName()
    {
          return myName;
    }
    
    public double quizAverage()
    {
        return 1.0*myTotalPoints/myNumQuizzes;
    }
}
