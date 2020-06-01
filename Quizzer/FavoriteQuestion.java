

public class FavoriteQuestion implements Question
{

    private String thing;
    
    public FavoriteQuestion()
    {
        String[] things = {"color", "food", "animal", "toy", "book", "dinosaur"};
        int index = (int)(Math.random() * things.length);
        thing = things[index];
    }

    public String getQuestion() {
        return "What is your favorite " + thing + "?";
    }
    
    public String getAnswer() {
        return "Whatever you want.";
    }
    
    public boolean isCorrect(String response) {
        return true;
    }

}
