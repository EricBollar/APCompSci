

public class CapitalQuestion implements Question
{

    private String state;
    private String capital;
    
    public CapitalQuestion()
    {
        String[] states = {"California", "Alaska", "Montana", "Oregon", "Washington"};
        String[] capitals = {"Sacramento", "Juneau", "Helena", "Salem", "Olympia"};
        int index = (int)(Math.random() * states.length);
        state = states[index];
        capital = capitals[index];
    }
    
    public String getQuestion() {
        return "What is the capital of " + state + "?";
    }
    
    public String getAnswer() {
        return capital;
    }
    
    public boolean isCorrect(String response) {
        return response.equals(capital);
    }


}
