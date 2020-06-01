

public class ArithmeticQuestion implements Question
{

    int a;
    int b;
    
    public ArithmeticQuestion()
    {
        a = (int)(Math.random() * 90) + 10;
        b = (int)(Math.random() * 90) + 10;
    }
    
    public String getQuestion() {
        return "What is " + a + " + " + b + "?";
    }
    
    public String getAnswer() {
        return (a + b) + "";
    }
    
    public boolean isCorrect(String response) {
        return response.equals(a + b + "");
    }


}
